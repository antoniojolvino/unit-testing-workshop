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
public class ChannelsImpl implements ChannelPort {

    public static final int DEFAULT_PAGE_SIZE = 25;
    public static final String MENSAGEM_SEM_RESPOSTA_OPB = "Sem resposta para API do Openbanking na rota de Canais Eletronicos";

    private ChannelsFeign channelsFeign;
    private ElectronicChannelMapper mapper;

    @Override
    public List<Empresa> getEletronicChannels() {

        ResponseElectronicChannels body = channelsFeign.getEletronicChannels(null).getBody();

        Assert.notNull(body, MENSAGEM_SEM_RESPOSTA_OPB);

        String totalRecords = body.getMeta().getTotalRecords();

        if (Integer.parseInt(totalRecords) > DEFAULT_PAGE_SIZE) {
            body = channelsFeign.getEletronicChannels(totalRecords).getBody();
            Assert.notNull(body, MENSAGEM_SEM_RESPOSTA_OPB);
        }

        return mapper.companyToEmpresa(body.getData().getBrand().getCompanies());
    }
}
