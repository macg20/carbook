package pl.emgie.carbook.feedstockservice.services.price;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.emgie.carbook.feedstockservice.services.BaseService;
import pl.emgie.commons.exceptions.ApplicationException;
import pl.emgie.commons.utils.StringUtils;

import java.math.BigDecimal;

@Service
@Qualifier("petrolPriceService")
public class PetrolPriceDownloadService extends BaseService implements FeedstockPriceDownloadService {
    private String downloadPageUrl;

    public PetrolPriceDownloadService(@Value("${feedstock.fuel.page.url}") String downloadPageUrl) {
        this.downloadPageUrl = downloadPageUrl;
    }

    @Override
    public BigDecimal getPrice() {

        try {
            Document document = Jsoup.connect(downloadPageUrl).get();
            Elements div = document.getElementsByClass("kontener-text");
            Element table = div.last();
            Elements rows = table.select("tr");
            return new BigDecimal(StringUtils.replaceCommaToDot(rows.get(2).select("td").get(2).text()));
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }
}
