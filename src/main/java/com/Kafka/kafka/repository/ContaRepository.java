package com.Kafka.kafka.repository;

import com.Kafka.kafka.domain.Conta;
import com.Kafka.kafka.domain.TipoConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
@Repository
@Transactional
public interface ContaRepository extends JpaRepository<Conta, String> {
    Optional<Conta> findByDocument(String document);

    @Modifying
    @Query(value = "UPDATE Conta c SET c.quantidadeSaque = :quantidadeSaque WHERE c.type_account = :tipoConta ")
    void updateSaques(@Param("tipoConta") TipoConta tipoConta, @Param("quantidadeSaque")Integer quantidadeSaque);
}
