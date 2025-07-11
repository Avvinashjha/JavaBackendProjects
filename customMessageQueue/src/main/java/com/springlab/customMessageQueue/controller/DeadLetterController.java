package com.springlab.customMessageQueue.controller;

import com.springlab.customMessageQueue.entities.DeadLetterEntity;
import com.springlab.customMessageQueue.entities.MessageEntity;
import com.springlab.customMessageQueue.enums.MessageStatus;
import com.springlab.customMessageQueue.repository.DeadLetterRepository;
import com.springlab.customMessageQueue.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customQueue/deadLetters")
public class DeadLetterController {
    @Autowired
    private DeadLetterRepository deadLetterRepository;
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public List<DeadLetterEntity> getAllDeadLetters(){
        return deadLetterRepository.findAll();
    }

    @PostMapping("/reprocess/{deadLetterId}")
    public String reprocessDeadLetter(@PathVariable Long deadLetterId){
        DeadLetterEntity deadLetter = deadLetterRepository.findById(deadLetterId)
                        .orElseThrow(() -> new RuntimeException("Dead Letter Not found"));
        MessageEntity newMessage = new MessageEntity();
        newMessage.setStatus(MessageStatus.PENDING);
        newMessage.setContent(deadLetter.getContent());
        newMessage.setRetryCount(0);
        newMessage.setMaxRetryCount(3);
        messageRepository.save(newMessage);
        deadLetterRepository.delete(deadLetter);
        return "Dead Letter reprocessed and moved back to queue as message Id" + newMessage.getId();
    }
}
