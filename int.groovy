pipeline {
    agent { label 'slave1' }
    
    parameters {
        choice (
            name: 'ENVIRONMENT_NAME',
            choices: ['int', 'qa', 'demo', 'stage', 'prod'],
            description: 'Choose which environment to deploy from master'
        )
    }

    stages {
        stage ('Deploy to INT') {
            when {
                anyOf {
                    expression { params.ENVIRONMENT_NAME == 'INT' }
                }
            }

            environment {
                REDIS_PORT = "6379"
            }

            steps {
                sh "docker-compose -f docker-compose.managed.yml -p ${params.ENVIRONMENT_NAME} down --remove-orphans"
                sh "docker-compose -f docker-compose.managed.yml -p ${params.ENVIRONMENT_NAME} up --force-recreate -d"
            }
        }

        stage ('Deploy to QA') {
            when {
                anyOf {
                    expression { params.ENVIRONMENT_NAME == 'QA' }
                }
            }

            environment {
                REDIS_PORT = "6380"
            }

            steps {
                sh "docker-compose -f docker-compose.managed.yml -p ${params.ENVIRONMENT_NAME} down --remove-orphans"
                sh "docker-compose -f docker-compose.managed.yml -p ${params.ENVIRONMENT_NAME} up --force-recreate -d"
            }
        }

        stage ('Deploy to Q') {
            when {
                anyOf {
                    expression { params.ENVIRONMENT_NAME == 'DEMO' }
                }
            }

            environment {
                REDIS_PORT = "6381"
            }

            steps {
                sh "docker-compose -f docker-compose.managed.yml -p ${params.ENVIRONMENT_NAME} down --remove-orphans"
                sh "docker-compose -f docker-compose.managed.yml -p ${params.ENVIRONMENT_NAME} up --force-recreate -d"
            }            
        }

        stage ('Set STAGE Environment Variables') {
            when {
                anyOf {
                    expression { params.ENVIRONMENT_NAME == 'STAGE' }
                }
            }

            environment {
                REDIS_PORT = "6382"
            }

            steps {
                sh "docker-compose -f docker-compose.managed.yml -p ${params.ENVIRONMENT_NAME} down --remove-orphans"
                sh "docker-compose -f docker-compose.managed.yml -p ${params.ENVIRONMENT_NAME} up --force-recreate -d"
            }            
        }

        stage ('Set PROD Environment Variables') {
            when {
                anyOf {
                    expression { params.ENVIRONMENT_NAME == 'PROD' }
                }
            }

            environment {
                REDIS_PORT = "6383"
            }

            steps {
                sh "docker-compose -f docker-compose.managed.yml -p ${params.ENVIRONMENT_NAME} down --remove-orphans"
                sh "docker-compose -f docker-compose.managed.yml -p ${params.ENVIRONMENT_NAME} up --force-recreate -d"
            }            
        }

        stage('Perform Docker Cleanup'){
            steps {
            // Remove dangling images and networks
            sh 'docker image prune -f'
            sh 'docker image prune -fa'
            sh 'docker network prune -f'
            }
        }
    }
}