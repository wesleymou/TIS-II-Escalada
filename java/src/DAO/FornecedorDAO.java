/* Pontifícia Universidade Católica de Minas Gerais || Trabalho Interdisciplinar de Software - 2º período
    Membros:
    Filipe Iannarelli Caldeira
    Gabriel Vinicius Ramos da Silva
    Paulo Angelo Dias Barbosa
    Wesley Mouraria Pereira
*/
package DAO;

import Main.Fornecedor;

//Criação de um Data Acess Object para acesso de fornecedor
public class FornecedorDAO extends DAO<Fornecedor, String> {
	
	public FornecedorDAO(String nomeArquivo){
		super(nomeArquivo);
	}
	
}
