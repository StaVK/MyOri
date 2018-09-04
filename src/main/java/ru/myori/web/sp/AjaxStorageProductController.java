package ru.myori.web.sp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.myori.model.StorageProduct;
import ru.myori.util.Parser;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/ajax/sp")
public class AjaxStorageProductController extends AbstractStorageProductController {
    @Autowired
    private HttpServletRequest request;

    @GetMapping(value = "/{storageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StorageProduct> getProducts(@PathVariable("storageId") int storageId) {
        List<StorageProduct> tmp=super.getProducts(storageId);
        return tmp;
    }

    @PostMapping
    public void createOrUpdate(
            @RequestParam("article") int article,
            @RequestParam("storageId") int storageId,
            @RequestParam("volume") int volume,
            @RequestParam("price") float price) {
        super.createOrUpdate(article, storageId, volume, price);


    }

    @PostMapping(value="/upload")
    public void handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("storage") int storageId) {
        String name = "test";
        if (!file.isEmpty()) {
            try {
                String uploadsDir = "/uploads/";
                String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
                if (!new File(realPathtoUploads).exists()) {
                    new File(realPathtoUploads).mkdir();
                }

//                log.info("realPathtoUploads = {}", realPathtoUploads);


                String orgName = file.getOriginalFilename();
                String filePath = realPathtoUploads + orgName;
                File dest = new File(filePath);
                file.transferTo(dest);

                parseXLS(filePath, storageId);

            } catch (Exception e) {
                System.out.println("Вам не удалось загрузить " + name + " => " + e.getMessage());
            }
        } else {
            System.out.println("Вам не удалось загрузить " + name + " потому что файл пустой.");
        }
    }

}
