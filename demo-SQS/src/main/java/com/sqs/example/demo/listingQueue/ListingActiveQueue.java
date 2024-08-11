package com.sqs.example.demo.listingQueue;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.util.List;

@Component
public class ListingActiveQueue {

    private final SqsClient sqsClient;

    public ListingActiveQueue(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void listarQueue(){
        List<String> colas = sqsClient.listQueues().queueUrls();
        colas.forEach(System.out::println);
    }




}
