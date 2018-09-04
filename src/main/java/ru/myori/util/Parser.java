package ru.myori.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.model.Product;
import ru.myori.model.StorageProduct;
import ru.myori.service.ProductService;
import ru.myori.service.StorageProductService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Parser {

    @Autowired
    StorageProductService storageProductService;

/*    public static Set<StorageProduct> parseXLS(String name){

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
                int article = Integer.parseInt(row.getCell(0).getStringCellValue());
                String description = row.getCell(1).getStringCellValue();
                double price = row.getCell(2).getNumericCellValue();
                int volume = Integer.parseInt(row.getCell(3).getStringCellValue());

                storageProductService

                StorageProduct sp=new StorageProduct();

                sp.setPrice((float)price);
                sp.setVolume(volume);

                storageProductSet.add(sp);

            }
//            result.append("\n");
        }

        return storageProductSet;
    }*/
}
