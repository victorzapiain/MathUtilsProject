pipeline {
    agent any
    environment {
        // Define the SonarQube server URL and token
        SONARQUBE_URL = 'http://localhost:9000'
        SONARQUBE_TOKEN = 'sonarqube_token'
    }
    tools {
        // Ensure that Maven and JDK 17 are available in Jenkins
        maven 'Maven 3'  // Adjust based on the Maven version installed in Jenkins
        jdk 'JDK 17'     // Ensure JDK 17 is installed on Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from GitHub...'
                git 'https://github.com/victorzapiain/MathUtilsProject.git'
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
                    sh '''
                    mvn sonar:sonar \
                        -Dsonar.host.url=${SONARQUBE_URL} \
                        -Dsonar.login=${SONARQUBE_TOKEN}
                    '''
                }
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running unit tests...'
                sh 'mvn test'
            }
        }

        stage('Code Coverage') {
            steps {
                echo 'Generating code coverage report with JaCoCo...'
                sh 'mvn jacoco:report'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'
                sh 'docker build -t my-java-app .'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                echo 'Pushing Docker image to Docker Hub...'
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
                    sh 'docker push my-java-app'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
