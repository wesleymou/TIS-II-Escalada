package Service;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import DAO.FornecedorDAO;
import Main.Fornecedor;

public final class FornecedorService {	

	private static final String CNPJ = "cnpj";
	private static final String NOME = "nome";
	private static final String TELEFONE = "telefone";
	private static final String ENDERECO = "endereco";
	private static final String EMAIL = "email";
	private static final String SERVICO = "servico";
	private static final String NOVONOME = "novoNome";
	
	private FornecedorDAO fornecedorDAO;
	
	public FornecedorService() {
		fornecedorDAO = new FornecedorDAO("fornecedores.ser");
	}
	
	public JSONObject add(Request request) {
		Query query = request.getQuery();

		long cnpj = query.getInteger(CNPJ);
		String nome = query.get(NOME);
		long telefone = query.getInteger(TELEFONE);
		String endereco = query.get(ENDERECO);
		String email = query.get(EMAIL);
		String servico = query.get(SERVICO);
		
		Fornecedor fornecedor = new Fornecedor(
				cnpj, 
				nome, 
				telefone, 
				endereco, 
				email, 
				servico);

		this.fornecedorDAO.add(fornecedor);
		return fornecedor.toJson();
	}
	
	public JSONArray get(Request request) {
		Set<Fornecedor> lista = fornecedorDAO.getLista();
		JSONArray listaJSON = new JSONArray();
		if(!lista.isEmpty()) {
			for(Fornecedor f : lista) {
				listaJSON.put(f.toJson());
			}
		} else {
			listaJSON.put(0, "null");
		}
		return listaJSON;
	}

	public JSONObject update (Request request) {
		Query query = request.getQuery();
		Fornecedor fornecedor = fornecedorDAO.get(request.getQuery().get(NOME));

		String novoNome = query.get(NOVONOME);
		if(novoNome != "")
			fornecedor.setNome(novoNome);
		fornecedor.setTelefone(query.getInteger(TELEFONE));
		fornecedor.setEndereco(query.get(ENDERECO));
		fornecedor.setEmail(query.get(EMAIL));
		fornecedor.setServico(query.get(SERVICO));
		
		fornecedorDAO.update(fornecedor);
		return fornecedor.toJson();		
	}
	

	public JSONObject remove (Request request) { 
		return new JSONObject(fornecedorDAO.delete(request.getQuery().get(NOME)));
	}
}
