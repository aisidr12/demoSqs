package com.sqs.example.demo.controller;

import com.sqs.example.demo.listingQueue.ListingActviveQueue;
import com.sqs.example.demo.publisherQueue.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SQSController {

   private final ListingActviveQueue listingActviveQueue;
   private final Publisher publisherQueue;

    public SQSController(ListingActviveQueue listingActviveQueue, Publisher publisherQueue) {
        this.listingActviveQueue = listingActviveQueue;
        this.publisherQueue = publisherQueue;
    }

    @GetMapping("/listar")
    public String getColas(){
      listingActviveQueue.listarQueue();
      return "hola";
    }
    @GetMapping("/enviar")
    public String sendMessage(){
        String messageToQueue = publisherQueue.sendMessageToQueue("message enviado");
        return messageToQueue;
    }


}
