<!DOCTYPE html>
<#import "spring.ftl" as spring/>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <a href="/logout">Выйти</a>
    <title>Мимимиметр</title>
</head>

<body>
<h1>Vote for the Cutest Cat!</h1>
<form action="/game/{catId}" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <div th:each="catPair : ${catPairs}">
        <h2>Cat 1: ${catPair.name}</h2>
        <a th:href="@{/images/{catId}(catId=${catPair.id})}">
            <img th:href="@{/images/{catId}(catId=${catPair.id})}" alt="Cat 1" width="150" height="200">
        </a>
        <input type="radio" name="votedCatId" th:value="${catPair.id}"> Vote for Cat 1<br>
        <hr>
    </div>
    <button type="submit">Submit Vote</button>
</form>
</body>
</html>