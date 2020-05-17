node('slave1') {

    environment {
        REDIS_PORT = '6380'
    }

    stages {
        stage("Env Variables") {
            sh "printenv"
            echo "echo ${env.REDIS_PORT}"
        }

        // stage('print redis port'){        
        // sh 'docker-compose -p QA up --force-recreate -d'
        // }

        // stage('Perform Docker Cleanup'){
        //     // Remove dangling images and networks
        //     sh 'docker image prune -f'
        //     sh 'docker image prune -fa'
        //     sh 'docker network prune -f'
        // }
    }
}