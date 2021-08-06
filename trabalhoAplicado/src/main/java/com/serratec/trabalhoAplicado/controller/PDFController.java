package com.serratec.trabalhoAplicado.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lowagie.text.DocumentException;
import com.serratec.trabalhoAplicado.config.PDFConfig;
import com.serratec.trabalhoAplicado.dto.ReciboDto;
import com.serratec.trabalhoAplicado.service.ReciboService;

@Controller
public class PDFController {

	@Autowired
	private ReciboService reciboService;

	@GetMapping("/{id}/users/export/pdf")
	public void exportToPDF(HttpServletResponse response, @PathVariable("id") Long id)
			throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		ReciboDto listUsers = reciboService.obterPorId(id);

		PDFConfig exporter = new PDFConfig(listUsers);
		exporter.export(response);

	}
}