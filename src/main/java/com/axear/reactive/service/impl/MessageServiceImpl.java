package com.axear.reactive.service.impl;

import com.axear.reactive.form.MessageForm;
import com.axear.reactive.model.Message;
import com.axear.reactive.repository.MessageRepository;
import com.axear.reactive.service.MessageService;
import com.axear.reactive.util.ReactorUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@DependsOn("messageRepository")
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Flux<Message> all() {
        return messageRepository.findAll();
    }

    @Override
    public Mono<Message> get(Long id) {
        return ReactorUtils.errorIfEmpty(messageRepository.findById(id), () -> new RuntimeException("There is no message with ID " + id));
    }

    @Override
    public Mono<Long> save(MessageForm message) {
        Message msg = new Message();
        msg.setString(message.getString());
        return messageRepository.save(msg).flatMap(savedMessage -> Mono.just(savedMessage.getId()));
    }

    @Override
    public Mono<Long> save(Long id, MessageForm message) {
        Message msg = new Message();
        msg.setId(id);
        msg.setString(message.getString());
        return messageRepository.save(msg).flatMap(savedMessage -> Mono.just(savedMessage.getId()));
    }
}
