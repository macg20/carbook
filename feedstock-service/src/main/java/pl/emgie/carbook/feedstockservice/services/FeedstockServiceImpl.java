package pl.emgie.carbook.feedstockservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.emgie.carbook.feedstockservice.domain.FeedstockEntity;
import pl.emgie.carbook.feedstockservice.repositories.FeedstockRepository;
import pl.emgie.carbook.feedstockservice.services.mapping.FeedstockDto;
import pl.emgie.carbook.feedstockservice.utils.FeedstockType;
import pl.emgie.commons.exceptions.ResourceNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedstockServiceImpl implements FeedstockService {

    private FeedstockRepository repository;

    @Autowired
    public FeedstockServiceImpl(FeedstockRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public FeedstockEntity saveOrUpdate(FeedstockEntity entity) {
        FeedstockEntity fromDb = repository.findFeedstockByDateAndType(entity.getType(), LocalDateTime.now());
        if (fromDb == null) {
            return repository.save(entity);
        }
        fromDb.setPrice(entity.getPrice());
        repository.save(fromDb);
        return fromDb;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedstockDto> findNewestData() {
        List<FeedstockEntity> feedstocks = repository.findNewestFeedstocksEntity();
        List<FeedstockDto> dtos = feedstocks.stream().map(this::mapToDto).collect(Collectors.toList());
        if (dtos != null && !dtos.isEmpty())
            return dtos;
        throw new ResourceNotFoundException("Resource not found");
    }


    @Override
    @Transactional(readOnly = true)
    public FeedstockDto findFeedstockByDateAndType(FeedstockType type, LocalDate date) {
        FeedstockEntity entity = repository.findFeedstockByDateAndType(type, date.atStartOfDay());
        if (entity != null)
            return mapToDto(entity);
        throw new ResourceNotFoundException("Resource not found");
    }

    @Override
    @Transactional(readOnly = true)
    public FeedstockDto findNewestFeedstockPriceByType(FeedstockType type) {
        FeedstockEntity entity = repository.findNewestFeedstockByType(type);
        if (entity != null)
            return mapToDto(entity);
        throw new ResourceNotFoundException("Resource not found");
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedstockDto> findFeedstockByDate(LocalDate date) {
        List<FeedstockEntity> feedstocks = repository.findFeedstockByDateForAllTypes(date.atStartOfDay());
        List<FeedstockDto> dtos = feedstocks.stream().map(this::mapToDto).collect(Collectors.toList());

        if (dtos != null && !dtos.isEmpty())
            return dtos;
        throw new ResourceNotFoundException("Resource not found");
    }

    private LocalDateTime getDate(FeedstockEntity entity) {
        return Optional.ofNullable(entity.getUpdateDate()).orElse(entity.getCreateDate());
    }

    private FeedstockDto mapToDto(FeedstockEntity entity) {
        FeedstockDto dto = new FeedstockDto();
        dto.setLastUpdateDate(getDate(entity));
        dto.setPrice(entity.getPrice());
        dto.setType(entity.getType().getName());
        return dto;
    }

}
