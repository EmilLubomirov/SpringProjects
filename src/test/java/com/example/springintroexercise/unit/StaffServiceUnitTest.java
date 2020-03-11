package com.example.springintroexercise.unit;

import com.example.springintroexercise.data.entities.Staff;
import com.example.springintroexercise.data.models.service.staff.StaffServiceModel;
import com.example.springintroexercise.data.repositories.StaffRepository;
import com.example.springintroexercise.data.services.StaffService;
import com.example.springintroexercise.data.services.impl.StaffServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

public class StaffServiceUnitTest
{
    StaffService staffService;

    StaffRepository staffRepository;
    ModelMapper modelMapper;
    List<Staff> staff;
    Staff person;
    StaffServiceModel staffServiceModel;

    @BeforeEach
    public void init()
    {
        person = new Staff();

        staff = new ArrayList<>();
        staffRepository = Mockito.mock(StaffRepository.class);
        modelMapper = new ModelMapper();

        staffService = new StaffServiceImpl(staffRepository, modelMapper);
        Mockito.when(staffRepository.findAll()).thenReturn(staff);
    }

    @Test
    public void getAll_onGetCall_shouldReturnThemCorrectly()
    {
        staff.clear();

        staff.add(person);
        assertEquals(1, staffService.getAll().size());
    }

    @Test
    public void remove_OnStaffCountGreaterThan4_shouldReturnTrue()
    {

        staff.clear();

        Staff person2 = new Staff();
        Staff person3 = new Staff();
        Staff person4 = new Staff();
        Staff person5 = new Staff();

        staff.addAll(List.of(person, person2, person3, person4, person5));
        staffServiceModel = new StaffServiceModel();

        Mockito.when(staffRepository.count())
                .thenReturn((long) staff.size());

        assertTrue(staffService.remove(staffServiceModel));
    }


    @Test
    public void remove_OnStaffCountEquals4_shouldReturnFalse()
    {

        staff.clear();

        Staff person2 = new Staff();
        Staff person3 = new Staff();
        Staff person4 = new Staff();

        staff.addAll(List.of(person, person2, person3, person4));
        staffServiceModel = new StaffServiceModel();

        Mockito.when(staffRepository.count())
                .thenReturn((long) staff.size());

        assertFalse(staffService.remove(staffServiceModel));
    }
}
