pipeline {
    agent any
    
    parameters {
        choice(
            name: 'browser',
            choices: ['chrome', 'firefox', 'edge'],
            description: 'Выберите браузер для тестов'
        )
        choice(
            name: 'browser_size',
            choices: ['1920x1080', '2560x1440', '1366x768'],
            description: 'Размер окна браузера'
        )
        choice(
            name: 'selenoid_host',
            choices: ['selenoid.autotests.cloud', 'localhost'],
            description: 'Хост Selenoid'
        )
    }
    
    environment {
        SELENOID_CREDENTIALS = credentials('selenoid_credentials')
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Run Tests') {
            steps {
                script {
                    if (isUnix()) {
                        sh '''
                            ./gradlew clean test \
                              -Dbrowser=${browser} \
                              -Dbrowser.size=${browser_size} \
                              -Dselenoid_host=${selenoid_host} \
                              -Dselenoid_login=${SELENOID_CREDENTIALS_USR} \
                              -Dselenoid_password=${SELENOID_CREDENTIALS_PSW}
                        '''
                    } else {
                        bat '''
                            gradlew.bat clean test ^
                              -Dbrowser=%browser% ^
                              -Dbrowser.size=%browser_size% ^
                              -Dselenoid_host=%selenoid_host% ^
                              -Dselenoid_login=%SELENOID_CREDENTIALS_USR% ^
                              -Dselenoid_password=%SELENOID_CREDENTIALS_PSW%
                        '''
                    }
                }
            }
        }
        
        stage('Generate Allure Report') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'allure generate allure-results --clean'
                    } else {
                        bat 'allure generate allure-results --clean'
                    }
                }
            }
        }
        
        stage('Publish Allure Report') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'allure-results']]
                ])
            }
        }
    }
    
    post {
        always {
            // Очистка workspace
            cleanWs()
        }
        success {
            // Уведомление об успешном выполнении
            echo 'Тесты выполнены успешно!'
        }
        failure {
            // Уведомление об ошибке
            echo 'Тесты завершились с ошибкой!'
        }
    }
}
