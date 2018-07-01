package pl.emgie.carbook.feedstockservice.test;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import pl.emgie.carbook.feedstockservice.domain.FeedstockEntity;
import pl.emgie.carbook.feedstockservice.repositories.FeedstockRepository;
import pl.emgie.carbook.feedstockservice.utils.FeedstockType;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static pl.emgie.carbook.feedstockservice.test.helpers.FeedstockPriceConstant.*;


public class FeedstockCustomRepositoryTest extends AbstractTest {

    @Autowired
    FeedstockRepository repository;

    @Mock
    FeedstockRepository mockRepository;

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

    @Test
    public void findMockNewestFeedstockDataTest() {

        when(mockRepository.findNewestFeedstocksEntity()).thenReturn(createNewestFeedstocksCollection());

        List<FeedstockEntity> result = mockRepository.findNewestFeedstocksEntity();
        verify(mockRepository).findNewestFeedstocksEntity();
        verify(mockRepository, times(1)).findNewestFeedstocksEntity();

        assertThat(result.size()).isEqualTo(4);
    }

    @Test
    @Transactional
    @Rollback
    public void findNewestFeedstockDataTest() {
        // given
        List<FeedstockEntity> entities = createNewestData();
        repository.saveAll(entities);

        //when
        List<FeedstockEntity> result = repository.findNewestFeedstocksEntity();
        List<FeedstockType> resultType = result.stream().map(x -> x.getType()).collect(Collectors.toList());


        //then
        assertThat(result.size()).isEqualTo(4);
        Arrays
                .stream(FeedstockType.values())
                .forEach(
                        x -> assertThat(resultType.contains(x)).isTrue()
                );
        result.forEach(x -> {
            assertThat(x.getCreateDate().toLocalDate()).isEqualTo(LocalDate.now());
        });
    }


    private List<FeedstockEntity> createNewestFeedstocksCollection() {
        return Arrays
                .asList(
                        createMockFeedstockEntityWithOilType(),
                        createMockFeedstockEntityWithDieselType(),
                        createMockFeedstockEntityWithPetrolType(),
                        createMockFeedstockEntityWithGasType()
                );
    }


    private FeedstockEntity createMockFeedstockEntityWithOilType() {
        return createFeedstockEntityByTypeAndPrice(FeedstockType.OIL, OIL_PRICE);
    }

    private FeedstockEntity createMockFeedstockEntityWithDieselType() {
        return createFeedstockEntityByTypeAndPrice(FeedstockType.DIESEL, DIESEL_PRICE);
    }

    private FeedstockEntity createMockFeedstockEntityWithPetrolType() {
        return createFeedstockEntityByTypeAndPrice(FeedstockType.PETROL, PETROL_PRICE);
    }

    private FeedstockEntity createMockFeedstockEntityWithGasType() {
        return createFeedstockEntityByTypeAndPrice(FeedstockType.GAS, GAS_PRICE);
    }

    private List<FeedstockEntity> createNewestData() {
        List<FeedstockEntity> entities = new ArrayList<>();
        entities.addAll(createNewestFeedstocksCollection());
        entities.addAll(createNewestFeedstocksYesterdayCollection());
        entities.addAll(createNewestFeedstocksDayBeforeYesterdayCollection());
        return entities;
    }

    private List<FeedstockEntity> createNewestFeedstocksYesterdayCollection() {
        List<FeedstockEntity> yesterdayCollection = Arrays
                .asList(
                        createMockFeedstockEntityWithOilType(),
                        createMockFeedstockEntityWithDieselType(),
                        createMockFeedstockEntityWithPetrolType(),
                        createMockFeedstockEntityWithGasType()
                );
        yesterdayCollection.forEach(entity -> entity.setCreateDate(LocalDateTime.now().minus(1, ChronoUnit.DAYS)));
        return yesterdayCollection;
    }

    private List<FeedstockEntity> createNewestFeedstocksDayBeforeYesterdayCollection() {
        List<FeedstockEntity> dayBeforeYesterdayCollection = Arrays
                .asList(
                        createMockFeedstockEntityWithOilType(),
                        createMockFeedstockEntityWithDieselType(),
                        createMockFeedstockEntityWithPetrolType(),
                        createMockFeedstockEntityWithGasType()
                );
        dayBeforeYesterdayCollection.forEach(entity -> entity.setCreateDate(LocalDateTime.now().minus(2, ChronoUnit.DAYS)));
        return dayBeforeYesterdayCollection;

    }

    private FeedstockEntity createFeedstockEntityByTypeAndPrice(FeedstockType type, BigDecimal price) {
        return FeedstockEntity
                .builder()
                .type(type)
                .price(price)
                .build();
    }
}
