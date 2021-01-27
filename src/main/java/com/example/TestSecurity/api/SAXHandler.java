package com.example.TestSecurity.api;

import com.example.TestSecurity.model.Valute;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author karpeykin
 * @Date 26.01.2021
 */
public class SAXHandler extends DefaultHandler {

    public List<Valute> valuteList = new ArrayList<>();
    Valute valute = null;
    String content = null;

    @Override
    //Triggered when the start of tag is found.
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes)
            throws SAXException {
        String dateFormat = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        switch(qName){
            //Create a new Employee object when the start tag is found
            case "Valute":
                valute = new Valute();
                valute.setDate(dateFormat);
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        switch(qName){
            //Add the employee to list once end tag is found
            case "Valute":
                valuteList.add(valute);
                break;
            //For all other end tags the employee has to be updated.
            case "CharCode":
                valute.setCharCode(content);
                break;
            case "Name":
                valute.setName(content);
                break;
            case "Nominal":
                valute.setNominal(Integer.parseInt(content));
                break;
            case "Value":
                valute.setValue(Double.parseDouble(content.replace(",", ".")));
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }
}
