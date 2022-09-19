package jms.workshop.kakfastream.processor;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.apache.kafka.streams.StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

public class SecondTaskProcessorTest {

    private SecondTaskProcessor secondTaskProcessor;

    private TestInputTopic<String, String> inputTopic;
    private TestOutputTopic<String, String> outputTopic;
    private static final String inboundTopic1 = "task-1-1";
    private static final String outboundTopic2 = "task-1-2";

    @BeforeEach
    public void init() {
        secondTaskProcessor = new SecondTaskProcessor();
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        Topology topology = secondTaskProcessor.secondPipeline(streamsBuilder).build();

        Properties streamsConfiguration = new Properties();
        streamsConfiguration.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        streamsConfiguration.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        TopologyTestDriver topologyTestDriver = new TopologyTestDriver(topology, streamsConfiguration);

        inputTopic = topologyTestDriver
                .createInputTopic(inboundTopic1, new StringSerializer(), new StringSerializer());

        outputTopic = topologyTestDriver
                .createOutputTopic(outboundTopic2, new StringDeserializer(), new StringDeserializer());
    }

    @Test
    void testPaymentTopology() {

        String message = "Message_Text_";
        String key1 = "1";
        inputTopic.pipeInput(key1, message + key1);
        System.out.println(outputTopic);
        assertThat(outputTopic.readKeyValuesToList(),
                hasItems(
                        KeyValue.pair(key1, message + key1 + "_edited")
                ));

    }


}
