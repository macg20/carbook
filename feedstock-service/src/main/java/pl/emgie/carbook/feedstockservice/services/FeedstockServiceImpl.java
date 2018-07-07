package pl.emgie.carbook.feedstockservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.emgie.carbook.feedstockservice.domain.FeedstockEntity;
import pl.emgie.carbook.feedstockservice.repositories.FeedstockRepository;
import pl.emgie.carbook.feedstockservice.services.mapping.FeedstocksPricesAndDateDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FeedstockServiceImpl implements FeedstockService {

    private FeedstockRepository repository;

    @Autowired
    public FeedstockServiceImpl(FeedstockRepository repository) {
        this.repository = repository;
    }

    @Override
    public FeedstockEntity saveOrUpdate(FeedstockEntity entity) {
        return null;
    }

    @Override
    public FeedstocksPricesAndDateDto findNewestData() {
        List<FeedstockEntity> feedstocks = repository.findNewestFeedstocksEntity();

        FeedstocksPricesAndDateDto feedstocksPricesAndDateDto = new FeedstocksPricesAndDateDto();
        feedstocks.forEach(x -> getData(feedstocksPricesAndDateDto, x));

        return feedstocksPricesAndDateDto;
    }

    private void getData(FeedstocksPricesAndDateDto feedstocksPricesAndDateDto, FeedstockEntity x) {
        switch (x.getType()) {
            case OIL:
                feedstocksPricesAndDateDto.setOilPrice(x.getPrice());
                feedstocksPricesAndDateDto.setOilLastUpdateDate(getDate(x));
                break;
            case GAS:
                feedstocksPricesAndDateDto.setGasPrice(x.getPrice());
                feedstocksPricesAndDateDto.setGasLastUpdateDate(getDate(x));
                break;
            case DIESEL:
                feedstocksPricesAndDateDto.setDieselPrice(x.getPrice());
                feedstocksPricesAndDateDto.setDieselLastUpdateDate(getDate(x));
                break;
            case PETROL:
                feedstocksPricesAndDateDto.setPetrolPrice(x.getPrice());
                feedstocksPricesAndDateDto.setGasLastUpdateDate(getDate(x));
                break;
        }
    }

    private LocalDateTime getDate(FeedstockEntity entity) {
        return Optional.ofNullable(entity.getUpdateDate()).orElse(entity.getCreateDate());
    }

}
