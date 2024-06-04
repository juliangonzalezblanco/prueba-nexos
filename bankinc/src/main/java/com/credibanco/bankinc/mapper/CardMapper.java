package com.credibanco.bankinc.mapper;

import com.credibanco.bankinc.domain.Card;
import com.credibanco.bankinc.dto.CardDTO;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper
public interface CardMapper {
    CardDTO toDTO(Card card);
    Card toEntity(CardDTO cardDTO);
    List<CardDTO> toDTOs(List<Card> cards);
    List<Card> toEntitys(List<CardDTO> cardsDTOs);
}
