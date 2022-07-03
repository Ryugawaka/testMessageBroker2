package com.example.sendJson.controller;


import com.example.sendJson.xmlparser.XMLParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonGetterController {


    @GetMapping("/message")
    public String jsonReceiver() throws Exception{
        XMLParser xmlParcer = new XMLParser();
       return xmlParcer.jsonReceiver();
    }
}
