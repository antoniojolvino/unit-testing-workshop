package br.com.itau.unittestingworkshop.client.rest.openbanking.mapper;

import br.com.itau.unittestingworkshop.client.rest.openbanking.dto.Company;
import br.com.itau.unittestingworkshop.client.rest.openbanking.dto.ElectronicChannel;
import br.com.itau.unittestingworkshop.client.rest.openbanking.dto.Service;
import br.com.itau.unittestingworkshop.core.domain.CanalEletronico;
import br.com.itau.unittestingworkshop.core.domain.Empresa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ElectronicChannelMapper {

    List<Empresa> companyToEmpresa(List<Company> companies);

    @Mapping(source = "name", target = "nome")
    @Mapping(source = "electronicChannels", target = "canaisEletronicos")
    Empresa companyToEmpresa(Company company);

    List<CanalEletronico> electronicChannelToCanalEletronico(List<ElectronicChannel> electronicChannels);

    @Mappings(
            {
                    @Mapping(source = "identification.type", target = "tipo"),
                    @Mapping(source = "identification.additionalInfo", target = "informacaoAdicional"),
                    @Mapping(source = "services", target = "servicos")
            }
    )
    CanalEletronico electronicChannelToCanalEletronico(ElectronicChannel electronicChannel);

    default List<String> servicesToStrings(List<Service> services) {
        return services.stream().map(Service::getName).collect(Collectors.toList());
    }
}
