package br.com.beblue.desafio.config;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class CustomJacksonObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @PostConstruct
    public void afterProps() {
        configure();
    }

    public static ObjectMapper build() {
        return new CustomJacksonObjectMapper().configure();
    }

    private ObjectMapper configure() {
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        // setSerializationInclusion(Include.NON_NULL);
        setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));

        SimpleModule module = new SimpleModule("DateModule");

        this.registerModule(module);
        this.registerModule(
                new JavaTimeModule()
                        .addSerializer(LocalDateTime.class,
                                new LocalDateTimeSerializer(DateTimeFormatter
                                        .ofPattern(DATE_TIME_FORMAT)))
                        .addDeserializer(LocalDateTime.class,
                                new LocalDateTimeDeserializer(DateTimeFormatter
                                        .ofPattern(DATE_TIME_FORMAT)))
                        .addSerializer(LocalDate.class,
                                new LocalDateSerializer(DateTimeFormatter
                                        .ofPattern(DATE_FORMAT)))
                        .addDeserializer(LocalDate.class,
                                new LocalDateDeserializer(DateTimeFormatter
                                        .ofPattern(DATE_FORMAT))));

        this.registerModule(new Jdk8Module());

        return this;
    }

}
