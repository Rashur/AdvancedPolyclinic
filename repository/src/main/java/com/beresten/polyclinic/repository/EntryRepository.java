package com.beresten.polyclinic.repository;

import com.beresten.polyclinic.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Integer> {
}
