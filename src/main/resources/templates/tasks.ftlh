<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>EvgenUlianov:HomeTask11Application</title>
</head>
<body>
<h1>EvgenUlianov:HomeTask11Application  </h1><hr>
<a href="/login">войти</a>
<!--<a href="/registration">зарегистрироваться</a>-->
<!---->
<h3>Список задач</h3>
<#list tasks as task>
    <div>
        <p><b>${task.id}</b> ${task.description} | <a href="/task/${task.id}">Подробнее...</a>
            <form action="/task/toggle/${task.id}" method="post"> <input type="submit" value="toggle"/></form>
        </p>
    </div>
</#list>
<h4>Новая задача</h4>
<form action="/tasks/add" method="post">
    название: <input type="text", name="name"/><br><br>
    <input type="submit" value="Добавить задачу"/>
</form>
</body>
</html>