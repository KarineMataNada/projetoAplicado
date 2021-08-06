package com.serratec.trabalhoAplicado.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.trabalhoAplicado.config.PDFConfig;
import com.serratec.trabalhoAplicado.dto.ReciboDto;
import com.serratec.trabalhoAplicado.exception.ResourceBadRequestException;
import com.serratec.trabalhoAplicado.exception.ResourceNotFoundException;
import com.serratec.trabalhoAplicado.model.HistoricoRecibo;
import com.serratec.trabalhoAplicado.model.Layout;
import com.serratec.trabalhoAplicado.model.Medico;
import com.serratec.trabalhoAplicado.model.Paciente;
import com.serratec.trabalhoAplicado.model.Recibo;
import com.serratec.trabalhoAplicado.model.Secretaria;
import com.serratec.trabalhoAplicado.model.email.Mailler;
import com.serratec.trabalhoAplicado.model.enuns.Acoes;
import com.serratec.trabalhoAplicado.repository.LayoutRepository;
import com.serratec.trabalhoAplicado.repository.MedicoRepository;
import com.serratec.trabalhoAplicado.repository.PacienteRepository;
import com.serratec.trabalhoAplicado.repository.ReciboRepository;
import com.serratec.trabalhoAplicado.repository.SecretariaRepository;

@Service
public class ReciboService {

	@Autowired
	private ReciboRepository repositorioRecibo;

	@Autowired
	private LayoutRepository repositorioLayout;

	@Autowired
	private MedicoRepository repositorioMedico;

	@Autowired
	private SecretariaRepository repositorioSecretaria;
	
	@Autowired
	private PacienteRepository repositorioPaciente;
	
	@Autowired 
	private Mailler mailler;

	public List<ReciboDto> obterTodos() {
		List<Recibo> recibo = repositorioRecibo.findAll();
		List<ReciboDto> reciboDto = new ArrayList<ReciboDto>();

		for (Recibo recibos : recibo) {
			reciboDto.add(reciboExibir(recibos));
		}
		return reciboDto;
	}

	public List<ReciboDto> obterTodosFinalizados(Recibo historicoRecibo) {
		Optional<List<Recibo>> lista = repositorioRecibo.findAllByStatusFinalizadoTrue();
		List<ReciboDto> reviboDto = new ArrayList<ReciboDto>();
		for (Recibo recibo : lista.get()) {
			reviboDto.add(reciboExibir(recibo));
		}
		if (lista.isEmpty()) {
			throw new ResourceNotFoundException("");
		}

		return reviboDto;
	}

	public Optional<Recibo> obterPorIdInfo(Long id) {
		Optional<Recibo> recibo = repositorioRecibo.findById(id);

		if (recibo.isEmpty()) {
			throw new ResourceNotFoundException("informação não encontrado!");
		}

		return recibo;
	}

	public ReciboDto obterPorId(Long id) {
		Optional<Recibo> recibo = repositorioRecibo.findById(id);

		if (recibo.isEmpty()) {
			throw new ResourceNotFoundException("Recibo não encontrado por id!");
		}
		return reciboExibir(recibo.get());
	}

	public Recibo adicionar(Recibo recibo) {

		Optional<Layout> reciboValidar = repositorioLayout.findById(recibo.getLayout().getId());
		Optional<Medico> medicoValidar = repositorioMedico.findById(recibo.getMedico().getId());
		Optional<Secretaria> secretariaValidar = repositorioSecretaria.findById(recibo.getSecretaria().getId());
		Optional<Paciente> pacienteValidar = repositorioPaciente.findById(recibo.getPaciente().getId());

		if (reciboValidar.get().getAtivo() == false) {
			throw new ResourceNotFoundException("Esse Layout foi excluido! Escolha um que esteja ativo.");
		}
		if (medicoValidar.isEmpty()) {
			throw new ResourceNotFoundException("Esse id não pertence ao um medico! Tente outro id.");
		}
		if (secretariaValidar.isEmpty()) {
			throw new ResourceNotFoundException("Esse id não pertence ao um(a) srecretario(a)! Tente outro id.");
		}
		if (pacienteValidar.isEmpty()) {
			throw new ResourceNotFoundException("Esse id não pertence ao um(a) Paciente! Tente outro id.");
		}

		recibo.setData(LocalDate.now());

		return repositorioRecibo.save(recibo);

	}

	public Recibo atualizar(Recibo reciboAtualizado, Long id) {
		Optional<Recibo> recibo = repositorioRecibo.findById(id);

		if (recibo.isEmpty()) {
			throw new ResourceNotFoundException("Recibo não encontrado por id");
		}
		if (recibo.get().isStatusFinalizado() == true) {
			throw new ResourceBadRequestException("Recibo ja esta finalizado");
		}

		HistoricoRecibo historicoRecibo = new HistoricoRecibo(Acoes.EDITADO, LocalDate.now(), reciboAtualizado);
		reciboAtualizado.setHistoricoRecibo(adicionarHistorico(historicoRecibo, recibo.get()));

		reciboAtualizado.setId(id);
		reciboAtualizado.setData(recibo.get().getData());
		return repositorioRecibo.save(reciboAtualizado);

	}

	public void deletar(Long id) {
		Optional<Recibo> recibo = repositorioRecibo.findById(id);

		if (recibo.isEmpty()) {
			throw new ResourceNotFoundException("Recibo não encontrado!");
		}

		HistoricoRecibo historicoRecibo = new HistoricoRecibo(Acoes.EXCLUIDO, LocalDate.now(), recibo.get());
		recibo.get().getHistoricoRecibo().add(historicoRecibo);
		recibo.get().setStatusFinalizado(true);

		repositorioRecibo.save(recibo.get());
	}

	public List<HistoricoRecibo> adicionarHistorico(HistoricoRecibo historicoRecibo, Recibo recibo) {
		List<HistoricoRecibo> lista = new ArrayList<>();
		if (recibo.getHistoricoRecibo() != null) {
			lista = recibo.getHistoricoRecibo();
		}

		lista.add(historicoRecibo);
		return lista;
	}

	private ReciboDto reciboExibir(Recibo recibo) {
		ReciboDto reciboDto = new ReciboDto();

		reciboDto.setCabecalho(recibo.getCabecalho());
		reciboDto.setId(recibo.getId());
		reciboDto.setNomePaciente(recibo.getPaciente().getNome());
		reciboDto.setCpfPaciente(recibo.getPaciente().getCpf());
		reciboDto.setNomeMedico(recibo.getMedico().getNome());
		reciboDto.setCrmMedico(recibo.getMedico().getCrm());

		reciboDto.setProcedimento(recibo.getProcedimento());
		if (recibo.getLayout().getSecretaria() == true) {
			reciboDto.setNomeSecretaria(recibo.getSecretaria().getNome());
		}
		if (recibo.getLayout().getFormaPagamento()) {
			reciboDto.setFormaPagamento(recibo.getFormaPagamento());
		}
		if (recibo.getLayout().getData()) {
			reciboDto.setData(recibo.getData());
		}

		reciboDto.setRodape(recibo.getRodape());
		reciboDto.setStatusFinalizado(recibo.isStatusFinalizado());
		reciboDto.setHistoricoRecibo(recibo.getHistoricoRecibo());

		return reciboDto;
	}

	public String enviarEmail(Long id) {
		Optional<Recibo> reciboPDF = repositorioRecibo.findById(id);

		if (reciboPDF.isEmpty()) {
			throw new ResourceNotFoundException("Recibo não encontrado!");
		}

		PDFConfig pdf = new PDFConfig(reciboExibir(reciboPDF.get()));

		return mailler.enviar(reciboPDF.get(), pdf);

	}
}
