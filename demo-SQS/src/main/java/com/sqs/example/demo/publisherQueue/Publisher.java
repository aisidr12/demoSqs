package com.sqs.example.demo.publisherQueue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@Component
public class Publisher {

    private final SqsClient sqsClient;
    @Value("${aws.queue.name}")
    private String nameQueue;


    public Publisher(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public String sendMessageToQueue(String message){
        String url = getUrl();
        System.out.println(getNameQueueSolid(url));
        SendMessageResponse sendMessageResponse = sqsClient.sendMessage(SendMessageRequest.builder().messageBody(message).queueUrl(nameQueue).build());
      return   sendMessageResponse.toString();
    }

    public String getNameQueueSolid(String url){
        String[] nombreQueue = url.split("/");
        return nombreQueue[nombreQueue.length-1];
    }

    private String getUrl() {
        GetQueueUrlResponse getQueueUrlResponse = sqsClient
                .getQueueUrl(GetQueueUrlRequest.builder().queueName(nameQueue).build());
        return getQueueUrlResponse.queueUrl();
    }
}
