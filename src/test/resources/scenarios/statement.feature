Feature: Получение выписки по счету
  Scenario: Получить выписку по счету. 200 ок
    When Отправить запрос на "https://reqres.in/api/users?page=2" и сохранить юзера как обьект
    Then Проверить, что Id равен 7
    And Проверить, что Email равен "michael.lawson@reqres.in"
    And Проверить, что FirstName равен "Michae"
    And Проверить, что LastName равен "Lawso"
    And Проверить, что Avatar равен "https://reqres.in/img/faces/7-image.jpg"
    And Собрать и вывести информацию об упавших мягких проверках