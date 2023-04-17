package com.example.springcloudcontract1888;

import com.example.springcloudcontract1888.SpringCloudContract1888Application.ExampleController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;

class ContractTestBase {

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.standaloneSetup(new ExampleController());
    }

}
