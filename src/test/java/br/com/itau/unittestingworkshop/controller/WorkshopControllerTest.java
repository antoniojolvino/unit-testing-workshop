package br.com.itau.unittestingworkshop.controller;

import br.com.itau.unittestingworkshop.UnitTestingWorkshopApplication;
import br.com.itau.unittestingworkshop.core.domain.TipoCanal;
import br.com.itau.unittestingworkshop.core.service.WorkshopService;
import br.com.itau.unittestingworkshop.mocks.EmpresasMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = UnitTestingWorkshopApplication.class)
class WorkshopControllerTest {
    @Autowired
    WorkshopControllerAdvice controllerAdvice;
    private MockMvc mockMvc;
    @Mock
    private WorkshopService service;
    @InjectMocks
    private WorkshopController controller;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(controllerAdvice).build();
    }

    @Test
    public void buscarTodosCanaisEletronicos() throws Exception {
        doReturn(EmpresasMock.getEmpresas()).when(service).getEmpresas(null);

        MvcResult mvcResult = this.mockMvc.perform(get("/workshop")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[*].canaisEletronicos").isArray())
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", hasItem("CHAT")))
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", hasItem("INTERNET_BANKING")))
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", hasItem("MOBILE_BANKING")))
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", hasItem("OUTROS")))
                .andReturn();
    }

    @Test
    public void buscarCanalEletronicoChat() throws Exception {
        doReturn(EmpresasMock.getEmpresasChat()).when(service).getEmpresas(TipoCanal.CHAT);

        MvcResult mvcResult = this.mockMvc.perform(get("/workshop")
                        .param("tipoCanal", TipoCanal.CHAT.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[*].canaisEletronicos").isArray())
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", hasItem("CHAT")))
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", not(hasItem("INTERNET_BANKING"))))
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", not(hasItem("MOBILE_BANKING"))))
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", not(hasItem("OUTROS"))))
                .andReturn();

    }

    @Test
    public void buscarCanalEletronicoInternetBanking() throws Exception {
        doReturn(EmpresasMock.getEmpresasInternetBanking()).when(service).getEmpresas(TipoCanal.INTERNET_BANKING);

        MvcResult mvcResult = this.mockMvc.perform(get("/workshop")
                        .param("tipoCanal", TipoCanal.INTERNET_BANKING.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[*].canaisEletronicos").isArray())
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", not(hasItem("CHAT"))))
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", hasItem("INTERNET_BANKING")))
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", not(hasItem("MOBILE_BANKING"))))
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", not(hasItem("OUTROS"))))
                .andReturn();

    }

    @Test
    public void buscarCanalEletronicoMobileBanking() throws Exception {
        doReturn(EmpresasMock.getEmpresasMobileBanking()).when(service).getEmpresas(TipoCanal.MOBILE_BANKING);

        MvcResult mvcResult = this.mockMvc.perform(get("/workshop")
                        .param("tipoCanal", TipoCanal.MOBILE_BANKING.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[*].canaisEletronicos").isArray())
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", not(hasItem("CHAT"))))
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", not(hasItem("INTERNET_BANKING"))))
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", hasItem("MOBILE_BANKING")))
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", not(hasItem("OUTROS"))))
                .andReturn();
    }

    @Test
    public void buscarCanalEletronicoOutros() throws Exception {
        doReturn(EmpresasMock.getEmpresasOutros()).when(service).getEmpresas(TipoCanal.OUTROS);

        MvcResult mvcResult = this.mockMvc.perform(get("/workshop")
                        .param("tipoCanal", TipoCanal.OUTROS.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[*].canaisEletronicos").isArray())
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", not(hasItem("CHAT"))))
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", not(hasItem("INTERNET_BANKING"))))
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", not(hasItem("MOBILE_BANKING"))))
                .andExpect(jsonPath("$[*].canaisEletronicos[*].tipo", hasItem("OUTROS")))
                .andReturn();

    }

    @Test
    public void buscarCanalEletronicoInvalido() throws Exception {
        doReturn(EmpresasMock.getEmpresasOutros()).when(service).getEmpresas(TipoCanal.OUTROS);

        final String QUALQUER_COISA = "Qualquer Coisa";
        MvcResult mvcResult = this.mockMvc.perform(get("/workshop")
                        .param("tipoCanal", QUALQUER_COISA)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.message", is("Request Parameter inválido")))
                .andExpect(jsonPath("$.request").isArray())
                .andExpect(jsonPath("$.request[*].parameter", hasItem("tipoCanal")))
                .andExpect(jsonPath("$.request[*].value", hasItem(QUALQUER_COISA)))
                .andReturn();

    }
}