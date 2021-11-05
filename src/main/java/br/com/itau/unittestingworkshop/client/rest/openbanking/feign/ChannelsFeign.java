package br.com.itau.unittestingworkshop.client.rest.openbanking.feign;

import br.com.itau.unittestingworkshop.client.rest.openbanking.dto.ResponseElectronicChannels;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "OpenbankingChannels", url = "https://api.itau/open-banking/channels/v1")
public interface ChannelsFeign {
    @GetMapping(value = "/electronic-channels", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseElectronicChannels> getCompaniesEletronicChannels(@RequestParam("page-size") String pageSize);
}
