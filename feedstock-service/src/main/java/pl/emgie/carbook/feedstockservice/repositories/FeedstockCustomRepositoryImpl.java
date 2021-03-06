package pl.emgie.carbook.feedstockservice.repositories;

import org.springframework.stereotype.Repository;
import pl.emgie.carbook.feedstockservice.domain.FeedstockEntity;
import pl.emgie.carbook.feedstockservice.domain.FeedstockEntity_;
import pl.emgie.carbook.feedstockservice.utils.FeedstockType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FeedstockCustomRepositoryImpl implements FeedstockCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

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
    public List<FeedstockEntity> findFeedstockByDateForAllTypes(LocalDateTime date) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(FeedstockEntity.class);

        Root<FeedstockEntity> root = criteriaQuery.from(FeedstockEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        Predicate startDate = criteriaBuilder.greaterThanOrEqualTo(root.get(FeedstockEntity_.createDate), date.toLocalDate().atStartOfDay());
        Predicate endDate = criteriaBuilder.lessThanOrEqualTo(root.get(FeedstockEntity_.createDate), date.toLocalDate().atTime(LocalTime.MAX));
        predicates.add(startDate);
        predicates.add(endDate);

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));


        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public FeedstockEntity findNewestFeedstockByType(FeedstockType type) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(FeedstockEntity.class);

        Root<FeedstockEntity> root = criteriaQuery.from(FeedstockEntity.class);

        Subquery subquery = criteriaQuery.subquery(FeedstockEntity.class);
        Root<FeedstockEntity> subRoot = subquery.from(FeedstockEntity.class);
        subquery.select(criteriaBuilder.greatest(subRoot.get((FeedstockEntity_.createDate))));
        subquery.where(criteriaBuilder.equal(subRoot.get(FeedstockEntity_.type),type));

        criteriaQuery.where(root.get(FeedstockEntity_.createDate).in(subquery), criteriaBuilder.equal(root.get(FeedstockEntity_.type),type));


        return (FeedstockEntity) entityManager.createQuery(criteriaQuery).setMaxResults(1).getSingleResult();
    }

    @Override
    public List<FeedstockEntity> findNewestFeedstocksEntity() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(FeedstockEntity.class);

        Root<FeedstockEntity> root = criteriaQuery.from(FeedstockEntity.class);

        Subquery subquery = criteriaQuery.subquery(FeedstockEntity.class);
        Root<FeedstockEntity> subRoot = subquery.from(FeedstockEntity.class);
        subquery.select(criteriaBuilder.greatest(subRoot.get((FeedstockEntity_.createDate))));
        subquery.groupBy(subRoot.get(FeedstockEntity_.type));

        criteriaQuery.where(root.get(FeedstockEntity_.createDate).in(subquery));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }


}
