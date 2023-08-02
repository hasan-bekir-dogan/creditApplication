package com.bekir.business.services;

import com.bekir.business.dto.CreditApplicationInfoDto;
import com.bekir.business.dto.CreditDto;
import com.bekir.data.entity.CreditEntity;

public interface CreditServices {

    // CRUD
    public CreditDto createCredit(CreditApplicationInfoDto creditApplicationInfoDto);

    // model mapper
    public CreditDto EntitytoDto(CreditEntity creditEntity);
    public CreditEntity DtoToEntity(CreditDto creditDto);
}
