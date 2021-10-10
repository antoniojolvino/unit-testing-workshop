package br.com.itau.unittestingworkshop.client.rest.openbanking.dto;

import lombok.Data;

import java.util.List;

@Data
public class Company {
    private String cnpjNumber;
    private List<ElectronicChannel> electronicChannels;
    private String name;
    private String urlComplementaryList;
}
