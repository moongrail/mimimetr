<!DOCTYPE html>
<#import "spring.ftl" as spring/>
<head xmlns:th="http://www.w3.org/1999/xhtml">
    <meta charset="UTF-8">
    <title>SignUp</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<h1>Страница регистрации</h1>
<h2>Введите свои данные:</h2>
<form action="/signup" method="post" enctype="multipart/form-data">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <@spring.bind path="signUpForm"/>
    <label for="name">Введите имя:</label>
    <@spring.formInput 'signUpForm.name' 'id="name" class="text" placeholder="Your name"'/>
    <@spring.showErrors '<br>' 'error' />
    <br>
    <br>
    <label for="email">Введите email:</label>
    <@spring.formInput 'signUpForm.email' 'id="email" class="text" placeholder="Your email"'/>
    <@spring.showErrors '<br>' 'error' />
    <br>
    <br>
    <label for="password">Введите пароль:</label>
    <@spring.formPasswordInput 'signUpForm.password' 'id="password" class="text" placeholder="Your password"'/>
    <@spring.showErrors '<br>' 'error' />
    <br>
    <br>
    <label for="avatar">Загрузить аватар:</label>
    <input type="file" id="signUpForm.avatar" name="avatar" >
    <@spring.showErrors '<br>' 'error' />
    <br>
    <br>
    <input type="submit" value="Зарегистрироваться">
</form>
</body>
</html>