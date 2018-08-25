package ru.myori.web.box;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.myori.model.Box;
import ru.myori.model.People;
import ru.myori.web.storage.AbstractStorageController;

@Controller
@RequestMapping(value = "/box")
public class JspBoxController extends AbstractBoxController{
    @GetMapping("/{boxId}")
    public String productsInStorage(@PathVariable("boxId") int boxId, Model model) {
        People people=boxService.get(boxId).getCustomer().getPeople();
        String customer=people.getName()+" "+people.getSurname();
        model.addAttribute("customer", customer);
        model.addAttribute("box", boxId);
        return "boxProducts";
    }
}
