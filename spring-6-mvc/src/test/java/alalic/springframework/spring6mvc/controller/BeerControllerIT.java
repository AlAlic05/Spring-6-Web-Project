package alalic.springframework.spring6mvc.controller;

import alalic.springframework.spring6mvc.entities.Beer;
import alalic.springframework.spring6mvc.mappers.BeerMapper;
import alalic.springframework.spring6mvc.model.BeerDTO;
import alalic.springframework.spring6mvc.model.BeerStyle;
import alalic.springframework.spring6mvc.repositories.BeerRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class BeerControllerIT {
    @Autowired
    BeerController controller;

    @Autowired
    BeerRepo repository;

    @Autowired
    BeerMapper mapper;

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;
    @Autowired
    private BeerRepo beerRepo;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void testLListBeers(){
        List<BeerDTO> dtos = controller.listBeers(null, null);
        assertThat(dtos.size()).isEqualTo(2413);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList() {
        repository.deleteAll();
        List<BeerDTO> dtos = controller.listBeers(null, null);
        assertThat(dtos.size()).isEqualTo(0);
    }

    @Test
    void testGetById() {
        Beer beer = repository.findAll().get(0);
        BeerDTO beerDTO = controller.getBeerById(beer.getId());

        assertThat(beerDTO).isNotNull();
    }

    @Test
    void testBeerIdIsNotFound() {
        assertThrows(NotFoundException.class, () -> {
            controller.getBeerById(UUID.randomUUID());
        });
    }

    @Rollback
    @Transactional
    @Test
    void saveNewBeerTest() {
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("New Beer")
                .build();

        ResponseEntity responseEntity = controller.handlePost(beerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo((HttpStatusCode.valueOf(201)));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);

        Beer beer = repository.findById(savedUUID).get();
        assertThat(beer).isNotNull();
    }
    
    @Rollback
    @Transactional
    @Test
    void updateExistingBeer() {
        Beer beer = repository.findAll().get(0);
        BeerDTO beerDTO = mapper.beerToBeerDTO(beer);
        beerDTO.setId(null);
        beerDTO.setVersion(null);
        final String beerName = "Updated Beer";
        beerDTO.setBeerName(beerName);

        ResponseEntity responseEntity = controller.updateById(beer.getId(), beerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo((HttpStatusCode.valueOf(204)));

        Beer updatedBeer = repository.findById(beer.getId()).get();
        assertThat(updatedBeer.getBeerName()).isEqualTo(beerName);
    }

    @Test
    void testUpdateNotFound() {
        assertThrows(NotFoundException.class, () -> {
            controller.updateById(UUID.randomUUID(), BeerDTO.builder().build());
        });
    }

    @Rollback
    @Transactional
    @Test
    void deleteByIdNotFound() {
        Beer beer = repository.findAll().get(0);
        ResponseEntity responseEntity = controller.deleteBeerById(beer.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo((HttpStatusCode.valueOf(204)));
        assertThat(repository.findById(beer.getId()).isEmpty());
    }

    @Test
    void testDeleteNotFound() {
        assertThrows(NotFoundException.class, () -> {
            controller.deleteBeerById(UUID.randomUUID());
        });
    }

    @Test
    void testPatchBeerBadName() throws Exception {
        Beer beer = beerRepo.findAll().get(0);

        Map<String, Object> beerMap = new HashMap<>();
        beerMap.put("beerName", "New Name 1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

        mockMvc.perform(patch(BeerController.BEER_PATH_ID, beer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beerMap)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testListBeersByName() throws Exception {
        mockMvc.perform(get(BeerController.BEER_PATH)
                .queryParam("beerName", "IPA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(336)));
    }

    @Test
    void testListBeersByStyle() throws Exception {
        mockMvc.perform(get(BeerController.BEER_PATH)
                .queryParam("beerStyle", BeerStyle.IPA.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(548)));
    }

    @Test
    void testListBeersByNameAndStyle() throws Exception {
        mockMvc.perform(get(BeerController.BEER_PATH)
                .queryParam("beerName", "Light")
                .queryParam("beerStyle", BeerStyle.IPA.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(5)));
    }
}