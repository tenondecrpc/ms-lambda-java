package com.luifrangm.lambda.utils;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class DynamoDbUtils {

    private static DynamoDbClient getDynamoDbClient() {
        return
            DynamoDbClient.builder()
                .region(Region.US_EAST_2)
                .credentialsProvider(AwsUtils.getAwsCredentialsProvider())
                .build();
    }
    public static DynamoDbEnhancedClient getDynamoDbEnhancedClient () {
        return
            DynamoDbEnhancedClient.builder()
                .dynamoDbClient(getDynamoDbClient())
                .build();
    }

}
