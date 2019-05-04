
public class CelulaCliente {
	Cliente item;
	CelulaCliente proximo;

	CelulaCliente(Cliente cliente) {
		this.item = cliente;
		this.proximo = null;
	}

	CelulaCliente(Cliente cliente, CelulaCliente proximo) {
		this.item = cliente;
		this.proximo = proximo;
	}

	CelulaCliente() {

	}
}
