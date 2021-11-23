package br.com.itau.unittestingworkshop.client.rest.openbanking.mapper;

import br.com.itau.unittestingworkshop.core.domain.Empresa;
import br.com.itau.unittestingworkshop.mocks.OpbMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = ElectronicChannelMapperImpl.class)
class ElectronicChannelMapperTest {

    @Autowired
    ElectronicChannelMapper mapper;

    @Test
    public void testaExecucaoComJsonVariado(){
        List<Empresa> empresas = mapper.companyToEmpresa(Objects.requireNonNull(OpbMock.getResponseElectronicChannelsQuebrado()).getData().getBrand().getCompanies());
        assertNotNull(empresas);
    }
}