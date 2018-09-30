package ru.myori.web.sp;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.AuthorizedUser;
import ru.myori.model.*;
import ru.myori.service.ProductService;
import ru.myori.service.StorageProductService;
import ru.myori.service.StorageService;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public abstract class AbstractStorageProductController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private StorageProductService storageProductService;

    @Autowired
    private StorageService storageService;

    @Autowired
    ServletContext context;

    public void printReport(int storageId) {
        try {
            JasperPrint jasperPrint;
            jasperPrint = create(storageId);
            if (jasperPrint != null) {
                // Print
                JRPrintServiceExporter exporter = new JRPrintServiceExporter();

                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                SimplePrintServiceExporterConfiguration configuration = new SimplePrintServiceExporterConfiguration();
/*            configuration.setPrintRequestAttributeSet(printRequestAttributeSet);
            configuration.setPrintServiceAttributeSet(printServiceAttributeSet);*/
                configuration.setDisplayPageDialog(false);
                configuration.setDisplayPrintDialog(true);
                exporter.setConfiguration(configuration);
                exporter.exportReport();
            }
        } catch (JRException e) {
            log.info("An error occurred while printing the report for storage: {} " + e, storageId);
        }
    }

    public JasperPrint create(int storageId) {
        try {
            log.info("Start generating a report, to storage {}", storageId);
//        String pathForSaving = "e:\\TestResult.pdf";
            String pathForPattern = "resources" + File.separator + "jrxml" + File.separator + "StorageProductReport.jrxml";

            pathForPattern = context.getRealPath(pathForPattern);
            List<StorageProduct> dataBeanList = storageProductService.getAll(storageId);

            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);

//            People people=boxService.get(boxId).getCustomer().getPeople();

            Map<String, Object> parameters = new HashMap<>();
            int userId = AuthorizedUser.id();

            String storageName=storageService.get(storageId,userId).getName();

            parameters.put("storageId", storageId);
            parameters.put("storageName", storageName);
/*            parameters.put("clientName", people.getName());
            parameters.put("clientSurname", people.getSurname());*/

            File reportPattern = new File(pathForPattern);

            if (reportPattern.exists()) {
                JasperDesign jasperDesign = JRXmlLoader.load(reportPattern);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign); //TODO Убрать компиляцию для продакшн
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
                //JasperExportManager.exportReportToPdfFile(jasperPrint, pathForSaving);  // Export to PDF

                log.info("Report generation for box {} completed", storageId);
                return JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);

            } else log.info("File {} not found!", pathForPattern);

        } catch (JRException e)
        {
            log.info("An error occurred while generating the report for box {}: " + e, storageId);
        }
        return null;
    }

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
