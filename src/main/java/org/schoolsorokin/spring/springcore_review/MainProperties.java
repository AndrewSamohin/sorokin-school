package org.schoolsorokin.spring.springcore_review;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Scanner;

@Configuration
@PropertySource("classpath:application.yaml")
public class MainProperties {

    @Bean
    public Scanner sc() {
        return new Scanner(System.in);
    }

}
