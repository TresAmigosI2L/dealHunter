pipeline {
    agent any

    tools {
        maven 'Maven 3.9.1'
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