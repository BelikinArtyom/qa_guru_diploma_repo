# Быстрая настройка Jenkins

## 🚀 Минимальная настройка за 5 минут

### 1. Установите плагины
- **Credentials Binding Plugin**
- **Allure Jenkins Plugin**

### 2. Создайте креденшиалы
```
Manage Jenkins → Manage Credentials → Global → Add Credentials

selenoid_credentials (Username with password)
- ID: selenoid_credentials
- Username: ваш_логин_selenoid
- Password: ваш_пароль_selenoid
```

### 3. Настройте джобу
```
This project is parameterized ✓

Choice Parameter:
- Name: browser
- Choices: chrome, firefox, edge

Choice Parameter:
- Name: browser_size
- Choices: 1920x1080, 2560x1440, 1366x768

Choice Parameter:
- Name: selenoid_host
- Choices: selenoid.autotests.cloud, localhost
```

### 4. Добавьте Credentials Binding
```
Build Environment → Use secret text(s) or file(s) ✓

Credentials:
- Variable: SELENOID_LOGIN
- Credentials: selenoid_credentials

- Variable: SELENOID_PASSWORD
- Credentials: selenoid_credentials
```

### 5. Команда сборки
```bash
./gradlew clean test \
  -Dbrowser=$browser \
  -Dbrowser.size=$browser.size \
  -Dselenoid_host=$selenoid_host \
  -Dselenoid_login=$SELENOID_LOGIN \
  -Dselenoid_password=$SELENOID_PASSWORD
```

## ✅ Готово!

Теперь ваши тесты будут:
- Безопасно получать креденшиалы из Jenkins
- Запускаться с выбранными параметрами
- Не содержать логины и пароли в коде

## 🔧 Альтернатива

Используйте готовый `Jenkinsfile` - просто скопируйте его в корень проекта и создайте Pipeline джобу.
