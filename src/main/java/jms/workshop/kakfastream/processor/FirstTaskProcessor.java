package jms.workshop.kakfastream.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.stereotype.Component;

import static jms.workshop.kakfastream.serdes.StreamConst.STRING_SERDE;

@Component
@Slf4j
@RequiredArgsConstructor
public class FirstTaskProcessor extends AbstractProcessor {

//    @Autowired
    public void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, String> stream = streamsBuilder.stream(inboundTopic1);
        stream.to(outboundTopic2, Produced.with(STRING_SERDE, STRING_SERDE));
//        stream.to(outboundTopic3, Produced.with(STRING_SERDE, STRING_SERDE));
    }
}
