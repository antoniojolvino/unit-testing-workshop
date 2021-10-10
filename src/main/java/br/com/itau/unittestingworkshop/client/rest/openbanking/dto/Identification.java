package br.com.itau.unittestingworkshop.client.rest.openbanking.dto;

import lombok.Data;

import java.util.List;

@Data
public class Identification {
    private String additionalInfo;
    private String type;
    private List<String> urls;
}
