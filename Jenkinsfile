pipeline {
    agent any  // Run the pipeline on any available Jenkins agent

    environment {
        DOCKER_IMAGE = 'satyadockermadhepura/learning'  // Docker image name
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials')  // Jenkins credentials for Docker Hub
        GITHUB_REPO = 'https://github.com/vermatikku/learning.git'  // GitHub repository URL
    }

    stages {
        stage('Clone Repository') {
            steps {
                // Clone the GitHub repository containing the Spring Boot app
                git branch: 'master', url: GITHUB_REPO
            }
        }

        stage('Build JAR') {
            steps {
                // Build the Spring Boot application JAR file using Maven
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image using the Dockerfile in the repository
                    docker.build(DOCKER_IMAGE, '.')
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    // Push the Docker image to Docker Hub
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_HUB_CREDENTIALS) {
                        docker.image(DOCKER_IMAGE).push()
                    }
                }
            }
        }
    }

    post {
        always {
            // Clean up workspace after the pipeline completes
            cleanWs()
        }
    }
}
