package com.marcinski.complaintquery.geo;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
class IpApiComService implements CityLocator {
    private static final String UNKNOWN = "UNKNOWN";
    private final RestTemplate restTemplate;

    public IpApiComService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String getCityByIp(String ip) {
        String url = String.format("http://ip-api.com/json/%s", ip);
        String country = UNKNOWN;
        try {
            IpApiResponse response = restTemplate.getForObject(url, IpApiResponse.class);
            if (response != null && "success".equalsIgnoreCase(response.status())) {
                country = response.country();
            }
        } catch (RestClientException e) {
            // loguj błąd, jeśli potrzebujesz
        }
        return country;
    }

}
