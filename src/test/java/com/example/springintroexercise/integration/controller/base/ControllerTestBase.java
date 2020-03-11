package com.example.springintroexercise.integration.controller.base;

import com.example.springintroexercise.integration.TestBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public class ControllerTestBase extends TestBase
{
    @Autowired
    protected MockMvc mockMvc;
}
