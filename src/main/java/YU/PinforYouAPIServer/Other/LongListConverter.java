package YU.PinforYouAPIServer.Other;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.List;

// 출처 : https://khdscor.tistory.com/39
@Converter
public class LongListConverter implements AttributeConverter<List<Long>, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Long> dataList) {
        try {
            return mapper.writeValueAsString(dataList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Long> convertToEntityAttribute(String data) {
        if(data.isEmpty()) return new ArrayList<>();

        try {
            return mapper.readValue(data, List.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
