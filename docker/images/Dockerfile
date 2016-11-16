FROM java:8u102-jdk
COPY dataingestorworker-1.0-jar-with-dependencies.jar /usr/src/dataingestorworker-1.0-jar-with-dependencies.jar
WORKDIR /usr/src
CMD java -cp dataingestorworker-1.0-jar-with-dependencies.jar com.sg.aurora.dataingestorworker.rest.resources.DataIngestorController