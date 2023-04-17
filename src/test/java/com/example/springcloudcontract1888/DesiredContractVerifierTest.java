package com.example.springcloudcontract1888;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.fileToBytes;

import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import org.junit.jupiter.api.Test;

class DesiredContractVerifierTest extends ContractTestBase {

    @Test
    public void validate_example() throws Exception {
        // given:
        MockMvcRequestSpecification request = given()
            .header("Content-Type", "multipart/form-data;boundary=9eae5c22-031b-414f-a328-252a528a93e9")
            .multiPart("file", "file.txt", fileToBytes(this, "example_request_file.txt"), "text/plain")
            .multiPart("metadata", "{\"value\": \"example\"}", "application/json");


        // when:
        ResponseOptions response = given().spec(request)
            .post("/example");

        // then:
        assertThat(response.statusCode()).isEqualTo(204);
    }

}
