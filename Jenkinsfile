pipeline {
    agent any

    tools {
        maven 'maven-3.9.1'
        jdk 'java-19'
    }
    stages {
        stage('Build') {
            steps {
                sh 'echo $MAVEN_HOME'
                sh 'echo $JAVA_HOME'
                sh 'java --version'
                sh 'mvn -B -DskipTests clean package'
            }
        }
    }
}