import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

class EventoService {
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
		
		return null;
	}
	
	public String get(Request request) {
		Query query = request.getQuery();
		String id = query.get("");
		
		return null;
		
	}
	
	public String update(Request request) {
		Query query = request.getQuery();
		
		return null;
		
	}
	
	public String remove (Request request) {
		Query query = request.getQuery();
		String id = query.get("");
		
		return null;
		
	}
}
