package Service;

import java.util.Iterator;
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
			Set<Inscricao> lista = evento.getInscricoes();
			if (!lista.isEmpty())
				for (Inscricao inscricao : lista)
					json.put(inscricao.toJson());
			else
				json.put(0, "null");
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
			Iterator<Inscricao> it = lista.iterator();
					while (it.hasNext()) {
						Inscricao inscricao = (Inscricao) it.next();
						if (inscricao.equals(cliente.getCpf())) {
							it.remove();
						lista.add(new Inscricao(
								clienteService.getCliente(request),
								evento,
								query.getInteger("qtdAdulto"),
								query.getInteger("qtdInfantil"),
								query.getFloat("valorRecebido"),
								query.get("tipoPagamento")));
						}
					}
					//			if (inscricaoLocal != null) {
					//				inscricaoLocal = new Inscricao(
					//						clienteService.getCliente(request),
					//						evento,
					//						query.getInteger("qtdAdulto"),
					//						query.getInteger("qtdInfantil"),
					//						query.getFloat("valorRecebido"),
					//						query.get("tipoPagamento"));
					//			}
		}
		return eventoService.update(request);
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
