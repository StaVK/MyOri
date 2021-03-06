package ru.myori.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import ru.myori.AuthorizedUser;
import ru.myori.service.OrderService;
import ru.myori.service.PeopleService;
import ru.myori.service.ProductService;
import ru.myori.service.UserService;
import ru.myori.to.UserTo;
import ru.myori.util.UserUtil;
import ru.myori.web.user.AbstractUserController;

import javax.validation.Valid;

@Controller
public class RootController extends AbstractUserController{

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PeopleService peopleService;

    @GetMapping("/")
    public String root() {
        return "orders";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/boxes")
    public String delivery() {
        return "boxes";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }
    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products";
    }
    @GetMapping("/orders")
    public String orders() {
        return "orders";
    }

    @GetMapping("/summaryOrder")
    public String summaryOrder() {
        return "summaryOrder";
    }

    @GetMapping("/storage")
    public String storage() {
        return "storage";
    }

    @GetMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("userTo", new UserTo());
        model.addAttribute("register", true);
        return "userProfile";
    }

    @PostMapping("/register")
    public String saveRegister(@Valid UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "profile";
        } else {
            super.create(UserUtil.createNewFromTo(userTo));
            status.setComplete();
            return "redirect:login?message=app.registered&username=" + userTo.getEmail();
        }
    }

    @GetMapping("/userProfile")
    public String profile(ModelMap model, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        UserTo userTo=authorizedUser.getUserTo();
        model.addAttribute("userTo", userTo);
//        model.addAttribute("people", userService.get(userTo.getId()).getPeople());
        return "userProfile";
    }

    @GetMapping("/customers")
    public String customers() {
        return "customers";
    }
}
