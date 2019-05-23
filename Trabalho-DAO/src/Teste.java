import javax.swing.JOptionPane;

class Teste {

	public static void main(String[] args) {
		ClienteDAO clienteDAO = new ClienteDAO("clientes.ser");
		EventoDAO eventoDAO = new EventoDAO("eventos.ser");
		
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
		eventoDAO.add(new Evento(
				"SP-Passarela",	//Nome
				"2020-05-26T10:00:00",	//Data de inicio
				"2020-05-26T15:00:00",	//Data de termino
				"Rua Estreira, Numero 5000",	//Local do Evento
				30,	//Capacidade
				40,	//Minimo de participantes
				600,	//Orcamento previo
				60,	//Valor do ingresso
				"\n	Chegada: 11h.\n	Saida: 14h.",	//Cronograma
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
		
		EventoDAO eventoDAONovo = new EventoDAO("eventos.ser");
		exibe(eventoDAONovo);
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
