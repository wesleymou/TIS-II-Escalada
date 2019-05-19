import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

class EventoDAO extends DAO<Evento, String> {
	
	public EventoDAO(String nomeArquivo){
		super(nomeArquivo);
	}

	@Override
	protected Set<Evento> getAll() {
		Set<Evento> lista = new TreeSet<Evento>();
		try (DataInputStream entrada = new DataInputStream(new FileInputStream(this.nomeArquivo))) {
			while (entrada.available()>0) {
				Evento evento = new Evento(
						entrada.readUTF(),
						entrada.readUTF(),
						entrada.readUTF(),
						entrada.readUTF(),
						entrada.readInt(),
						entrada.readInt(),
						entrada.readDouble(),
						entrada.readDouble(),
						entrada.readUTF(),
						entrada.readUTF());
				lista.add(evento);
			}
			return lista;	
		} catch(EOFException e) {
			System.out.println("Erro de EOFException.");
		} catch (IOException e) {
			System.out.println("ERRO ao ler os eventos do disco rígido!");
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
			for(Evento E: this.lista) {
				saida.writeUTF(E.getNome());
				saida.writeUTF(E.getDataInicio());
				saida.writeUTF(E.getDataTermino());
				saida.writeUTF(E.getLocal());
				saida.writeInt(E.getCapacidade());
				saida.writeInt(E.getQuorum());
				saida.writeDouble(E.getOrcamentoPrevio());
				saida.writeDouble(E.getValorIngresso());
				saida.writeUTF(E.getCronograma());
				//			Convenio
				saida.writeUTF(E.getStatus());
				saida.flush();
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar os eventos no disco!");
			e.printStackTrace();
		}
	}
}
