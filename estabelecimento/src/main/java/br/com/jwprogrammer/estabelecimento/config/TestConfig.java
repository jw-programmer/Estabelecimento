package br.com.jwprogrammer.estabelecimento.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.jwprogrammer.estabelecimento.services.DbService;

@Configuration
@Profile("test")
public class TestConfig {
    @Autowired
    private DbService db;

    @Bean
    public boolean instanceTestDatabase() {
        db.instanceDB();
        return true;
    }
}
