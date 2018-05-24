package ru.myori.web.box;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.myori.model.Box;


import java.util.List;

@RestController
@RequestMapping("/ajax/box")
public class AjaxBoxController extends AbstractBoxController{

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Box> getAll() {
        return super.getAll();
    }

    @PostMapping(value = "/addBox", produces = MediaType.APPLICATION_JSON_VALUE)
    public Box createOrUpdate(@RequestParam("customerId") int customerId){
        return super.create(customerId);
    }
}
