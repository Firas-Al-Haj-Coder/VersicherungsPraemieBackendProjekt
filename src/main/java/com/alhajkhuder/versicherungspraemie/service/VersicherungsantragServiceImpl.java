package com.alhajkhuder.versicherungspraemie.service;

import org.springframework.stereotype.Service;

import com.alhajkhuder.versicherungspraemie.enitity.Versicherungsantrag;
import com.alhajkhuder.versicherungspraemie.repository.RegionRepository;
import com.alhajkhuder.versicherungspraemie.repository.VersicherungsantragRepository;

import lombok.AllArgsConstructor;

/**
 * Stellt die Funktion calculatePremium() bereit für dritten Anbieter 
 * Stellt die Funktion saveVersicherungsantrag() für die Presestierung der Antragsstelllerseingaben in die Datenbank über die injizierte versicherungsantragRepository
 */

@AllArgsConstructor
@Service
public class VersicherungsantragServiceImpl implements VersicherungsantragService {

    private final VersicherungsantragRepository versicherungsantragRepository;
    private final RegionService regionService;

    @Override
    public double calculatePremium(int kilometerLeistung, String fahrzeugTyp, String plz) {

        double kmFactor = getKmFactor(kilometerLeistung);

        double fahrzeugFactor = getFahrzeugFactor(fahrzeugTyp);

        double regionFactor = regionService.getRegionFactor(plz);

        // System.out.println(kmFactor);
        // System.out.println(fahrzeugFactor);
        // System.out.println(regionFactor);
        double premium = kmFactor * fahrzeugFactor * regionFactor;

        // Runden auf zwei Dezimalstellen
        return Math.ceil(premium * 100.0) / 100.0;        
    }

    @Override
    public Versicherungsantrag saveVersicherungsantrag(Versicherungsantrag antrag) {
        double premium = calculatePremium(antrag.getKilometerLeistung(), antrag.getFahrzeugTyp(), antrag.getPlz());
        antrag.setPraemie(premium);
        return versicherungsantragRepository.save(antrag);
    }

    private double getKmFactor(int kilometerLeistung) {
        if (kilometerLeistung <= 5000) {
            return 0.5;
        } else if (kilometerLeistung <= 10000) {
            return 1.0;
        } else if (kilometerLeistung < 20000) {
            return 1.5;
        } else {
            return 2.0;
        }
    }
    
    private double getFahrzeugFactor(String fahrzeugTyp) {
        switch(fahrzeugTyp) {
            case "SUV":
                return 1.4;
            case "Sportwagen": 
                return 1.8;
            default:
                return 1.0;
        }
    }
}
