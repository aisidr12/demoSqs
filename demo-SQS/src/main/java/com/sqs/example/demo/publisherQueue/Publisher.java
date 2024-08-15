package com.sqs.example.demo.publisherQueue;

import com.sqs.example.demo.InformationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.time.LocalDateTime;

@Component
public class Publisher {

    private final SqsClient sqsClient;
    @Value("${aws.sqs.url}")
    private String urlQueue;


    public Publisher(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public InformationDto sendMessageToQueue(String message) {
        try {
            SendMessageResponse messageResponse = sqsClient.sendMessage(SendMessageRequest.builder().messageBody(message).queueUrl(urlQueue).build());
            return new InformationDto(messageResponse.messageId(), LocalDateTime.now());
        }catch (Exception e){
            return new InformationDto(e.getMessage(),LocalDateTime.now());
        }

    }
}
