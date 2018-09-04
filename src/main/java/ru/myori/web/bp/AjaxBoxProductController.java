package ru.myori.web.bp;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.myori.model.BoxProduct;

import java.util.List;

@RestController
@RequestMapping("/ajax/boxProduct")
public class AjaxBoxProductController extends AbstractBoxProductController {

    @GetMapping(value = "/{box}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BoxProduct> getAll(@PathVariable("box") int boxId) {
        return super.getAllByBox(boxId);
    }

    @GetMapping(value = "/printSpReport/{box}")
    public void printSpReport(@PathVariable("box") int boxId) {
        super.printReport(boxId);
    }

    @GetMapping(value = "/sendToCustomer/{box}")
    public void sendToCustomer(@PathVariable("box") int boxId) {
        super.sendToCustomer(boxId);
    }


}
