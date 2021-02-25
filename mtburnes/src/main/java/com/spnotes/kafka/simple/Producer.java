package com.spnotes.kafka.simple;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;
import java.lang.Math;
import java.util.concurrent.TimeUnit;

/**
 * Created by sunilpatil on 12/28/15.
 */
public class Producer {
    private static Scanner in;
    private static int delayLength;
    public static void main(String[] argv)throws Exception {
        if (argv.length != 2) {
            System.err.println("Please specify 2 parameters ");
            System.exit(-1);
        }
        String topicName = argv[0];
        try {
            delayLength = Integer.parseInt(argv[1]);
        }catch(Exception e){
            delayLength = 500;
        }
//        in = new Scanner(System.in);


//        System.out.println("Enter message(type exit to quit)");

        //Configure the Producer
        Properties configProperties = new Properties();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        org.apache.kafka.clients.producer.Producer producer = new KafkaProducer(configProperties);
        String line = "" + Math.random()*10;
        for(int i = 0; i < 100000; i++) {
            //Note: This sleep will add an artificial delay between produced records. This is a commandline variable.
            //This is for stress-testing and exploration purposes. This has the potential to be mega dumb.
            TimeUnit.MILLISECONDS.sleep(delayLength);
            ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName,line);
            producer.send(rec);
            line = "" + Math.random()*10;
        }
//        in.close();
        producer.close();
    }
}
