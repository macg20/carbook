package pl.emgie.carbook.feedstockservice.services.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.emgie.carbook.feedstockservice.domain.FeedstockEntity;
import pl.emgie.carbook.feedstockservice.repositories.FeedstockRepository;
import pl.emgie.carbook.feedstockservice.services.FeedstockService;
import pl.emgie.carbook.feedstockservice.services.price.FeedstockPriceDownloadService;
import pl.emgie.carbook.feedstockservice.utils.FeedstockType;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
public class FeedstockPriceDownloadScheduler {

    private FeedstockPriceDownloadService oilPriceDownloadService;
    private FeedstockPriceDownloadService gasPriceDownloadService;
    private FeedstockPriceDownloadService dieselPriceDownloadService;
    private FeedstockPriceDownloadService petrolPriceDownloadService;

    private FeedstockRepository feedstockRepository;
    private FeedstockService feedstockService;

    @Autowired
    public FeedstockPriceDownloadScheduler(@Qualifier("oilPriceService") FeedstockPriceDownloadService oilPriceDownloadService,
                                           @Qualifier("gasPriceService") FeedstockPriceDownloadService gasPriceDownloadService,
                                           @Qualifier("dieselPriceService") FeedstockPriceDownloadService dieselPriceDownloadService,
                                           @Qualifier("petrolPriceService") FeedstockPriceDownloadService petrolPriceDownloadService,
                                           FeedstockRepository feedstockRepository,
                                           FeedstockService feedstockService) {
        this.oilPriceDownloadService = oilPriceDownloadService;
        this.gasPriceDownloadService = gasPriceDownloadService;
        this.dieselPriceDownloadService = dieselPriceDownloadService;
        this.petrolPriceDownloadService = petrolPriceDownloadService;
        this.feedstockRepository = feedstockRepository;
        this.feedstockService = feedstockService;
    }

    //    @Scheduled
    public void downloadFeedstockPrices() {
        Set<FeedstockEntity> entities = new HashSet<>();
        entities.add(createFeedstockEntity(FeedstockType.OIL, oilPriceDownloadService.getPrice()));
        entities.add(createFeedstockEntity(FeedstockType.GAS, gasPriceDownloadService.getPrice()));
        entities.add(createFeedstockEntity(FeedstockType.DIESEL, dieselPriceDownloadService.getPrice()));
        entities.add(createFeedstockEntity(FeedstockType.PETROL, petrolPriceDownloadService.getPrice()));

        entities.forEach(feedstockService::saveOrUpdate);
    }

    private FeedstockEntity createFeedstockEntity(FeedstockType type, BigDecimal price) {
        return FeedstockEntity.builder().type(type).price(price).build();
    }
}
