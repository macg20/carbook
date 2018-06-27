package pl.emgie.carbook.feedstockservice.services.mapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FeedstocksPricesAndDateDto {

    private BigDecimal oilPrice;
    private LocalDateTime oilLastUpdateDate;

    private BigDecimal dieselPrice;
    private LocalDateTime dieselLastUpdateDate;

    private BigDecimal petrolPrice;
    private LocalDateTime petrolLastUpdateDate;

    private BigDecimal gasPrice;
    private LocalDateTime gasLastUpdateDate;

    public BigDecimal getOilPrice() {
        return oilPrice;
    }

    public void setOilPrice(BigDecimal oilPrice) {
        this.oilPrice = oilPrice;
    }

    public LocalDateTime getOilLastUpdateDate() {
        return oilLastUpdateDate;
    }

    public void setOilLastUpdateDate(LocalDateTime oilLastUpdateDate) {
        this.oilLastUpdateDate = oilLastUpdateDate;
    }

    public BigDecimal getDieselPrice() {
        return dieselPrice;
    }

    public void setDieselPrice(BigDecimal dieselPrice) {
        this.dieselPrice = dieselPrice;
    }

    public LocalDateTime getDieselLastUpdateDate() {
        return dieselLastUpdateDate;
    }

    public void setDieselLastUpdateDate(LocalDateTime dieselLastUpdateDate) {
        this.dieselLastUpdateDate = dieselLastUpdateDate;
    }

    public BigDecimal getPetrolPrice() {
        return petrolPrice;
    }

    public void setPetrolPrice(BigDecimal petrolPrice) {
        this.petrolPrice = petrolPrice;
    }

    public LocalDateTime getPetrolLastUpdateDate() {
        return petrolLastUpdateDate;
    }

    public void setPetrolLastUpdateDate(LocalDateTime petrolLastUpdateDate) {
        this.petrolLastUpdateDate = petrolLastUpdateDate;
    }

    public BigDecimal getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(BigDecimal gasPrice) {
        this.gasPrice = gasPrice;
    }

    public LocalDateTime getGasLastUpdateDate() {
        return gasLastUpdateDate;
    }

    public void setGasLastUpdateDate(LocalDateTime gasLastUpdateDate) {
        this.gasLastUpdateDate = gasLastUpdateDate;
    }
}
