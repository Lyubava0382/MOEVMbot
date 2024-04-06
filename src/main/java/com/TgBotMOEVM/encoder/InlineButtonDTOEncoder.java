package com.TgBotMOEVM.encoder;

import com.TgBotMOEVM.DTO.InlineButtonDTO;
import com.TgBotMOEVM.exception.InlineButtonCallbackDataParseException;
import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;

@UtilityClass
public class InlineButtonDTOEncoder {
    private static final String DELIMITER = " ";
    private static final int MAX_BIT_SIZE = 64;

    /**
     * Groups data from the object to string
     * @return String containing object's data
     *
     //* @throws ButtonCallbackDataLimitExceedException If packed data exceeds size limit
     */
    public String encode(InlineButtonDTO buttonData) throws Exception{//ButtonCallbackDataLimitExceedException {
        String dataString = buttonData.getCommand() + DELIMITER + buttonData.getData();

        if (doesDataStringExceedSizeLimit(dataString)) {
            throw new Exception();//ButtonCallbackDataLimitExceedException(getDataStringByteSize(dataString));
        }

        return dataString;
    }

    /**
     * Creates object based on packed string
     * @param data String containing data for this class
     * @return New object with data from the string
     //* @throws InlineButtonCallbackDataParseException If input string isn't originated from this class
     */
    public InlineButtonDTO decode(String data) throws InlineButtonCallbackDataParseException {

        if (doesDataStringExceedSizeLimit(data)) {
            throw new  InlineButtonCallbackDataParseException("Data string is bigger then expected! This isn't InlineButton data.");
        }

        String[] dataSplit = data.split(DELIMITER);

        InlineButtonDTO instance;

        // Checking if data string contain the expected number of parameters
        if (dataSplit.length != InlineButtonDTO.class.getDeclaredFields().length) {
            throw new InlineButtonCallbackDataParseException("Cannot parse data from input string. Wrong parameters number.");
        } else {
            instance = new InlineButtonDTO();
        }

        return instance;
    }


    private boolean doesDataStringExceedSizeLimit(String data) {
        return getDataStringByteSize(data) > MAX_BIT_SIZE;
    }

    private int getDataStringByteSize(String data) {
        return data.getBytes(StandardCharsets.UTF_8).length;
    }
}