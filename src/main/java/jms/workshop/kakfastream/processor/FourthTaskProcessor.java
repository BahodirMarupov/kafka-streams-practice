package jms.workshop.kakfastream.processor;

import jms.workshop.kakfastream.serdes.StreamConst;
import jms.workshop.kakfastream.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class FourthTaskProcessor extends AbstractProcessor {

//    @Autowired
    public void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, Employee> stream = streamsBuilder.stream(inboundTopic1,
                Consumed.with(StreamConst.STRING_SERDE, StreamConst.EMPLOYEE_SERDE()));
        stream = stream.filter((k, v) -> v != null);
        stream.to(outboundTopic4, Produced.with(StreamConst.STRING_SERDE, StreamConst.EMPLOYEE_SERDE()));
    }

}
