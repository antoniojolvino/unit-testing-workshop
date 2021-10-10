package br.com.itau.unittestingworkshop.client.rest.openbanking.dto;

import lombok.Data;

import java.util.List;

@Data
public class Brand {
    private List<Company> companies;
    private String name;
}
