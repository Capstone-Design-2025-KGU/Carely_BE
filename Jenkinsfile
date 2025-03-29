pipeline {
    agent any

    environment {
        APP_VERSION = "1.0.$BUILD_ID"
        APP_NAME = 'carely'
        AWS_DEFAULT_REGION = 'ap-northeast-2'
        AWS_DOCKER_REGISTRY = '920373025050.dkr.ecr.ap-northeast-2.amazonaws.com'
        AWS_ECS_CLUSTER = 'carely-cluster'
        AWS_ECS_SERVICE_PROD ='carely-service'
        AWS_ECS_TD_PROD = 'carely-task'
    }

    stages {
        stage('Add Env') {
            steps {
                withCredentials([file(credentialsId: 'carely-application', variable: 'application')]) {
                    sh 'cp ${application}  src/main/resources/application.yml'
                }
            }
        }
        stage('Build') {
            agent {
                docker {
                    image 'gradle:8.10-jdk17-focal'
                    reuseNode true
                }
            }
            steps {
                sh '''
                ls -la
                ./gradlew clean build -x test
                ls -la
                echo "[DEBUG] 현재 application.yml:"
                cat src/main/resources/application.yml
                jar xf build/libs/*.jar BOOT-INF/classes/application.yml
                cat BOOT-INF/classes/application.yml
                '''
            }
        }

         stage('Build Docker image') {
            agent {
                docker {
                    image 'my-aws-cli'
                    reuseNode true
                    args "-u root -v /var/run/docker.sock:/var/run/docker.sock --entrypoint=''"
                }
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'my-aws', passwordVariable: 'AWS_SECRET_ACCESS_KEY', usernameVariable: 'AWS_ACCESS_KEY_ID')]) {
                    sh '''
                        docker build --platform linux/amd64 -t $AWS_DOCKER_REGISTRY/$APP_NAME:$APP_VERSION .
                        aws ecr get-login-password | docker login --username AWS --password-stdin $AWS_DOCKER_REGISTRY
                        docker push $AWS_DOCKER_REGISTRY/$APP_NAME:$APP_VERSION
                    '''
                }
            }
        }

        stage('Deploy to AWS') {
            agent {
                docker {
                    image 'my-aws-cli'
                    reuseNode true
                    args "--entrypoint=''"
                }
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'my-aws', passwordVariable: 'AWS_SECRET_ACCESS_KEY', usernameVariable: 'AWS_ACCESS_KEY_ID')]) {
                    sh '''
                        sed -i "s/#APP_VERSION#/$APP_VERSION/g" aws/task-definition-prod.json 
                        LATEST_TD_REVISION=$(aws ecs register-task-definition --cli-input-json file://aws/task-definition-prod.json | jq '.taskDefinition.revision')  
                        aws ecs update-service --cluster $AWS_ECS_CLUSTER --service $AWS_ECS_SERVICE_PROD --task-definition $AWS_ECS_TD_PROD:$LATEST_TD_REVISION 
                        aws ecs wait services-stable --cluster $AWS_ECS_CLUSTER --services $AWS_ECS_SERVICE_PROD  
                    '''
                } 
            }
        }
    }
}