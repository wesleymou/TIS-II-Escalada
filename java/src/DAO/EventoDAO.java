/* Pontifícia Universidade Católica de Minas Gerais || Trabalho Interdisciplinar de Software - 2º período
    Membros:
    Filipe Iannarelli Caldeira
    Gabriel Vinicius Ramos da Silva
    Paulo Angelo Dias Barbosa
    Wesley Mouraria Pereira
*/
package DAO;

import java.util.Set;

import Main.Evento;

//Criação de um Data Acess Object para acesso de evento
public class EventoDAO extends DAO<Evento, String> {
	
	public EventoDAO(String nomeArquivo){
		super(nomeArquivo);
	}
	
	public Set<Evento> getAllEventos() {
		return lista;
	}
}
