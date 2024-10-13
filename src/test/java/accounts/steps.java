package accounts;

import clients.ApiClientAccount;
import dto.AccountInfo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;

import java.util.ArrayList;
import java.util.List;

import static utils.ApiClientBase.getResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class steps {

    Response response;

    private ApiClientAccount apiClientAccount;

    List<AccountInfo> accountInfo = new ArrayList<>();
    SoftAssertions softAssertions = new SoftAssertions();

    //токен и запрашивается в рамках конструктора ApiClientAccount
    @Given("Создаем апи клиента для микросервиса Accounts")
    public void createApiClientAccount() {
        apiClientAccount = new ApiClientAccount();
    }

    // Метод получения токена через отдельный метод.
    // Сейчас юзается статичный токен и запрашивается в рамках конструктора ApiClientAccount
//    @Given("Создаем апи клиента для микросервиса Accounts")
//    public void createApiClientAccountAndGetToken() {
//        apiClientAccount = new ApiClientAccount();
//        apiClientAccount.authenticate();
//
//    }

    @When("Отправить GET запрос для микрсосервиса Accounts на path {string}")
    public void getRequest(String path) {
        //TODO реализовать сторэдж
        //path = getCheckStorage(path);
        apiClientAccount.get(path);
    }

    @Then("Проверить, что transitAccount.accountNumber равен {string}")
    public void accountNumberEquals(String accountNumber) {
        assertEquals(accountNumber, getResponse().getBody().jsonPath().getList("", AccountInfo.class).get(0).getAccountNumber());
    }

    @Then("Проверить,что у {int} элемента массива" +
            " accountNumber равен {string}," +
            " name равен {string}," +
            " currency равен {string}," +
            " bankBik равен {string}," +
            " accountType равен {string}," +
            " activationDate равен {string}," +
            " balance.otb равен {int}," +
            " balance.authorized равен {int}," +
            " balance.pendingPayments равен {int}," +
            " balance.pendingRequisitions равен {int}," +
            " transitAccount.accountNumber равен {string}")
    public void accountNumberEquals(int number,
                                    String accountNumber,
                                    String name,
                                    String currency,
                                    String bankBik,
                                    String accountType,
                                    String activationDate,
                                    int balanceOtb,
                                    int balanceAuthorized,
                                    int balancePendingPayments,
                                    int balancePendingRequisitions,
                                    String transitAccountAccountNumber) {
        softAssertions.assertThat(accountInfo.get(number).getAccountNumber()).isEqualTo(accountNumber);
        softAssertions.assertThat(accountInfo.get(number).getName()).isEqualTo(name);
        softAssertions.assertThat(accountInfo.get(number).getCurrency()).isEqualTo(currency);
        softAssertions.assertThat(accountInfo.get(number).getBankBik()).isEqualTo(bankBik);
        softAssertions.assertThat(accountInfo.get(number).getAccountType()).isEqualTo(accountType);
        softAssertions.assertThat(accountInfo.get(number).getActivationDate()).isEqualTo(activationDate);
        softAssertions.assertThat(accountInfo.get(number).getBalance().getOtb()).isEqualTo(balanceOtb);
        softAssertions.assertThat(accountInfo.get(number).getBalance().getAuthorized()).isEqualTo(balanceAuthorized);
        softAssertions.assertThat(accountInfo.get(number).getBalance().getPendingPayments()).isEqualTo(balancePendingPayments);
        softAssertions.assertThat(accountInfo.get(number).getBalance().getPendingRequisitions()).isEqualTo(balancePendingRequisitions);
        softAssertions.assertThat(accountInfo.get(number).getTransitAccount().getAccountNumber()).isEqualTo(transitAccountAccountNumber);
    }

    @And("Собрать и вывести информацию об упавших мягких проверках")
    public void checkAllSoftAssertions() {
        softAssertions.assertAll();
    }
}
