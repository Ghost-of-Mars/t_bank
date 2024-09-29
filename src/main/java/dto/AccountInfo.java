package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountInfo {
    private String accountNumber;
    private String name;
    private String currency;
    private String bankBik;
    private String accountType;
    private String activationDate;
    private Balance balance;
    private TransitAccount transitAccount;

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Balance {
        private Integer otb;
        private Integer authorized;
        private Integer pendingPayments;
        private Integer pendingRequisitions;
    }

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TransitAccount {
        private String accountNumber;
    }
}
