package pl.emgie.carbook.feedstockservice.services.price;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.emgie.carbook.feedstockservice.services.BaseService;
import pl.emgie.commons.exceptions.ApplicationException;

import java.math.BigDecimal;

@Service
@Qualifier("oilPriceService")
public class OilPriceDownloadService extends BaseService implements FeedstockPriceDownloadService {

    private String downloadPageUrl;

    public OilPriceDownloadService(@Value("${feedstock.oil.page.url}") String downloadPageUrl) {
        this.downloadPageUrl = downloadPageUrl;
    }

    @Override
    public BigDecimal getPrice() {
        Document document = null;
        try {
            document = Jsoup.connect(downloadPageUrl).get();
            return new BigDecimal(getElement(document).text());
        } catch (Exception e) {
            getLogger().error("Retrieve price by other way", e);
            try {
                Element strong = getElement(document).selectFirst("b");
                BigDecimal price = new BigDecimal(strong.text().replace(",", "."));
                getLogger().error("Downloaded price by other way");
                return price;
            } catch (Exception excep) {
                getLogger().error(excep.getMessage(), excep);
                throw new ApplicationException(e);
            }
        }
    }

    private Element getElement(Document document) {
        Element tr = document.getElementById("oil");
        Element td = tr.getElementsByClass("ar").first();
        return td;
    }
}
