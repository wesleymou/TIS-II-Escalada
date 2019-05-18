import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class ClienteDAO extends DAO<Cliente,Long> {
	
	public ClienteDAO(String nomeArquivo) {
		super(nomeArquivo);
	}

	@Override
	protected Set<Cliente> getAll() {
		Set<Cliente> lista = new TreeSet<Cliente>();
		try (DataInputStream entrada = new DataInputStream(new FileInputStream(this.nomeArquivo))) {
			while (entrada.available()>0) {
				Cliente cliente = new Cliente(
						entrada.readLong(),
						entrada.readUTF(),
						entrada.readLong(),
						entrada.readLong(),
						entrada.readUTF(),
						entrada.readUTF(),
						entrada.readUTF());
				lista.add(cliente);
			}
			return lista;	
		} catch(EOFException e) {
			System.out.println("Erro de EOFException.");
		} catch (IOException e) {
			System.out.println("ERRO ao ler os clientes do disco rígido!");
			try {
				new File(this.nomeArquivo).createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	@Override
	protected void gravar() {
		try (DataOutputStream saida = new DataOutputStream(new FileOutputStream(this.nomeArquivo, false))) {
			for(Cliente cliente: this.lista) {
			saida.writeLong(cliente.getCpf());
			saida.writeUTF(cliente.getNome());
			saida.writeLong(cliente.getNumFone1());
			saida.writeLong(cliente.getNumFone2());
			saida.writeUTF(cliente.getEndereco());
			saida.writeUTF(cliente.getEmail());
			saida.writeUTF(cliente.getEventos());
			saida.flush();
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar os clientes no disco!");
			e.printStackTrace();
		}
	}
}
