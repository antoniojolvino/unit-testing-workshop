package br.com.itau.unittestingworkshop.mocks;


import br.com.itau.unittestingworkshop.core.domain.Empresa;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EmpresasMock {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Empresa> getResponseElectronicChannels() {
        try {
            return Arrays.asList(objectMapper.readValue(EmpresasMock.class.getClassLoader().getResourceAsStream("200_workshop_result_all.json"), Empresa[].class));
        } catch (IOException e) {
            return null;
        }
    }
}
