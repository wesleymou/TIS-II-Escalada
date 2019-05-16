import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

public class ClienteService {
	
	private static final String CPF = "cpf";
	private static final String NOME = "nome";
	private static final String NUNFONE1 = "telRes";
	private static final String NUNFONE2 = "telCel";
	private static final String ENDERECO = "endereco";
	private static final String EMAIL = "email";
	private static final String EVENTOS = "eventosInscritos";
	private static final String NOVONOME = "novoNome";
		
	private ClienteDAO clienteDAO;
	
	public ClienteService() {
		clienteDAO = new ClienteDAO();
	}
	
	public JSONObject add(Request request) {
		Query query = request.getQuery();

		float cpf = query.getFloat(CPF);
		String nome = query.get(NOME);
		float nunFone1 = query.getFloat(NUNFONE1);
		float nunFone2 = query.getFloat(NUNFONE2);
		String endereco = query.get(ENDERECO);
		String email = query.get(EMAIL);
		String eventos = query.get(EVENTOS);
		
		Cliente cliente = new Cliente(cpf, nome, nunFone1, nunFone2, endereco, email, eventos);
		
		this.clienteDAO.add(cliente);
		return cliente.toJson();
	}
	
	public JSONObject get(Request request) {
		return clienteDAO.get(request.getQuery().getFloat(CPF)).toJson();
//		return this.getAll(request);
	}
	
	public JSONObject update(Request request) {
		Cliente cliente = clienteDAO.get(request.getQuery().getFloat(CPF));

		Query query = request.getQuery();
		if(query.get(NOVONOME) != "")
			cliente.setNome(query.get(NOVONOME));
		cliente.setCpf(query.getFloat(CPF));
		cliente.setNumFone1(query.getFloat(NUNFONE1));
		cliente.setNumFone2(query.getFloat(NUNFONE2));
		cliente.setEndereco(query.get(ENDERECO));
		cliente.setEmail(query.get(EMAIL));
		cliente.setEventos(query.get(EVENTOS));
		
		clienteDAO.update(cliente);
		return cliente.toJson();
	}
	
	public JSONObject remove (Request request) {
		return new JSONObject(clienteDAO.delete(request.getQuery().getFloat(CPF)));
	}
	
	public JSONArray getAll(Request request) {
		List<Cliente> lista = clienteDAO.getAll();
		JSONArray listaJson = new JSONArray();
		if(!lista.isEmpty()){
			for(Cliente e : lista) {
				listaJson.put(e.toJson());
			}
		}else {
			JSONObject j = new JSONObject();
			listaJson.put(0, "null");
		}
		return listaJson;
	}
}
