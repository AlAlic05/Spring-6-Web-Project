package alalic.springframework.spring6reactivemongo.bootstrap;

import alalic.springframework.spring6reactivemongo.domain.Beer;
import alalic.springframework.spring6reactivemongo.domain.Customer;
import alalic.springframework.spring6reactivemongo.repos.BeerRepo;
import alalic.springframework.spring6reactivemongo.repos.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final BeerRepo beerRepo;
    private final CustomerRepo customerRepo;

    @Override
    public void run(String... args) throws Exception {
        beerRepo.deleteAll()
                .doOnSuccess(success -> {
                    loadBeerData();
                }).subscribe();

        customerRepo.deleteAll()
                .doOnSuccess(success -> loadCustomerData())
                .subscribe();
    }

    private void loadBeerData() {

        beerRepo.count().subscribe(count -> {
            if (count == 0) {
                Beer beer1 = Beer.builder()
                        .beerName("Galaxy Cat")
                        .beerStyle("Pale Ale")
                        .upc("12356")
                        .price(new BigDecimal("12.99"))
                        .quantityOnHand(122)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                Beer beer2 = Beer.builder()
                        .beerName("Crank")
                        .beerStyle("Pale Ale")
                        .upc("12356222")
                        .price(new BigDecimal("11.99"))
                        .quantityOnHand(392)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                Beer beer3 = Beer.builder()
                        .beerName("Sunshine City")
                        .beerStyle("IPA")
                        .upc("12356")
                        .price(new BigDecimal("13.99"))
                        .quantityOnHand(144)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                beerRepo.save(beer1).subscribe(beer -> {
                    System.out.println(beer.toString());
                });
                beerRepo.save(beer2).subscribe(beer -> {
                    System.out.println(beer.toString());
                });
                beerRepo.save(beer3).subscribe(beer -> {
                    System.out.println(beer.toString());
                });

                System.out.println("Loaded Beers: " + beerRepo.count().block());
            }
        });
    }

    private void loadCustomerData() {
        customerRepo.count().subscribe(count -> {
            if(count == 0){
                customerRepo.save(Customer.builder()
                                .customerName("Customer 1")
                                .build())
                        .subscribe();

                customerRepo.save(Customer.builder()
                                .customerName("Customer 2")
                                .build())
                        .subscribe();

                customerRepo.save(Customer.builder()
                                .customerName("Customer 3")
                                .build())
                        .subscribe();
            }
        });
    }
}
