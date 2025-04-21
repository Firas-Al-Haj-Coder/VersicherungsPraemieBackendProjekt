package com.alhajkhuder.versicherungspraemie.enitity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Versicherungsantrag {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @NonNull
    private Integer kilometerLeistung;
    @NonNull
    @NotBlank(message = "Fahrzeugtyp kann nicht leer sein") 
    private String fahrzeugTyp;
    @NonNull
    @NotBlank(message = "Postleitzahl kann nicht leer sein") 
    private String plz; 
    private double praemie; 
}