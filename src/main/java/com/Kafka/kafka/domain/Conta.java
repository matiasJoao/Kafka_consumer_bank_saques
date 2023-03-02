package com.Kafka.kafka.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@Entity(name = "Conta")
@Table(name = "Conta")
public class Conta {

    @Id
    @GenericGenerator(name="UUIDGenerator", strategy ="uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private String id;

    private String id_document;


    private String document;

    private Integer agency;

    private Integer number_account;
    @Enumerated(EnumType.STRING)
    private TipoConta type_account;

    private Integer verify_digit;

    private Double balance;


    private Integer quantidadeSaque;

}