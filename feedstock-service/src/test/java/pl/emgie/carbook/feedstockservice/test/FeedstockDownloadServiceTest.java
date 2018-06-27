package pl.emgie.carbook.feedstockservice.test;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pl.emgie.carbook.feedstockservice.services.price.FeedstockPriceDownloadService;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static pl.emgie.carbook.feedstockservice.test.helpers.FeedstockPriceConstant.*;

public class FeedstockDownloadServiceTest extends AbstractTest {

    @Mock
    private FeedstockPriceDownloadService oilPriceDownloadService;

    @Mock
    private FeedstockPriceDownloadService petrolPriceDownloadService;

    @Mock
    private FeedstockPriceDownloadService gasPriceDownloadService;

    @Mock
    private FeedstockPriceDownloadService dieselPriceDownloadService;

    @Test
    public void downloadOilPriceTest() {
        when(oilPriceDownloadService.getPrice()).thenReturn(OIL_PRICE);

        BigDecimal price = oilPriceDownloadService.getPrice();
        assertThat(price).isNotNull();
        assertThat(price).isEqualTo(OIL_PRICE);
    }

    @Test
    public void downloadGasPriceTest() {
        when(gasPriceDownloadService.getPrice()).thenReturn(GAS_PRICE);

        BigDecimal price = gasPriceDownloadService.getPrice();
        assertThat(price).isNotNull();
        assertThat(price).isEqualTo(GAS_PRICE);
    }

    @Test
    public void downloadPetrolPriceTest() {
        when(petrolPriceDownloadService.getPrice()).thenReturn(PETROL_PRICE);

        BigDecimal price = petrolPriceDownloadService.getPrice();
        assertThat(price).isNotNull();
        assertThat(price).isEqualTo(PETROL_PRICE);
    }

    @Test
    public void downloadDieselPriceTest() {

        when(dieselPriceDownloadService.getPrice()).thenReturn(DIESEL_PRICE);

        BigDecimal price = dieselPriceDownloadService.getPrice();
        assertThat(price).isNotNull();
        assertThat(price).isEqualTo(DIESEL_PRICE);
    }

}
