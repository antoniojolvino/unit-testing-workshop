package br.com.itau.unittestingworkshop.mocks;


import br.com.itau.unittestingworkshop.client.rest.openbanking.dto.ResponseElectronicChannels;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class OpbMock {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ResponseElectronicChannels getResponseElectronicChannels() {
        try {
            return objectMapper.readValue(OpbMock.class.getClassLoader().getResourceAsStream("200_opb_electronic-channels.json"), ResponseElectronicChannels.class);
        } catch (IOException e) {
            return null;
        }
    }

    public static ResponseElectronicChannels getResponseElectronicChannelsPage1() {
        try {
            return objectMapper.readValue(OpbMock.class.getClassLoader().getResourceAsStream("200_opb_electronic-channels-page1.json"), ResponseElectronicChannels.class);
        } catch (IOException e) {
            return null;
        }
    }
}
