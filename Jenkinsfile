pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials') // Define your Docker Hub credentials in Jenkins
        GITHUB_REPO = 'https://github.com/yourusername/your-repo.git'
        DOCKER_IMAGE = 'your-username/your-app-name'
        K8S_NAMESPACE = 'default'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: GITHUB_REPO
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build(DOCKER_IMAGE, '.')
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_HUB_CREDENTIALS) {
                        docker.image(DOCKER_IMAGE).push()
                    }
                }
            }
        }

        stage('Deploy to Minikube') {
            steps {
                script {
                    sh 'kubectl config use-context minikube'
                    sh 'kubectl set image deployment/your-deployment your-container=${DOCKER_IMAGE}:${GIT_COMMIT}'
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
