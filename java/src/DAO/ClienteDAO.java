package DAO;

import Main.Cliente;

public class ClienteDAO extends DAO<Cliente,Long> {
	
	public ClienteDAO(String nomeArquivo) {
		super(nomeArquivo);
	}
}
