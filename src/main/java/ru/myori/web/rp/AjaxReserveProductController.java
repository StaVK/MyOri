package ru.myori.web.rp;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.myori.model.ReserveProduct;

@RestController
@RequestMapping("/ajax/rp")
public class AjaxReserveProductController extends AbstractReserveProductController {

    @PostMapping("/editV")
    public void editVolume(@RequestParam(value = "opId") int opId, @RequestParam(value = "reserveVolume") int volume) {

        super.update(opId, volume);
    }
}
