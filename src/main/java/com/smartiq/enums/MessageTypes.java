package com.smartiq.enums;

public enum MessageTypes {

    SUCCESS("0000", "İşlem başarılı"),
    API_KEY_HEADER_NOT_FOUND("0001","Api header bilgileri eksik!"),
    WRONG_USERNAME_PASSWORD("0002", "Api kullanıcı bilgileri yanlış!"),
    NOT_FOUND("0003", "Kayıt bulunamadı");

    private String code;

    private String message;

    MessageTypes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
