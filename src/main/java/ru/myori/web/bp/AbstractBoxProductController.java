package ru.myori.web.bp;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myori.AuthorizedUser;
import ru.myori.model.BoxProduct;
import ru.myori.service.BoxProductService;
import ru.myori.service.BoxService;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractBoxProductController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    BoxProductService boxProductService;

    @Autowired
    BoxService boxService;

    @Autowired
    ServletContext context;

    public List<BoxProduct> getAllByBox(int boxId) {
        int userId = AuthorizedUser.id();
        log.info("get all box {} products for User {}", boxId, userId);
        return boxProductService.getAllByBox(boxId);
    }

    public void printReport(int boxId) {
        try {
            JasperPrint jasperPrint;
            jasperPrint = create(boxId);
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
            log.info("An error occurred while printing the report for box: {} " + e, boxId);
        }
    }

    public JasperPrint create(int boxId) {
        try {
            log.info("Start generating a report, to box {}", boxId);
//        String pathForSaving = "e:\\TestResult.pdf";
            String pathForPattern = "resources" + File.separator + "jrxml" + File.separator + "BoxReport.jrxml";

            pathForPattern = context.getRealPath(pathForPattern);
            List<BoxProduct> dataBeanList = boxProductService.getAllByBox(boxId);

            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("boxId", boxId);
            parameters.put("clientName", boxService.get(boxId).getForUser().getName());

            File reportPattern = new File(pathForPattern);

            if (reportPattern.exists()) {
                JasperDesign jasperDesign = JRXmlLoader.load(reportPattern);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign); //TODO Убрать компиляцию для продакшн
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
                //JasperExportManager.exportReportToPdfFile(jasperPrint, pathForSaving);  // Export to PDF

                log.info("Report generation for box {} completed", boxId);
                return JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);

            } else log.info("File {} not found!", pathForPattern);

        } catch (JRException e)
        {
            log.info("An error occurred while generating the report for box {}: " + e, boxId);
        }
        return null;
    }

}
