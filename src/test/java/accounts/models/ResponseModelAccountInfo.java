package accounts.models;

import lombok.Data;



@Data
public class ResponseModelAccountInfo {
    private String accountNumber;
    private String name;
    private String currency;
    private String bankBik;
    private String accountType;
    private String activationDate;
    private Balance balance;
    private TransitAccount transitAccount;

    @Data
    public static class Balance {
        private Integer otb;
        private Integer authorized;
        private Integer pendingPayments;
        private Integer pendingRequisitions;
    }

    @Data
    public static class TransitAccount {
        private String accountNumber;
    }
}
