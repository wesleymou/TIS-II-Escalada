
public class Processo{
	private int numero;
	private String status, dados;
	private Fornecedor fornecedor;
	
	public Processo(int numero, String status, Fornecedor fornecedor, String dados) {
		this.numero = numero;
		this.status = status;
		this.fornecedor = fornecedor;
		this.dados = dados;
	}

	public Processo(String status, Fornecedor fornecedor) {
		this.status = status;
		this.fornecedor = fornecedor;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}	
}
