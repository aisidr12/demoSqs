package com.sqs.example.demo.receiveQueue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

import java.util.List;

@Component
public class ReceiveSqsActive {
    private final SqsClient sqsClient;
    @Value("${aws.sqs.url}")
    private String nameQueue;

    public ReceiveSqsActive(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public List<String> getMessagesFromQueue() {
        try {
            ReceiveMessageResponse messageResponse = sqsClient.receiveMessage(ReceiveMessageRequest.builder()
                    .queueUrl(nameQueue)
                    .maxNumberOfMessages(10)
                    .build());

            return messageResponse.messages().isEmpty() ? List.of() : messageResponse.messages().stream().map(Message::body).toList();
        } catch (Exception e) {

            return List.of();
        }
    }
}