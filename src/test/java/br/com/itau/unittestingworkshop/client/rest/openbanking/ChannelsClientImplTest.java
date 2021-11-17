package br.com.itau.unittestingworkshop.client.rest.openbanking;

import br.com.itau.unittestingworkshop.client.rest.openbanking.dto.ResponseElectronicChannels;
import br.com.itau.unittestingworkshop.client.rest.openbanking.feign.ChannelsFeign;
import br.com.itau.unittestingworkshop.client.rest.openbanking.mapper.ElectronicChannelMapper;
import br.com.itau.unittestingworkshop.core.domain.Empresa;
import br.com.itau.unittestingworkshop.mocks.EmpresasMock;
import br.com.itau.unittestingworkshop.mocks.OpbMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ChannelsClientImplTest {

    @Mock
    private ElectronicChannelMapper mapper;

    @Mock
    private ChannelsFeign feign;

    @InjectMocks
    ChannelsClientImpl client;

    @Test
    public void getCompaniesEletronicChannelsIndiretoSemTotal(){
        ResponseElectronicChannels responseElectronicChannelsPage1 = OpbMock.getResponseElectronicChannelsPage1();
        List<Empresa> empresasMock = EmpresasMock.getEmpresas();
        assert responseElectronicChannelsPage1 != null;

        doReturn(ResponseEntity.ok(responseElectronicChannelsPage1))
                .when(feign)
                .getCompaniesEletronicChannels(null);

        doReturn(ResponseEntity.ok(OpbMock.getResponseElectronicChannels()))
                .when(feign)
                .getCompaniesEletronicChannels(responseElectronicChannelsPage1.getMeta().getTotalRecords());

        doReturn(empresasMock)
                .when(mapper)
                .companyToEmpresa(anyList());

        List<Empresa> companiesEletronicChannels = client.getCompaniesEletronicChannels();

        assertEquals(empresasMock, companiesEletronicChannels);
    }

    @Test
    public void getCompaniesEletronicChannelsDiretoComTotal(){
        List<Empresa> empresasMock = EmpresasMock.getEmpresas();

        doReturn(ResponseEntity.ok(OpbMock.getResponseElectronicChannels()))
                .when(feign)
                .getCompaniesEletronicChannels(null);

        doReturn(empresasMock)
                .when(mapper)
                .companyToEmpresa(anyList());

        List<Empresa> companiesEletronicChannels = client.getCompaniesEletronicChannels();

        assertEquals(empresasMock, companiesEletronicChannels);
    }

    @Test
    public void getCompaniesEletronicChannelsRetornoIntegracaoNulo(){
        doReturn(ResponseEntity.ok(null))
                .when(feign)
                .getCompaniesEletronicChannels(null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> client.getCompaniesEletronicChannels());

        assertEquals(ChannelsClientImpl.MENSAGEM_SEM_RESPOSTA_OPB, exception.getMessage());
    }

    @Test
    public void getCompaniesEletronicChannelsRetornoRechamadaIntegracaoNulo(){
        ResponseElectronicChannels responseElectronicChannelsPage1 = OpbMock.getResponseElectronicChannelsPage1();
        assert responseElectronicChannelsPage1 != null;

        doReturn(ResponseEntity.ok(responseElectronicChannelsPage1))
                .when(feign)
                .getCompaniesEletronicChannels(null);

        doReturn(ResponseEntity.ok(null))
                .when(feign)
                .getCompaniesEletronicChannels(responseElectronicChannelsPage1.getMeta().getTotalRecords());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> client.getCompaniesEletronicChannels());

        assertEquals(ChannelsClientImpl.MENSAGEM_SEM_RESPOSTA_OPB, exception.getMessage());
    }
}