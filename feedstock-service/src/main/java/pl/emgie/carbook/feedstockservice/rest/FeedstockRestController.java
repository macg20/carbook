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
import pl.emgie.carbook.feedstockservice.utils.FeedstockType;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/feedstock")
public class FeedstockRestController extends BaseService {

    private FeedstockService feedstockService;

    @Autowired
    public FeedstockRestController(FeedstockService feedstockService) {
        this.feedstockService = feedstockService;
    }

    @GetMapping("/prices/")
    public List<FeedstockDto> findNewestFeedstocksPrices() {
        List<FeedstockDto> dtos = feedstockService.findNewestData();
        return dtos;
    }

    @GetMapping("/prices/type/{type}/date/{date}")
    public FeedstockDto findDataByTypeAndDate(@PathVariable("type") String type, @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        FeedstockDto dto = feedstockService.findFeedstockByDateAndType(FeedstockType.valueOf(type), date);
        return dto;
    }

    @GetMapping("/prices/type/{type}")
    public FeedstockDto findNewestFeedstockPriceByType(@PathVariable("type") String type) {
        FeedstockDto dto = feedstockService.findNewestFeedstockPriceByType(FeedstockType.valueOf(type));
        return dto;
    }

    @GetMapping("/prices/date/{date}")
    public List<FeedstockDto> findFeedstockPriceByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<FeedstockDto> dtos = feedstockService.findFeedstockByDate(date);
        return dtos;
    }
}
