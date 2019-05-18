
public class Fornecedor {
	
	private int[] cnpj, telefone;
	private String nome, endereco, email, servico;
	
	public Fornecedor (int[]cnpj, String nome) {
		this.cnpj = cnpj;
		this.nome = nome;		
	}
	
	public Fornecedor (int[]cnpj, String nome, int[] telefone, String endereco, String email, String servico ) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.servico = servico;		
	}

	public int[] getCnpj() {
		return cnpj;
	}

	public int[] getTelefone() {
		return telefone;
	}

	public void setTelefone(int[] telefone) {
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
