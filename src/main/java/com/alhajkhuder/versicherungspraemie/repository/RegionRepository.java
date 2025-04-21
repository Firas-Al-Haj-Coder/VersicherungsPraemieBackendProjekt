package com.alhajkhuder.versicherungspraemie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alhajkhuder.versicherungspraemie.enitity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {


}

