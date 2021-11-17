package br.com.itau.unittestingworkshop.client.rest.openbanking;

import br.com.itau.unittestingworkshop.client.rest.openbanking.dto.ResponseElectronicChannels;
import br.com.itau.unittestingworkshop.client.rest.openbanking.feign.ChannelsFeign;
import br.com.itau.unittestingworkshop.client.rest.openbanking.mapper.ElectronicChannelMapper;
import br.com.itau.unittestingworkshop.core.domain.Empresa;
import br.com.itau.unittestingworkshop.core.port.ChannelPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@AllArgsConstructor
public class ChannelsClientImpl implements ChannelPort {

    public static final String MENSAGEM_SEM_RESPOSTA_OPB = "Sem resposta para API do Openbanking na rota de Canais Eletronicos";
    public static final String _1 = "1";

    private ChannelsFeign channelsFeign;
    private ElectronicChannelMapper mapper;

    @Override
    public List<Empresa> getCompaniesEletronicChannels() {

        ResponseElectronicChannels body = channelsFeign.getCompaniesEletronicChannels(null).getBody();

        Assert.notNull(body, MENSAGEM_SEM_RESPOSTA_OPB);

        if (!_1.equals(body.getMeta().getTotalPages())) {
            String totalRecords = body.getMeta().getTotalRecords();
            body = channelsFeign.getCompaniesEletronicChannels(totalRecords).getBody();
            Assert.notNull(body, MENSAGEM_SEM_RESPOSTA_OPB);
        }

        return mapper.companyToEmpresa(body.getData().getBrand().getCompanies());
    }
}
