package Main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import org.json.JSONObject;

public class Inscricao implements Serializable, Comparable<Inscricao>{

	private static final long serialVersionUID = 1L;
	private int qtdAdulto;
	private int qtdInfantil;
	private double valorTotal =0;
	private double valorRecebido = 0;
	private Cliente cliente;
	private Evento evento;
	private String tipoPagamento;

	public Inscricao(Cliente cliente, Evento evento, int qtdAdulto, int qtdInfantil, float valorRecebido,String tipoPagamento) {
		this.cliente = cliente;
		this.evento = evento;
		this.qtdAdulto = qtdAdulto;
		this.qtdInfantil = qtdInfantil;
		this.valorRecebido = valorRecebido;
		this.valorTotal = (qtdAdulto * evento.getValorIngresso()) + (qtdInfantil * (evento.getValorIngresso()/2));
		this.tipoPagamento = tipoPagamento;

	}
	
	public boolean equals(Object o) {
		return o instanceof Long && this.cliente.getCpf() == ((Long)o);
	}

	public boolean estaPago() {
		return valorTotal <= valorRecebido;
	}

	public JSONObject toJson() {
//		return new JSONObject(new Gson().toJson(this));
		JSONObject j = new JSONObject();
		j.put("qtdAdulto", qtdAdulto);
		j.put("qtdInfantil", qtdInfantil);
		j.put("valorTotal", valorTotal);
		j.put("valorRecebido", valorRecebido);
		j.put("cliente", cliente.toJson());
		j.put("evento", evento);
		j.put("tipoPagamento", tipoPagamento);
		return j;
	}
	
	public void geraRecibo(double valor) throws IOException {
		PrintWriter gravar = new PrintWriter(new FileWriter("Recibo.txt"));
		gravar.printf("Confirmo por meio deste, o pagamento no valor de R$ "+valor+
				", por parte do cliente "+cliente.getNome()+", CPF: "+cliente.getCpf()+
				", referente ao evento "+evento.getNome()+", na academia Escalada.");
		gravar.printf("\nEste recibo nao se trata de documento fiscal");
		gravar.close();
		Runtime.getRuntime().exec("Recibo.txt");
	}

	public int getQtdAdulto() {
		return qtdAdulto;
	}

	public void setQtdAdulto(int qtdAdulto) {
		this.qtdAdulto = qtdAdulto;
		this.valorTotal = (qtdAdulto * evento.getValorIngresso()) + (qtdInfantil * (evento.getValorIngresso()/2));
	}

	public int getQtdInfantil() {
		return qtdInfantil;
	}

	public void setQtdInfantil(int qtdInfantil) {
		this.qtdInfantil = qtdInfantil;
		this.valorTotal = (qtdAdulto * evento.getValorIngresso()) + (qtdInfantil * (evento.getValorIngresso()/2));
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public double getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(double valorRecebido) {
		this.valorRecebido += valorRecebido;
	}

	public double valorPendente() {
		return valorTotal - valorRecebido; 
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	@Override
	public int compareTo(Inscricao o) {
		return Long.compareUnsigned(cliente.getCpf(), o.getCliente().getCpf());
	}

}
