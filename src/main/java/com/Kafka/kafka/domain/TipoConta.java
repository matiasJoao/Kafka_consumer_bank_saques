package com.Kafka.kafka.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum TipoConta {

    PJ ("PJ","Pessoa Juridica",50),
    PF("PF","Pessoa Fisica",5),
    GOV("GOV","Governamental",250);

    private String sigla;
    private String descricao;
    private Integer quantidadeSaque;

}