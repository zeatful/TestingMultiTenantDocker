pipeline {
    agent any

    environment {
        NAME = "INT"
        REDIS_PORT = "6379"
    }

    stages {
        stage("Env Variables") {
            steps {
                echo "${env.REDIS_PORT}"
                sh "docker-compose -p ${env.NAME} up -d"
            }                        
        }
    }
}