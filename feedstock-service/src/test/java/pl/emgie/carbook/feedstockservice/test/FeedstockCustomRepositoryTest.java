package pl.emgie.carbook.feedstockservice.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import pl.emgie.carbook.feedstockservice.domain.FeedstockEntity;
import pl.emgie.carbook.feedstockservice.repositories.FeedstockRepository;
import pl.emgie.carbook.feedstockservice.utils.FeedstockType;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;


public class FeedstockCustomRepositoryTest extends AbstractTest {

    @Autowired
    FeedstockRepository repository;

    @Test
    @Transactional
    @Rollback
    public void findFeedstockEntityByTypeAndCreateDateTest() {
        // given
        FeedstockEntity mockEntity = createMockFeedstockEntityWithOilType();
        repository.save(mockEntity);

        //when
        FeedstockEntity fromDb = repository.findFeedstockByDateAndType(FeedstockType.OIL, LocalDate.now().atStartOfDay());

        //then
        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getCreateDate()).isNotNull();
        assertThat(fromDb.getUpdateDate()).isNull();

    }

    private FeedstockEntity createMockFeedstockEntityWithOilType() {
        return FeedstockEntity
                .builder()
                .type(FeedstockType.OIL)
                .price(BigDecimal.valueOf(78.8d))
                .build();
    }
}
