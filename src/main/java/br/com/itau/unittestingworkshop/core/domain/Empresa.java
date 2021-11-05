package br.com.itau.unittestingworkshop.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {
    private String nome;
    private List<CanalEletronico> canaisEletronicos;
}
