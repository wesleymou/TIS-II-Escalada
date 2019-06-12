package Main;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;


public class Fornecedor implements Comparable<Fornecedor>, Serializable{

	private static final long serialVersionUID = 1L;
	private long cnpj, telefone;
	private String nome, endereco, email, servico;

	private Set<Fornecedor> fornecedores = new HashSet<Fornecedor>();
	
	public Fornecedor (long cnpj, String nome) {
		this.cnpj = cnpj;
		this.nome = nome;		
	}
	
	public Fornecedor (long cnpj2, String nome, long telefone2, String endereco, String email, String servico ) {
		this.cnpj = cnpj2;
		this.nome = nome;
		this.telefone = telefone2;
		this.endereco = endereco;
		this.email = email;
		this.servico = servico;		
	}
	
	public JSONObject toJson() {

		JSONObject j = new JSONObject();
		j.put("cnpj", cnpj);
	    j.put("nome", nome);
	    j.put("telefone", telefone);
	    j.put("endereco", endereco);
	    j.put("email", email);
	    j.put("servico", servico);
	    for (Fornecedor fornecedor : this.fornecedores) {
			j.put("fornecedor",fornecedor.toJson());
		}
		return j;
	}
	
	@Override
	public int compareTo(Fornecedor fornecedor) {
		return this.getNome().compareToIgnoreCase(fornecedor.getNome());
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Fornecedor && this.getNome().equalsIgnoreCase(((Fornecedor)o).getNome()))
			return true;
		if(o instanceof String && this.getNome().equalsIgnoreCase((String)o))
			return true;
		return false;
	}

	public long getCnpj() {
		return cnpj;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}
}
