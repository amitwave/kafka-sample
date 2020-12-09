package com.wave.kafka.streams.config;

import com.wave.kafka.producer.UserDeserializer;
import com.wave.kafka.producer.UserSerialiser;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.config.StreamsBuilderFactoryBeanCustomizer;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME;

@Configuration
//@EnableKafka
@EnableKafkaStreams
public class KafkaStreamConfig {

    @Autowired
    private KafkaProperties kafkaProperties; // picks up properties from yaml

    @Primary
    //@Bean(name = "defaultKafkaStreams")
    @Bean(name = DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs() {
        Map<String, Object> config = new HashMap<>();
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-stream-default");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        return new KafkaStreamsConfiguration(config);
    }

    // @Primary
    //@Bean(name = "defaultKafkaStreams")
    // @Bean(name = DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs1(@Qualifier("defaultStreamsConfig") Map<String, Object> config) {
        //  Map<String, Object> config = defaultStreamsConfig();
        // config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.serdeFrom(new UserSerialiser(), new UserDeserializer()).getClass());
        return new KafkaStreamsConfiguration(config);
    }

    @Bean("defaultStreamsConfig")
    public Map<String, Object> defaultStreamsConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-stream-default1");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        return config;
    }

    @Bean("userStreamsConfig")
    public Map<String, Object> userStreamsConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-stream-user");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.serdeFrom(new UserSerialiser(), new UserDeserializer()).getClass());
        return config;
    }

    @Bean(name = "customStreamBuilder1")
    public FactoryBean<StreamsBuilder> customStreamBuilder1(
            @Qualifier(DEFAULT_STREAMS_CONFIG_BEAN_NAME)
                    ObjectProvider<KafkaStreamsConfiguration> streamsConfigProvider,
            ObjectProvider<StreamsBuilderFactoryBeanCustomizer> customizerProvider) {
        Map<String, Object> config = new HashMap<>();
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-stream-custom");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.serdeFrom(String.class).getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.serdeFrom(String.class).getClass());


        KafkaStreamsConfiguration streamsConfig = new KafkaStreamsConfiguration(config);
        if (streamsConfig != null) {
            //streamsConfig.asProperties().setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.serdeFrom(new UserSerialiser(), new UserDeserializer()).getClass()));
            StreamsBuilderFactoryBean fb = new StreamsBuilderFactoryBean(streamsConfig);
            StreamsBuilderFactoryBeanCustomizer customizer = customizerProvider.getIfUnique();
            if (customizer != null) {
                customizer.configure(fb);
            }


            return fb;
        } else {
            throw new UnsatisfiedDependencyException(KafkaStreamConfig.class.getName(),
                    "customStreamBuilder", "streamsConfig", "There is no '" +
                    DEFAULT_STREAMS_CONFIG_BEAN_NAME + "' " + KafkaStreamConfig.class.getName() +
                    " bean in the application context.\n" +
                    "Consider declaring one or don't use @EnableKafkaStreams.");
        }
    }

    @Bean(name = "customStreamBuilder")
    public FactoryBean<StreamsBuilder> customStreamBuilder(
            @Qualifier(DEFAULT_STREAMS_CONFIG_BEAN_NAME)
                    ObjectProvider<KafkaStreamsConfiguration> streamsConfigProvider,
            ObjectProvider<StreamsBuilderFactoryBeanCustomizer> customizerProvider) {
        Map<String, Object> config = new HashMap<>();
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-stream-custom");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.serdeFrom(new UserSerialiser(), new UserDeserializer()).getClass());


        KafkaStreamsConfiguration streamsConfig = new KafkaStreamsConfiguration(config);
        if (streamsConfig != null) {
           //streamsConfig.asProperties().setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.serdeFrom(new UserSerialiser(), new UserDeserializer()).getClass()));
            StreamsBuilderFactoryBean fb = new StreamsBuilderFactoryBean(streamsConfig);
            StreamsBuilderFactoryBeanCustomizer customizer = customizerProvider.getIfUnique();
            if (customizer != null) {
                customizer.configure(fb);
            }



            return fb;
        }
        else {
            throw new UnsatisfiedDependencyException(KafkaStreamConfig.class.getName(),
                    "customStreamBuilder", "streamsConfig", "There is no '" +
                    DEFAULT_STREAMS_CONFIG_BEAN_NAME + "' " + KafkaStreamConfig.class.getName() +
                    " bean in the application context.\n" +
                    "Consider declaring one or don't use @EnableKafkaStreams.");
        }
    }


}