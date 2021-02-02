package classes.MedQueue;

import classes.controller.LogInController;
import classes.model.DriverManagerConnectionPool;
import config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
@Import(WebSecurityConfig.class)
@ComponentScan(basePackages="classes.controller")
@RestController
public class MedQueueApplication {

    public static void main(String[] args) { SpringApplication.run(MedQueueApplication.class, args); }



}