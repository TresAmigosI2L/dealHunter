pipeline {
    agent any

    tools {
        maven 'maven-3.6.3'
    }
    stages {
        stage('Build') {
            steps {
                sh 'echo $MAVEN_HOME'
                sh 'mvn -B -DskipTests clean package'
            }
        }
    }
}