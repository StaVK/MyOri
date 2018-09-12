package ru.myori.web.sp;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.AuthorizedUser;
import ru.myori.model.Product;
import ru.myori.model.Storage;
import ru.myori.model.StorageProduct;
import ru.myori.service.ProductService;
import ru.myori.service.StorageProductService;
import ru.myori.service.StorageService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class AbstractStorageProductController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private StorageProductService storageProductService;

    public List<StorageProduct> getProducts(int storageId){
        int userId = AuthorizedUser.id();
        log.info("getProducts in storage {} for User {}", storageId, userId);
        return storageProductService.getAll(storageId);
    }

    public StorageProduct create(StorageProduct storageProduct) {
        int userId = AuthorizedUser.id();
//        checkNew(meal);
        log.info("create {} for User {}", storageProduct, userId);
        return storageProductService.save(storageProduct);
    }

    public void update(StorageProduct storageProduct) {
        int userId = AuthorizedUser.id();
//        assureIdConsistent(meal, id);
        log.info("update {} for User {}", storageProduct, userId);
//        storageProductService.update(storageId, storage, userId);
    }

    public StorageProduct getByArticle(int article, int storageId){
        int userId = AuthorizedUser.id();
        log.info("getByArticle {} and by storage {} for User {}", article, storageId, userId);
        return storageProductService.getFirstByArticle(article, storageId);
    }

    public void createOrUpdate(int article, int storageId, int volume, float price){
        int userId = AuthorizedUser.id();
        storageProductService.createOrUpdate(article, storageId, volume, price, userId);
    }


/*    public Storage get(int storageId){
        int userId = AuthorizedUser.id();
        log.info("get storage {} for User {}", storageId, userId);
        return storageService.get(storageId, userId);
    }

    public Set<Storage> getAll(){
        int userId = AuthorizedUser.id();
        log.info("getAll storages for User {}", userId);
        return storageService.getAll(userId);
    }


*/

    protected void parseXLS(String name, int storageId){
        int userId = AuthorizedUser.id();

//        StringBuilder result = new StringBuilder();
        InputStream in = null;
        HSSFWorkbook wb = null;

        Set<StorageProduct> storageProductSet=new HashSet<>();

        try {
            in = new FileInputStream(name);
            wb = new HSSFWorkbook(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        while (it.hasNext()) {
            Row row = it.next();
            if(row.getCell(0).getCellType()==Cell.CELL_TYPE_NUMERIC) {
                int article = ((Number)(getCell(row.getCell(0)))).intValue();
//                String description =(String) getCell(row.getCell(1));
                double price = (Double) getCell(row.getCell(2));
                int volume = ((Number)(getCell(row.getCell(3)))).intValue();

                storageProductService.createOrUpdate(article, storageId,volume, (float) price,userId);

            }

        }

    }
    private Object getCell(Cell cell){
        Object result=0;
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_STRING:
                result=cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                result=cell.getNumericCellValue();
                break;
        }
        return result;
    }
}
