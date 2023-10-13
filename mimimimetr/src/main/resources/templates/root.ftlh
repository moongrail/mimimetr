<!DOCTYPE html>
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <a href="/login" style="left: 30px">Войти</a>
    <a href="/signup" style="left: 30px">Зарегистрироваться</a>
    <a href="/logout">Выйти</a>
    <title>Мимимиметр - Топ 10</title>
</head>
<body>
<h1>Мимимиметр - Топ 10</h1>
<ul>
    <#list topTenCatList as cat>
        <li style="display: flex; align-items: center;">
            ${cat_index + 1}.
            <div style="margin-left: 5px;">
                ${cat.name}
            </div>
            <div style="flex: 1;">
                <img src="/images/${cat.id}" alt="" width="160" height="200" style="left: inherit; margin: 10px;">
            </div>
        </li>
    </#list>
</ul>

</body>
</html>