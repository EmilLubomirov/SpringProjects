package com.example.springintroexercise.api.controllers;

import com.example.springintroexercise.data.models.service.staff.StaffServiceModel;
import com.example.springintroexercise.data.services.StaffService;
import com.example.springintroexercise.enums.Position;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;

@Controller
@Transactional
public class RestApiStaffFired
{
    private final StaffService staffService;

    public RestApiStaffFired(StaffService staffService, ModelMapper modelMapper) {
        this.staffService = staffService;
    }


    @RequestMapping("/staff/fired")
    public void staffFired(@RequestParam(name = "staffFirstName") String staffFirstName,
                             @RequestParam(name = "staffLastName") String staffLastName,
                             @RequestParam(name = "staffPosition") String staffPosition)
    {
        StaffServiceModel model = new StaffServiceModel();
        model.setFirstName(staffFirstName);
        model.setLastName(staffLastName);
        model.setPosition(Position.valueOf(staffPosition));

        staffService.remove(model);
    }
}
