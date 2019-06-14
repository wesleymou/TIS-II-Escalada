package Service;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Request;

import Main.Evento;
import Main.Inscricao;

public class IndicadorService {

	private EventoService eventoService;

	public IndicadorService(EventoService eventoService) {
		this.eventoService = eventoService;
	}


	//************ Porcentagem de inscritos em relação à capacidade total do evento ***************//

	public String porcInsc(Evento evento) {
		return evento.getInscricoes().size() + "/" + evento.getCapacidade() + " (" + (evento.getInscricoes().size() / evento.getCapacidade()) * 100 + "%)";
	}


	//************ Porcentagem de inscritos que pagaram parcialmente em relação à quantidade total de inscritos ***************//

	public String pagouParcial(Evento evento) {
		int i=0;
		for(Inscricao inscricao: evento.getInscricoes())
			if(!inscricao.estaPago())
				i++;
		return i + "/" + evento.getInscricoes().size() + " (" + (i*1.0/evento.getInscricoes().size()) * 100 + "%)";
	}


	//************ Porcentagem de inscritos que pagaram totalmente em relação à quantidade total de inscritos ***************//

	public String pagouTotal(Evento evento) {
		int i=0;
		for(Inscricao inscricao: evento.getInscricoes())
			if(inscricao.estaPago())
				i++;
		return (i + "/" + evento.getInscricoes().size() + " (" + (i*1.0/evento.getInscricoes().size()) * 100 + "%)");
	}


	//************ Porcentagem de inscritos que pagaram totalmente ou parcialmente no DÉBITO ou DINHEIRO ***************//

	public String pagouDebito(Evento evento) {
			int i=0;
			for(Inscricao inscricao: evento.getInscricoes())
				if(inscricao.getTipoPagamento().equals("debito") || inscricao.getTipoPagamento().equals("dinheiro"))
					i++;
			return (i + "/" + evento.getInscricoes().size() + " (" + (i*1.0/evento.getInscricoes().size()) * 100 + "%)");
	}


	//************ Porcentagem de inscritos que pagaram totalmente ou parcialmente no CRÉDITO ou CHEQUE ***************//

	public String pagouCredito(Evento evento) {
			int i=0;
			for(Inscricao inscricao: evento.getInscricoes())
				if(inscricao.getTipoPagamento().equals("credito") || inscricao.getTipoPagamento().equals("cheque"))
					i++;
			return (i + "/" + evento.getInscricoes().size() + " (" + (i*1.0/evento.getInscricoes().size()) * 100 + "%)");
	}



	public JSONArray getAll(Request request) {
		Set<Evento> eventos = eventoService.getAllEventos();
		JSONArray lista = new JSONArray();
		for (Evento evento : eventos) {
			JSONArray jsonArray = new JSONArray();
			jsonArray.put(evento.getNome());
			jsonArray.put(porcInsc(evento));
			jsonArray.put(pagouDebito(evento));
			jsonArray.put(pagouCredito(evento));
			jsonArray.put(pagouParcial(evento));
			jsonArray.put(pagouTotal(evento));
			lista.put(jsonArray);
		}
		return lista;
	}

}
