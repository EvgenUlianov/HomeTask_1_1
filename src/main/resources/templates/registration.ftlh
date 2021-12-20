<!DOCTYPE html>
<html>
<head>
  <title>EvgenUlianov:HomeTask11Application</title>
</head>
<body>
<h1>EvgenUlianov:HomeTask11Application</h1><hr>
<h4>Регистрация</h4>
<form action="/registration" method="post">
  Имя пользователя: <input type="text" name="username"><br><br>
  Пароль: <input type="password" name="password"><br><br>
  <input type="hidden" name="_csrf" value="${_csrf.token}">
  <input type="submit" value="зарегистрироваьтся"/>
</form>
<#if errorMessage??>
  <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>