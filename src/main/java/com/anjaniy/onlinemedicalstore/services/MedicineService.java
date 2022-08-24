package com.anjaniy.onlinemedicalstore.services;
import com.anjaniy.onlinemedicalstore.dto.MedicineDto;
import com.anjaniy.onlinemedicalstore.models.Medicine;
import com.anjaniy.onlinemedicalstore.repositories.MedicineRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<MedicineDto> getAllMedicines() {
        List<Medicine> medicines = medicineRepository.findAll();
        return medicines.stream().map(medicine -> modelMapper.map(medicine, MedicineDto.class)).collect(Collectors.toList());
    }

    public MedicineDto getMedicine(Long medicineId) {
        return modelMapper.map(medicineRepository.findById(medicineId), MedicineDto.class);
    }

    public void addMedicine(MedicineDto medicineDto) {
        medicineRepository.save(modelMapper.map(medicineDto, Medicine.class));
    }

    public void updateMedicine(MedicineDto medicineDto) {
        addMedicine(medicineDto);
    }

    public void deleteMedicine(Long medicineId) {
        medicineRepository.deleteById(medicineId);
    }
}
