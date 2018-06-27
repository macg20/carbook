package pl.emgie.carbook.feedstockservice.services;

import org.springframework.stereotype.Service;
import pl.emgie.carbook.feedstockservice.domain.FeedstockEntity;
import pl.emgie.carbook.feedstockservice.repositories.FeedstockRepository;
import pl.emgie.carbook.feedstockservice.services.mapping.FeedstocksPricesAndDateDto;

@Service
public class FeedstockServiceImpl implements FeedstockService {

    private FeedstockRepository repository;

    @Override
    public FeedstockEntity saveOrUpdate(FeedstockEntity entity) {
        return null;
    }

    @Override
    public FeedstocksPricesAndDateDto findNewestData() {
        return null;
    }


}
