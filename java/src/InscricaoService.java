
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

class InscricaoService {

	private EventoService eventoService;
	private ClienteService clienteService;

	public InscricaoService(EventoService eventoService, ClienteService clienteService) {
		this.eventoService = eventoService;
		this.clienteService= clienteService;
	}

	public JSONObject add(Request request) {
		Query query = request.getQuery();
		Evento evento;
		if ((evento = eventoService.getEvento(request)) != null) {
			evento.getInscricoes().add(new Inscricao(
					clienteService.getCliente(request),
					evento,
					query.getInteger("adulto"),
					query.getInteger("crianca"),
					query.getFloat("valorRecebido"),
					query.get("tipoPagamento")));
		}
		return eventoService.update(request);
	}

	public JSONArray get(Request request) {
		JSONArray json = new JSONArray();
		Evento evento;
		if ((evento = eventoService.getEvento(request)) != null) {
			Set<Inscricao> lista = evento.getInscricoes();
			if (!lista.isEmpty())
				for (Inscricao inscricao : lista)
					json.put(inscricao.toJson());
			else
				json.put(0, "null");
		}
		return json;
	}

	@SuppressWarnings("unlikely-arg-type")
	public JSONObject update(Request request) {
		Query query = request.getQuery();
		Evento evento;
		Cliente cliente;
		if ((evento = eventoService.getEvento(request)) != null &&
				(cliente = clienteService.getCliente(request)) != null) {
			Set<Inscricao> lista = evento.getInscricoes();
			Inscricao inscricaoLocal = null;
			for (Inscricao inscricao : lista) {
				if (inscricao.equals(cliente.getCpf()))
					inscricaoLocal = inscricao;
			}
			if (inscricaoLocal != null) {
				inscricaoLocal = new Inscricao(
						clienteService.getCliente(request),
						evento,
						query.getInteger("adulto"),
						query.getInteger("crianca"),
						query.getFloat("valorRecebido"),
						query.get("tipoPagamento"));
			}
		}
		return evento.toJson();
	}

	public JSONObject remove(Request request) {
		Query query = request.getQuery();
		Evento evento;
		if ((evento = eventoService.getEvento(request)) != null) {
			Set<Inscricao> lista = evento.getInscricoes();
			return new JSONObject(lista.removeIf(t -> t.equals(Long.parseLong(query.get("cpf")))));
		}
		return new JSONObject("erro");
	}
}
