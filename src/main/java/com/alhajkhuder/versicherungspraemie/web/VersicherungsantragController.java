package com.alhajkhuder.versicherungspraemie.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alhajkhuder.versicherungspraemie.enitity.Versicherungsantrag;
import com.alhajkhuder.versicherungspraemie.service.VersicherungsantragService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@AllArgsConstructor
@RestController
@RequestMapping("/antrag")
public class VersicherungsantragController {

    VersicherungsantragService versicherungsantragService;

    @PostMapping
    public ResponseEntity<Versicherungsantrag> createAntrag(@RequestBody @Valid Versicherungsantrag antrag) {

        // Prämium wird automatisch berechnet und gespeichert 
        return new ResponseEntity<>(versicherungsantragService.saveVersicherungsantrag(antrag), HttpStatus.CREATED);
    }
    
    
}
