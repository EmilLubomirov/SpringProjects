package com.example.springintroexercise.integration.controller.api;

import com.example.springintroexercise.integration.controller.base.ControllerTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestApiRemoveApplicantControllerTest extends ControllerTestBase
{

    @Test
    @WithMockUser
    public void removeApplicant_onPostCall_shouldRedirectToAppliers() throws Exception
    {
        MvcResult mvcResult = mockMvc.perform(get("/appliers/removed")
                .param("username", "username"))
                .andExpect(status().isOk()).andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();

        assertEquals("redirect:/appliers", actualResult);
    }
}
