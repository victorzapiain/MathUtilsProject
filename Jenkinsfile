pipeline {
    agent any
    environment {
        SONARQUBE_URL = 'http://localhost:9000'
        SONARQUBE_TOKEN = 'sonarqube_token'
    }
    tools {
        maven 'Maven 3'
        jdk 'JDK 17'
    }
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from GitHub...'
                script {
                    // Checkout from main branch using your GitHub credentials
                    git credentialsId: 'GitHubToken', branch: 'main', url: 'https://github.com/victorzapiain/MathUtilsProject.git'
                }
            }
        }
        stage('Build') {
            steps {
                echo 'Building project using Maven...'
                sh 'mvn clean install'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube analysis...'
                script {
                    // Run SonarQube analysis
                    sh "mvn sonar:sonar -Dsonar.projectKey=MathUtilsProject -Dsonar.host.url=${SONARQUBE_URL} -Dsonar.login=${SONARQUBE_TOKEN}"
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Running unit tests...'
                sh 'mvn test'
            }
        }
        stage('Jacoco Coverage') {
            steps {
                echo 'Generating Jacoco test coverage...'
                sh 'mvn jacoco:report'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                // Your deployment steps here
            }
        }
    }
}
