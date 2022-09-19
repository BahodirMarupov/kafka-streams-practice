package jms.workshop.kakfastream.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Producer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    public String sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
        return "Ok";
    }
}
