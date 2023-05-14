package com.example.doan.services.imp;

import com.example.doan.dtos.DateRegistDTO;
import com.example.doan.models.DateRegistClassSection;
import com.example.doan.repositories.DateRegistRepository;
import com.example.doan.services.IDateRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DateRegistServiceImpl implements IDateRegistService {
    @Autowired
    private DateRegistRepository dateRegistRepository;

    @Override
    public String setDateRegist(DateRegistDTO dateRegistDTO) {
        List<DateRegistClassSection> listClass =  dateRegistRepository.findAll();
        if(listClass.size() == 0){
            DateRegistClassSection newDateRegist = new DateRegistClassSection();
            newDateRegist.setStartDate(dateRegistDTO.getStartDate());
            newDateRegist.setEndDate(dateRegistDTO.getEndDate());
            dateRegistRepository.save(newDateRegist);
        }else {
            DateRegistClassSection dateRegist = listClass.get(0);
            dateRegist.setStartDate(dateRegistDTO.getStartDate());
            dateRegist.setEndDate(dateRegistDTO.getEndDate());
            dateRegistRepository.save(dateRegist);
        }
        return "Success";
    }

    @Override
    public DateRegistClassSection getDateRegist() {
        List<DateRegistClassSection> listClass =  dateRegistRepository.findAll();
        return listClass.get(0);
    }
}
