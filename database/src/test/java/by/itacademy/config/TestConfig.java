package by.itacademy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Yury V. on 22.06.17.
 */

@Configuration
@Import(Config.class)
public class TestConfig {

    @Value("${jdbc.url}")
    private String dbUrl;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

}
