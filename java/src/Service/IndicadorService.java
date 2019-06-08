package Service;

import org.simpleframework.http.Request;

import Main.Evento;
import Main.Inscricao;

/* Falta: 
 * 
 * Percentual do número de desistentes em relação ao número total de inscritos
 * Percentual do número de participantes confirmados em relação ao número de inscrições
 * 
 * Necessário campo para definir status de confirmação para então definir os parametros de indicador
 */


public class IndicadorService {
	
	private EventoService eventoService;
	
	public IndicadorService(EventoService eventoService) {
		this.eventoService = eventoService;
	}

	
	//************ Porcentagem de inscritos em relação à capacidade total do evento ***************//
	
	public double porcInsc(Request request) {
		Evento evento;
		if ((evento = eventoService.getEvento(request)) != null) {
			return evento.getInscricoes().size()/evento.getCapacidade()*100;
		}
		return 0;
	}
	
	//************ Porcentagem de inscritos que pagaram parcialmente em relação à quantidade total de inscritos ***************//
	
	public double pagouParcial(Request request) {
		Evento evento;
		if ((evento = eventoService.getEvento(request)) != null) {
			int i=0;
			for(Inscricao in: evento.getInscricoes()) {
				if(!in.estaPago()) {
					i++;
				}
			}
			return i*1.0/evento.getInscricoes().size()*100;
		}
		return 0;
	}
	
	//************ Porcentagem de inscritos que pagaram totalmente em relação à quantidade total de inscritos ***************//
	
	public double pagouTotal(Request request) {
		Evento evento;
		if ((evento = eventoService.getEvento(request)) != null) {
			int i=0;
			for(Inscricao in: evento.getInscricoes()) {
				if(in.estaPago()) {
					i++;
				}
			}
			return i*1.0/evento.getInscricoes().size()*100;
		}
		return 0;
	}
	
	
	//************ Porcentagem de inscritos que pagaram totalmente ou parcialmente no DÉBITO ou DINHEIRO ***************//
	
	
	public double pagouDebito(Request request) {
		Evento evento;
		if ((evento = eventoService.getEvento(request)) != null) {
			int i=0;
			for(Inscricao in: evento.getInscricoes()) {
				if(in.getTipoPagamento() == "Débito" || in.getTipoPagamento() == "Dinheiro") {
					i++;
				}
			}
			return i*1.0/evento.getInscricoes().size()*100;
		}
		return 0;
	}
	
	
	//************ Porcentagem de inscritos que pagaram totalmente ou parcialmente no CRÉDITO ou CHEQUE ***************//
	
	
	public double pagouCredito(Request request) {
		Evento evento;
		if ((evento = eventoService.getEvento(request)) != null) {
			int i=0;
			for(Inscricao in: evento.getInscricoes()) {
				if(in.getTipoPagamento() == "Crédito" || in.getTipoPagamento() == "Cheque") {
					i++;
				}
			}
			return i*1.0/evento.getInscricoes().size()*100;
		}
		return 0;
	}
}
