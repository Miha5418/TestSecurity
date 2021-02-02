package com.example.TestSecurity.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "history")
@Data
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String valuteFrom;
    private String valuteTo;
    private Integer quantity;
    private Double result;
    private String date;

    public History(String valuteFrom, String valuteTo, Integer quantity, Double result, String date) {
        this.valuteFrom = valuteFrom;
        this.valuteTo = valuteTo;
        this.quantity = quantity;
        this.result = result;
        this.date = date;
    }

    public History() {

    }
}
