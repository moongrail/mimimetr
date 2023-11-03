<!DOCTYPE html>
<html>
<head>
    <title>Мимиметр лайкер котиков</title>
    <a href="/logout">Выйти</a>
    <style>
        .center {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 40vh;
        }

        ul {
            display: flex;
            list-style-type: none;
            padding: 0;
        }

        li {
            margin-right: 60px;
        }
    </style>
</head>
<body>
<h1>Мимиметр лайкер котиков и Рами Маликова</h1>
<div class="center">
    <ul>
        <#list catPairs as catPair>
            <li>
                <a href="/images/${catPair.id}">
                    <a href="/images/${catPair.id}">
                        <img src="/images/${catPair.id}" alt="Cat 1" width="150" height="200">
                    </a>
                    <div>${catPair.name}</div>
                    <form action="/game/vote" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <input type="hidden" name="votedCatId" value="${catPair.id}">
                        <button type="submit">Like</button>
                    </form>
                </a>
            </li>
        </#list>
    </ul>
</div>
</body>
</html>