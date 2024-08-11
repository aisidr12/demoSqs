package com.sqs.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class SQSConfig {

    @Value("${aws.access.key}")
    private String accessKey;
    @Value("${aws.secret.key}")
    private String secretKey;

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder()
                .region(Region.US_EAST_1)  // Change to your region
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)
                ))
                .build();
    }
}
