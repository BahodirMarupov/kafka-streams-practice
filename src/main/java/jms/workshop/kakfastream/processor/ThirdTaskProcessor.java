package jms.workshop.kakfastream.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static jms.workshop.kakfastream.serdes.StreamConst.STRING_SERDE;

@Component
@Slf4j
@RequiredArgsConstructor
public class ThirdTaskProcessor extends AbstractProcessor {
    @Value("${custom.kafka.topic1-3}")
    private String inboundTopic2;

    @Autowired
    public void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, String> stream1 = streamsBuilder.stream(inboundTopic1);
        KStream<String, String> stream2 = streamsBuilder.stream(inboundTopic2);
        stream1 = stream1.filter(AbstractProcessor::isNotEmpty).map(ThirdTaskProcessor::map);
        stream2 = stream2.filter(AbstractProcessor::isNotEmpty).map(ThirdTaskProcessor::map);
        ValueJoiner<String, String, String> innerJoin = (l, r) -> {
            log.info("left stream: {}", l);
            log.info("right stream: {}", r);
            return "left:  " + l + ", right: " + r;
        };
        KStream<String, String> joinedStream = stream1.join(stream2, innerJoin, JoinWindows.ofTimeDifferenceAndGrace(Duration.ofSeconds(60), Duration.ofSeconds(30)));
        joinedStream.to(outboundTopic2, Produced.with(STRING_SERDE, STRING_SERDE));

    }

    private static KeyValue<? extends String, ? extends String> map(String k, String v) {
        String[] split = v.split(":");
        if (split.length == 0)
            return new KeyValue<>("1", "nothing");
        if (split.length == 1)
            return new KeyValue<>(split[0], "nothing");
        return new KeyValue<>(split[0], split[1]);
    }

}

