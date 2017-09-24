# Project Title

Concurrent Callcenter.

### Prerequisites

What things you need to install the software and how to install them

```
Maven 3
Java 1.8
```

### Installing

mvn clean install

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

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details


