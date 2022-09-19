package jms.workshop.kakfastream.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
@Log4j2
public class Task1 {

    @KafkaListener(topics = "task-1-1")
    public void listener1(String message) {
        log.info("Message consumed by first outbound channel: {} ", message);
    }

    @KafkaListener(topics = "task-1-2")
    public void listener2(String message) {
        log.info("Message consumed by second outbound channel: {}", message);
    }
}
