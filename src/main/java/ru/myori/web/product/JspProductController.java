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

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("product", super.get(getId(request)));
        return "productForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product(null, null, 0.0));
        return "productForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Product product = new Product(Integer.valueOf(request.getParameter("article")),
                request.getParameter("description"),
                Double.valueOf(request.getParameter("price")));

        if (request.getParameter("prodId").isEmpty()) {
            super.create(product);
        } else {
            super.update(product);//TODO Сделать апдейт чтоб именно исправлял, а не создавал новую запись
        }
        return "redirect:/products";
    }


    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("prodId"));
        return Integer.valueOf(paramId);
    }
}
