import javax.swing.JOptionPane;

class Teste {

	public static void main(String[] args) {
		ClienteDAO clienteDAO = new ClienteDAO("clientes.dat");
		EventoDAO eventoDAO = new EventoDAO("eventos.dat");
		
		for(Cliente c : clienteDAO.getLista()) {
			System.out.println("Cliente: " + c.toJson());
		}
		
		eventoDAO.add(new Evento(
				"BH-Passarela",	//Nome
				"2019-05-26T10:00:00",	//Data de inicio
				"2019-05-26T15:00:00",	//Data de termino
				"Rua Dos Bobos, Numero Tal",	//Local do Evento
				20,	//Capacidade
				10,	//Minimo de participantes
				500,	//Orcamento previo
				50,	//Valor do ingresso
				"\n	Chegada: 10h.\n	Saida: 15h.",	//Cronograma
				"Aguardando quorum minimo."));	//Status
		
		exibe(eventoDAO);
		
		Evento evento = eventoDAO.get("BH-Passarela");
		evento.setCapacidade(50);
		evento.setNome("Serra da Piedade");
		eventoDAO.update(evento);
		JOptionPane.showMessageDialog(null, "Apos .update", null, 1);
		
		exibe(eventoDAO);
		
		eventoDAO.delete("Serra da Piedade");
		JOptionPane.showMessageDialog(null, "Apos .delete", null, 1);
		
		exibe(eventoDAO);
	}
	
	public static void exibe(EventoDAO eventoDAO) {
		for(Evento e : eventoDAO.getLista()) {
			JOptionPane.showMessageDialog(null, 
					"Local do Evento: " + e.getLocal() + "\n" +
					"Data de inicio: " + e.getDataInicio() + "\n" +
					"Data de termino: " + e.getDataTermino() + "\n" +
					"Capacidade: " + e.getCapacidade() + "\n" +
					"Minimo de participantes: " + e.getQuorum() + "\n" +
					"Orcamento previo: R$" + e.getOrcamentoPrevio() + "\n" +
					"Valor do ingresso: R$" + e.getValorIngresso() + "\n" +
					"Cronograma: " + e.getCronograma() + "\n" +
					"Status: " + e.getStatus() + "\n",
					e.getNome(), 1);
		}
	}

}
