# Train Tracker Service

As the name suggests a train tracker to help me with my office commute. Uses API from National Rail via [Rail Data Marketplace](https://raildata.org.uk/).
Implementation though processes can be followed on my blog [https://devrandom.blog/category/train-tracker/](https://devrandom.blog/category/train-tracker/).

## Getting Started
Train tracker needs JDK version 13 or above, get the JDK from [here](https://openjdk.org/install/).

```shell
$ git clone git@github.com:cx0der/train-tracker-service.git

$ cd train-tracker-service

# Build and run
$ ./gradlew run

# Build fat jar distribution
$ ./gradlew fatJar

$ java -jar build/libs/train-tracker-service-1.0.0.jar <args>
```

## Modes of operation
Train tracker has two modes of operation, namely `refresh` mode and `serve` mode.

### Refresh
Refresh mode fetches the current running status of the trains within a 120-minute window between source and destination stations.
```shell
# kick off a refresh mode
java -jar train-tracker-service-1.0.0.jar refresh
```

### Serve
In the `serve` mode, Train Tracker will start an HTTP server

```shell
# start http server
java -jar train-tracker-service-1.0.0.jar serve
```

## License
Train Tracker is free and open-source software licensed under [GPLv3](LICENSE).
