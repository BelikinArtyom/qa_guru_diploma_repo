pipeline {
    agent any
    
    tools {
        gradle 'Gradle 8.0'
        jdk 'JDK 17'
    }
    
    environment {
        ALLURE_VERSION = '2.19.0'
        ALLURE_RESULTS = 'build/allure-results'
        ALLURE_REPORT = 'build/allure-report'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                sh 'gradle clean build -x test'
            }
        }
        
        stage('Test') {
            steps {
                sh 'gradle test'
            }
            post {
                always {
                    // Сохраняем результаты тестов
                    archiveArtifacts artifacts: 'build/allure-results/**/*', fingerprint: true
                    
                    // Генерируем Allure отчет
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: ALLURE_RESULTS]]
                    ])
                }
            }
        }
    }
    
    post {
        always {
            // Очистка workspace
            cleanWs()
        }
    }
}
