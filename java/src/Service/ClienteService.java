package Service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import DAO.ClienteDAO;
import Main.Cliente;

public class ClienteService {
	
	private static final String CPF = "cpf";
	private static final String NOME = "nome";
	private static final String NUNFONE1 = "numFone1";
	private static final String NUNFONE2 = "numFone2";
	private static final String ENDERECO = "endereco";
	private static final String EMAIL = "email";
	private static final String STATUS = "status";
	private static final String NOVOCPF = "novoCpf";
		
	private ClienteDAO clienteDAO;
	
	public ClienteService() {
		clienteDAO = new ClienteDAO("clientes.ser");
	}
	
	public JSONObject add(Request request) {
		Query query = request.getQuery();

		long cpf = Long.parseLong(query.get(CPF));
		String nome = query.get(NOME);
		long nunFone1 = Long.parseLong(query.get(NUNFONE1));
		long nunFone2 = Long.parseLong(query.get(NUNFONE2));
		String endereco = query.get(ENDERECO);
		String email = query.get(EMAIL);
		String status = query.get(STATUS);
		
		Cliente cliente = new Cliente(
				cpf,
				nome,
				nunFone1,
				nunFone2,
				endereco,
				email,
				status);
		
		this.clienteDAO.add(cliente);
		return cliente.toJson();
	}
	
	public JSONArray get(Request request) {
		JSONArray listaJson = new JSONArray();
		clienteDAO.getLista().stream()
		.forEach(c -> listaJson.put(c.toJson()));
		return listaJson;
	}
	
	
	public Cliente getCliente(Request request) {
		return clienteDAO.get(Long.parseLong(request.getQuery().get(CPF)));
	}
	
	public JSONObject update(Request request) {
		Query query = request.getQuery();
		Cliente cliente = clienteDAO.get(Long.parseLong(query.get(CPF)));
		
		String novoCpf = query.get(NOVOCPF);
		if(novoCpf != "")
			cliente.setCpf(Long.parseLong(novoCpf));
		cliente.setNome(query.get(NOME));
		cliente.setNumFone1(Long.parseLong(query.get(NUNFONE1)));
		cliente.setNumFone2(Long.parseLong(query.get(NUNFONE2)));
		cliente.setEndereco(query.get(ENDERECO));
		cliente.setEmail(query.get(EMAIL));
		cliente.setStatus(query.get(STATUS));
		
		clienteDAO.update(cliente);
		return cliente.toJson();
	}
	
	public JSONObject remove (Request request) {
		return new JSONObject(clienteDAO.delete(Long.parseLong(request.getQuery().get(CPF))));
	}

}
