/* Pontifícia Universidade Católica de Minas Gerais || Trabalho Interdisciplinar de Software - 2º período
    Membros:
    Filipe Iannarelli Caldeira
    Gabriel Vinicius Ramos da Silva
    Paulo Angelo Dias Barbosa
    Wesley Mouraria Pereira
*/
package Service;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Request;

import Main.Evento;
import Main.Inscricao;

//Criação da classe IndicadorService, seu construtor, variáveis e métodos,
//utilizado como uma "interface" entre os campos da página e os dados de um fornecedor no servidor, além dos cálculos requisitados
public class IndicadorService {

	private EventoService eventoService;

	public IndicadorService(EventoService eventoService) {
		this.eventoService = eventoService;
	}


	//************ Porcentagem de inscritos em relação à capacidade total do evento ***************//

	public JSONObject porcInsc(Evento evento) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("porcInsc", evento.getInscricoes().size() + "/" + evento.getCapacidade() + " (" + (evento.getInscricoes().size() / evento.getCapacidade()) * 100 + "%)");
		return jsonObject;
	}


	//************ Porcentagem de inscritos que pagaram parcialmente em relação à quantidade total de inscritos ***************//

	public JSONObject pagouParcial(Evento evento) {
		int i=0;
		for(Inscricao inscricao: evento.getInscricoes())
			if(!inscricao.estaPago())
				i++;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagouParcial", i + "/" + evento.getInscricoes().size() + " (" + (i*1.0/evento.getInscricoes().size()) * 100 + "%)");
		return jsonObject;
	}


	//************ Porcentagem de inscritos que pagaram totalmente em relação à quantidade total de inscritos ***************//

	public JSONObject pagouTotal(Evento evento) {
		int i=0;
		for(Inscricao inscricao: evento.getInscricoes())
			if(inscricao.estaPago())
				i++;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagouTotal", i + "/" + evento.getInscricoes().size() + " (" + (i*1.0/evento.getInscricoes().size()) * 100 + "%)");
		return jsonObject;
	}


	//************ Porcentagem de inscritos que pagaram totalmente ou parcialmente no DÉBITO ou DINHEIRO ***************//

	public JSONObject pagouDebito(Evento evento) {
			int i=0;
			for(Inscricao inscricao: evento.getInscricoes())
				if(inscricao.getTipoPagamento() == "D�bito" || inscricao.getTipoPagamento() == "Dinheiro")
					i++;
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("pagouDebito", i + "/" + evento.getInscricoes().size() + " (" + (i*1.0/evento.getInscricoes().size()) * 100 + "%)");
			return jsonObject;
	}


	//************ Porcentagem de inscritos que pagaram totalmente ou parcialmente no CRÉDITO ou CHEQUE ***************//

	public JSONObject pagouCredito(Evento evento) {
			int i=0;
			for(Inscricao inscricao: evento.getInscricoes())
				if(inscricao.getTipoPagamento() == "Cr�dito" || inscricao.getTipoPagamento() == "Cheque")
					i++;
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("pagouCredito", i + "/" + evento.getInscricoes().size() + " (" + (i*1.0/evento.getInscricoes().size()) * 100 + "%)");
			return jsonObject;
	}



	public JSONArray getAll(Request request) {
		Set<Evento> eventos = eventoService.getAllEventos();
		JSONArray lista = new JSONArray();
		for (Evento evento : eventos) {
			JSONArray jsonArray = new JSONArray();
			jsonArray.put(porcInsc(evento));
			jsonArray.put(pagouDebito(evento));
			jsonArray.put(pagouCredito(evento));
			jsonArray.put(pagouParcial(evento));
			jsonArray.put(pagouTotal(evento));

			JSONObject jsonObject = new JSONObject();
			jsonObject.put(evento.getNome(), jsonArray);
			lista.put(jsonObject);
		}
		return lista;
	}

}
