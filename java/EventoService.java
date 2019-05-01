import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

public final class EventoService {
	private ListaEvento listaDeEventos;

	public String add(Request request) {
		Query query = request.getQuery();

		String nome = query.get("nome");
		//		LocalDateTime dataInicio = query.get(key);
		//		LocalDateTime dataTermino = query.get(key);
		String local = query.get("local");
		int capacidade = query.getInteger("capacidade");
		int quorum = query.getInteger("quorum");
		double orcamentoPrevio = query.getFloat("orcamentoPrevio");
		double valorIngresso = query.getFloat("valorIngresso");
		//		Map <Array,Array> cronograma = new HashMap<Array,Array>() = query.get(key);
		//		int convenio[] = query.get(key);
		//		Inscricoes = new Inscricao[] = query.get(key);
		String status = query.get("status");

		Evento evento = new Evento(nome, local, dataInicio, dataTermino);
		evento.setCapacidade(capacidade);
		evento.setQuorum(quorum);
		evento.setOrcamentoPrevio(orcamentoPrevio);
		evento.setValorIngresso(valorIngresso);
		evento.setCronograma(cronograma);
		evento.setConvenio(convenio);
		evento.setStatus(status);

		listaDeEventos.create(evento);
		return evento.toString();
	}

	public JSONObject get(Request request) {
		Evento evento = listaDeEventos.read(request.getQuery().get("nome"));
		if ((evento) != null)
			return evento.toJson();
		return null;
	}

	//TODO - Terminar o metodo de update
	public String update(Request request) {
		String nome = request.getQuery().get("nome");
		listaDeEventos.update(nome);
		return null;

	}

	public JSONObject remove (Request request) {
		Evento evento = listaDeEventos.delete(request.getQuery().get("nome"));
		if (evento != null)
			return evento.toJson();
		return null;
	}
}
