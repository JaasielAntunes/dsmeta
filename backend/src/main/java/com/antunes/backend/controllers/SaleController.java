package com.antunes.backend.controllers;

import com.antunes.backend.entities.Sale;
import com.antunes.backend.services.SaleService;
import com.antunes.backend.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@Autowired
	private SmsService smsService;

	@GetMapping(value = "/find")
	public Page<Sale> buscarVendas(
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate,
			Pageable pageable) {
		return service.findSales(minDate, maxDate, pageable);
	}

	@GetMapping(value = "/{id}/notification")
	public ResponseEntity<String> enviarSms(@PathVariable Long id) {
		smsService.sendSms(id);
		return ResponseEntity.status(HttpStatus.OK).body("Mensagem enviada com sucesso!");
	}
}
