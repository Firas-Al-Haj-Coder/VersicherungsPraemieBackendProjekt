package com.alhajkhuder.versicherungspraemie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alhajkhuder.versicherungspraemie.enitity.Versicherungsantrag;

@Repository
public interface VersicherungsantragRepository extends JpaRepository<Versicherungsantrag, Long> {
	
}
