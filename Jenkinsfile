pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'echo $MAVEN_HOME'
                sh 'mvn -B -DskipTests clean package'
            }
        }
    }
}