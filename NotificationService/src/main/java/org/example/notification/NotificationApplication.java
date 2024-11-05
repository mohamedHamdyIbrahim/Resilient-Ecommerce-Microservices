package org.example.notification;
import org.example.amqp.RabbitMQMessageProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication(
        scanBasePackages = {
                "org.example.notification",
                "org.example.amqp",
        }
)
@EntityScan(basePackages = {"entities"})
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
}
