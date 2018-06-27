package pl.emgie.carbook.feedstockservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.emgie.carbook.feedstockservice.services.price.DieselPriceDownloadService;
import pl.emgie.carbook.feedstockservice.services.price.GasPriceDownloadService;
import pl.emgie.carbook.feedstockservice.services.price.OilPriceDownloadService;
import pl.emgie.carbook.feedstockservice.services.price.PetrolPriceDownloadService;

@SpringBootApplication
public class FeedstockServiceApplication implements CommandLineRunner {

    @Autowired
    private OilPriceDownloadService oilPriceDownloadService;
    @Autowired
    private PetrolPriceDownloadService petrolPriceDownloadService;
    @Autowired
    private GasPriceDownloadService gasPriceDownloadService;
    @Autowired
    private DieselPriceDownloadService dieselPriceDownloadService;

    public static void main(String[] args) {
        SpringApplication.run(FeedstockServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        oilPriceDownloadService.getPrice();
//        dieselPriceDownloadService.getPrice();
//        petrolPriceDownloadService.getPrice();
//        gasPriceDownloadService.getPrice();
    }
}
