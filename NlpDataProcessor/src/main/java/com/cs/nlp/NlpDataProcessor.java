package com.cs.nlp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class NlpDataProcessor {

    public static void main(String[] args) {
            SpringApplication.run(NlpDataProcessor.class, args);
        }
}
