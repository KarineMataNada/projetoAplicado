package com.serratec.trabalhoAplicado.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.trabalhoAplicado.dto.ReciboDto;
import com.serratec.trabalhoAplicado.exception.ResourceBadRequestException;
import com.serratec.trabalhoAplicado.exception.ResourceNotFoundException;
import com.serratec.trabalhoAplicado.model.HistoricoRecibo;
import com.serratec.trabalhoAplicado.model.Recibo;
import com.serratec.trabalhoAplicado.model.enuns.Acoes;

import com.serratec.trabalhoAplicado.repository.ReciboRepository;



@Service
public class ReciboService {

	@Autowired
	private ReciboRepository repositorioRecibo;
	

	public List<ReciboDto> obterTodos() {
        List<Recibo> recibo =    repositorioRecibo.findAll();
        List<ReciboDto> reciboDto= new ArrayList<ReciboDto>();

        for (Recibo recibos : recibo) {
            reciboDto.add(reciboExibir(recibos));
        }
        return reciboDto;
}
	
	 public List<ReciboDto> obterTodosFinalizados(Recibo historicoRecibo){
	     Optional<List<Recibo>> lista = repositorioRecibo.findAllByStatusFinalizadoTrue();
	        List<ReciboDto> reviboDto = new ArrayList<ReciboDto>();
	        for (Recibo recibo : lista.get()) {
	            reviboDto.add(reciboExibir(recibo));
	        }
	     if(lista.isEmpty()) {
	        throw new ResourceNotFoundException("");
	    }

	     return reviboDto;
}
	 
	
	 public Optional<Recibo> obterPorIdInfo(Long id) {
		 Optional<Recibo> recibo = repositorioRecibo.findById(id);	 
		
		 if(recibo.isEmpty()) {
			throw new ResourceNotFoundException("informação não encontrado!");
		}
		 
		 return recibo;
}
	
	   public ReciboDto obterPorId(Long id) {
	         Optional<Recibo> recibo = repositorioRecibo.findById(id);

	         if(recibo.isEmpty()) {
	            throw new ResourceNotFoundException("Recibo não encontrado por id!");
	        }
	         return reciboExibir(recibo.get());
}
  

	public Recibo adicionar(Recibo recibo) {
		
//		Layout layout = repositorioLayout.findById(recibo.getLayout().getId()).get();
//
//	     if(layout.getData() == true) {
	        recibo.setData(LocalDate.now());
//
//	    } if(layout.getFormaPagamento() == true) {
//	        recibo.getFormaPagamento();
//
//	    } if(layout.getSecretaria() == true) {
//	        recibo.getSecretaria().getNome();
//
//	    } if (layout.getValor() == true) {
//	        recibo.getProcedimento().forEach(p -> p.getValor());
//	    }
	  
		return repositorioRecibo.save(recibo);
		
}
	
	
	 public Recibo atualizar(Recibo reciboAtualizado, Long id) {
		 Optional<Recibo> recibo = repositorioRecibo.findById(id);
		   				 
			if(recibo.isEmpty()) {
				throw new ResourceNotFoundException("Recibo não encontrado por id");
			} if (recibo.get().isStatusFinalizado() == true) {
				throw new ResourceBadRequestException("Recibo ja esta finalizado");
			}
			
			HistoricoRecibo historicoRecibo = new HistoricoRecibo(Acoes.EDITADO, LocalDate.now(), reciboAtualizado);
		    reciboAtualizado.setHistoricoRecibo(adicionarHistorico(historicoRecibo, recibo.get()));	 
			
		    reciboAtualizado.setId(id);		
			
			return repositorioRecibo.save(reciboAtualizado);
			
}
	
	
	public void deletar(Long id) {
	    Optional<Recibo> recibo = repositorioRecibo.findById(id);
	    
	   
	    if(recibo.isEmpty()) {
			throw new ResourceNotFoundException("Recibo não encontrado!");
		}
	  
	    HistoricoRecibo historicoRecibo = new HistoricoRecibo(Acoes.EXCLUIDO, LocalDate.now(), recibo.get());
	    recibo.get().getHistoricoRecibo().add(historicoRecibo); 
	    recibo.get().setStatusFinalizado(true);
	    
	    repositorioRecibo.save(recibo.get());
}
	
	public List<HistoricoRecibo> adicionarHistorico(HistoricoRecibo historicoRecibo, Recibo recibo){
		List<HistoricoRecibo> lista = new ArrayList<>();
		if(recibo.getHistoricoRecibo() != null) {
			lista = recibo.getHistoricoRecibo();
}
		
		lista.add(historicoRecibo);
		return lista;
}
	
	private ReciboDto reciboExibir(Recibo recibo) {
        ReciboDto reciboDto =  new ReciboDto();
        
        reciboDto.setCabecario(recibo.getCabecalho());
        reciboDto.setId(recibo.getId());
        reciboDto.setNomePaciente(recibo.getPaciente().getNome());
        reciboDto.setCpfPaciente(recibo.getPaciente().getCpf());
        reciboDto.setNomeMedico(recibo.getMedico().getNome());
        reciboDto.setCrmMedico(recibo.getMedico().getCrn());
        reciboDto.setNomeSecretaria(recibo.getSecretaria().getNome());
        reciboDto.setProcedimento(recibo.getProcedimento());
        reciboDto.setFormaPagamento(recibo.getFormaPagamento());
        reciboDto.setData(recibo.getData());
        reciboDto.setRodape(recibo.getRodape());
        reciboDto.setStatusFinalizado(recibo.isStatusFinalizado());
        reciboDto.setHistoricoRecibo(recibo.getHistoricoRecibo());

        return reciboDto;
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
	

