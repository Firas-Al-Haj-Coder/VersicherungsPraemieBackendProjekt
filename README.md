# VersicherungsPrämieProjekt

Diese Applikation berechnet die Versicherungsprämie basierend auf der jährlichen Kilometerleistung, dem Fahrzeugtyp und der Postleitzahl des Nutzers. Die Berechnung erfolgt durch die Kombination von Faktoren, die für jede dieser Eingaben definiert sind.

---

## **Funktionen**
1. **Berechnung der Versicherungsprämie**:
   - Die Prämie wird basierend auf drei Faktoren berechnet:
     - **Kilometerleistung**: Ein Faktor, der die jährliche Fahrleistung berücksichtigt.
     - **Fahrzeugtyp**: Ein Faktor, der den Fahrzeugtyp (z. B. SUV, Sedan) berücksichtigt.
     - **Region**: Ein Faktor, der auf der Postleitzahl basiert und regionale Unterschiede berücksichtigt.

2. **Speicherung der Antragsdaten**:
   - Die eingegebenen Daten und die berechnete Prämie werden in einer Datenbank gespeichert.

3. **REST-API**:
   - Die Applikation stellt eine REST-API bereit, um Anträge zu erstellen und die berechnete Prämie zurückzugeben.

---

## **API-Endpunkte**

### **POST /antrag**
- **Beschreibung**: Erstellt einen neuen Versicherungsantrag und berechnet die Prämie.
- **Request Body**:
  ```json
  {
    "kilometerLeistung": 5000,
    "fahrzeugTyp": "SUV",
    "plz": "13599"
  }

- Response: 
```json 
    {
    "id": 1,
    "kilometerLeistung": 5000,
    "fahrzeugTyp": "SUV",
    "plz": "13599",
    "praemie": 0.91
    }
```

* Prämie = 0.5 * 1.4 * 1.3 = 0.91


## Technologien
1. Spring Boot: Framework für die Backend-Entwicklung und Exception Behandlung.
2. H2-Datenbank: In-Memory-Datenbank für die Speicherung der Antragsdaten und Inferenz der Regionsfaktoren (PLZ -> Bundesland -> Regionfaktor).
3. Lombok: Reduziert Boilerplate-Code durch Annotationen.
4. JUnit 5: Für Unit- und Integrationstests.
5. MockMvc: Zum Testen der REST-API.

## Schritte zum Starten der Anwendung

Projekt bauen: `mvn clean install`

Anwendung starten: `mvn clean spring-boot:run`

API testen:
+ Verwende Tools wie Postman oder curl, um Anfragen an die API zu senden.
+ Oder nutze die mit eingefügte React-Weboberfläche 



