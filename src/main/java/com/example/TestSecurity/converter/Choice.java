package com.example.TestSecurity.converter;

import com.example.TestSecurity.model.Valute;

public class Choice {

    public static Double converted(Valute valuteFrom, Valute valuteTo, Integer quantity){
        return Math.rint(((valuteFrom.getValue() / valuteFrom.getNominal())
                / (valuteTo.getValue() / valuteTo.getNominal()) * quantity) * 100.0) /100.0;
    }
    //Сделать метод конвертации
}
