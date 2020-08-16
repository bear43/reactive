package com.axear.reactive.controller.message;

import com.axear.reactive.form.MessageForm;
import com.axear.reactive.model.Message;
import com.axear.reactive.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/all")
    public Flux<Message> all() {
        return messageService.all();
    }

    @GetMapping("/{id}")
    public Mono<Message> get(@PathVariable Long id) {
        return messageService.get(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Long> postSave(@RequestBody MessageForm message) {
        return messageService.save(message);
    }

    @PutMapping("/{id}")
    public Mono<Long> putSave(@PathVariable Long id, @RequestBody MessageForm message) {
        return messageService.save(id, message);
    }
}
