package com.alhajkhuder.versicherungspraemie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alhajkhuder.versicherungspraemie.service.RegionService;
import com.alhajkhuder.versicherungspraemie.service.VersicherungsantragService;

@SpringBootTest
public class BusinessLogikTests {

    @Autowired
    RegionService regionService;

    @Autowired
    VersicherungsantragService versicherungsantragService;

    @Test
    void testGetRegionFactor() {
        String plz = "13599"; // in Berlin
        double expectedFactor = 1.3; // Erwarteter Faktor für diese PLZ

        double actualFactor = regionService.getRegionFactor(plz);

        assertEquals(expectedFactor, actualFactor);
    }

    @Test
    void testCalculatePremium() {
        // Testfall 1: SUV mit PLZ 13599 und 5000 km
        double expectedPremium1 = 0.91;
        double actualPremium1 = versicherungsantragService.calculatePremium(5000, "SUV", "13599");
        assertEquals(expectedPremium1, actualPremium1,
                "Die berechnete Prämie für SUV, PLZ 13599 und 5000 km sollte 0.91 sein.");

        // Testfall 2: Sedan mit PLZ 13599 und 10000 km
        double expectedPremium2 = 1.3;
        double actualPremium2 = versicherungsantragService.calculatePremium(10000, "Sedan", "13599");
        assertEquals(expectedPremium2, actualPremium2,
                "Die berechnete Prämie für Sedan, PLZ 13599 und 10000 km sollte 1.3 sein.");

        // Testfall 3: Unbekannter Fahrzeugtyp mit PLZ 99999 und 20000 km
        double expectedPremium3 = 2.0; // Standardfaktor für unbekannte PLZ und Fahrzeugtyp
        double actualPremium3 = versicherungsantragService.calculatePremium(20000, "Unknown", "99999");
        assertEquals(expectedPremium3, actualPremium3,
                "Die berechnete Prämie für unbekannten Fahrzeugtyp und PLZ sollte 2.0 sein.");
    }

}
