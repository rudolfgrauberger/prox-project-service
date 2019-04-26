pipeline {
    agent {
        docker {
            image 'maven:3.6.1-jdk-8-alpine'
            args '-v maven-data:/root/.m2'
        }
    }
    environment {
        REPOSITORY = "ptb-gp-ss2019.archi-lab.io"
        IMAGE = "project-service"
    }
    stages {
        stage("Build") {
            steps {
                sh "mvn clean install" // Führt den Maven build aus
		sh "ls -ls"
            }
        }
        stage('SonarQube Analysis') {
            steps {
		echo "SonarQube..."
            }
        }
        stage("Test") {
            steps {
                echo "Testing..."
            }
        }
        stage("Code Quality Check") {
            steps {
                echo "Code Quality Check..."
            }
        }
        stage("Deploy") {
            environment {
                SERVERPORT = "22413"
                YMLFILENAME = "docker-compose-project-service.yml"
                SSHUSER = "jenkins"
                SERVERNAME = "fsygs15.inf.fh-koeln.de"
            }
            steps {
		sh "cat /etc/hosts"
                //sh "scp -P ${SERVERPORT} -v ${IMAGE}.tar ${SSHUSER}@${SERVERNAME}:~/"     // Kopiert per ssh die tar Datei auf dem Produktionsserver
                //sh "scp -P ${SERVERPORT} -v ${YMLFILENAME} ${SSHUSER}@${SERVERNAME}:/srv/projektboerse/"
                //sh "ssh -p ${SERVERPORT} ${SSHUSER}@${SERVERNAME} " +
                //        "'docker image load -i ${IMAGE}.tar; " +
                //        /*"docker network inspect ptb &> /dev/null || docker network create ptb; " + */ // when connecting to other services, enable this
                //        "docker-compose -p ptb -f /srv/projektboerse/${YMLFILENAME} up -d'"
            }
        }
    }
}
