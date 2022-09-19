package jms.workshop.kakfastream.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
@Log4j2
public class Task4 {

    @KafkaListener(topics = "task-1-4")
    public void listener4(String message) {

        log.info("Message consumed by task-1-4 outbound channel: {}", message);
    }
}
