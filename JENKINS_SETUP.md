# Настройка Jenkins для передачи креденшиалов

## 1. Установка необходимых плагинов

В Jenkins необходимо установить следующие плагины:
- **Credentials Binding Plugin** - для работы с креденшиалами
- **Parameterized Trigger Plugin** - для параметризованных сборок

## 2. Создание креденшиалов в Jenkins

### 2.1 Создание креденшиалов для Selenoid

1. Перейдите в **Manage Jenkins** → **Manage Credentials**
2. Выберите домен **Global** → **Add Credentials**
3. Выберите тип **Username with password**
4. Заполните поля:
   - **ID**: `selenoid_credentials`
   - **Description**: `Selenoid credentials for UI tests`
   - **Username**: ваш логин для Selenoid
   - **Password**: ваш пароль для Selenoid
5. Нажмите **OK**



## 3. Настройка параметров сборки

### 3.1 Choice Parameter для браузера

1. В настройках джобы выберите **This project is parameterized**
2. Добавьте **Choice Parameter**:
   - **Name**: `browser`
   - **Choices**:
     ```
     chrome
     firefox
     edge
     ```
   - **Description**: `Выберите браузер для тестов`

### 3.2 Choice Parameter для размера браузера

1. Добавьте еще один **Choice Parameter**:
   - **Name**: `browser.size`
   - **Choices**:
     ```
     1920x1080
     2560x1440
     1366x768
     ```
   - **Description**: `Размер окна браузера`

### 3.3 Choice Parameter для хоста Selenoid

1. Добавьте **Choice Parameter**:
   - **Name**: `selenoid_host`
   - **Choices**:
     ```
     selenoid.autotests.cloud
     localhost
     ```
   - **Description**: `Хост Selenoid`

## 4. Настройка Credentials Binding

### 4.1 Добавление Credentials Binding

1. В настройках джобы найдите раздел **Build Environment**
2. Отметьте **Use secret text(s) or file(s)**
3. Добавьте **Credentials**:

#### Для Selenoid:
- **Variable**: `SELENOID_LOGIN`
- **Credentials**: выберите `selenoid_credentials`
- **Variable**: `SELENOID_PASSWORD`
- **Credentials**: выберите `selenoid_credentials`



## 5. Настройка команды сборки

В разделе **Build** → **Execute shell** (Linux) или **Execute Windows batch command** (Windows):

### Linux/Mac:
```bash
./gradlew clean test \
  -Dbrowser=$browser \
  -Dbrowser.size=$browser.size \
  -Dselenoid_host=$selenoid_host \
  -Dselenoid_login=$SELENOID_LOGIN \
  -Dselenoid_password=$SELENOID_PASSWORD
```

### Windows:
```batch
gradlew.bat clean test ^
  -Dbrowser=%browser% ^
  -Dbrowser.size=%browser.size% ^
  -Dselenoid_host=%selenoid_host% ^
  -Dselenoid_login=%SELENOID_LOGIN% ^
  -Dselenoid_password=%SELENOID_PASSWORD%
```

## 6. Альтернативный способ через Pipeline

Если используете Jenkinsfile, можно использовать такой код:

```groovy
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
        stage('Run Tests') {
            steps {
                sh '''
                    ./gradlew clean test \
                      -Dbrowser=${browser} \
                      -Dbrowser.size=${browser_size} \
                      -Dselenoid_host=${selenoid_host} \
                      -Dselenoid_login=${SELENOID_CREDENTIALS_USR} \
                      -Dselenoid_password=${SELENOID_CREDENTIALS_PSW}
                '''
            }
        }
    }
}
```

## 7. Проверка безопасности

- Креденшиалы никогда не отображаются в логах сборки
- Пароли маскируются звездочками в интерфейсе Jenkins
- Доступ к креденшиалам можно ограничить на уровне пользователей/ролей

## 8. Тестирование

После настройки:
1. Запустите сборку с параметрами
2. Проверьте, что тесты получают все необходимые параметры
3. Убедитесь, что креденшиалы не отображаются в логах
