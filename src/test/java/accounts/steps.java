package accounts;

import dto.AccountInfo;
import apiClients.ApiClient;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static specs.RequestSpec.reqSpec;
import static specs.ResponseSpec.respSpec200;

public class steps {

    List<AccountInfo> accountInfo = new ArrayList<>();
    SoftAssertions softAssertions = new SoftAssertions();

    @When("Отправить запрос на {string}")
    public void sendRequestToURL(String string) {

        accountInfo = (given(reqSpec)
                .header("Authorization", ApiClient.TOKEN)
                .when()
                .get(string)
                .then()
                .spec(respSpec200)
                .extract().jsonPath().getList("", AccountInfo.class));
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
