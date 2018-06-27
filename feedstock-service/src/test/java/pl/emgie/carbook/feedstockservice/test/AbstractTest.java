package pl.emgie.carbook.feedstockservice.test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.emgie.carbook.feedstockservice.FeedstockServiceApplication;

@SpringBootTest(classes = FeedstockServiceApplication.class)
@ExtendWith(SpringExtension.class)
public abstract class AbstractTest {
}
