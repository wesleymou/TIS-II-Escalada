
public interface DAO<T,K> {
	public void add(T tipo);
	public T get(K chave);
	public void update(T tipo);
	public boolean delete(K chave);
}
