package com.news.command;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.news.http.LocalNewsApi;
import com.news.model.LocalNews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalNewsHystrixCommand extends HystrixCommand<LocalNews> {

    private static final Logger LOG = LoggerFactory.getLogger(LocalNewsHystrixCommand.class);

    private LocalNewsApi localNewsApi;
    private String city;

    public LocalNewsHystrixCommand(LocalNewsApi localNewsApi, String city) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("LocalNews"))
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                    .withExecutionTimeoutInMilliseconds(5000)));
        this.localNewsApi = localNewsApi;
        this.city = city;
    }

    @Override
    protected LocalNews run() throws Exception {
        LOG.debug("Inside Local news command: {}", city);
        return this.localNewsApi.getLocalNewsForCity(city).execute().body();
    }

    @Override
    protected LocalNews getFallback() {
        return LocalNews.NO_NEWS;
    }
}
