package br.com.itau.unittestingworkshop.client.rest.openbanking.dto;

import lombok.Data;

@Data
public class Links {
    private String first;
    private String last;
    private String next;
    private String prev;
    private String self;
}
