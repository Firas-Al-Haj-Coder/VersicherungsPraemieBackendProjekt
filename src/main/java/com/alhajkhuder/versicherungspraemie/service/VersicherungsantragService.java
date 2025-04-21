package com.alhajkhuder.versicherungspraemie.service;

import com.alhajkhuder.versicherungspraemie.enitity.Versicherungsantrag;

public interface VersicherungsantragService {
    
    public double calculatePremium(int kilometerLeistung, String fahrzeugTyp, String plz);
    public Versicherungsantrag saveVersicherungsantrag(Versicherungsantrag antrag);


}
