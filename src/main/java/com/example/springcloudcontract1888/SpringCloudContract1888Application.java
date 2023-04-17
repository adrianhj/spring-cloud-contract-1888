package com.example.springcloudcontract1888;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
public class SpringCloudContract1888Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudContract1888Application.class, args);
    }

    @RestController
    public static class ExampleController {

        record Metadata(String value) {}

        @PostMapping("/example")
        public ResponseEntity<Void> example(
            @RequestPart(name = "file") MultipartFile file,
            @RequestPart(name = "metadata") Metadata metadata
        ) {
            return ResponseEntity.noContent().build();
        }

    }

}
