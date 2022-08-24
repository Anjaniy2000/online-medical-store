package com.anjaniy.onlinemedicalstore.repositories;

import com.anjaniy.onlinemedicalstore.models.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}
