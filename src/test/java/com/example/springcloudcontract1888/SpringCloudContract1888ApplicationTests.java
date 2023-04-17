package com.example.springcloudcontract1888;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springcloudcontract1888.SpringCloudContract1888Application.ExampleController.Metadata;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SpringCloudContract1888ApplicationTests {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    TestRestTemplate template;

    @Test
    @DisplayName("Should successfully invoke the /example endpoint")
    void test() {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        body.add("file", new ClassPathResource("contracts/file.txt"));
        body.add("metadata", new Metadata("example"));

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        var result = template.exchange("/example", HttpMethod.POST, new HttpEntity<>(body, headers), Void.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}
