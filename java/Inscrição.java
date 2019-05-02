
public class Inscrição {
	private int qtdAdulto;
	private int qtdInfantil;
	private double valorTotal;
	private double valorRecebido;
	private Cliente cliente;
	private String[] participantes;
	private Evento evento;
	private String tipoPagamento;
	private boolean status;
	
	public Inscrição(int qtdAdulto, int qtdInfantil) {
		
	}
	public double simularValor{
		
	}
	public void geraRecibo() {
		
	}
	public boolean inscricaoConfirmada() {
		
	}
	public boolean pagouTotal() {
		
	}
	public int getQtdAdulto() {
		return qtdAdulto;
	}
	public void setQtdAdulto(int qtdAdulto) {
		this.qtdAdulto = qtdAdulto;
	}
	public int getQtdInfantil() {
		return qtdInfantil;
	}
	public void setQtdInfantil(int qtdInfantil) {
		this.qtdInfantil = qtdInfantil;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public double getValorRecebido() {
		return valorRecebido;
	}
	public void setValorRecebido(double valorRecebido) {
		this.valorRecebido = valorRecebido;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String[] getParticipantes() {
		return participantes;
	}
	public void setParticipantes(String[] participantes) {
		this.participantes = participantes;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
