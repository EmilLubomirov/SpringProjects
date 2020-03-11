package com.example.springintroexercise.data.services.impl;

import com.example.springintroexercise.data.entities.Staff;
import com.example.springintroexercise.data.models.service.staff.StaffServiceModel;
import com.example.springintroexercise.data.repositories.StaffRepository;
import com.example.springintroexercise.data.services.StaffService;
import com.example.springintroexercise.enums.Position;
import com.example.springintroexercise.utils.staff.StaffUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService
{
    private final StaffRepository staffRepository;
    private final ModelMapper modelMapper;
    //private final StaffUtil staffUtil;

    public StaffServiceImpl(StaffRepository staffRepository, ModelMapper modelMapper) {
        this.staffRepository = staffRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StaffServiceModel addStaff(StaffServiceModel model)
    {
        Staff staff = staffRepository.saveAndFlush(modelMapper.map(model, Staff.class));

//        long identicalStaffCount = staffUtil.getCountOfStaffWithIdenticalData(model.getFirstName(),
//                                    model.getLastName(), model.getPosition(), getAll());
//
//        staffUtil.updateStaffIfNecessary(identicalStaffCount, model);

        return modelMapper.map(staff, StaffServiceModel.class);
    }

    @Override
    public List<StaffServiceModel> getAll()
    {
        return staffRepository.findAll()
                .stream()
                .map(s -> modelMapper.map(s, StaffServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean remove(StaffServiceModel model)
    {
        if (staffRepository.count() > 4)
        {
            staffRepository.removeByFirstNameAndLastNameAndPosition(model.getFirstName(),
                    model.getLastName(),
                    model.getPosition());

            return true;
        }

        return false;
    }

    @Override
    public void seedStaff()
    {
        Staff person1 = new Staff();
        person1.setFirstName("Yordan");
        person1.setLastName("Ivanov");
        person1.setPosition(Position.ACCOUNTANT);


        Staff person2 = new Staff();
        person2.setFirstName("Borislav");
        person2.setLastName("Tsvetkov");
        person2.setPosition(Position.SECURITY);


        Staff person3 = new Staff();
        person3.setFirstName("Silvia");
        person3.setLastName("Petrova");
        person3.setPosition(Position.CLEANER);

        Staff person4 = new Staff();
        person4.setFirstName("Elena");
        person4.setLastName("Simeonova");
        person4.setPosition(Position.COOK);

        staffRepository.saveAndFlush(person1);
        staffRepository.saveAndFlush(person2);
        staffRepository.saveAndFlush(person3);
        staffRepository.saveAndFlush(person4);
    }

    @Override
    public long count()
    {
        return staffRepository.count();
    }
}
