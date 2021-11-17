package br.com.itau.unittestingworkshop.core.service;

import br.com.itau.unittestingworkshop.core.domain.Empresa;
import br.com.itau.unittestingworkshop.core.domain.TipoCanal;
import br.com.itau.unittestingworkshop.core.port.ChannelPort;
import br.com.itau.unittestingworkshop.mocks.EmpresasMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkshopServiceTest {

    @Mock
    private ChannelPort channelPort;

    @InjectMocks
    private WorkshopService service;

    @BeforeEach
    public void setupMock(){
        when(channelPort.getCompaniesEletronicChannels()).thenReturn(EmpresasMock.getEmpresas());
    }

    @Test
    @DisplayName("Quando recupera empresas sem um tipo de canal, então recupera todas as empresas recuperadas")
    public void getEmpresasSemTipoCanal() {

        List<Empresa> empresas = service.getEmpresas(null);

        assertEquals(EmpresasMock.getEmpresas(), empresas);
    }

    @Test
    @DisplayName("Quando recupera empresas com canal CHAT, então retorna somente empresas com o canal CHAT")
    public void getEmpresasTipoCanalChat() {
        List<Empresa> empresas = service.getEmpresas(TipoCanal.CHAT);

        assertNotEquals(EmpresasMock.getEmpresas(), empresas);

        empresas.forEach(
                empresa -> empresa.getCanaisEletronicos().forEach(
                        canal -> assertEquals(TipoCanal.CHAT.name(), canal.getTipo())));

    }

    @Test
    @DisplayName("Quando recupera empresas com canal INTERNET_BANKING, então retorna somente empresas com o canal INTERNET_BANKING")
    public void getEmpresasTipoCanalInternetBanking() {
        List<Empresa> empresas = service.getEmpresas(TipoCanal.INTERNET_BANKING);

        assertNotEquals(EmpresasMock.getEmpresas(), empresas);

        empresas.forEach(
                empresa -> empresa.getCanaisEletronicos().forEach(
                        canal -> assertEquals(TipoCanal.INTERNET_BANKING.name(), canal.getTipo())));
    }

    @Test
    @DisplayName("Quando recupera empresas com canal MOBILE_BANKING, então retorna somente empresas com o canal MOBILE_BANKING")
    public void getEmpresasTipoCanalMobile() {
        List<Empresa> empresas = service.getEmpresas(TipoCanal.MOBILE_BANKING);

        assertNotEquals(EmpresasMock.getEmpresas(), empresas);

        empresas.forEach(
                empresa -> empresa.getCanaisEletronicos().forEach(
                        canal -> assertEquals(TipoCanal.MOBILE_BANKING.name(), canal.getTipo())));
    }

    @Test
    @DisplayName("Quando recupera empresas com canal OUTROS, então retorna somente empresas com o canal OUTROS")
    public void getEmpresasTipoCanalOutros() {
        List<Empresa> empresas = service.getEmpresas(TipoCanal.OUTROS);

        assertNotEquals(EmpresasMock.getEmpresas(), empresas);

        empresas.forEach(
                empresa -> empresa.getCanaisEletronicos().forEach(
                        canal -> assertEquals(TipoCanal.OUTROS.name(), canal.getTipo())));
    }
}