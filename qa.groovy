pipeline {
    agent any

    environment {
        REDIS_PORT = '6380'
    }

    stages {
        stage("Env Variables") {
            step ('blah') {
                sh "printenv"
                echo "echo ${env.REDIS_PORT}"
            }                        
        }
    }
}