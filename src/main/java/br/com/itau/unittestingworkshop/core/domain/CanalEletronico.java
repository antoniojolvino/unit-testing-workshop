package br.com.itau.unittestingworkshop.core.domain;

import lombok.Data;

import java.util.List;

@Data
public class CanalEletronico {
    String tipo;
    String informacaoAdicional;
    List<String> servicos;
}
