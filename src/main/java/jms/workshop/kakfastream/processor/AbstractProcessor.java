package jms.workshop.kakfastream.processor;

import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

public abstract class AbstractProcessor {
    @Value("${custom.kafka.topic1-1}")
    protected String inboundTopic1 = "task-1-1";

    @Value("${custom.kafka.topic1-2}")
    protected String outboundTopic2 = "task-1-2";

    @Value("${custom.kafka.topic1-3}")
    protected String outboundTopic3 = "task-1-3";

    @Value("${custom.kafka.topic1-4}")
    protected String outboundTopic4 = "task-1-4";

    public static boolean isNotEmpty(String key, String value) {
        return key == null && (value != null && !Objects.equals(value, ""));
    }

}
