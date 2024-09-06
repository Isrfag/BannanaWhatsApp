package com.banana.bananawhatsapp.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"com.banana.bananawhatsapp.persistencia","com.banana.bananawhatsapp.servicios", "com.banana.bananawhatsapp.controladores"})
@EntityScan("com.banana.bananawhatsapp.modelos")
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.banana.bananawhatsapp.persistencia")
public class SpringConfig {


}