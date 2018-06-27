package pl.emgie.carbook.feedstockservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.emgie.carbook.feedstockservice.services.BaseService;
import pl.emgie.carbook.feedstockservice.services.FeedstockService;
import pl.emgie.carbook.feedstockservice.services.mapping.FeedstockDto;
import pl.emgie.carbook.feedstockservice.services.mapping.FeedstocksPricesAndDateDto;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/feedstock")
public class FeedstockRestController extends BaseService {

    private FeedstockService feedstockService;

    @Autowired
    public FeedstockRestController(FeedstockService feedstockService) {
        this.feedstockService = feedstockService;
    }

    @GetMapping("/prices/")
    public FeedstocksPricesAndDateDto getNewestFeedstocksPrices() {
        getLogger().error("getNewestFeedstocksPrices");
//        FeedstocksPricesAndDateDto dto = feedstockService.findNewestData();
        return new FeedstocksPricesAndDateDto();
    }

    @GetMapping("/prices/type/{type}/date/{date}")
    public FeedstockDto getDataByTypeAndDate(@PathVariable("type") String type, @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        getLogger().error("getDataByTypeAndDate");
//        FeedstocksPricesAndDateDto dto = feedstockService.findNewestData();
        //findFeedstockByDateAndType
        return new FeedstockDto();
    }

    @GetMapping("/prices/type/{type}")
    public FeedstockDto getNewestFeedstockPriceByType(@PathVariable("type") String type) {
        getLogger().error("getNewestFeedstockPrice");
//findNewestFeedstockByType
//        FeedstocksPricesAndDateDto dto = feedstockService.findNewestData();
        return new FeedstockDto();
    }

    @GetMapping("/prices/date/{date}")
    public FeedstocksPricesAndDateDto getNewestFeedstockPriceByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        getLogger().error("getNewestFeedstockPriceByDate");
//        FeedstocksPricesAndDateDto dto = feedstockService.findNewestData();
        //findFeedstockByDateForAllTypes
        return new FeedstocksPricesAndDateDto();
    }
}
