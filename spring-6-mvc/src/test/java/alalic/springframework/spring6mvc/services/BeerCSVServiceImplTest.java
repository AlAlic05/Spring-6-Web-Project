package alalic.springframework.spring6mvc.services;

import alalic.springframework.spring6mvc.model.BeerCSVRecord;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BeerCSVServiceImplTest {
    BeerCSVService beerCSVService = new BeerCSVServiceImpl();

    @Test
    void convertCSV() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csvdata/beers.csv");
        List<BeerCSVRecord> recs = beerCSVService.convertCSV(file);
        System.out.println(recs.size());
        assertThat(recs.size()).isGreaterThan(0);
    }
}