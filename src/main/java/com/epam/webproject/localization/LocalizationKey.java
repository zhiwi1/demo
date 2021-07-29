package com.epam.webproject.localization;

public enum LocalizationKey {
    LOGIN_ERROR_MESSAGE("login.error"),
    TEXT_ADMIN_USERS_REGISTRATION_DATE("admin.users_list_registration_date"),
    TEXT_ADMIN_USERS_GROUP("admin.users_list_group"),
    TEXT_ADMIN_USERS_STATUS("admin.users_list_status"),
    TEXT_ADMIN_USERS_ACTIONS("admin.users_list_actions"),
    TEXT_ADMIN_USERS_BUTTON_BAN("admin.users_list_button_ban_text"),
    TEXT_ADMIN_USERS_BUTTON_UNBAN("admin.users_list_button_unban_text"),
    TEXT_ADMIN_USERS_SUCCESS_BAN_MESSAGE("admin.users_list_ban_success_message"),
    TEXT_ADMIN_USERS_SUCCESS_UNBAN_MESSAGE("admin.users_list_unban_success_message"),
    TEXT_ADMIN_USERS_ERROR_BAN_MESSAGE("admin.users_list_ban_error_message"),
    TEXT_ADMIN_USERS_ERROR_UNBAN_MESSAGE("admin.users_list_unban_error_message");
    private final String key;

    LocalizationKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.key;
    }
}

