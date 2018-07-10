package com.charles.hikari;

import com.charles.hikari.dao.StudentRepository;
import com.charles.hikari.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.util.List;

import static java.lang.System.exit;

@SpringBootApplication
public class HikariApplication implements CommandLineRunner {


    @Autowired
    DataSource dataSource;

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(HikariApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("DATASOURCE = " + dataSource);

        // If you want to check the HikariDataSource settings
        //HikariDataSource newds = (HikariDataSource)dataSource;
        //System.out.println("DATASOURCE = " + newds.getMaximumPoolSize());

        if (args.length <= 0) {
            System.err.println("[Usage] java xxx.jar {insert name email | display}");
        } else {
            if (args[0].equalsIgnoreCase("insert")) {
                System.out.println("Add student...");
                String name = args[1];
                String email = args[2];
                studentRepository.addStudent(name, email);
            }

            if (args[0].equalsIgnoreCase("display")) {
                System.out.println("Display all student...");
                List<Student> list = studentRepository.findAll();
                list.forEach(x -> System.out.println(x));
            }
            System.out.println("Done!");
        }
        exit(0);
    }
}
