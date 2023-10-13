<!DOCTYPE html>
<#--<#import "spring.ftl" as spring/>-->
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
        <h2>Cat 1: ${catPair.cat1.getName}</h2>
        <img th:src="@{/images/{catId}(catId=${catPair.cat1.id})}" alt="Cat 1" width="150" height="200">
        <h2>Cat 2: ${catPair.cat2.name}</h2>
        <img th:src="@{/images/{catId}(catId=${catPair.cat2.id})}" alt="Cat 2" width="150" height="200">
        <input type="radio" name="votedCatId" th:value="${catPair.cat1.id}"> Vote for Cat 1<br>
        <input type="radio" name="votedCatId" th:value="${catPair.cat2.id}"> Vote for Cat 2<br>
        <hr>
    </div>
    <button type="submit">Submit Vote</button>
</form>
</body>
</html>