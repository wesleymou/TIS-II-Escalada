package Service;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import Main.Cliente;
import Main.Evento;
import Main.Inscricao;

public class InscricaoService {

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
					query.getInteger("qtdAdulto"),
					query.getInteger("qtdInfantil"),
					query.getFloat("valorRecebido"),
					query.get("tipoPagamento")));
		}
		return eventoService.update(request);
	}

	public JSONArray get(Request request) {
		JSONArray json = new JSONArray();
		Evento evento;
		if ((evento = eventoService.getEvento(request)) != null) {
			evento.getInscricoes().stream()
			.forEach(t -> json.put(t.toJson()));
		}
		return json;
	}

	public JSONObject update(Request request) {
		Query query = request.getQuery();
		Evento evento;
		Cliente cliente;
		if ((evento = eventoService.getEvento(request)) != null &&
				(cliente = clienteService.getCliente(request)) != null) {
			Set<Inscricao> lista = evento.getInscricoes();
			if (lista.removeIf(i -> i.equals(cliente.getCpf())))
				lista.add(new Inscricao(
						clienteService.getCliente(request),
						evento,
						query.getInteger("qtdAdulto"),
						query.getInteger("qtdInfantil"),
						query.getFloat("valorRecebido"),
						query.get("tipoPagamento")));
		}
		return eventoService.update(request);
	}

	@SuppressWarnings("unlikely-arg-type")
	public JSONObject remove(Request request) {
		Evento evento;
		if ((evento = eventoService.getEvento(request)) != null) {
			Set<Inscricao> lista = evento.getInscricoes();
			return new JSONObject(lista.removeIf(t -> t.equals(Long.parseLong(request.getQuery().get("cpf")))));
		}
		return new JSONObject("erro");
	}
}
