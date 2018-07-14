package pl.emgie.carbook.feedstockservice.services.mapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FeedstockDto {

    private BigDecimal price;
    private LocalDateTime lastUpdateDate;
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
