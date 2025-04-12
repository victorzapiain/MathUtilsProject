pipeline {
    agent any

    environment {
        EMAIL_RECIPIENT = 'victorzapiain@gmail.com'
        SONARQUBE_URL = 'http://localhost:9000'  // Your SonarQube URL
        SONARQUBE_TOKEN = 'sonarqube_token'     // Your SonarQube token
    }

    tools {
        maven 'Maven 3'  // Ensure Maven 3 is installed on Jenkins
        jdk 'JDK 17'     // Ensure JDK 17 is installed on Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests...'
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    echo 'Running SonarQube analysis...'
                    sh '''
                    mvn sonar:sonar \
                        -Dsonar.host.url=${SONARQUBE_URL} \
                        -Dsonar.login=${SONARQUBE_TOKEN}
                    '''
                }
            }
        }

        stage('Notify') {
            steps {
                echo 'Sending notification...'
                emailext(
                    to: "${EMAIL_RECIPIENT}",
                    subject: "Build ${env.JOB_NAME} #${env.BUILD_NUMBER} - ${currentBuild.result}",
                    body: """
                        Build result: ${currentBuild.result}
                        Project: ${env.JOB_NAME}
                        Build Number: ${env.BUILD_NUMBER}
                        View build: ${env.BUILD_URL}
                    """
                )
            }
        }
    }

    post {
        always {
            echo 'Always run this...'
            junit '**/target/surefire-reports/*.xml'  // Publish JUnit test results

            // JaCoCo code coverage report
            jacoco()
        }
    }
}

