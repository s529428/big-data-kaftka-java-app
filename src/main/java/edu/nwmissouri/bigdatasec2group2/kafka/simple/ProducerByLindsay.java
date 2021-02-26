package edu.nwmissouri.bigdatasec2group2.kafka.simple;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by sunilpatil on 12/28/15. Modified by Denise Case on 10/29/2019.
 */
public class ProducerByLindsay {
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

    // Make our own messages - create your custom logic here

    String input = in.nextLine();

    for (int i = 1; i <= 5; i++) {
    //sending code to user
      String code = randomMorseCode(i);
      ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, code);
      producer.send(rec);
      //sending its meaning
      String message = randomMorseCode(i);
      ProducerRecord<String, String> rec2 = new ProducerRecord<String, String>(topicName, message);
      producer.send(rec2);
    }

    // still allow input from keyboard

    String line = in.nextLine();
    while (!line.equals("exit")) {
      ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, line);
      producer.send(rec);
      line = in.nextLine();
    }

    in.close();
    producer.close();

  }

  private static String randomMorseCode(int num) {
    switch(num){
    case 0:
        return "... --- ...";
    case 1:
        return "-.-. --.- ";
    case 2:
        return ".-.";
    case 3:
        return "--... ...--";
    case 4:
        return ".. / .-.. --- ...- / -.-- --- ..- ";
    case 5:
        return "-... .. --. / -.. .- - .-";
    case 6:
        return "....- ....- ..... .---- --...";
    case 7:
        return "-.- .- ..-. -.- .-";
    case 8:
        return "--.. --- --- -.- . . .--. . .-.";
    case 9:
        return ".-.. .. -. -.. ... .- -.--";
    default:
        return ".. -.. -.-";
    }

  }
  private static String assocatiedMessage(int num){
    switch(num){
        case 0:
            return "SOS";
        case 1:
            return "seek you";
        case 2:
            return "Roger";
        case 3:
            return "Common greeting";
        case 4:
            return "I love you";
        case 5:
            return "Big data";
        case 6:
            return "44517";
        case 7:
            return "kafka";
        case 8:
            return "zookeeper";
        case 9:
            return "Lindsay";
        default:
            return "I dont know";

        }
        
  }
}