public class CelulaEvento {

	Evento item;
	CelulaEvento proximo;

	CelulaEvento(Evento evento) {
		item = evento;
		proximo = null;
	}

	CelulaEvento() {

	}
}
