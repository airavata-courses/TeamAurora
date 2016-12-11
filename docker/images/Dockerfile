FROM java:8u102-jdk
COPY forecasttriggerworker-1.0-jar-with-dependencies.jar /usr/src/forecasttriggerworker-1.0-jar-with-dependencies.jar
WORKDIR /usr/src
CMD java -cp forecasttriggerworker-1.0-jar-with-dependencies.jar com.sg.aurora.forecasttriggerworker.rest.ForecastTriggerController