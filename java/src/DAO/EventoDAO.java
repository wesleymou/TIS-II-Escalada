package DAO;

import java.util.Set;

import Main.Evento;

public class EventoDAO extends DAO<Evento, String> {
	
	public EventoDAO(String nomeArquivo){
		super(nomeArquivo);
	}
	
	public Set<Evento> getAllEventos() {
		return lista;
	}
}
