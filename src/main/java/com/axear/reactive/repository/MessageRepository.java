package com.axear.reactive.repository;

import com.axear.reactive.model.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {

}
