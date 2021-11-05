package br.com.itau.unittestingworkshop.core.port;

import br.com.itau.unittestingworkshop.core.domain.Empresa;

import java.util.List;

public interface ChannelPort {
    List<Empresa> getCompaniesEletronicChannels();
}
