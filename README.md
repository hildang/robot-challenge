# Robot Challenge

## Pre-requisite 
Java 11+

## Build
Run the following command in a terminal window in the root directory of this project:

```
./mvnw clean package -Dmaven.test.skip
```

A jar file will be created under target/ folder. 

## Run the application
Run the following command to start the applicaiton: 

```
 java -jar target/robot-challenge-0.0.1.jar
```

You will see the following output: 

```
Enter Command:

```
Then you can start entering your commands

## Testing
Test cases can be found under src/test/java folder.
Run the following command to run the test cases:

```
 ./mvnw test
```

## IDE
This project is created using Spring Tool Suite 4. You can import it in any IDE. Since a library Lombok is used to assist the development, a plugin needs to be installed in your preferred IDE.
You may visit [this blog](https://www.baeldung.com/lombok-ide) for the installation in Eclipse or IntelliJ