package edu.nwmissouri.bigdatasec2group2.kafka.simple;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Thomas Tran on 02/26/2021
 */
public class ProducerByThomas {
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

    for (int i = 1; i <= 10; i++) {
        String meals = mealPlans();
        ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, meals);
        producer.send(rec);
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

  private static String mealPlans() {
    String[] meals = {"Breakfast","Second breakfast","Brunch", "Lunch", "Dinner"};
    String[] mainEntree = {"omelette", "roast beef sandwich", "pho", "pizza", "chili"};
    String[] sideDish = {"sweet potatoe fries", "salad", "mashed potatoes", "vegetables", "fruits"};
    String[] drink = {"water", "tea", "juice", "coffee", "smoothie"};

    Random plan = new Random();

    int count = 4;
    int minIndex = 0;
    int maxIndex = 5;

    int[] randomize = plan.ints(count, minIndex, maxIndex).toArray();

    return meals[randomize[0]] + " you will eat " + mainEntree[randomize[1]] + " with " + sideDish[randomize[2]] + " and " + drink[randomize[3]] + ".";
        
  }
}