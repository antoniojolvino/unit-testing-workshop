package br.com.itau.unittestingworkshop.core.service;

import br.com.itau.unittestingworkshop.core.domain.Empresa;
import br.com.itau.unittestingworkshop.core.port.ChannelPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WorkshopService {

    private static final String MOBILE_BANKING = "MOBILE_BANKING";

    private ChannelPort channelPort;

    public List<Empresa> getEmpresasMobile() {

        List<Empresa> eletronicChannels = channelPort.getEletronicChannels();

        return eletronicChannels.stream().filter(
                emp -> emp.getCanaisEletronicos().stream().anyMatch(
                        can -> can.getTipo().equals(MOBILE_BANKING)
                )
        ).collect(Collectors.toList());
    }
}
