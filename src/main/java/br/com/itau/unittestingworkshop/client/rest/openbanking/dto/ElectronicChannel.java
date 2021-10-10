package br.com.itau.unittestingworkshop.client.rest.openbanking.dto;

import lombok.Data;

import java.util.List;

@Data
public class ElectronicChannel {
    private Identification identification;
    private List<Service> services;
}
