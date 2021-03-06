package ru.myori.web.storage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/storage")
public class JspStorageController extends AbstractStorageController{

    @GetMapping("/{storageId}")
    public String productsInStorage(@PathVariable("storageId") int storageId, Model model) {
        model.addAttribute("storageId", storageId);
        model.addAttribute("storageName", super.get(storageId).getName());
        return "storageProducts";
    }
}
