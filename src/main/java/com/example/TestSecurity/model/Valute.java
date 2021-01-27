package com.example.TestSecurity.model;

import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 * @author karpeykin
 * @Date 25.01.2021
 */
@Entity
@Table(name = "valutes")
@Data
public class Valute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String charCode;
    private String date;
    private Integer nominal;
    private String name;
    private Double value;



    public Valute() {

    }

}

