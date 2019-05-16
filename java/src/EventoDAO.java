import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class EventoDAO implements DAO<Evento,String> {

	public EventoDAO(){

	}

	@Override
	public void add(Evento evento) {
		
		try (DataOutputStream saida = new DataOutputStream(new FileOutputStream("eventos.dat", true))) {
			saida.writeUTF(evento.getNome());
			saida.writeUTF(evento.getDataInicio());
			saida.writeUTF(evento.getDataTermino());
			saida.writeUTF(evento.getLocal());
			saida.writeInt(evento.getCapacidade());
			saida.writeInt(evento.getQuorum());
			saida.writeDouble(evento.getOrcamentoPrevio());
			saida.writeDouble(evento.getValorIngresso());
			saida.writeUTF(evento.getCronograma());
			//			Convenio
			saida.writeUTF(evento.getStatus());
			saida.flush();

		} catch (Exception e) {
			System.out.println("ERRO ao gravar o evento '" + evento.getNome() + "' no disco!");
			e.printStackTrace();
		}
	}

	@Override
	public Evento get(String chave) {
		try (DataInputStream entrada = new DataInputStream(new FileInputStream("eventos.dat"))) {
			while (entrada.available()>0) {
				String nome = entrada.readUTF();
				String dataInicio = entrada.readUTF();
				String dataTermino = entrada.readUTF();
				String local = entrada.readUTF();
				int capacidade = entrada.readInt();
				int quorum = entrada.readInt();
				double orcamentoPrevio = entrada.readDouble();
				double valorIngresso = entrada.readDouble();
				String cronograma = entrada.readUTF();
				String status = entrada.readUTF();

				if(chave.equals(nome))
					return new Evento(nome, local, dataInicio, dataTermino, capacidade, quorum, orcamentoPrevio, valorIngresso, cronograma, status);
			}
		} catch(EOFException e) { 

		} catch (Exception e) {
			System.out.println("ERRO ao ler o evento '" + chave + "' do disco rígido!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Evento evento) {
		List<Evento> lista = this.getAll();
		Iterator<Evento> it = lista.iterator();
		while(it.hasNext()) {
		      Evento e = it.next();
		      if(e.getNome().equals(evento.getNome())) {
		    	  lista.remove(e);
		    	  lista.add(evento);
		      }
		}
		File file = new File("eventos.dat");
		if (file.exists())
			file.delete();
		for(Evento e: lista) {
			this.add(e); System.out.println(e.toJson() + "2");}
	}

	@Override
	public boolean delete(String chave) {
		List<Evento> lista = this.getAll();
		boolean foiRemovido = lista.removeIf(T -> T.getNome().equals(chave));
		if(foiRemovido) {
			File file = new File("eventos.dat");
			if (file.exists())
				file.delete();
			for(Evento e: lista)
				this.add(e);
		}
		return foiRemovido;
	}

	@Override
	public List<Evento> getAll() {
		List<Evento> lista = new ArrayList<Evento>();
		try (DataInputStream entrada = new DataInputStream(new FileInputStream("eventos.dat"))) {
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
		} catch (Exception e) {
			System.out.println("ERRO ao ler os eventos do disco rígido!");
			e.printStackTrace();
		}
		return null;
	}
}
