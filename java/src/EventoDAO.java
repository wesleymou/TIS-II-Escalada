import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class EventoDAO implements DAO<Evento,String> {

	private List<Evento> listaDeEventos;
	public EventoDAO(){
		listaDeEventos = new ArrayList<Evento>(getAll());
	}

	@Override
	public void add(Evento evento) {
		listaDeEventos.add(evento);
		this.gravar();
	}

	@Override
	public Evento get(String chave) {
		for(Evento evento:listaDeEventos) {
			if(evento.getNome().equals(chave))
				return evento;
		}
		return null;
		
//		try (DataInputStream entrada = new DataInputStream(new FileInputStream("eventos.dat"))) {
//			while (entrada.available()>0) {
//				String nome = entrada.readUTF();
//				String dataInicio = entrada.readUTF();
//				String dataTermino = entrada.readUTF();
//				String local = entrada.readUTF();
//				int capacidade = entrada.readInt();
//				int quorum = entrada.readInt();
//				double orcamentoPrevio = entrada.readDouble();
//				double valorIngresso = entrada.readDouble();
//				String cronograma = entrada.readUTF();
//				String status = entrada.readUTF();
//
//				if(chave.equals(nome))
//					return new Evento(nome, local, dataInicio, dataTermino, capacidade, quorum, orcamentoPrevio, valorIngresso, cronograma, status);
//			}
//		} catch(EOFException e) { 
//
//		} catch (Exception e) {
//			System.out.println("ERRO ao ler o evento '" + chave + "' do disco rígido!");
//			e.printStackTrace();
//		}
//		return null;
	}

	@Override
	public void update(Evento evento) {
		Iterator<Evento> it = listaDeEventos.iterator();
		while(it.hasNext()) {
		      Evento e = it.next();
		      if(e.getNome().equals(evento.getNome())) {
		    	  listaDeEventos.remove(e);
		      }
		}
  	  this.add(evento);
	}

	@Override
	public boolean delete(String chave) {
		boolean foiRemovido = listaDeEventos.removeIf(T -> T.getNome().equals(chave));
		if(foiRemovido) {
			this.gravar();
		}
		return foiRemovido;
	}

	private List<Evento> getAll() {
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
		} catch (IOException e) {
			System.out.println("ERRO ao ler os eventos do disco rígido!");
			try {
				new File("eventos.dat").createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public List<Evento> getListaDeEventos() {
		return listaDeEventos;
	}

	private void gravar() {
		try (DataOutputStream saida = new DataOutputStream(new FileOutputStream("eventos.dat", false))) {
			for(Evento E:listaDeEventos) {
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
