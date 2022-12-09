package com.beresten.polyclinic.repository;

import com.beresten.polyclinic.model.MedicalCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalCardRepository extends JpaRepository<MedicalCard, Integer> {
}
