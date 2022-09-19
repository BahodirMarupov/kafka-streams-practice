package jms.workshop.kakfastream.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jms.workshop.kakfastream.model.Employee;
import jms.workshop.kakfastream.producer.Producer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;
@RequiredArgsConstructor
@RestController
public class MessageController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Producer producer;

    @GetMapping
    public String send() throws JsonProcessingException {
        Employee employee = Employee.builder()
                .id(1L)
                .name("Jack")
                .company("EPAM")
                .position("Solution Architect")
                .experience(10L).build();
        String message = objectMapper.writeValueAsString(employee);
        producer.sendMessage("task-1-4", message);
        return "Ok";
    }
}
