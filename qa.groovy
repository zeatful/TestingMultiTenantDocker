pipeline {
    agent any

    environment {
        NAME = "QA"
        REDIS_PORT = "6380"
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