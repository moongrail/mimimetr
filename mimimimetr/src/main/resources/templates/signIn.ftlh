<!DOCTYPE html>
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <a href="/signup" style="left: 30px">Зарегистрироваться</a>
    <title>SignIn</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<h1>Авторизуйтесь, чтобы продолжить</h1>
<h2>Введите свой email и пароль</h2>
<form action="/login" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <@spring.bind path="signInForm"/>
    <p class="error">${spring.status.errorMessage}</p>
    <label for="email">Адрес электронной почты:</label>
    <@spring.formInput 'signInForm.email' 'id="email" placeholder="Емейл"'/>
    <@spring.showErrors '<br>' 'error' />
    <br>
    <br>
    <label for="password">Ваш пароль:</label>
    <@spring.formPasswordInput 'signInForm.password' 'id="password" class="text" placeholder="Пароль"'/>
    <@spring.showErrors '<br>' 'error' />
    <br>
    <br>
    <label>
        <input type="checkbox" name="remember-me"> Запомнить меня?
    </label>
    <input type="submit" value="Войти">
</form>
</body>
</html>