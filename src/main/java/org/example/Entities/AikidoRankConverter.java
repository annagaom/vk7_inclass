package org.example.Entities;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AikidoRankConverter implements AttributeConverter<AikidoRank, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AikidoRank rank) {
        if (rank == null) return null;
        return rank.getLevel();
    }

    @Override
    public AikidoRank convertToEntityAttribute(Integer level) {
        if (level == null) return null;
        return AikidoRank.fromLevel(level);
    }
}
