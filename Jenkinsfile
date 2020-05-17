node('slave1') {
    stage('Checkout'){
        checkout scm
    }

    stage('Stop Current Services'){
        sh 'docker-compose -p QA down --remove-orphans'
        sh 'docker-compose -p INT down --remove-orphans'
    }

    stage('Build & Start Services'){
        sh 'export REDIS_PORT=6839'
        sh 'docker-compose -p QA up --force-recreate -d'
        sh 'export REDIS_PORT=6840'
        sh 'docker-compose -p INT up --force-recreate -d'
    }

    stage('Perform Docker Cleanup'){
        // Remove dangling images and networks
        sh 'docker image prune -f'
        sh 'docker image prune -fa'
        sh 'docker network prune -f'
    }
}