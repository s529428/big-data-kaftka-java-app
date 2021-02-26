package edu.nwmissouri.bigdatasec2group2.kafka.simple;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Caroline Finnerty on 2/26/21
 */

 public class ProducerByCaroline {
    private static Scanner in;

    public static void main(String[] args) {
        if(args.length != 1){
            System.err.println("Please specify the name of the topic");
            System.exit(-1);
        }

        String topicName = args[0];
        in = new Scanner(System.in);
        System.out.println("Thanks for that tasty topic " + topicName + "\n");
        System.out.println("Enter a message (TYPE EXIT TO QUIT) Happy pokemon day");

        //Configure the Producer
        Properties configProperties = new Properties();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        System.out.println("The configuration properties are: " + configProperties.toString());
        System.out.println("\nWill use this configuration to create a producer.\n");

        org.apache.kafka.clients.producer.Producer producer = new KafkaProducer(configProperties);

        //Time to make our custom message

        Random rand = new Random();

        String input = in.nextLine();

        // We need 10 messages to print I think
        for (int i = 0; i<10; i++){
            //get a random num to pick message
            int randNum = rand.nextInt(10);
            String pokemon = randomPokemon(randNum);
            ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, pokemon);
            producer.send(rec);
        }

        // Allow the user to type exit to get out.
        String line = in.nextLine();
        while(!line.equals("exit")){
            ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, (line + ", Happy pokemon day!"));
            producer.send(rec);
            line = in.nextLine();
        }

        //User has typed exit

        in.close();
        producer.close();

    }

    private static String randomPokemon(int num) {
        switch(num){
            case 0:
                return "Bulbasaur";
            case 1:
                return "Ivysaur";
            case 2:
                return "Venasaur";
            case 3:
                return "Charmander";
            case 4:
                return "Charmelon";
            case 5:
                return "Charizard";
            case 6:
                return "Pikachu";
            case 7:
                return "Piplup";
            case 8:
                return "Prinplup";
            case 9:
                return "Empleon";
            default:
                return "Arceus";
        }
    }
 }