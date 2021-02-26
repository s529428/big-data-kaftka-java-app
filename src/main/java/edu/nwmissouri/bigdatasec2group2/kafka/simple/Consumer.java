package edu.nwmissouri.bigdatasec2group2.kafka.simple;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;


/**
 * Created by Caroline Finnerty on 12/26/21.
 */

 public class Consumer {
     private static Scanner in;

     public static void main(String[] args) throws Exception {
         //Must come with topic and groupID
         if(args.length != 2) {
             System.err.printf("Usage: %s <topicName> <groupID>\n", Consumer.class.getSimpleName());
             System.exit(-1);
         }

         in = new Scanner(System.in);
         String topicName = args[0];
         String groupID = args[1];

         //Start thread
         ConsumerThread consumerRunnable = new ConsumerThread(topicName,groupID);
         consumerRunnable.start();
         String line = "";

         //Keep getting input until the input is 'exit'
         while(!line.equals("exit")){
             line = in.next();
         }

         //Input has stopped
         consumerRunnable.getKafkaConsumer().wakeup();
         System.out.println("Stopping the consumer!!!");
         consumerRunnable.join();
     }

     private static class ConsumerThread extends Thread {
         //Variables
         private String topicName;
         private String groupID;
         private KafkaConsumer<String, String> kafkaConsumer;

        //Initialize the class
         public ConsumerThread(String topicName, String groupID){
             this.groupID = groupID;
             this.topicName = topicName;
         }

         public void run(){
             Properties configProperties = new Properties();
             configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
             configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");
             configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
             configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupID);
             configProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "simple");

             //Find where we start processing messages from
             kafkaConsumer = new KafkaConsumer<String, String>(configProperties);
             kafkaConsumer.subscribe(Arrays.asList(topicName));

             //Start processing messages yo
             try {
                 while(true) {
                     ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                     for(ConsumerRecord<String, String> record : records)
                        System.out.println(record.value());
                 }
             }catch(WakeupException ex){
                 System.out.println("Exception caught "+ ex.getMessage());
             }finally{
                 kafkaConsumer.close();
                 System.out.println("After closing KafkaConsumer");
             }
         }

         public KafkaConsumer<String,  String> getKafkaConsumer(){
             return this.kafkaConsumer;
         }
     }
 }