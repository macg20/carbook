package pl.emgie.carbook.feedstockservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseService {

    Logger logger = LoggerFactory.getLogger(getClass());

    public Logger getLogger() {
        return logger;
    }
}
