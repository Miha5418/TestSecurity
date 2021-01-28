package com.example.TestSecurity.service;


import com.example.TestSecurity.api.SAXHandler;
import com.example.TestSecurity.model.Valute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.*;
import java.util.List;

/**
 * @author karpeykin
 * @Date 25.01.2021
 */
@Service
public class XMLService {

    private final static Logger logger = LoggerFactory.getLogger(XMLService.class);


    public static List<Valute> parseVaeCurs(String date) {

        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = null;
        SAXHandler handler = new SAXHandler();

        try {
            parser = parserFactor.newSAXParser();
            parser.parse("http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return handler.valuteList;
    }
}