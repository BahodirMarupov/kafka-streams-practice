package jms.workshop.kakfastream.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

import static jms.workshop.kakfastream.serdes.StreamConst.STRING_SERDE;

@Component
@Slf4j
@RequiredArgsConstructor
public class SecondTaskProcessor extends AbstractProcessor {


    @Autowired
    public StreamsBuilder secondPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, String> stream = streamsBuilder.stream(inboundTopic1);

        stream = stream.filter(AbstractProcessor::isNotEmpty)
                .flatMap((key, value) -> Arrays.stream(value.split(" ")).map(item ->
                        new KeyValue<>(String.valueOf(item.length()), item + "_edited")
                ).collect(Collectors.toList()));

        stream.to(outboundTopic2, Produced.with(STRING_SERDE, STRING_SERDE));
        return streamsBuilder;

    }
}
