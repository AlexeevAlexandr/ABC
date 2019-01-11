package сом.example.application.dao;

import com.opencsv.bean.CsvBindByName;

public class RegisteredVisitorsDAO {

    @CsvBindByName
    private Long Id;

    @CsvBindByName
    private String UsrName;

    @CsvBindByName
    private String FirstName;

    @CsvBindByName
    private String LastName;

    @CsvBindByName
    private boolean enabled;

    @CsvBindByName
    private String Email;

    @CsvBindByName
    private String Country;

    @CsvBindByName
    private String Gender;

    @CsvBindByName
    private String Password;

    public RegisteredVisitorsDAO(){}

    public RegisteredVisitorsDAO(Long id, String usrName, String firstName, String lastName, boolean enabled, String email, String country, String gender, String password) {
        this.Id = id;
        this.UsrName = usrName;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.enabled = enabled;
        this.Email = email;
        this.Country = country;
        this.Gender = gender;
        this.Password = password;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsrName() {
        return UsrName;
    }

    public void setUsrName(String usrName) {
        UsrName = usrName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
