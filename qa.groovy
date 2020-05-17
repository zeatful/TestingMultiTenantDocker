pipeline {
    agent any

    environment {
        REDIS_PORT = '6380'
    }

    stage("Env Variables") {
        sh "printenv"
        echo "echo ${env.REDIS_PORT}"
    }
}