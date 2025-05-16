package com.dpilaloa.api.clients.movements.controller;

import com.dpilaloa.api.clients.movements.service.ReportService;
import com.dpilaloa.api.clients.movements.service.models.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController implements ReportsApi{

    private final ReportService reportService;

    @GetMapping("/{clientId}")
    @Override
    public Mono<ResponseEntity<Flux<Report>>> getReportByDate(@PathVariable UUID clientId, @RequestParam String startDate, @RequestParam String endDate, ServerWebExchange exchange) {

        return Mono.just(ResponseEntity.ok().body(reportService.getReportByDate(clientId,startDate,endDate)));
    }
}
