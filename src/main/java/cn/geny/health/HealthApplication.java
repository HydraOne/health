package cn.geny.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author hydraone
 */
@SpringBootApplication
@EnableCaching
public class HealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthApplication.class, args);
    }

}