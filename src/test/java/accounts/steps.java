package accounts;

import accounts.models.ResponseModelAccountInfo;
import accounts.models.ResponseModelAccountInfo.Balance;
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

    List<ResponseModelAccountInfo> responseModelAccountInfo = new ArrayList<>();
    SoftAssertions softAssertions = new SoftAssertions();

    @When("Отправить запрос на {string}")
    public void sendRequestToURL(String string) {

        responseModelAccountInfo = (given(reqSpec)
                .header("Authorization", ApiClient.TOKEN)
                .when()
                .get(string)
                .then()
                .spec(respSpec200)
                .extract().jsonPath().getList("", ResponseModelAccountInfo.class));
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
        softAssertions.assertThat(responseModelAccountInfo.get(number).getAccountNumber()).isEqualTo(accountNumber);
        softAssertions.assertThat(responseModelAccountInfo.get(number).getName()).isEqualTo(name);
        softAssertions.assertThat(responseModelAccountInfo.get(number).getCurrency()).isEqualTo(currency);
        softAssertions.assertThat(responseModelAccountInfo.get(number).getBankBik()).isEqualTo(bankBik);
        softAssertions.assertThat(responseModelAccountInfo.get(number).getAccountType()).isEqualTo(accountType);
        softAssertions.assertThat(responseModelAccountInfo.get(number).getActivationDate()).isEqualTo(activationDate);
        softAssertions.assertThat(responseModelAccountInfo.get(number).getBalance().getOtb()).isEqualTo(balanceOtb);
        softAssertions.assertThat(responseModelAccountInfo.get(number).getBalance().getAuthorized()).isEqualTo(balanceAuthorized);
        softAssertions.assertThat(responseModelAccountInfo.get(number).getBalance().getPendingPayments()).isEqualTo(balancePendingPayments);
        softAssertions.assertThat(responseModelAccountInfo.get(number).getBalance().getPendingRequisitions()).isEqualTo(balancePendingRequisitions);
        softAssertions.assertThat(responseModelAccountInfo.get(number).getTransitAccount().getAccountNumber()).isEqualTo(transitAccountAccountNumber);
    }

    @And("Собрать и вывести информацию об упавших мягких проверках")
    public void checkAllSoftAssertions() {
        softAssertions.assertAll();
    }
}
