import org.json.JSONObject;

class Cliente {
	private static final String CPF = "cpf";
	private static final String NOME = "nome";
	private static final String NUMFONE1 = "telRes";
	private static final String NUMFONE2 = "telCel";
	private static final String ENDERECO = "endereco";
	private static final String EMAIL = "email";
	private static final String EVENTOS = "eventosInscritos";
	
	private float cpf;
	private String nome;
	private float numFone1;
	private float numFone2;
	private String endereco;
	private String email;
	private String eventos;
//	private Evento[] eventos;
	
	Cliente(float cpf, String nome, float numFone1, float numFone2, String endereco, String email, String eventos){
		setCpf(cpf);
		setNome(nome);
		setNumFone1(numFone1);
		setNumFone2(numFone2);
		setEndereco(endereco);
		setEmail(email);
		setEventos(eventos);
	}
	
	public JSONObject toJson() {
		JSONObject clienteJson = new JSONObject();
		clienteJson.put(CPF, this.cpf);
		clienteJson.put(NOME, this.nome);
		clienteJson.put(NUMFONE1, this.numFone1);
		clienteJson.put(NUMFONE2, this.numFone2);
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

	public float getNumFone1() {
		return numFone1;
	}

	public void setNumFone1(float numFone1) {
		this.numFone1 = numFone1;
	}

	public float getNumFone2() {
		return numFone2;
	}

	public void setNumFone2(float numFone2) {
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

	public String getEventos() {
//	public Evento[] getEventos() {
		return eventos;
	}

	public void setEventos(String eventos) {
//	public void setEventos(Evento[] eventos) {
		this.eventos = eventos;
	}

}