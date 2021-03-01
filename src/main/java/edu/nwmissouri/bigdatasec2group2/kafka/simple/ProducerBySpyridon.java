package edu.nwmissouri.bigdatasec2group2.kafka.simple;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

/** 
 * Spyridon 03/01/2021
 */
public class ProducerBySpyridon {
    private static Scanner in;

    public static void main(String[] argv) throws Exception {
      if (argv.length != 1) {
        System.err.println("Please specify 1 parameter (the name of the topic)");
        System.exit(-1);
      }
      String topicName = argv[0];
      in = new Scanner(System.in);
      System.out.println("Thank you for providing the topic " + topicName + "\n");
      System.out.println("Enter message (type exit to quit).\n");
  
      // Configure the Producer
      Properties configProperties = new Properties();
      configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
      configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
          "org.apache.kafka.common.serialization.ByteArraySerializer");
      configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
          "org.apache.kafka.common.serialization.StringSerializer");
      System.out.println("The configuration properties are: " + configProperties.toString());
      System.out.println("\nWill use this configuration to create a producer.\n");
  
      org.apache.kafka.clients.producer.Producer producer = new KafkaProducer(configProperties);

      // Make a message. 

    

    for (int i = 1; i <= 10; i++) {
        String destination = destPick();
        ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, destination);
        producer.send(rec);
    }

    // allow input from keyboard

    String line = in.nextLine();
    while (!line.equals("exit")) {
      ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, line);
      producer.send(rec);
      line = in.nextLine();
    }

    in.close();
    producer.close();

  }

  private static String destPick() {
    String[] destination = {"Puerto Rico","Canada","Australia", "Ireland", "Cuba"};
    String[] cities = {"San Juan", "Montreal", "Sydney", "Dublin", "Havana"};
    Random plan = new Random();

    int count = 4;
    int minIndex = 0;
    int maxIndex = 5;

    int[] randomize = plan.ints(count, minIndex, maxIndex).toArray();

    return destination[randomize[0]] + " in " + cities[randomize[1]] ;
  }
        
}
