import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

public final class EventoService {
	private static final String NOME = "nome";
	private static final String DATAINICIO = "dataInicio";
	private static final String DATATERMINO = "dataFim";
	private static final String LOCAL = "local";
	private static final String CAPACIDADE = "capacidade";
	private static final String QUORUM = "minimo";
	private static final String ORCAMENTOPREVIO = "orcamento";
	private static final String VALORINGRESSO = "ingresso";
	private static final String CRONOGRAMA = "cronograma";
//	private static final String CONVENIO = "convenios";
	private static final String STATUS = "status";
	private static final String NOVONOME = "novoNome";
	
	private ListaEvento listaDeEventos;

	public JSONObject add(Request request) {
		Query query = request.getQuery();

		String nome = query.get(NOME);
		// LocalDateTime dataInicio = LocalDateTime.parse(query.get(DATAINICIO));
		// LocalDateTime dataTermino = LocalDateTime.parse(query.get(DATATERMINO));
		String dataInicio = query.get(DATAINICIO);
		String dataTermino = query.get(DATATERMINO);
		String local = query.get(LOCAL);
		int capacidade = query.getInteger(CAPACIDADE);
		int quorum = query.getInteger(QUORUM);
		double orcamentoPrevio = query.getFloat(ORCAMENTOPREVIO);
		double valorIngresso = query.getFloat(VALORINGRESSO);
		String cronograma = query.get(CRONOGRAMA);
		String status = query.get(STATUS);

		Evento evento = new Evento(nome, local, dataInicio, dataTermino, capacidade, quorum, orcamentoPrevio,
				valorIngresso, cronograma, status);

		this.listaDeEventos.create(evento);
		return evento.toJson();
	}

	public JSONArray get(Request request) {
		return listaDeEventos.todosOsEventos();
	}

	public JSONObject update(Request request) {
		Evento evento = listaDeEventos.read(request.getQuery().get(NOME));

		Query query = request.getQuery();
		if(query.get(NOVONOME) != "")
			evento.setNome(query.get(NOVONOME));
		evento.setDataInicio(query.get(DATAINICIO));
		evento.setDataTermino(query.get(DATATERMINO));
		evento.setLocal(query.get(LOCAL));
		evento.setCapacidade(query.getInteger(CAPACIDADE));
		evento.setQuorum(query.getInteger(QUORUM));
		evento.setOrcamentoPrevio(query.getFloat(ORCAMENTOPREVIO));
		evento.setValorIngresso(query.getFloat(VALORINGRESSO));
		evento.setCronograma(query.get(CRONOGRAMA));
		evento.setStatus(query.get(STATUS));
		
		return evento.toJson();

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
