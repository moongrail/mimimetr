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
<form action="/signup" method="post">
    <@spring.bind path="signUpForm"/>
    <label for="name">Enter name:</label>
    <@spring.formInput 'signUpForm.name' 'id="name" class="text" placeholder="Your name"'/>
    <@spring.showErrors '<br>' 'error' />
    <br>
    <label for="email">Enter email:</label>
    <@spring.formInput 'signUpForm.email' 'id="email" class="text" placeholder="Your email"'/>
    <@spring.showErrors '<br>' 'error' />
    <br>
    <br>
    <label for="password">Enter password:</label>
    <@spring.formPasswordInput 'signUpForm.password' 'id="password" class="text" placeholder="Your password"'/>
    <@spring.showErrors '<br>' 'error' />
    <br>
    <br>
    <label for="avatar">Upload avatar:</label>
    <input type="file" id="signUpForm.avatar" name="avatar">
    <br>
    <br>
    <input type="submit" value="Sign Up">
</form>
</body>
</html>