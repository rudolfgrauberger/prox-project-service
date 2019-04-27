# PTB Project Service
The purpose of this service is to provide a backend for projects of the "Projekt- und Themenbörse (PTB)" web application of TH Köln.

## Installation
``` bash
mvn clean package
```
Executes the [Maven default lifecycle](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html) up to the package phase. During `package` phase a runnable JAR is created and a docker image is build.

### Deploy
If you wan't to deploy this version to the private nexus docker registry use the `deploy` phase.

``` bash
mvn -Dmaven.deploy.skip=true deploy
```

`-Dmaven.deploy.skip=true` ensures that the default behavior of the maven deploy phase is not executed. However, the attached maven plugin to this phase still execute and pushes the image to the private docker repository.

## Usage
```
docker-compose -f docker-compose-project-service.yml up
```
Starts a Docker container based on the compose file and the image. A Docker network named `ptb` is required for the communication between services:
```
docker network create ptb
```
It is also possible to run the JAR directly with specific Spring profiles:
```
java -jar -Dspring.profiles.active=local target/project-service-0.0.1-SNAPSHOT.jar
```

## Documentation
You can view the full documentation under the following [GitHub Wiki](https://github.com/Archi-Lab/ptb-documentation/wiki).

## Contributing

## About the Team
This service is currently developed by the PTB Team composed of students from [TH Köln](https://www.th-koeln.de/):

- Julian Lengelsen ([@jlengelsen](https://github.com/jlengelsen))
- Mansoor Rahmati ([@ManSoorSour](https://github.com/ManSoorSour))
- Jan Seidler ([@janseidler](https://github.com/janseidler))
