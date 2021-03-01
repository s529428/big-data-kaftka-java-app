# big-data-kafka-java-app

### Team-Lead: Caroline Finnerty
[https://github.com/s529428](https://github.com/s529428)
### Team Members:

- Soumya Chidambar Rao Waddankeri: [https://github.com/soumyarao28](https://github.com/soumyarao28)
- Spyridon Kaperonis: [https://github.com/SpyridonKaperonis/](https://github.com/SpyridonKaperonis/)
- Thomas Tran: [https://github.com/thomastran7](https://github.com/thomastran7)
- Michael Burnes: [https://github.com/mtburnes](https://github.com/mtburnes)
  - [Stress Testing Kafka](https://github.com/s529428/big-data-kaftka-java-app/tree/main/mtburnes)
- Lindsay G: [https://github.com/LinGill21](https://github.com/LinGill21)

## Process to Run:

### Start Zookeeper Service
```PowerShell
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

### Start Kafka Service
```PowerShell
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

### Create the Kafka Topic
```PowerShell
.\bin\windows\kafka-topics.bat --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --create --topic TOPIC-NAME-HERE
```

### Compile and Build Fat Jar File

Do this in the root project folder. We compile using Maven to create an executable jar file. If you want to find the artifacts they will be in the 'target' folder generated.
```PowerShell
mvn clean compile assembly:single
```

### Start Consumer

Run this command in the project root folder. Make sure to include a topic and groupID, the command below has the topic test and the groupID of group1
```PowerShell
java -cp target/big-data-kaftka-java-app-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.bigdatasec2group2.kafka.simple.Consumer test group1
```

### Start Producer

Open a new PowerShell as Admin in the root project folder. Make sure to use the same topic as the command above!!!

```PowerShell
java -cp target/big-data-kaftka-java-app-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.bigdatasec2group2.kafka.simple.ProducerByCaroline test

java -cp target/big-data-kaftka-java-app-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.bigdatasec2group2.kafka.simple.ProducerByLindsay test

java -cp target/big-data-kaftka-java-app-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.bigdatasec2group2.kafka.simple.ProducerByThomas test

java -cp target/big-data-kaftka-java-app-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.bigdatasec2group2.kafka.simple.ProducerBySoumya test

java -cp target/big-data-kaftka-java-app-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.bigdatasec2group2.kafka.simple.ProducerBySpyridon test
```


