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
         if(args.length != 2) {
             System.err.printf("Usage: %s <topicName> <groupID>\n", Consumer.class.getSimpleName());
             System.exit(-1);
         }

         in = new Scanner(System.in);
     }
 }