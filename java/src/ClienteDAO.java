import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements DAO<Cliente,Double> {
	
	public ClienteDAO() {
		
	}

	@Override
	public void add(Cliente cliente) {
		
		try (DataOutputStream saida = new DataOutputStream(new FileOutputStream("clientes.dat", true))) {
			saida.writeDouble(cliente.getCpf());
			saida.writeUTF(cliente.getNome());
			saida.writeDouble(cliente.getNumFone1());
			saida.writeDouble(cliente.getNumFone2());
			saida.writeUTF(cliente.getEndereco());
			saida.writeUTF(cliente.getEmail());
			saida.writeUTF(cliente.getEventos());
			saida.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o cliente '" + cliente.getNome() + "' no disco!");
			e.printStackTrace();
		}
	}
	
	public Cliente get(double chave) {
		try (DataInputStream entrada = new DataInputStream(new FileInputStream("clientes.dat"))) {
			while (entrada.available()>0) {		
				double cpf = entrada.readDouble();
				String nome = entrada.readUTF();
				double numFone1 = entrada.readDouble();
				double numFone2 = entrada.readDouble();
				String endereco = entrada.readUTF();
				String email = entrada.readUTF();
				String eventos = entrada.readUTF();

				if(chave == cpf)
					return new Cliente(cpf, nome, numFone1, numFone2, endereco, email, eventos);
			}
		} catch(EOFException e) { 

		} catch (Exception e) {
			System.out.println("ERRO ao ler o cliente '" + chave + "' do disco rígido!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Cliente cliente) {
		List<Cliente> lista = this.getAll();
		for(Cliente e : lista) {
			if(e.getCpf() == cliente.getCpf())
				e = cliente;
		}
		File file = new File("clientes.dat");
		if (file.exists())
			file.delete();
		for(Cliente e: lista)
			this.add(e);
	}

	@Override
	public boolean delete(Double chave) {
		List<Cliente> lista = this.getAll();
		boolean foiRemovido = lista.removeIf(T -> T.getCpf()==chave);
		if(foiRemovido) {
			File file = new File("clientes.dat");
			if (file.exists())
				file.delete();
			for(Cliente e: lista)
				this.add(e);
		}
		return foiRemovido;
	}

	@Override
	public List<Cliente> getAll() {
		List<Cliente> lista = new ArrayList<Cliente>();
		try (DataInputStream entrada = new DataInputStream(new FileInputStream("clientes.dat"))) {
			while (entrada.available()>0) {
				Cliente cliente = new Cliente(
						entrada.readDouble(),
						entrada.readUTF(),
						entrada.readDouble(),
						entrada.readDouble(),
						entrada.readUTF(),
						entrada.readUTF(),
						entrada.readUTF());
				lista.add(cliente);
			}
			return lista;	
		} catch(EOFException e) {
			System.out.println("Erro de EOFException.");
		} catch (Exception e) {
			System.out.println("ERRO ao ler os eventos do disco rígido!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Cliente get(Double chave) {
		// TODO Auto-generated method stub
		return null;
	}
}
