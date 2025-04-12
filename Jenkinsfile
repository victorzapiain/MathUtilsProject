pipeline {
    agent any

    environment {
        SONARQUBE = 'SonarQube' // Update with your actual SonarQube server name if different
        MAVEN_HOME = '/usr/share/maven' // Adjust this if Maven is installed in a different path
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64' // Java 17 on Ubuntu
        EMAIL_RECIPIENT = 'victorzapiain@gmail.com'
    }

    tools {
        maven 'Maven 3.8.1' // Update to match your Maven version label in Jenkins
        jdk 'JDK 17' // Ensure "JDK 17" is configured in Jenkins Global Tool Configuration
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn clean install"
            }
        }

        stage('Static Code Analysis') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn sonar:sonar -Dsonar.projectKey=YourProjectKey -Dsonar.host.url=http://your-sonarqube-server"
            }
        }

        stage('Run Tests') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn test"
            }
        }

        stage('Code Coverage') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn jacoco:report"
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
            }
        }

        stage('Send Notifications') {
            steps {
                script {
                    emailext(
                        to: "${EMAIL_RECIPIENT}",
                        subject: "Build ${currentBuild.fullDisplayName} - ${currentBuild.result}",
                        body: """
                        Hello,

                        The build for ${env.JOB_NAME} [#${env.BUILD_NUMBER}] has completed with status: ${currentBuild.result}.

                        View the details at: ${env.BUILD_URL}

                        Regards,
                        Jenkins CI
                        """,
                        mimeType: 'text/plain'
                    )
                }
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
        success {
