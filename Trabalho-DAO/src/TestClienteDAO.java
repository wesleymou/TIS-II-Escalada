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

class TestClienteDAO {

	Cliente cliente = new Cliente(
			11122233344L,		//CPF
			"João da Silva",	//Nome
			3134225566L,		//Numero de telefone residencial
			31997224455L,		//Numero de celular 
			"Rua dos Bobos, n Tal",	//Endereco
			"jao@gmail.com",	//Email
			"BH-Passarela");	//Eventos cadastrados
	
	@Test
	void testGetAll() {
		ClienteDAO clienteDAO = new ClienteDAO("clientes.dat");
		assertFalse(clienteDAO.getLista().isEmpty());
	}
	
	@Test
	void testAdd() throws FileNotFoundException, IOException {
		ClienteDAO clienteDAO = new ClienteDAO("clientes.dat");
		clienteDAO.add(cliente);
		Set<Cliente> set = clienteDAO.getLista();
		assertTrue("Contem evento." ,set.contains(cliente));
	}
	
	@Test
	void testGet() {
		ClienteDAO clienteDAO = new ClienteDAO("clientes.dat");
		clienteDAO.add(cliente);
		assertEquals("Recupera evento.", cliente, clienteDAO.get(11122233344L));
	}
	
	@Test
	void testUpdate() {
		ClienteDAO clienteDAO = new ClienteDAO("clientes.dat");
		clienteDAO.add(cliente);
		Set<Cliente> set = clienteDAO.getLista();
		
		Iterator<Cliente> key = set.iterator();
		Cliente clienteLocal = null;
		while(key.hasNext()) {
			Cliente clienteDaLista = key.next();
			if(clienteDaLista.equals(cliente)) {
				clienteLocal = clienteDaLista;
				break;
			}
		}
		
		clienteLocal.setEndereco("Rua Epaminondas, n 1000");;
		clienteLocal.setNome("Maria da Silva");
		clienteDAO.update(clienteLocal);
		
		Set<Cliente> novoSet = clienteDAO.getLista();
		Iterator<Cliente> novaKey = novoSet.iterator();
		Cliente novoClienteLocal = null;
		while(novaKey.hasNext()) {
			Cliente clienteDaLista = novaKey.next();
			if(clienteDaLista.equals(clienteLocal)) {
				novoClienteLocal = clienteDaLista;
				break;
			}
		}
		
		
		assertEquals("Endereco atualizado.", "Rua Epaminondas, n 1000", novoClienteLocal.getEndereco());
		assertEquals("Nome atualizado.", "Maria da Silva", novoClienteLocal.getNome());
	}
	
	@Test
	void testDelete() {
		ClienteDAO clienteDAO = new ClienteDAO("clientes.dat");
		Set<Cliente> set = clienteDAO.getLista();
		clienteDAO.add(cliente);
		assertTrue("Contem evento." ,set.contains(cliente));
		clienteDAO.delete(11122233344L);
		set = clienteDAO.getLista();
		assertFalse("Nao contem evento." ,set.contains(cliente));
	}
	
	@Test
	void testGravar() throws FileNotFoundException, IOException {
		ClienteDAO clienteDAO = new ClienteDAO("clientes.dat");
		clienteDAO.add(cliente);
		
		try (DataInputStream entrada = new DataInputStream(new FileInputStream("clientes.dat"))) {
			assertEquals("CPF gravado", 11122233344L, entrada.readLong());
			assertEquals("Nome gravado", "João da Silva", entrada.readUTF());
		}
	}
}
