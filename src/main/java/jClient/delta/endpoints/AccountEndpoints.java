package jClient.delta.endpoints;

public enum AccountEndpoints
{
    GET_POSITION("private/get_position"),
    GET_POSITIONS("private/get_positions"),
    GET_SUMMARY("private/get_account_summary"),
    GET_ANNOUNCEMENTS("public/get_announcements"),
    CREATE_SUB_ACCOUNT("private/create_subaccount"),
    GET_SUB_ACCOUNTS("private/get_subaccounts"),
    SET_SUB_ACCOUNT_EMAIL("private/set_email_for_subaccount"),
    SET_SUB_ACCOUNT_NAME("/private/change_subaccount_name"),
    SET_SUB_ACCOUNT_PASSWORD("private/set_password_for_subaccount"),
    TOGGLE_SUB_ACCOUNT_LOGIN("/private/toggle_subaccount_login"),
    CREATE_API_KEY("private/create_api_key"),
    LIST_API_KEYS("private/list_api_keys"),
    RESET_API_KEY("private/reset_api_key"),
    REMOVE_API_KEY("private/remove_api_key"),
    SET_API_KEY_AS_DEFAULT("private/set_api_key_as_default");
    
    private final String endpoint;

    AccountEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
