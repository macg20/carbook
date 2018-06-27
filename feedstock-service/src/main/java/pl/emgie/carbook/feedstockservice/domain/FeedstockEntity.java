package pl.emgie.carbook.feedstockservice.domain;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import pl.emgie.carbook.feedstockservice.utils.FeedstockType;
import pl.emgie.commons.converters.LocalDateTimeConverter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedstocks")
@DynamicUpdate
@GenericGenerator(name = "feedstock_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "feedstock_sequence"),
                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
        })
public class FeedstockEntity {

    @Id
    @GeneratedValue(generator = "feedstock_generator")
    private BigInteger id;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private FeedstockType type;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createDate;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime updateDate;

    @Version
    private Long version;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public java.math.BigDecimal getPrice() {
        return price;
    }

    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
    }

    public FeedstockType getType() {
        return type;
    }

    public void setType(FeedstockType type) {
        this.type = type;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @PrePersist
    private void prePersist() {
        this.createDate = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.updateDate = LocalDateTime.now();
    }

    public static NeedType builder() {
        return new FeedstockBuilder();
    }

    public static class FeedstockBuilder implements NeedType, NeedPrice, Build {

        private BigDecimal price;
        private FeedstockType type;

        @Override
        public Build price(BigDecimal price) {
            this.price = price;
            return this;
        }

        @Override
        public NeedPrice type(FeedstockType type) {
            this.type = type;
            return this;
        }

        @Override
        public FeedstockEntity build() {
            FeedstockEntity entity = new FeedstockEntity();
            entity.setType(this.type);
            entity.setPrice(this.price);
            return entity;
        }

    }

    public interface NeedPrice {

        Build price(BigDecimal price);
    }

    public interface NeedType {
        NeedPrice type(FeedstockType type);
    }

    public interface Build {

        FeedstockEntity build();
    }
}
