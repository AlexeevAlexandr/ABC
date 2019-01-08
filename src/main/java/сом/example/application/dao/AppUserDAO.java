package сом.example.application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import сом.example.application.formbean.AppUserForm;
import сом.example.application.model.AppUser;
import сом.example.application.model.Gender;

import java.util.*;

@Repository
public class AppUserDAO {

    // Config in WebSecurityConfig
    private final PasswordEncoder passwordEncoder;

    private static final Map<Long, AppUser> USERS_MAP = new HashMap<>();

    static {
        initDATA();
    }

    @Autowired
    public AppUserDAO(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private static void initDATA() {
        String encrytedPassword = "";

        AppUser tom = new AppUser(1L, "tom", "Tom", "Tom", //
                true, Gender.MALE, "tom@waltdisney.com", encrytedPassword, "US");

        AppUser jerry = new AppUser(2L, "jerry", "Jerry", "Jerry", //
                true, Gender.MALE, "jerry@waltdisney.com", encrytedPassword, "US");

        USERS_MAP.put(tom.getUserId(), tom);
        USERS_MAP.put(jerry.getUserId(), jerry);
    }

    private Long getMaxUserId() {
        long max = 0;
        for (Long id : USERS_MAP.keySet()) {
            if (id > max) {
                max = id;
            }
        }
        return max;
    }

    //

    public AppUser findAppUserByUserName(String userName) {
        Collection<AppUser> appUsers = USERS_MAP.values();
        for (AppUser u : appUsers) {
            if (u.getUserName().equals(userName)) {
                return u;
            }
        }
        return null;
    }

    public AppUser findAppUserByEmail(String email) {
        Collection<AppUser> appUsers = USERS_MAP.values();
        for (AppUser u : appUsers) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    public List<AppUser> getAppUsers() {
        return new ArrayList<>(USERS_MAP.values());
    }

    public AppUser createAppUser(AppUserForm form) {
        Long userId = this.getMaxUserId() + 1;
        String encrytedPassword = this.passwordEncoder.encode(form.getPassword());

        AppUser user = new AppUser(userId, form.getUserName(), //
                form.getFirstName(), form.getLastName(), false, //
                form.getGender(), form.getEmail(), form.getCountryCode(), //
                encrytedPassword);

        USERS_MAP.put(userId, user);
        return user;
    }

}
