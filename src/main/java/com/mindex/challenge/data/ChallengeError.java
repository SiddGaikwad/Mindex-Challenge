package com.mindex.challenge.data;

public class ChallengeError {
    // This class is for handling exceptions
    private int errorCode;
    private String errorMessage;

    public ChallengeError(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ChallengeError() {
    }
}
