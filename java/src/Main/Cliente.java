/* Pontifícia Universidade Católica de Minas Gerais || Trabalho Interdisciplinar de Software - 2º período
    Membros:
    Filipe Iannarelli Caldeira
    Gabriel Vinicius Ramos da Silva
    Paulo Angelo Dias Barbosa
    Wesley Mouraria Pereira
*/
package Main;

import java.io.Serializable;

import org.json.JSONObject;

import com.google.gson.Gson;

//Criação da classe Cliente, seu construtor, variáveis e métodos,
//implementando as interfaces Comparable e Serializable para ordenação dos objetos
public class Cliente implements Comparable<Cliente>,Serializable{

	private static final long serialVersionUID = 1L;

	
	private long cpf;
	private String nome;
	private long numFone1;
	private long numFone2;
	private String endereco;
	private String email;
	private String status;
	
	public Cliente(long cpf, String nome, long numFone1, long numFone2, String endereco, String email, String status){
		setCpf(cpf);
		setNome(nome);
		setNumFone1(numFone1);
		setNumFone2(numFone2);
		setEndereco(endereco);
		setEmail(email);
		setStatus(status);
	}
	
	@Override
	public int compareTo(Cliente cliente) {
		return Long.compare(this.getCpf(), cliente.getCpf());
	}
	
	@Override
	public boolean equals (Object o) {
		if(o instanceof Cliente && this.getNome().equalsIgnoreCase(((Cliente)o).getNome()))
			return true;
		if(o instanceof Long && this.getCpf() ==(Long) o)
			return true;
		return false;
	}
	
	public JSONObject toJson() {
		return new JSONObject(new Gson().toJson(this));

	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getNumFone1() {
		return numFone1;
	}

	public void setNumFone1(long numFone1) {
		this.numFone1 = numFone1;
	}

	public long getNumFone2() {
		return numFone2;
	}

	public void setNumFone2(long numFone2) {
		this.numFone2 = numFone2;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
