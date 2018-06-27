package pl.emgie.carbook.feedstockservice.repositories;

import pl.emgie.carbook.feedstockservice.domain.FeedstockEntity;
import pl.emgie.carbook.feedstockservice.domain.FeedstockEntity_;
import pl.emgie.carbook.feedstockservice.utils.FeedstockType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FeedstockCustomRepositoryImpl implements FeedstockCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public FeedstockEntity findFeedstockByDateAndType(FeedstockType type, LocalDateTime createDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(FeedstockEntity.class);

        Root<FeedstockEntity> root = criteriaQuery.from(FeedstockEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        Predicate startDate = criteriaBuilder.greaterThanOrEqualTo(root.get(FeedstockEntity_.createDate), createDate.toLocalDate().atStartOfDay());
        Predicate endDate = criteriaBuilder.lessThanOrEqualTo(root.get(FeedstockEntity_.createDate), createDate.toLocalDate().atTime(LocalTime.MAX));
        Predicate feedstockType = criteriaBuilder.equal(root.get(FeedstockEntity_.type), type);
        predicates.add(startDate);
        predicates.add(endDate);
        predicates.add(feedstockType);
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));
        return (FeedstockEntity) entityManager.createQuery(criteriaQuery).setMaxResults(1).getSingleResult();
    }

    @Override
    public Collection<FeedstockEntity> findFeedstockByDateForAllTypes(LocalDateTime date) {
        return null;
    }

    @Override
    public FeedstockEntity findNewestFeedstockByType(FeedstockType type) {
        return null;
    }

    @Override
    public List<FeedstockEntity> findNewestFeedstock() {
        return null;
    }


}
