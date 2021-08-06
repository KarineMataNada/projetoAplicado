package com.serratec.trabalhoAplicado.config;

import java.awt.Color;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.serratec.trabalhoAplicado.dto.ReciboDto;

public class PDFConfig {

	private ReciboDto recibo;

	public PDFConfig(ReciboDto recibo) {
		this.recibo = recibo;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(4);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Recibo ID", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Nome Paciente", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("CPF Paciente", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Nome Medico", font));
		table.addCell(cell);

	}

	private void writeTableHeader1(PdfPTable tablemedico) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(4);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("CRM", font));
		tablemedico.addCell(cell);

		cell.setPhrase(new Phrase("Secretaria", font));
		tablemedico.addCell(cell);

		cell.setPhrase(new Phrase("Data", font));
		tablemedico.addCell(cell);

		cell.setPhrase(new Phrase("FormaPagamento", font));
		tablemedico.addCell(cell);

	}

	private void writeTableHeader2(PdfPTable tableProcedimento) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(4);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Procedimento", font));
		tableProcedimento.addCell(cell);

	}

	private void writeTableData(PdfPTable table) {

		table.addCell(String.valueOf(recibo.getId()));
		table.addCell(recibo.getNomePaciente());
		table.addCell(recibo.getCpfPaciente());
		table.addCell(recibo.getNomeMedico());
	}

	private void writeTableData1(PdfPTable tablemedico) {

		tablemedico.addCell(recibo.getCrmMedico());
		tablemedico.addCell(recibo.getNomeSecretaria());
		tablemedico.addCell(String.valueOf(recibo.getData()));
		tablemedico.addCell(String.valueOf(recibo.getFormaPagamento()));
		
	}
	
	private void writeTableData2(PdfPTable tableProcedimento) {
	

		tableProcedimento.addCell(String.valueOf(recibo.getProcedimento().stream().map(p -> p.getClass())));

	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLACK);

		Paragraph p = new Paragraph(recibo.getCabecalho(), font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		PdfPTable tablemedico = new PdfPTable(4);
		tablemedico.setWidthPercentage(100f);
		tablemedico.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f });
		tablemedico.setSpacingBefore(10);

		writeTableHeader1(tablemedico);
		writeTableData1(tablemedico);

		document.add(tablemedico);
		
		PdfPTable tableProcedimento = new PdfPTable(4);
		tableProcedimento.setWidthPercentage(100f);
		tableProcedimento.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f });
		tableProcedimento.setSpacingBefore(10);

		writeTableHeader2(tableProcedimento);
		writeTableData2(tableProcedimento);

		document.add(tableProcedimento);
		
		document.close();

	}
}