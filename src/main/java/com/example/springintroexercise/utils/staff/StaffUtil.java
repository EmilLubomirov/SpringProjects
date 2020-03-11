package com.example.springintroexercise.utils.staff;

import com.example.springintroexercise.data.models.service.staff.StaffServiceModel;
import com.example.springintroexercise.enums.Position;

import java.util.List;

public interface StaffUtil
{
    long getCountOfStaffWithIdenticalData(String firstName, String lastName, Position position, List<StaffServiceModel> models);

    boolean areIdentical(String firstName, String lastName, Position position, StaffServiceModel model);

    void updateStaffIfNecessary(long count, StaffServiceModel model);
}
