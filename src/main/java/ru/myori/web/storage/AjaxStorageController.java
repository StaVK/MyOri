package ru.myori.web.storage;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.myori.model.Storage;

import java.util.Set;

@RestController
@RequestMapping("/ajax/storage")
public class AjaxStorageController extends AbstractStorageController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Storage> getAll() {
        return super.getAll();
    }
}
