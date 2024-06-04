package com.credibanco.bankinc.mapper;

import com.credibanco.bankinc.domain.Transaction;
import com.credibanco.bankinc.dto.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface TransactionMapper {
    @Mappings({
            @Mapping(source = "card.cardId", target = "cardId")
    })
    TransactionDTO toDTO(Transaction transaction);
    @Mappings({
            @Mapping(source = "cardId", target = "card.cardId")
    })
    Transaction toEntity(TransactionDTO transactionDTO);
}
