package jms.workshop.kakfastream.serdes.convertors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jms.workshop.kakfastream.model.Employee;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class EmployeeDeserializer implements Deserializer<Employee> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Employee deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing...");
            return objectMapper.readValue(new String(data, "UTF-8"), Employee.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to MessageDto");
        }
    }

    @Override
    public void close() {
    }
}