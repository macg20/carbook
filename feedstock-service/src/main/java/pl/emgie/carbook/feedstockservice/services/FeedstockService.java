package pl.emgie.carbook.feedstockservice.services;

import pl.emgie.carbook.feedstockservice.domain.FeedstockEntity;
import pl.emgie.carbook.feedstockservice.services.mapping.FeedstockDto;
import pl.emgie.carbook.feedstockservice.utils.FeedstockType;

import java.time.LocalDate;
import java.util.List;

public interface FeedstockService {

    FeedstockEntity saveOrUpdate(FeedstockEntity entity);

    List<FeedstockDto> findNewestData();

    FeedstockDto findFeedstockByDateAndType(FeedstockType type, LocalDate date);

    FeedstockDto findNewestFeedstockPriceByType(FeedstockType type);

    List<FeedstockDto> findFeedstockByDate(LocalDate date);
}
