
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Test;

class TestEventoDAO {
	Evento evento = new Evento(
			"BH-Passarela",	//Nome
			"2019-05-26T10:00:00",	//Data de inicio
			"2019-05-26T15:00:00",	//Data de termino
			"Rua Dos Bobos, Numero Tal",	//Local do Evento
			20,	//Capacidade
			10,	//Minimo de participantes
			500,	//Orcamento previo
			50,	//Valor do ingresso
			"\n	Chegada: 10h.\n	Saida: 15h.",	//Cronograma
			"Aguardando quorum minimo.");	//Status)
	
	@Test
	void testGetAll() {
		EventoDAO eventoDAO = new EventoDAO("eventos.dat");
		assertFalse(eventoDAO.getLista().isEmpty());
	}
	
	@Test
	void testAdd() throws FileNotFoundException, IOException {
		EventoDAO eventoDAO = new EventoDAO("eventos.dat");
		eventoDAO.add(evento);
		Set<Evento> set = eventoDAO.getLista();
		assertTrue("Contem evento." ,set.contains(evento));
	}
	
	@Test
	void testGet() {
		EventoDAO eventoDAO = new EventoDAO("eventos.dat");
		eventoDAO.add(evento);
		assertEquals("Recupera evento.", evento, eventoDAO.get("BH-Passarela"));
	}
	
	@Test
	void testUpdate() {
		EventoDAO eventoDAO = new EventoDAO("eventos.dat");
		eventoDAO.add(evento);
		Set<Evento> set = eventoDAO.getLista();
		Iterator<Evento> key = set.iterator();
		Evento eventoLocal = null;
		while(key.hasNext()) {
			Evento eventoDaLista = key.next();
			if(eventoDaLista.equals(evento)) {
				eventoLocal = eventoDaLista;
				break;
			}
		}
		
		eventoLocal.setCapacidade(50);
		eventoLocal.setQuorum(25);
		eventoDAO.update(eventoLocal);
		
		Set<Evento> novoSet = eventoDAO.getLista();
		Iterator<Evento> novaKey = novoSet.iterator();
		while(novaKey.hasNext()) {
			Evento eventoDaLista = novaKey.next();
			if(eventoDaLista.equals(evento)) {
				assertEquals("Evento atualizado.", eventoLocal, eventoDaLista);
				break;
			}
		}
		
		Evento novoEvento = eventoDAO.get("BH-Passarela");
		assertEquals("Capacidade alterada.", 50, novoEvento.getCapacidade());
		assertEquals("Quorum alterado.", 25, novoEvento.getQuorum());
	}
	
	@Test
	void testDelete() {
		EventoDAO eventoDAO = new EventoDAO("eventos.dat");
		Set<Evento> set = eventoDAO.getLista();
		eventoDAO.add(evento);
		assertTrue("Contem evento." ,set.contains(evento));
		eventoDAO.delete("BH-Passarela");
		set = eventoDAO.getLista();
		assertFalse("Nao contem evento." ,set.contains(evento));
	}
	
	@Test
	void testGravar() throws FileNotFoundException, IOException {
		EventoDAO eventoDAO = new EventoDAO("eventos.dat");
		eventoDAO.add(evento);
		
		try (DataInputStream entrada = new DataInputStream(new FileInputStream("eventos.dat"))) {
			assertEquals("Nome gravado", "BH-Passarela", entrada.readUTF());
			assertEquals("Data gravada", "2019-05-26T10:00:00", entrada.readUTF());
		}
	}
}
