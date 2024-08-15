package com.sqs.example.demo.controller;

import com.sqs.example.demo.InformationDto;
import com.sqs.example.demo.listingQueue.ListingActiveQueue;
import com.sqs.example.demo.publisherQueue.Publisher;
import com.sqs.example.demo.receiveQueue.ReceiveSqsActive;
import jakarta.annotation.Nonnull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SQSController {

    private final ListingActiveQueue listingActiveQueue;
    private final Publisher publisherQueue;
    private final ReceiveSqsActive receiveSqsActive;

    public SQSController(ListingActiveQueue listingActiveQueue, Publisher publisherQueue, ReceiveSqsActive receiveSqsActive) {
        this.listingActiveQueue = listingActiveQueue;
        this.publisherQueue = publisherQueue;
        this.receiveSqsActive = receiveSqsActive;
    }

    @GetMapping("/list")
    public String getQueues() {
        listingActiveQueue.listQueueActives();
        return "hola";
    }

    @PostMapping("/send")
    public ResponseEntity<InformationDto> sendMessage(@RequestBody @Nonnull String messageToQueue) {
        InformationDto informationDto = publisherQueue.sendMessageToQueue(messageToQueue);
        return ResponseEntity.ok(informationDto);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(receiveSqsActive.getMessagesFromQueue());
    }
}
