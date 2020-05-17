node('slave1') {

    environment {
        REDIS_PORT='6380'
    }

    stage('Checkout'){
        checkout scm
    }

    stage('print redis port'){
        echo "echo ${REDIS_PORT}"
    // sh 'docker-compose -p QA up --force-recreate -d'
    }

    // stage('Perform Docker Cleanup'){
    //     // Remove dangling images and networks
    //     sh 'docker image prune -f'
    //     sh 'docker image prune -fa'
    //     sh 'docker network prune -f'
    // }
}