package ru.myori.web.bp;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.myori.model.BoxProduct;


import java.util.List;

@RestController
@RequestMapping("/ajax/bp")
public class AjaxBoxProductController extends AbstractBoxProductController {

    @GetMapping(value = "/{box}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BoxProduct> getAll(@PathVariable("box") int boxId) {
        return super.getAllByBox(boxId);
    }

}
