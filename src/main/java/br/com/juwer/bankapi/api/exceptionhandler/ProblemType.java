package br.com.juwer.bankapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    INVALID_DATA("/invalid-data", "Invalid data"),
    INCOMPREHENSIBLE_MESSAGE("/incomprihencible-message", "Incomprehensible message"),
    RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found"),
    BUSINESS_ERROR("/business-error", "Violation of business logic"),
    SYSTEM_ERROR("/system-error", "System error"),
    INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
    ACCESS_DENIED("/access-denied", "Access denied"),
    TOKEN_EXPIRED("/token-expired", "Token expired");

    private final String url;
    private final String title;

    ProblemType(String uri, String title) {
        this.url = "http://localhost:8080" + uri;
        this.title = title;
    }
}
