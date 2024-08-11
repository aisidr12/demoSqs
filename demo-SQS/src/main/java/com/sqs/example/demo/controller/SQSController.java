package com.sqs.example.demo.controller;

import com.sqs.example.demo.listingQueue.ListingActiveQueue;
import com.sqs.example.demo.publisherQueue.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SQSController {

   private final ListingActiveQueue listingActiveQueue;
   private final Publisher publisherQueue;

    public SQSController(ListingActiveQueue listingActiveQueue, Publisher publisherQueue) {
        this.listingActiveQueue = listingActiveQueue;
        this.publisherQueue = publisherQueue;
    }

    @GetMapping("/listar")
    public String getColas(){
      listingActiveQueue.listarQueue();
      return "hola";
    }
    @GetMapping("/enviar")
    public String sendMessage(){
        String messageToQueue = publisherQueue.sendMessageToQueue("message enviado");
        return messageToQueue;
    }


}
