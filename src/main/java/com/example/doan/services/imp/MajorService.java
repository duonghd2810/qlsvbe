package com.example.doan.services.imp;

import com.example.doan.dtos.MajorDTO;
import com.example.doan.exceptions.NotFoundException;
import com.example.doan.models.Major;
import com.example.doan.repositories.MajorRepository;
import com.example.doan.services.IMajorService;
import com.example.doan.utils.ConvertObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MajorService implements IMajorService {
    @Autowired
    private MajorRepository majorRepository;

    @Override
    public Major getMajorById(Long id) {
        Optional<Major> major = majorRepository.findById(id);
        if(major.isEmpty()){
            throw new NotFoundException("Major is not found");
        }
        return major.get();
    }

    @Override
    public Major createMajor(MajorDTO majorDTO) {
        Major major = new Major();
        ConvertObject.convertMajorDTOToMajor(majorDTO,major);
        Major newMajor = majorRepository.save(major);
        return newMajor;
    }

    @Override
    public List<Major> getListMajor() {
        return majorRepository.findAll();
    }

    @Override
    public Major updateMajor(MajorDTO majorDTO, Long id) {
        Optional<Major> major = majorRepository.findById(id);
        if(major.isEmpty()){
            throw new NotFoundException("Major not found");
        }
        ConvertObject.convertMajorDTOToMajor(majorDTO,major.get());
        Major newUMajor = majorRepository.save(major.get());
        return newUMajor;
    }

    @Override
    public String deleteMajor(Long id) {
        Optional<Major> major = majorRepository.findById(id);
        if(major.isEmpty()){
            throw new NotFoundException("Major not found");
        }
        majorRepository.delete(major.get());
        return "Delete success";
    }
}
