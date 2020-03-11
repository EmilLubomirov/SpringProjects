package com.example.springintroexercise.integration.controller.api;

import com.example.springintroexercise.data.entities.Staff;
import com.example.springintroexercise.data.repositories.StaffRepository;
import com.example.springintroexercise.integration.controller.base.ControllerTestBase;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonParser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RestApiStaffControllerTest extends ControllerTestBase
{
    List<Staff> staff;

    Staff person;

    @MockBean
    StaffRepository staffRepository;


    @Override
    protected void beforeEach()
    {
        staff = new ArrayList<>();
        person = new Staff();

        Mockito.when(staffRepository.findAll())
                .thenReturn(staff);
    }

    @Test
    @WithMockUser
    public void staff_onGetCallAndExistingStaff_shouldReturnThemCorrectly() throws Exception
    {
        staff.clear();
        staff.add(person);

         MvcResult response = mockMvc.perform(get("/api-staff"))
                .andExpect(status().isOk())
                 .andReturn();

         String actualResult = response.getResponse().getContentAsString();
         JsonArray jsonValues = JsonParser.parseArray(new StringReader(actualResult));

         assertEquals(staff.size(), jsonValues.size());
    }

    @Test
    @WithMockUser
    public void staff_onGetCallWhenNoExistingStaff_shouldReturnEmptyJSON() throws Exception
    {
        staff.clear();

        MvcResult response = mockMvc.perform(get("/api-staff"))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = response.getResponse().getContentAsString();
        JsonArray jsonValues = JsonParser.parseArray(new StringReader(actualResult));

        assertEquals(staff.size(), jsonValues.size());

    }
}
