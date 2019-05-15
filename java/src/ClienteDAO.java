import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements DAO<Cliente,Float> {
	
	public ClienteDAO() {
		
	}

	@Override
	public void add(Cliente cliente) {
		try (DataOutputStream saida = new DataOutputStream(new FileOutputStream("clientes.dat", true))) {
			saida.writeFloat(cliente.getCpf());
			saida.writeUTF(cliente.getNome());
			saida.writeFloat(cliente.getNumFone1());
			saida.writeFloat(cliente.getNumFone2());
			saida.writeUTF(cliente.getEndereco());
			saida.writeUTF(cliente.getEmail());
			saida.writeUTF(cliente.getEventos());
			saida.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o cliente '" + cliente.getNome() + "' no disco!");
			e.printStackTrace();
		}
	}

	
	public Cliente get(float chave) {
		try (DataInputStream entrada = new DataInputStream(new FileInputStream("clientes.dat"))) {
			while (entrada.available()>0) {		
				float cpf = entrada.readFloat();
				String nome = entrada.readUTF();
				float numFone1 = entrada.readFloat();
				float numFone2 = entrada.readFloat();
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
	}

	@Override
	public boolean delete(Float chave) {
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
						entrada.readFloat(),
						entrada.readUTF(),
						entrada.readFloat(),
						entrada.readFloat(),
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
	public Cliente get(Float chave) {
		// TODO Auto-generated method stub
		return null;
	}
}
