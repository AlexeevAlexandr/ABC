package сом.example.application.dao;

import org.springframework.stereotype.Repository;
import сом.example.application.model.Country;

import java.util.*;

@Repository
public class CountryDAO {

    private static final Map<String, Country> COUNTRIES_MAP = new HashMap<>();

    static {
        initDATA();
    }

    private static void initDATA() {
        String[] countries = Locale.getISOCountries();
        for (String country : countries) {
            Locale locale = new Locale("en", country);
            Country con = new Country(locale.getCountry(),locale.getDisplayCountry(locale));
            COUNTRIES_MAP.put(con.getCountryCode(),con);
        }
    }

    public List<Country> getCountries() {
        return new ArrayList<>(COUNTRIES_MAP.values());
    }
}
