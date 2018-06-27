package pl.emgie.carbook.feedstockservice.services;

import pl.emgie.carbook.feedstockservice.domain.FeedstockEntity;
import pl.emgie.carbook.feedstockservice.services.mapping.FeedstocksPricesAndDateDto;

public interface FeedstockService {

   FeedstockEntity saveOrUpdate(FeedstockEntity entity);

   FeedstocksPricesAndDateDto findNewestData();
}
