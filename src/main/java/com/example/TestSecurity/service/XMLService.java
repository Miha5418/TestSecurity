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



/*try {
            String URL = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + dateFormat.format(date);

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(false);
            documentBuilderFactory.setValidating(false);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            URLConnection urlConnection = new URL(URL).openConnection();

            urlConnection.addRequestProperty("Accept", "application/xml");
            Document document = documentBuilder.parse(urlConnection.getInputStream());
            document.getDocumentElement().normalize();


            // normalize XML response
            NodeList nodeList = document.getDocumentElement().getChildNodes();



            //loop all available valute nodes
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node ValCurs  = nodeList.item(i);

                NodeList nodeValuteList = ValCurs.getChildNodes();

                for (int j = 0; j < nodeValuteList.getLength(); j++) {

                    Node tmpValute  = nodeList.item(i);

                    if (tmpValute.getNodeType() == Node.ELEMENT_NODE) {
                        Element elem = (Element) tmpValute;

                        Valute valute = new Valute(
                                Integer.parseInt(String.valueOf(elem.getElementsByTagName("NumCode"))),
                                String.valueOf(elem.getElementsByTagName("CharCode")),
                                String.valueOf(elem.getElementsByTagName("Name")),
                                Double.parseDouble(String.valueOf(elem.getElementsByTagName("Value"))),
                                dateFormat.format(date));

                        valuteRepo.save(valute);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }*/

