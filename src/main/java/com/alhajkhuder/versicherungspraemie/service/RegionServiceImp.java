package com.alhajkhuder.versicherungspraemie.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alhajkhuder.versicherungspraemie.enitity.Region;
import com.alhajkhuder.versicherungspraemie.repository.RegionRepository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

/**
 * Lädet die CSV Datei und verarbeitet die Einträge in die Datenbank bzw. Repository
 * Ermittelt den Region_Faktor anhand des Bundeslandes  
 */
@AllArgsConstructor
@Service
public class RegionServiceImp implements RegionService {
    
    private final RegionRepository regionRepository; 
    private final Map<String, Double> regionFactorsMap = new HashMap<>();


    public double getRegionFactor(String plz){ // plz -> bundesland Region Factor
        return regionFactorsMap.getOrDefault(plz, 1.0); 
    }

    @PostConstruct
    public void loadRegions() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/postcodes.csv")))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("ISO_3166_1_ALPHA_2")) continue; // Kopfzeile überspringen

                // Entfernt Anführungszeichen aus der gesamten Zeile
                line = line.replace("\"", "");

                String[] fields = line.split(",");
                
                Region region = new Region();
                region.setPlz(fields[6].trim());
                region.setRegion1BundesLand(fields[2].trim());
                region.setRegion3Land(fields[4].trim());
                region.setRegion4Stadt(fields[5].trim());
                region.setOrtBezirk(fields[7].trim());

                regionRepository.save(region);
                
                double factor = calculateRegionFactor(fields[2]); // bundesland -> Faktor
                regionFactorsMap.put(fields[6].trim(), factor); // plz -> bundeslandfactor 
            }

            //  System.out.println(regionFactorsMap);
        }
    }

    private double calculateRegionFactor(String bundesLand) {
        // Bereinigt den Eingabewert
        bundesLand = bundesLand.trim();
        switch (bundesLand) {
            case "Baden-Württemberg":
                return 1.1;
            case "Berlin":
                return 1.3;
            case "Bayern":
                return 1.2;
            default:
                return 1.0;
        }
    }
    
}
