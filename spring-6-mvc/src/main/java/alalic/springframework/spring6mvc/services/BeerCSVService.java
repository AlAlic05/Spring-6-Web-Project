package alalic.springframework.spring6mvc.services;

import alalic.springframework.spring6mvc.model.BeerCSVRecord;

import java.io.File;
import java.util.List;

public interface BeerCSVService {
    List<BeerCSVRecord> convertCSV(File csvFile);
}
