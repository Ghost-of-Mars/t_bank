Feature: Получение счета v3

  Background: Создаем апи клиент
    * Создаем апи клиента для микросервиса Accounts

  Scenario Outline: Получить информацию по счету. 200 ок
    * Отправить GET запрос для микрсосервиса Accounts на path <url>
    * Проверить, что transitAccount.accountNumber равен <accountNumber>
    * Проверить,что у <number> элемента массива accountNumber равен <accountNumber>, name равен <name>, currency равен <currency>, bankBik равен <bankBik>, accountType равен <accountType>, activationDate равен <activationDate>, balance.otb равен <balanceOtb>, balance.authorized равен <balanceAuthorized>, balance.pendingPayments равен <balancePendingPayments>, balance.pendingRequisitions равен <balancePendingRequisitions>, transitAccount.accountNumber равен <transitAccountAccountNumber>
    * Собрать и вывести информацию об упавших мягких проверках
    Examples:
      | url                | number | accountNumber          | name                     | currency | bankBik     | accountType | activationDate | balanceOtb | balanceAuthorized | balancePendingPayments | balancePendingRequisitions | transitAccountAccountNumber |
      | "v3/bank-accounts" | 0      | "40802678901234567890" | "Валютный фунтовый счет" | "643"    | "123456789" | "Current"   | "2010-08-03"   | 45089      | 0                 | 0                      | 0                          | "11223344556677889900"      |

