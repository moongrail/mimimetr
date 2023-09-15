<!DOCTYPE html>
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <title>SignUp</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<h1>Sign Up Page</h1>
<h2>Please Enter your data:</h2>
<form action="/signUp" method="post">
    <@spring.bind path="signUpForm"/>
    <label for="username">Enter username:</label>
    <@spring.formInput 'signUpForm.username' 'id="username" class="text" placeholder="Your username"'/>
    <@spring.showErrors '<br>' 'error' />
    <#if uniqueUsernameExcepion??>
        <br>
        <span class="error">This username already exists</span>
    </#if>
    <br>
    <br>
    <label for="password">Enter password:</label>
    <@spring.formPasswordInput 'signUpForm.password' 'id="password" class="text" placeholder="Your password"'/>
    <@spring.showErrors '<br>' 'error' />
    <br>
    <br>
    <input type="submit" value="Sign Up">
</form>
</body>
</html>