package com.luifrangm.lambda.utils;


import software.amazon.awssdk.auth.credentials.*;

public class AwsUtils {

    private static final String ACCES_KEY = "XXXXXXXX";
    private static final String SECRET_KEY = "XXXXXXXXXXXXXXX";

    private static AwsCredentials getAwsCredentials() {
        return AwsBasicCredentials.create(ACCES_KEY, SECRET_KEY);
    }

    public static AwsCredentialsProvider getAwsCredentialsProvider() {
        return
            StaticCredentialsProvider.create(getAwsCredentials());
    }

}
