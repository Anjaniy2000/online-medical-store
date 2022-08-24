package com.anjaniy.onlinemedicalstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedicineDto {
    private long medicineId;

    @NotBlank(message = "Medicine name is required")
    private String medicineName;

    @NotBlank(message = "Medicine description is required")
    private String medicineDescription;

    @NotNull(message = "Medicine price is required")
    private Long price;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd-MM-yyyy")
    private Date medicineExpiryDate;

    @NotNull(message = "Quantity is required")
    private Long quantity;
}
