package pl.emgie.carbook.feedstockservice.services.mapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FeedstockDto {

    private BigDecimal price;
    private LocalDateTime lastUpdateDate;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
