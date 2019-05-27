import java.io.Serializable;

import org.json.JSONObject;

import com.google.gson.Gson;

public class Fornecedor implements Comparable<Fornecedor>, Serializable{

	private static final long serialVersionUID = 1L;
	private long cnpj, telefone;
	private String nome, endereco, email, servico;
	
	public Fornecedor (long cnpj, String nome) {
		this.cnpj = cnpj;
		this.nome = nome;		
	}
	
	public Fornecedor (long cnpj, String nome, long telefone, String endereco, String email, String servico ) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.servico = servico;		
	}
	
	public JSONObject toJson() {
		return new JSONObject(new Gson().toJson(this));
	}
	
	@Override
	public int compareTo(Fornecedor fornecedor) {
		return this.getNome().compareToIgnoreCase(fornecedor.getNome());
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Fornecedor && this.getNome().equalsIgnoreCase(((Fornecedor)o).getNome()))
			return true;
		if(o instanceof Long && this.getCnpj() == (Long)o)
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
