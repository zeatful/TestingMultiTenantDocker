pipeline {
    agent any

    environment {
        REDIS_PORT = '6380'
    }

    stages {
        stage("Env Variables") {
            steps {
                step ('print environment variables') {
                    sh "printenv"
                }
                step ('print REDIS_PORT') {
                    echo "${env.REDIS_PORT}"
                } 
            }                        
        }
    }
}