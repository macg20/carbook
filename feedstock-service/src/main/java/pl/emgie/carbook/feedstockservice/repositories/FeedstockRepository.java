package pl.emgie.carbook.feedstockservice.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.emgie.carbook.feedstockservice.domain.FeedstockEntity;

import java.math.BigInteger;

public interface FeedstockRepository extends CrudRepository<FeedstockEntity, BigInteger>, FeedstockCustomRepository {
}
