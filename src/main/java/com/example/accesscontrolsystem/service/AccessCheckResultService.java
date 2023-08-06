package com.example.accesscontrolsystem.service;

import com.example.accesscontrolsystem.model.AccessCheckResultModel;
import com.example.accesscontrolsystem.model.ResultPDFExporter;
import com.example.accesscontrolsystem.repository.AccessCheckResultRepository;
import com.lowagie.text.DocumentException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccessCheckResultService {
    private final AccessCheckResultRepository accessCheckResultRepository;
    @Value("${csv.output.path}")
    String path;

    public void addAccessCheckResult(AccessCheckResultModel acr) {
        accessCheckResultRepository.save(acr);
    }

    public List<AccessCheckResultModel> getResults() {
        return accessCheckResultRepository.findAll();
    }

    public AccessCheckResultModel getResultById(Long id) {
        return accessCheckResultRepository.findById(id).orElse(null);
    }

    public void saveEditResult(AccessCheckResultModel acr) {
        accessCheckResultRepository.save(acr);
    }

    public void removeResult(Long id) {
        accessCheckResultRepository.deleteById(id);
    }

    public void removeOldestTen(){
      getResults().stream()
              .sorted(Comparator.comparing(u -> u.getCreationDate()))
              .limit(10)
              .collect(Collectors.toList())
              .forEach(e-> removeResult(e.getId()));
    }
    public void removeAll(){
        accessCheckResultRepository.deleteAll();
    }

    public void exportToPDF(HttpServletResponse response)throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=results_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<AccessCheckResultModel> list = getResults();

        ResultPDFExporter exporter = new ResultPDFExporter(list);
        exporter.export(response);
    }

    public void sortResults(List<AccessCheckResultModel> res, String sortBy) {
        if (sortBy != null) {
            switch (sortBy) {
                case "id":
                    res.sort(Comparator.comparing(AccessCheckResultModel::getId));
                    break;
                case "creationDate":
                    res.sort(Comparator.comparing(u -> u.getCreationDate().toString()));
                    break;
                case "description":
                    res.sort(Comparator.comparing(e -> e.getDescription().toLowerCase()));
                    break;
                default:
                    // Obsłuż nieznany parametr sortowania
                    break;
            }
        }
    }
    @PostConstruct
    public void removeOlderThan() throws IOException{
        Date dateToCompare = new Date(System.currentTimeMillis() - 5 * 60 * 1000);
        List<AccessCheckResultModel> acrFiltered = getResults().stream()
                .filter(e-> e.getCreationDate().before(dateToCompare))
                .collect(Collectors.toList());
        if(!acrFiltered.isEmpty()) {
            saveToCSV(acrFiltered);
            acrFiltered.forEach(e -> removeResult(e.getId()));
        }
    }



    public String getACRCount(List<AccessCheckResultModel> acr) {
        return "Total number of records: " + acr.size();
    }



    private void saveToCSV(List<AccessCheckResultModel> list) throws IOException{
       Boolean isBackup = false;
       Boolean isEmpty = false;

        try{

            File newBackup = new File(path);
            if(!newBackup.exists()){
                newBackup.createNewFile();
                isEmpty = true;
            } else {
                isBackup = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            isBackup=false;
        }
        if(isBackup) {
            try {
                FileWriter writer = new FileWriter(path, true);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withDelimiter(';'));
                if (isEmpty) {
                    csvPrinter.printRecord("Id", "Description", "CreationDate");
                }
                for (AccessCheckResultModel e : list) {
                    csvPrinter.printRecord(e.getId(), e.getDescription(), e.getCreationDate());
                }
                csvPrinter.flush();
            } catch (IOException e) {
                throw new IOException("Cannot make backup");
            }
        }

    }
}
