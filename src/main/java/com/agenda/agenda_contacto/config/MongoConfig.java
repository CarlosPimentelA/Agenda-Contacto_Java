package com.agenda.agenda_contacto.config;

import com.mongodb.MongoClientSettings;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
public class MongoConfig {

    @Bean
    public CodecRegistry customCodecRegistry() {
        // Tu lógica de PojoCodecProvider migrada a un Bean de Spring
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();

        return fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(pojoCodecProvider));
    }
}