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
                sh "mvn clean compile"
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
                sh "mvn test"
            }
        }
        stage("Code Quality Check") {
            steps {
                echo "Code Quality Check..."
            }
        }
        stage("Deploy") {
            steps {
                sh "mvn -Dmaven.test.skip=true -Dmaven.install.skip=true -Dmaven.deploy.skip=true deploy"
            }
        }
    }
}
