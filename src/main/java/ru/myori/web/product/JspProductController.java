package ru.myori.web.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.myori.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequestMapping(value = "/products")
public class JspProductController extends AbstractProductController {

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:/products";
    }

/*    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("meal", super.get(getId(request)));
        return "mealForm";
    }*/

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product(1, "Туалетная вода", 1000.0));
        return "productForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Product product = new Product(Integer.valueOf(request.getParameter("article")),
                request.getParameter("description"),
                Double.valueOf(request.getParameter("price")));

        if (request.getParameter("id").isEmpty()) {
            super.create(product);
        } else {
            super.update(product, getId(request));
        }
        return "redirect:/products";
    }

/*    @PostMapping("/filter")
    public String getBetween(HttpServletRequest request, Model model) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("meals", super.getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }*/

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
