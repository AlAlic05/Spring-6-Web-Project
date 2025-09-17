package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient;;

    @Test
    void testDeleteBeer() {
        BeerDTO newDto = BeerDTO.builder()
                .beerName("New Beer")
                .beerStyle(BeerStyle.IPA)
                .upc("123456789012")
                .price(new BigDecimal(72.49))
                .quantityOnHand(2)
                .build();

        BeerDTO beerDTO = beerClient.createBeer(newDto);
        beerClient.deleteBeer(beerDTO.getId());

        assertThrows(HttpClientErrorException.class, () -> {
            beerClient.getBeerById(beerDTO.getId());
        });
    }

    @Test
    void testUpdateBeer() {
        BeerDTO newDto = BeerDTO.builder()
                .beerName("New Beer")
                .beerStyle(BeerStyle.IPA)
                .upc("123456789012")
                .price(new BigDecimal(72.49))
                .quantityOnHand(2)
                .build();

        BeerDTO beerDTO = beerClient.createBeer(newDto);
        final String updatedName = "Updated Name";
        beerDTO.setBeerName(updatedName);
        BeerDTO updatedBeer = beerClient.updateBeer(beerDTO);

        assertEquals(updatedName, updatedBeer.getBeerName());
    }

    @Test
    void testCreateBeer() {
        BeerDTO newDto = BeerDTO.builder()
                .beerName("New Beer")
                .beerStyle(BeerStyle.IPA)
                .upc("123456789012")
                .price(new BigDecimal(72.49))
                .quantityOnHand(2)
                .build();

        BeerDTO savedDto = beerClient.createBeer(newDto);
        assertNotNull(savedDto);
    }


    @Test
    void getBeerById() {
        Page<BeerDTO> beerDtos = beerClient.listBeers();
        BeerDTO dto = beerDtos.getContent().get(0);

        BeerDTO dtoById = beerClient.getBeerById(dto.getId());

        assertNotNull(dtoById);
    }

    @Test
    void listBeersNoBeerName() {
        beerClient.listBeers(null, null, 1,25);
    }


    @Test
    void listBeersWithName() {
        beerClient.listBeers("ALE", BeerStyle.ALE.toString(), 1, 25);
    }

    @Test
    void listBeerWithStyle() {
        beerClient.listBeers(null, BeerStyle.ALE.toString(), 1, 25);
    }
}