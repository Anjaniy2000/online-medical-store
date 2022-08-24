package com.anjaniy.onlinemedicalstore.controllers;

import com.anjaniy.onlinemedicalstore.dto.MedicineDto;
import com.anjaniy.onlinemedicalstore.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/getAllMedicines")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicineDto> getAllMedicines(){
        return medicineService.getAllMedicines();
    }


    @GetMapping("/getMedicine/{medicineId}")
    @ResponseStatus(HttpStatus.OK)
    public MedicineDto getMedicine(@PathVariable("medicineId") Long medicineId){
        return medicineService.getMedicine(medicineId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addMedicine")
    @ResponseStatus(HttpStatus.CREATED)
    public String addMedicine(@Valid @RequestBody MedicineDto medicineDto){
        medicineService.addMedicine(medicineDto);
        return "Medicine Added Successfully";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateMedicine")
    @ResponseStatus(HttpStatus.OK)
    public String updateMedicine(@Valid @RequestBody MedicineDto medicineDto){
        medicineService.updateMedicine(medicineDto);
        return "Medicine Updated Successfully";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteMedicine/{medicineId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteMedicine(@PathVariable ("medicineId") Long medicineId){
        medicineService.deleteMedicine(medicineId);
        return "Medicine Deleted Successfully";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/greet")
    @ResponseStatus(HttpStatus.OK)
    public String greet(){
        return "Hello";
    }
}
