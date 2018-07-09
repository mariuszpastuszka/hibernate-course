package pl.mpas.learn_hibernate.my_converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SecretConverter implements AttributeConverter<Secret, String> {


    @Override
    public String convertToDatabaseColumn(Secret attribute) {
        return attribute.getSecret();
    }

    @Override
    public Secret convertToEntityAttribute(String dbData) {
        return new Secret(dbData);
    }
}
