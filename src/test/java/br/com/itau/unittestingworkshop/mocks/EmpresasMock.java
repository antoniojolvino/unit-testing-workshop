package br.com.itau.unittestingworkshop.mocks;


import br.com.itau.unittestingworkshop.core.domain.Empresa;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EmpresasMock {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Empresa> getEmpresas() {
        try {
            return Arrays.asList(objectMapper.readValue(EmpresasMock.class.getClassLoader().getResourceAsStream("200_workshop_result_all.json"), Empresa[].class));
        } catch (IOException e) {
            return null;
        }
    }

    public static List<Empresa> getEmpresasMobileBanking() {
        try {
            return Arrays.asList(objectMapper.readValue(EmpresasMock.class.getClassLoader().getResourceAsStream("200_result_MOBILE_BANKING.json"), Empresa[].class));
        } catch (IOException e) {
            return null;
        }
    }

    public static List<Empresa> getEmpresasChat() {
        try {
            return Arrays.asList(objectMapper.readValue(EmpresasMock.class.getClassLoader().getResourceAsStream("200_result_CHAT.json"), Empresa[].class));
        } catch (IOException e) {
            return null;
        }
    }

    public static List<Empresa> getEmpresasInternetBanking() {
        try {
            return Arrays.asList(objectMapper.readValue(EmpresasMock.class.getClassLoader().getResourceAsStream("200_result_INTERNET_BANKING.json"), Empresa[].class));
        } catch (IOException e) {
            return null;
        }
    }

    public static List<Empresa> getEmpresasOutros() {
        try {
            return Arrays.asList(objectMapper.readValue(EmpresasMock.class.getClassLoader().getResourceAsStream("200_result_OUTROS.json"), Empresa[].class));
        } catch (IOException e) {
            return null;
        }
    }
}
