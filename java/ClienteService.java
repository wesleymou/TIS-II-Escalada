import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

class ClienteService {
	public String add(Request request) {
		Query query = request.getQuery();
		
		return null;
	}
	
	public String get(Request request) {
		Query query = request.getQuery();
		String id = query.get("");
		
		return null;
		
	}
	
	public String update(Request request) {
		Query query = request.getQuery();
		
		return null;
		
	}
	
	public String remove (Request request) {
		Query query = request.getQuery();
		String id = query.get("");
		
		return null;	
	}
}
