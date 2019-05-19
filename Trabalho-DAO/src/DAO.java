import java.util.Set;
import java.util.TreeSet;

abstract class DAO<T,K> {

	protected Set<T> lista;
	String nomeArquivo;
	
	public DAO(String nomeArquivo){
		this.nomeArquivo = nomeArquivo;
		lista = new TreeSet<T>(this.getAll());
	}

	public boolean add(T t) {
		boolean foiAdicionado = lista.add(t);
		this.gravar();
		return foiAdicionado;
	}

	@SuppressWarnings("unlikely-arg-type")
	public T get(K chave) {
		for(T t:lista) {
			if(t.equals(chave))
				return t;
		}
		return null;
	}

	//TODO
	public boolean update(T t) {
		boolean foiAtualizado = false;
		if(this.lista.removeIf(x -> x.equals(t)))
			foiAtualizado = lista.add(t);
		return foiAtualizado;
	}

	//TODO
	@SuppressWarnings("unlikely-arg-type")
	public boolean delete(K chave) {
		boolean foiRemovido = false;
		if(foiRemovido = lista.removeIf(x -> x.equals(chave)))
			this.gravar();
		return foiRemovido;
	}
	
	public Set<T> getLista() {
		return lista;
	}

	protected abstract Set<T> getAll();
	protected abstract void gravar();
}
