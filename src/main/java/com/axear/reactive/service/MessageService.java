package com.axear.reactive.service;

import com.axear.reactive.form.MessageForm;
import com.axear.reactive.model.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessageService {
    Flux<Message> all();

    Mono<Message> get(Long id);

    Mono<Long> save(MessageForm message);

    Mono<Long> save(Long id, MessageForm message);
}
