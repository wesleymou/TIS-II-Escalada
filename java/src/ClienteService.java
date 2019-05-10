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
		
	private ListaClientes listaDeClientes;
	
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
		
		this.listaDeClientes.create(cliente);
		return cliente.toJson();
	}
	
	public JSONObject get(Request request) {
		Cliente cliente = listaDeClientes.read(request.getQuery().get(NOME));
/*		float cpf = request.getQuery().getFloat(CPF);
		Cliente cliente = listaDeClientes.read(cpf);*/

		if (cliente != null)
			return cliente.toJson();
		return null;
	}
	
	//TODO terminar metodo
	public JSONObject update(Request request) {
		Cliente cliente = listaDeClientes.read(request.getQuery().get(NOME));
//		float cpf = request.getQuery().getFloat(CPF);
//		Cliente cliente = listaDeClientes.read(cpf);
		Query query = request.getQuery();
		if(query.get(NOVONOME) != "")
			cliente.setNome(query.get(NOVONOME));
		cliente.setCpf(query.getFloat(CPF));
		cliente.setNumFone1(query.getFloat(NUNFONE1));
		cliente.setNumFone2(query.getFloat(NUNFONE2));
		cliente.setEndereco(query.get(ENDERECO));
		cliente.setEmail(query.get(EMAIL));
		cliente.setEventos(query.get(EVENTOS));
		
		return cliente.toJson();
	}
	
	public JSONObject remove (Request request) {
		Cliente cliente = listaDeClientes.delete(request.getQuery().get(NOME));
		if (cliente != null)
			return cliente.toJson();
		return null;
//		float cpf = request.getQuery().getFloat(CPF);
//		return 	this.listaDeClientes.delete(cpf).toJson();
	}

	public ClienteService() {
		listaDeClientes = new ListaClientes();
	}
}
