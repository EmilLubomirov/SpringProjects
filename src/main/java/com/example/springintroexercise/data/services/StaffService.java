package com.example.springintroexercise.data.services;

import com.example.springintroexercise.data.models.service.staff.StaffServiceModel;

import java.util.List;

public interface StaffService
{
    StaffServiceModel addStaff(StaffServiceModel model);

    List<StaffServiceModel> getAll();

    boolean remove(StaffServiceModel model);

    void seedStaff();

    long count();
}
