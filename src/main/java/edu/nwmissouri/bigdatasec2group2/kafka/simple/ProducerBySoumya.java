package edu.nwmissouri.bigdatasec2group2.kafka.simple;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Soumya Chidambar Rao Waddankeri  on 02/28/2021
 */
public class ProducerBySoumya {
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

    //create your custom logic here
    Random rand = new Random();

    for (int i = 1; i <= 10; i++) {
	//generate random number between 1 to 12
        int generateRandomNumber = rand.nextInt(12)+1;
        String rollDie = RollADie(generateRandomNumber);
        ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, rollDie);
        producer.send(rec);
      }

    String line = in.nextLine();
    while (!line.equals("exit")) {
      ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, line);
      producer.send(rec);
      line = in.nextLine();
    }

    in.close();
    producer.close();

  }
    
        private static String RollADie(int dieNum) {
        switch(dieNum){
            case 1:
                return "You move by 1";
            case 2:
                return "You move by 2";
            case 3:
                return "You move by 3";
            case 4:
                return "You move by 4";
            case 5:
                return "You move by 5";
            case 6:
                return "You move by 6";
            case 7:
                return "You have rolled 2 dices -You move by 7";
            case 8:
                return "You have rolled 2 dices -You move by 8";
            case 9:
                return "You have rolled 2 dices -You move by 9";
            case 10:
                return "You have rolled 2 dices -You move by 10";
            case 11:
                return "You have rolled 2 dices -You move by 11";
            case 12:
                return "You have rolled 2 dices -You move by 12";
            default:
                return "Ok Bye";
        }
    }
        
  }
