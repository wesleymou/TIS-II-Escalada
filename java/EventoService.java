import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;

import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

public final class EventoService {
	private final static String NOME = "nome";
//	private final static String DATAINICIO = "dataInicio";
//	private final static String DATATERMINO = "dataFim";
	private final static String LOCAL = "local";
	private final static String CAPACIDADE = "capacidade";
	private final static String QUORUM = "minimo";
	private final static String ORCAMENTOPREVIO = "orcamento";
	private final static String VALORINGRESSO = "ingresso";
//	private final static String CRONOGRAMA = "cronograma";
//	private final static String CONVENIO = "convenios";
	private final static String STATUS = "status";
	
	
	private ListaEvento listaDeEventos;

	public JSONObject add(Request request) {
		Query query = request.getQuery();
		String nome = query.get(NOME);
		//		LocalDateTime dataInicio = query.get(DATAINICIO);
		//		LocalDateTime dataTermino = query.get(DATATERMINO);
		String local = query.get(LOCAL);
		int capacidade = query.getInteger(CAPACIDADE);
		int quorum = query.getInteger(QUORUM);
		double orcamentoPrevio = query.getFloat(ORCAMENTOPREVIO);
		double valorIngresso = query.getFloat(VALORINGRESSO);
		String status = query.get(STATUS);

		LocalDateTime dataInicio = null, dataTermino=null;//RETIRAR DEPOIS
		Evento evento = new Evento(nome, local, dataInicio, dataTermino);
		evento.setCapacidade(capacidade);
		evento.setQuorum(quorum);
		evento.setOrcamentoPrevio(orcamentoPrevio);
		evento.setValorIngresso(valorIngresso);
		evento.setStatus(status);

		this.listaDeEventos.create(evento);
		return evento.toJson();
	}

	public JSONObject get(Request request) {
		Evento evento = listaDeEventos.read(request.getQuery().get(NOME));
		if ((evento) != null)
			return evento.toJson();
		return null;
	}

	//TODO - Terminar o metodo de update
	public JSONObject update(Request request) {
		String nome = request.getQuery().get(NOME);
		listaDeEventos.update(nome);
		return null;

	}

	public JSONObject remove (Request request) {
		Evento evento = listaDeEventos.delete(request.getQuery().get(NOME));
		if (evento != null)
			return evento.toJson();
		return null;
	}
	
	public EventoService() {
		listaDeEventos = new ListaEvento();
	}
}
