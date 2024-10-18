package com.example.triodb;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@SpringBootApplication
@RestController
public class TrioDbApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(TrioDbApplication.class, args);
        Thread th = new Thread() {
            @Override
            public void run() {
                super.run();
                while (true){
//                    try {
//                        ProcessBuilder pb = new ProcessBuilder("root/pgsql-backup.sh");
//                        Process p = pb.start();
                        System.out.println("Резервная копия сохранена!");
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
                    try {
                        Thread.sleep(1000 * 60 * 60 * 24);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        th.start();
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}



