package deribit.client.endpoints;

public enum WalletEndpoints {
    CANCEL_TRANSFER_BY_ID("private/cancel_transfer_by_id"),
    CANCEL_WITHDRAWAL("private/cancel_withdrawal"),
    CREATE_DEPOSIT_ADDRESS("private/create_deposit_address"),
    GET_CURRENT_DEPOSIT_ADDRESS("private/get_current_deposit_address"),
    GET_DEPOSITS("private/get_deposits"),
    GET_TRANSFERS("private/get_transfers"),
    GET_WITHDRAWALS("private/get_withdrawals"),
    SUBMIT_TRANSFER_TO_SUBACCOUNT("private/submit_transfer_to_subaccount"),
    SUBMIT_TRANSFER_TO_USER("private/submit_transfer_to_user"),
    WITHDRAW("private/withdraw");


    private final String endpoint;

    WalletEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
