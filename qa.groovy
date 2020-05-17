pipeline {
    agent any

    stages {
        stage("Env Variables") {
            environment {
                REDIS_PORT = "6380"
            }

            steps {
                echo "${env.REDIS_PORT}"
            }                        
        }
    }
}