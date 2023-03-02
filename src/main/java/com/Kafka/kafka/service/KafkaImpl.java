package com.Kafka.kafka.service;

import com.Kafka.kafka.domain.TipoConta;
import com.Kafka.kafka.repository.ContaRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class KafkaImpl implements  KafkaService{
    private static final String timeZone = "America/Sao_Paulo";
    private final ContaRepository contaRepository;

    public KafkaImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    @KafkaListener(topics = "Transacoes", groupId = "Kafka")
    public void receberMensagem(ConsumerRecord<String, String> consumerRecord) {
        var data = consumerRecord.value();
        var conta = contaRepository.findByDocument(data);
        if(conta.isEmpty()){
            throw new RuntimeException("error conta documento");
        }
        if(conta.get().getQuantidadeSaque() > 0){
          var quantidadeSaque =  conta.get().getQuantidadeSaque() - 1;
          conta.get().setQuantidadeSaque(quantidadeSaque);
          contaRepository.save(conta.get());
        }

    }

    @Override
    @Scheduled(cron = "50 46 * * * *" , zone = timeZone)
    public void resetarQuantidadeSaque() {
            for (TipoConta tipoConta : TipoConta.values()){
                contaRepository.updateSaques(tipoConta, tipoConta.getQuantidadeSaque());
            }
    }
}
