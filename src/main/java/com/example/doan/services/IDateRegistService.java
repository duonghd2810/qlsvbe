package com.example.doan.services;

import com.example.doan.dtos.DateRegistDTO;
import com.example.doan.models.DateRegistClassSection;

public interface IDateRegistService {
    String setDateRegist(DateRegistDTO dateRegistDTO);
    DateRegistClassSection getDateRegist();
}
