package com.example.sendJson.xmlparser;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.JSONObject;
import org.json.XML;

import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

public class XMLParser {

    private final static String QUEUE_NAME = "xml-get";

    public String jsonReceiver() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("Received '" + message + "'");
            JSONObject json = new JSONObject(message);
            String xml = XML.toString(json);
            System.out.println(xml);
//            File file = new File("src/main/resources/file.txt");
            FileWriter fw = new FileWriter("src/main/resources/file.xml");
            fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<root>\n"+xml+"\n</root>");
            fw.close();
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });
        return "message received";
    }
}
