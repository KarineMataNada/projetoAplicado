package com.serratec.trabalhoAplicado.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.trabalhoAplicado.exception.ResourceBadRequestException;
import com.serratec.trabalhoAplicado.exception.ResourceNotFoundException;
import com.serratec.trabalhoAplicado.model.HistoricoRecibo;
import com.serratec.trabalhoAplicado.model.Recibo;
import com.serratec.trabalhoAplicado.model.email.MenssagemEmail;
import com.serratec.trabalhoAplicado.model.enuns.Acoes;
import com.serratec.trabalhoAplicado.repository.ReciboRepository;



@Service
public class ReciboService {

	@Autowired
	private ReciboRepository repositorioRecibo;
	

	public List<Recibo> obterTodos() {
		return repositorioRecibo.findAll();
	}
	
	public List<Recibo> obterTodosFinalizados(HistoricoRecibo historicoRecibo){
	 Optional<List<Recibo>> lista = repositorioRecibo.findAllByStatusFinalizadoTrue();
	 
	 if(lista.isEmpty()) {
		throw new ResourceNotFoundException("");
	}
	  
	 return lista.get();
	}
	
	
	public Optional<Recibo> obterPorId(Long id) {
		 Optional<Recibo> recibo = repositorioRecibo.findById(id);	 
		
		 if(recibo.isEmpty()) {
			throw new ResourceNotFoundException("Recibo não encontrado por id!");
		}
		 return recibo;
	}
		
  

	public Recibo adicionar(Recibo recibo) {
		
	
		
	 if(recibo.getLayout().getData() == true) {
		recibo.setData(LocalDate.now());
		
	} if(recibo.getLayout().getFormaPagamento() == true) {
		recibo.getFormaPagamento();
		
	} if(recibo.getLayout().getSecretaria() == true) {
		recibo.getSecretaria().getNome();	
		
	} if (recibo.getLayout().getValor() == true) {
	    recibo.getProcedimento().forEach(p -> p.getValor());
	}
	  
		recibo.getMedico().getNome();
		recibo.getMedico().getCrn();
		
		recibo.getPaciente().getNome();
		recibo.getPaciente().getEndereco();
		
		recibo.getProcedimento().forEach(p -> p.getDescricao());
		recibo.getProcedimento().forEach(p -> p.getNomeProcedimento());
	  
		return repositorioRecibo.save(recibo);
		
	}
	
	 public Recibo atualizar(Recibo recibo, Long id) {
		 Optional<Recibo> reciboAtualizado = repositorioRecibo.findById(id);
		   				 
			if(reciboAtualizado.isEmpty()) {
				throw new ResourceNotFoundException("Recibo não encontrado por id");
			} if (reciboAtualizado.get().isStatusFinalizado() == true) {
				throw new ResourceBadRequestException("Recibo ja esta finalizado");
			}
			
			HistoricoRecibo historicoRecibo = new HistoricoRecibo(Acoes.EDITADO, LocalDate.now());
		    recibo.setHistoricoRecibo(adicionarHistorico(historicoRecibo, recibo));	 
			
			recibo.setId(id);		
			return repositorioRecibo.save(recibo);
			
		}
	
	
	public void deletar(Recibo recibo, Long id) {
	    Optional<Recibo> deletarRecibo = repositorioRecibo.findById(id);
	    
	    if(deletarRecibo.isEmpty()) {
			throw new ResourceNotFoundException("Recibo não encontrado!");
		}
	  
	    HistoricoRecibo historicoRecibo = new HistoricoRecibo(Acoes.EXCLUIDO, LocalDate.now());
	    recibo.setHistoricoRecibo(adicionarHistorico(historicoRecibo, recibo));	 
	    recibo.setStatusFinalizado(true);
	    
	    repositorioRecibo.save(recibo);
}
	
	public List<HistoricoRecibo> adicionarHistorico(HistoricoRecibo historicoRecibo, Recibo recibo){
		List<HistoricoRecibo> lista = new ArrayList<>();
		lista = recibo.getHistoricoRecibo();
		lista.add(historicoRecibo);
		return lista;
	}
	
//	 public void enviarEmail(){
//		
//	    	var mensagem ="<!doctypehtml><html lang=en><meta charset=UTF-8><meta content=\\\"IE"
//	    				+ "=edge\\\"http-equiv=X-UA-Compatible><meta content=\\\"width=device-width,"
//	    				+ "initial-scale=1\\\"name=viewport><title>Recibo</title><body style=backgr"
//	    				+ "ound-color:#8abee6;color:#fff;text-align:center><h2 style=text-align:cente"
//	    				+ "Segue a baixo seu recibo!</h4>";
//	    		
//	    				
//	    				var email = new MenssagemEmail();
//	    				mailler.enviar(email);
//	    	}
	    } 
	

