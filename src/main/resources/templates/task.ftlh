<!DOCTYPE html>
<html>
<head>
    <title>EvgenUlianov:HomeTask11Application</title>
</head>
<body>
<h1>EvgenUlianov:HomeTask11Application</h1>
<hr>
<h4>Подробная информация о задаче</h4>
<b>Номер задачи:</b>${task.id}<br>
<b>Описание:</b>${task.description}<br>
<hr>
<form action="/task/edit/${task.id}" method="post">
    Новое название: <input type="text", name="name"/><br><br>
    <input type="submit" value="Переименовать задачу"/>
</form>
<form action="/task/delete/${task.id}" method="post">
    <input type="submit" value="Удалить задачу"/>
</form>
<br>
<a href="/">Вернуться в список</a>
</body>
</html>