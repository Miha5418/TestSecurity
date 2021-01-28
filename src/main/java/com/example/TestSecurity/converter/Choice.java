package com.example.TestSecurity.converter;

import com.example.TestSecurity.model.Valute;

public class Choice {

    public static Double converted(Valute valuteFrom, Valute valuteTo, Integer quantity){
        return (valuteFrom.getValue() / valuteFrom.getNominal())
                / (valuteTo.getValue() / valuteTo.getNominal()) * quantity;
    }
    //Сделать метод конвертации
}
