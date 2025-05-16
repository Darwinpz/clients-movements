package com.dpilaloa.api.clients.movements.service.impl;

import com.dpilaloa.api.clients.movements.repository.MovementRepository;
import com.dpilaloa.api.clients.movements.service.ReportService;
import com.dpilaloa.api.clients.movements.service.mapper.ReportMapper;
import com.dpilaloa.api.clients.movements.service.models.Report;
import com.dpilaloa.api.clients.movements.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final MovementRepository movementRepository;

    @Override
    public Flux<Report> getReportByDate(UUID clientId, String startDate, String endDate) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);
            LocalDate dateInitial = LocalDate.parse(startDate, formatter);
            LocalDate dateFinal  = LocalDate.parse(endDate, formatter);

            LocalDateTime initialDateTime = dateInitial.atStartOfDay();
            LocalDateTime finalDateTime = dateFinal.atTime(23, 59, 59);

            return movementRepository.findReportByClientAndDate(clientId,initialDateTime,finalDateTime)
                    .map(ReportMapper.INSTANCE::reportDtoToReport);

        } catch (DateTimeParseException e) {
            return Flux.error(new ServerWebInputException(Constants.INCORRECT_DATE_FORMAT));
        }

    }
}
