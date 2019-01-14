package сом.example.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import сом.example.application.dao.AppUserDAO;
import сом.example.application.dao.CountryDAO;
import сом.example.application.formbean.AppUserForm;
import сом.example.application.model.AppUser;
import сом.example.application.model.Country;
import сом.example.application.validator.AppUserValidator;

import java.util.List;

@Controller
public class MainController {

    private final AppUserDAO appUserDAO;
    private final CountryDAO countryDAO;
    private final AppUserValidator appUserValidator;

    @Autowired
    public MainController(AppUserDAO appUserDAO, CountryDAO countryDAO, AppUserValidator appUserValidator) {
        this.appUserDAO = appUserDAO;
        this.countryDAO = countryDAO;
        this.appUserValidator = appUserValidator;
    }

    // Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form target
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        if (target.getClass() == AppUserForm.class) {
            dataBinder.setValidator(appUserValidator);
        }
    }

    @RequestMapping(value = "/")
    public String viewHome() {
        return "welcomePage";
    }

    @RequestMapping(value = "/members")
    public String viewMembers(Model model) {
        List<AppUser> list = appUserDAO.getAppUsers();
        model.addAttribute("members", list);
        return "membersPage";
    }

    @RequestMapping(value = "/registerSuccessful")
    public String registerSuccessfulPage() {
        return "registerSuccessfulPage";
    }

    // Show Register page.
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String viewRegister(Model model) {

        AppUserForm form = new AppUserForm();
        List<Country> countries = countryDAO.getCountries();

        model.addAttribute("appUserForm", form);
        model.addAttribute("countries", countries);
        return "registerPage";
    }

    // This method is called to save the registration information.
    // @Validated: To ensure that this Form
    // has been Validated before this method is invoked.
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegister(Model model, @ModelAttribute("appUserForm") @Validated AppUserForm appUserForm, BindingResult result, final RedirectAttributes redirectAttributes) {

        // Validate result
        if (result.hasErrors()) {
            List<Country> countries = countryDAO.getCountries();
            model.addAttribute("countries", countries);
            return "registerPage";
        }
        AppUser newUser;
        try {
            newUser = appUserDAO.createAppUser(appUserForm);
        }
        // Other error!!
        catch (Exception e) {
            List<Country> countries = countryDAO.getCountries();
            model.addAttribute("countries", countries);
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "registerPage";
        }

        redirectAttributes.addFlashAttribute("flashUser", newUser);

        return "redirect:/registerSuccessful";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/403")
    public String error403() {
        return "error/403";
    }

    @GetMapping(value = "/admin")
    public String admin() {
        return "admin";
    }
}
