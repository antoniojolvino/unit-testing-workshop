package br.com.itau.unittestingworkshop.core.service;

import br.com.itau.unittestingworkshop.core.domain.Empresa;
import br.com.itau.unittestingworkshop.core.domain.TipoCanal;
import br.com.itau.unittestingworkshop.core.port.ChannelPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WorkshopService {
    private ChannelPort channelPort;

    public List<Empresa> getEmpresas(TipoCanal tipoCanal) {
        List<Empresa> empresas = channelPort.getCompaniesEletronicChannels();
        try {
            Assert.notNull(tipoCanal, "Tipo do canal não informado");
            //Filtra as empresas que contem o tipo de canal informado e depois captura somente o canal informado da lista de canais
            return empresas.stream().filter(
                            emp -> emp.getCanaisEletronicos().stream().anyMatch(
                                    can -> can.getTipo().equals(tipoCanal.name())
                            )
                    ).peek(company ->
                            company.setCanaisEletronicos(company.getCanaisEletronicos().stream().filter(
                                            eletronicChannel ->
                                                    eletronicChannel.getTipo().equals(tipoCanal.name()))
                                    .collect(Collectors.toList())))
                    .collect(Collectors.toList());
        } catch (Throwable t) {
            //Devolve a lista de empresas com todos os canais
            return empresas;
        }
    }
}
