package br.com.itau.unittestingworkshop.core.domain;

import lombok.Data;

import java.util.List;

@Data
public class Empresa {
    private String nome;
    private List<CanalEletronico> canaisEletronicos;
}
