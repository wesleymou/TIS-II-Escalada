import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Inscricao {
	
	private int qtdAdulto;
	private int qtdInfantil;
	private double valorTotal =0;
	private double valorRecebido = 0;
	private Cliente cliente;
	private String[] participantes;
	private Evento evento;
	private String tipoPagamento;
	private boolean status;
	private PrintWriter gravar;
	
	public Inscricao(int qtdAdulto, int qtdInfantil) {
		this.qtdAdulto = qtdAdulto;
		this.qtdInfantil = qtdInfantil;		
	}
	
	public double simularValor(){
		valorTotal = (qtdAdulto * evento.getValorIngresso()) + (qtdInfantil * (evento.getValorIngresso()/2));
		return valorTotal;		
	}
	
	public void geraRecibo() throws IOException {
		FileWriter arq = new FileWriter("Recibo.txt");
		gravar = new PrintWriter(arq);
		gravar.printf("Recibo\nNome do cliente: " + cliente.getNome() + "\nTipo de pagamento: " + tipoPagamento + "Valor pago: " + valorRecebido + "\nParticipantes cadastrados: ");
		for (int i = 0; i<(qtdInfantil + qtdAdulto); i++) {
			gravar.printf("\n-"+participantes[i]);
		}
		gravar.printf("\nEste recibo nao se trata de documento fiscal");
		arq.close();
			Runtime.getRuntime().exec("Recibo.txt");
	}
	
	public boolean isInscricaoConfirmada() {
		return status;		
	}
	
	public boolean isPagouTotal() {
		if(valorRecebido >= valorTotal)
			status = true;
		else
			status = false;
		return status;		
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
		this.valorRecebido += valorRecebido;
	}
	
	public double valorFaltaPagar() {
		return valorTotal - valorRecebido; 
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
