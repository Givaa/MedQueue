package classes.MedQueue;

import classes.model.DriverManagerConnectionPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@RestController
public class MedQueueApplication {

    public static void main(String[] args) { SpringApplication.run(MedQueueApplication.class, args); }



}