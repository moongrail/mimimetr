<!DOCTYPE html>
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <title>Мимимиметр - Топ 10</title>
</head>
<body>
<h1>Мимимиметр - Топ 10</h1>
<#--<form action="/top" method="get">-->
    <ul>
        <#list topTenCatList as cat>
            <li> ${cat.name} <img src="/images/${cat.id}" alt=""></li>
        </#list>
    </ul>
<#--</form>-->

</body>
</html>