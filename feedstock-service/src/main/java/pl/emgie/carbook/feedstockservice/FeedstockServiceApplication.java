package pl.emgie.carbook.feedstockservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.emgie.carbook.feedstockservice.repositories.FeedstockRepository;

@SpringBootApplication
public class FeedstockServiceApplication implements CommandLineRunner {

    @Autowired
    FeedstockRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(FeedstockServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.findNewestFeedstocksEntity();
    }
}
