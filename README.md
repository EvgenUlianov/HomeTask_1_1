# HomeTask_1_1

# Работа через браузер
Браузерное приложение для работы со списком задач,
1. На стартовой странице выводится список всех задач
2. Для каждой задачи доступно переключение состояние задачи (с "выполнена" на "не выполнена"
   и наоборот) по кнопке "toggle"
3. Для каждой задачи доступен переход для подробного изучения задачи по гиперссылке "Подробнее..."
4. На стартовой странице в разделе "Новая задача" доступно добавление задачи, для этого необходимо 
   ввести название в поле "название" и нажать кнопку "Добавить задачу"
5. На странице задачи доступно переименование задачи, для этого необходимо
   ввести название в поле "Новое название" и нажать кнопку "Переименовать задачу"
6. На странице задачи доступно удаление задачи, для этого необходимо
   нажать кнопку "Удалить задачу"
7. На странице задачи доступен переход в стартовую страницу без выполнения действий 
   по гиперссылке "Вернуться в список"
8. По пути /login  можно залогиниться либо зарегистрироваться   

   
# Работа через REST-API

REST API приложение для работы со списком задач,
1. GET taskRest/{id} возвращает задачу по номеру id
2. GET taskRest возвращает список всех задач
3. POST taskRest/toggle/{id} переключает состояние задачи (с "выполнена" на "не выполнена"
   и наоборот), возвращает задачу по номеру id
4. POST taskRest/add добавление задачи, обязательно передать параметр name, возвращает задачу
5. POST taskRest/edit/{id} переименовывает задачи, обязательно передать параметр name, возвращает задачу
6. POST taskRest/delete/{id}  удаление задачи, возвращает пустую строку
7. POST userRest/add добавление пользователя, обязательно передать параметры name и password
пример возвращаемого значения типа "Задача":
   {
      "id": 1,
      "name": "task1",
      "completed": false
   }

логирование
1. программа ведет запись событий в лог файл "log.txt", сохраняемый в каталоге проекта
2. Порт сервера по умолчанию установлен 54322, при необходимости внесите изменения в файле "application.properties"

хранение данных
Задачи хранятся в Базе данных PostgreSQL

Доступ
базовая аутентификация, ограничение доступа на уровне записей на чтение, изменение и удаление данных
изначально есть админ, только с его учетки можно создавать пользователей