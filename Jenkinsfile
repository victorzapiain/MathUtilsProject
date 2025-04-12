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
                script {
                    // Make sure to specify the GitHub repository URL
                    git 'https://github.com/victorzapiain/MathUtilsProject.git'
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
                    sh '''
                    mvn sonar:sonar \
                        -Dsonar.host.url=${SONARQUBE_URL} \
                        -Dsonar.login=${SONARQUBE_TOKEN} \
                        -Dsonar.projectKey=MathUtilsProject \
                        -Dsonar.projectName=MathUtilsProject \
                        -Dsonar.projectVersion=1.0
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
