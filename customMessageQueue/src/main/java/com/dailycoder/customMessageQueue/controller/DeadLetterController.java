package com.dailycoder.customMessageQueue.controller;

import com.dailycoder.customMessageQueue.entities.DeadLetterEntity;
import com.dailycoder.customMessageQueue.entities.MessageEntity;
import com.dailycoder.customMessageQueue.enums.MessageStatus;
import com.dailycoder.customMessageQueue.repository.DeadLetterRepository;
import com.dailycoder.customMessageQueue.repository.MessageRepository;
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
