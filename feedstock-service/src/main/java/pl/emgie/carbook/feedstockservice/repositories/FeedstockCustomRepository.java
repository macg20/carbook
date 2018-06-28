package pl.emgie.carbook.feedstockservice.repositories;

import pl.emgie.carbook.feedstockservice.domain.FeedstockEntity;
import pl.emgie.carbook.feedstockservice.utils.FeedstockType;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface FeedstockCustomRepository {

    FeedstockEntity findFeedstockByDateAndType(FeedstockType type, LocalDateTime date);

    List<FeedstockEntity> findFeedstockByDateForAllTypes(LocalDateTime date);

    FeedstockEntity findNewestFeedstockByType(FeedstockType type);

    List<FeedstockEntity> findNewestFeedstocksEntity();


}
