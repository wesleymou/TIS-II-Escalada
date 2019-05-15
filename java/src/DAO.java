import java.util.List;

public interface DAO<T> {
	public void add(T evento);
	public T get(String chave);
	public void update(T evento);
	public boolean delete(String chave);
	public List<?> getAll();
}
