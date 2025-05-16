package com.dpilaloa.api.clients.movements.service;

import com.dpilaloa.api.clients.movements.service.models.Report;
import reactor.core.publisher.Flux;

import java.util.UUID;


public interface ReportService {

    Flux<Report> getReportByDate(UUID clientId, String startDate, String endDate);

}
