package com.example.springintroexercise.utils.staff;

import com.example.springintroexercise.data.models.service.staff.StaffServiceModel;
import com.example.springintroexercise.enums.Position;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffUtilImpl implements StaffUtil
{

    @Override
    public long getCountOfStaffWithIdenticalData(String firstName, String lastName, Position position, List<StaffServiceModel> staff)
    {
        long count  = 0;

        for (StaffServiceModel person : staff)
        {
            if (areIdentical(firstName, lastName, position, person))
            {
                count++;
            }
        }

        return count;
    }

    @Override
    public boolean areIdentical(String firstName, String lastName, Position position, StaffServiceModel model)
    {
        return model.getFirstName().equals(firstName) &&
                model.getLastName().equals(lastName) &&
                model.getPosition().name().equals(position.name());
    }

    @Override
    public void updateStaffIfNecessary(long count, StaffServiceModel model)
    {
        if (count > 0)
        {
            //TODO
        }
    }
}
