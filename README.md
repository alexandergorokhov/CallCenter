# Project Title

Concurrent Callcenter.

### Prerequisites

```
Maven 3
Java 1.8
```

### Installing

mvn clean install

##Class diagramm

![alt tag](https://docs.google.com/document/d/1Px5DvAmOJSTQlFmLHYRzxMnVuGb16lkx5V_VSkSMslY/edit)
## Running the tests

The main requirement tests are run by default with maven, or you can run it from your prefered IDE with JUNIT.
For quicker build you can disable run tests from maven pom.

### Quick test explanation
```
testDispacher - allows you to simulate configurable number of cuncurrent calls.
testOccupancyTest - allows you to check how many calls a different CallCenter members attended.
Keep in mind since this is a multi thread application, prerequisite are important.
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Alexander Gorokhov**



