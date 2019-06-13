/* Pontifícia Universidade Católica de Minas Gerais || Trabalho Interdisciplinar de Software - 2º período
    Membros:
    Filipe Iannarelli Caldeira
    Gabriel Vinicius Ramos da Silva
    Paulo Angelo Dias Barbosa
    Wesley Mouraria Pereira
*/
package DAO;

import Main.Cliente;

//Criação de um Data Acess Object para acesso de cliente
public class ClienteDAO extends DAO<Cliente,Long> {
	
	public ClienteDAO(String nomeArquivo) {
		super(nomeArquivo);
	}
}
