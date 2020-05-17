node('slave1') {

    environment {
        REDIS_PORT = 6379
    }

    stage('Checkout'){
        checkout scm
    }

    stage('Build & Start Services'){
        sh 'docker-compose -p INT up --force-recreate -d'
    }

    stage('Perform Docker Cleanup'){
        // Remove dangling images and networks
        sh 'docker image prune -f'
        sh 'docker image prune -fa'
        sh 'docker network prune -f'
    }
}