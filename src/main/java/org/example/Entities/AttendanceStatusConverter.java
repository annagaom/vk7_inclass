package org.example.Entities;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AttendanceStatusConverter implements AttributeConverter<AttendanceStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AttendanceStatus status) {
        if (status == null) return null;
        return status.getCode();
    }

    @Override
    public AttendanceStatus convertToEntityAttribute(Integer code) {
        if (code == null) return null;
        return AttendanceStatus.fromCode(code);
    }
}
