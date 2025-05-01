package com.dailycoder.customMessageQueue.service;

import com.dailycoder.customMessageQueue.entities.DeadLetterEntity;
import com.dailycoder.customMessageQueue.entities.MessageEntity;
import com.dailycoder.customMessageQueue.enums.MessageStatus;
import com.dailycoder.customMessageQueue.repository.DeadLetterRepository;
import com.dailycoder.customMessageQueue.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Component
public class MessageQueueService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private DeadLetterRepository deadLetterRepository;
    @Autowired
    private ExecutorService taskExecutor;
    private static final int VISIBILITY_TIMEOUT_SECONDS = 30;
    public void produce(String content){
        MessageEntity message = new MessageEntity();
        message.setContent(content);
        message.setStatus(MessageStatus.PENDING);
        messageRepository.save(message);
    }

    public List<MessageEntity> getMessages(MessageStatus status){
        return messageRepository.findTop10ByStatusOrderByIdAsc(status);
    }

    public List<MessageEntity> getMessages(LocalDateTime visibilityLimit){
        return messageRepository.findPendingOrTimeoutMessages(visibilityLimit);
    }

    @Scheduled(fixedRate = 2000)
    public void consume(){
        LocalDateTime timeoutLimit = LocalDateTime.now().minusSeconds(VISIBILITY_TIMEOUT_SECONDS);

        List<MessageEntity> pendingMessages = getMessages(timeoutLimit);
        for(MessageEntity message: pendingMessages){
            message.setStatus(MessageStatus.PROCESSING);
            message.setPickedAt(LocalDateTime.now());
            messageRepository.save(message);
            taskExecutor.submit(() -> processMessage(message));
        }
    }

    private void processMessage(MessageEntity message){
        System.out.println("Processing Message ID: " + message.getId() + " Content: "+ message.getContent());
        try {
            // simulating delay as in real world there must be some processing time
            Thread.sleep(2000);
            if(simulateRandomFailure()){
                throw new RuntimeException("Simulated Process Failure");
            }
            message.setStatus(MessageStatus.DONE);
            messageRepository.save(message);
            System.out.println("Processing Message ID: " + message.getId() + " Status: "+ MessageStatus.DONE);
        }catch (Exception e){
            handleFailure(message, e);
        }
    }

    private void handleFailure(MessageEntity message, Exception e){
        message.setRetryCount(message.getRetryCount()+1);
        if(message.getRetryCount() >= message.getMaxRetryCount()){
            message.setStatus(MessageStatus.FAILED);
            messageRepository.save(message);
            DeadLetterEntity deadLetterEntity = new DeadLetterEntity();
            deadLetterEntity.setOriginalMessageId(message.getId());
            deadLetterEntity.setContent(message.getContent());
            deadLetterEntity.setFailureReason(e.getMessage());
            deadLetterEntity.setFailedAt(LocalDateTime.now());
            deadLetterRepository.save(deadLetterEntity);
            System.err.println("Message ID: "+ message.getId() + " Permanently Failed after "+ message.getMaxRetryCount() + " retries.");
        }else{
            message.setStatus(MessageStatus.PENDING);
            System.err.println("Retrying Message ID: " + message.getId() + ", Retry #"+ message.getMaxRetryCount());
        }

    }

    private boolean simulateRandomFailure(){
        return Math.random() < 0.3;
    }
}
