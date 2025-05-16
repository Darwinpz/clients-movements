package com.dpilaloa.api.clients.movements.service.mapper;

import com.dpilaloa.api.clients.movements.service.dto.ReportDto;
import com.dpilaloa.api.clients.movements.service.models.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReportMapper {

    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    @Mapping(source = "marker", target = "date")
    @Mapping(source = "initial", target = "balanceInitial")
    @Mapping(source = "balance", target = "balanceFinal")
    @Mapping(source = "account", target = "accountNumber")
    Report reportDtoToReport (ReportDto reportDto);

}
