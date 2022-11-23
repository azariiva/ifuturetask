package ru.spbifuture.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "test.config")
public class TestConfigurationProperties {

    private Integer rCount;
    private Integer wCount;
    private List<Integer> idList;

    public Integer getrCount() {
        return rCount;
    }

    public void setrCount(Integer rCount) {
        this.rCount = rCount;
    }

    public Integer getwCount() {
        return wCount;
    }

    public void setwCount(Integer wCount) {
        this.wCount = wCount;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }
}
