import org.json.JSONObject;

class Cliente {
	private static final String CPF = "cpf";
	private static final String NOME = "nome";
	private static final String NUNFONE1 = "telRes";
	private static final String NUNFONE2 = "telCel";
	private static final String ENDERECO = "endereco";
	private static final String EMAIL = "email";
	private static final String EVENTOS = "eventosInscritos";
	
	private float cpf;
	private String nome;
	private float nunFone1;
	private float nunFone2;
	private String endereco;
	private String email;
	private Evento[] eventos;
	
	Cliente(float cpf, String nome, float nunFone1){
		this.cpf = cpf;
		this.nome = nome;
		this.nunFone1 = nunFone1;
	}
	
	public JSONObject toJson() {
		JSONObject clienteJson = new JSONObject();
		clienteJson.put(CPF, this.cpf);
		clienteJson.put(NOME, this.nome);
		clienteJson.put(NUNFONE1, this.nunFone1);
		clienteJson.put(NUNFONE2, this.nunFone2);
		clienteJson.put(ENDERECO, this.endereco);
		clienteJson.put(EMAIL, this.email);
		clienteJson.put(EVENTOS, this.eventos);
		
		return clienteJson;
	}

	public float getCpf() {
		return cpf;
	}

	public void setCpf(float cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getNunFone1() {
		return nunFone1;
	}

	public void setNunFone1(float nunFone1) {
		this.nunFone1 = nunFone1;
	}

	public float getNunFone2() {
		return nunFone2;
	}

	public void setNunFone2(float nunFone2) {
		this.nunFone2 = nunFone2;
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

	public Evento[] getEventos() {
		return eventos;
	}

	public void setEventos(Evento[] eventos) {
		this.eventos = eventos;
	}

}
