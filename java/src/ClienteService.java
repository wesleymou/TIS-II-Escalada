import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

class ClienteService {
	
	private static final String CPF = "cpf";
	private static final String NOME = "nome";
	private static final String NUNFONE1 = "telRes";
	private static final String NUNFONE2 = "telCel";
	private static final String ENDERECO = "endereco";
	private static final String EMAIL = "email";
//	private static final String EVENTOS = "eventosInscritos";
	
	
	private ListaClientes listaDeClientes;
	
	public JSONObject add(Request request) {
		Query query = request.getQuery();

		int cpf = query.getInteger(CPF);
		String nome = query.get(NOME);
		int nunFone1 = query.getInteger(NUNFONE1);
		int nunFone2 = query.getInteger(NUNFONE2);
		String endereco = query.get(ENDERECO);
		String email = query.get(EMAIL);
		
		Cliente cliente = new Cliente(cpf, nome, nunFone1);
		cliente.setNunFone2(nunFone2);
		cliente.setEndereco(endereco);
		cliente.setEmail(email);
		
		this.listaDeClientes.create(cliente);
		
		return cliente.toJson();
	}
	
	public JSONObject get(Request request) {
		int cpf = request.getQuery().getInteger(CPF);
		Cliente cliente = listaDeClientes.read(cpf);
		if (cliente != null)
			return cliente.toJson();
		return null;
	}
	
	//TODO terminar metodo
	public JSONObject update(Request request) {
		int cpf = request.getQuery().getInteger(CPF);
		Cliente cliente = listaDeClientes.read(cpf);
		if (cliente != null)
			return cliente.toJson();
		return null;
	}
	
	public JSONObject remove (Request request) {
		int cpf = request.getQuery().getInteger(CPF);
		return 	this.listaDeClientes.delete(cpf).toJson();
	}

	public ClienteService() {
		listaDeClientes = new ListaClientes();
	}
}
