import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Evento {
	private String nome;
	private LocalDateTime dataInicio;
	private LocalDateTime dataTermino;
	private String local;
	private int capacidade;
	private int quorum;
	private double orcamentoPrevio;
	private double valorIngresso;
	private Map <Array,Array> cronograma = new HashMap<Array,Array>();
	private int convenio[];
	//private Inscricoes = new Inscricao[];
	private String status;
		public Evento(String nome, String local, LocalDateTime inicio, LocalDateTime termino){
			this.setNome(nome);
			this.setLocal(local);
			this.setDataInicio(inicio);
			this.setDataTermino(termino);
		}
		
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public LocalDateTime getDataInicio() {
			return dataInicio;
		}
		public void setDataInicio(LocalDateTime dataInicio) {
			this.dataInicio = dataInicio;
		}
		public LocalDateTime getDataTermino() {
			return dataTermino;
		}
		public void setDataTermino(LocalDateTime dataTermino) {
			this.dataTermino = dataTermino;
		}
		public String getLocal() {
			return local;
		}
		public void setLocal(String local) {
			this.local = local;
		}
		public int getCapacidade() {
			return capacidade;
		}
		public void setCapacidade(int capacidade) {
			this.capacidade = capacidade;
		}
		public int getQuorum() {
			return quorum;
		}
		public void setQuorum(int quorum) {
			this.quorum = quorum;
		}
		public double getOrcamentoPrevio() {
			return orcamentoPrevio;
		}
		public void setOrcamentoPrevio(double orcamentoPrevio) {
			this.orcamentoPrevio = orcamentoPrevio;
		}
		public double getValorIngresso() {
			return valorIngresso;
		}
		public void setValorIngresso(double valorIngresso) {
			this.valorIngresso = valorIngresso;
		}
		
		public int[] getConvenio() {
			return convenio;
		}
		public void setConvenio(int[] convenio) {
			this.convenio = convenio;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}

		public Map <Array,Array> getCronograma() {
			return cronograma;
		}

		public void setCronograma(Map <Array,Array> cronograma) {
			this.cronograma = cronograma;
		}
}
