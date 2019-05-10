import org.json.JSONArray;
import org.json.JSONObject;

public class ListaEvento {

    private CelulaEvento primeiro;
    private CelulaEvento ultimo;

    public ListaEvento() {
        ultimo = primeiro = new CelulaEvento();
    }

    private Boolean estaVazia() {
        if (primeiro == ultimo) {
            return true;
        } else {
            return false;
        }
    }

    public String imprimir() {
        if (estaVazia()) {
            return "Lista vazia.";
        }
        CelulaEvento aux = primeiro.proximo;
        String texto = "";
        while (aux != null) {
            texto += aux.item.getNome();
            aux = aux.proximo;
            if (aux == null) {
                texto += ".";
            } else {
                texto += ", ";
            }
        }
        return texto;
    }

    public void create(Evento evento) {
        ultimo.proximo = new CelulaEvento();
        ultimo = ultimo.proximo;
        ultimo.item = evento;
    }

    public Evento read(String nome) {
        CelulaEvento aux = primeiro.proximo;
        while (aux != null) {
            if (aux.item.getNome().equals(nome)) {
                return aux.item;
            } else {
                aux = aux.proximo;
            }
        }
        return null;
    }

    //TODO - Terminar o metodo de update
    public Evento update(String nome) {
    	Evento evento = read(nome);
    	return evento;
    }

    public Evento delete(String nome) {
        CelulaEvento aux = primeiro.proximo, sombra = primeiro;
        while (aux != null) {
            if (aux.item.getNome().equals(nome)) {
                sombra.proximo = aux.proximo;
                if (aux == ultimo) {
                    ultimo = sombra;
                }
                return aux.item;
            } else {
                sombra = aux;
                aux = aux.proximo;
            }
        }
        return null;
    }
    
    public JSONArray todosOsEventos() {
    	JSONArray Json = new JSONArray();
    	CelulaEvento aux = primeiro.proximo;
    	while(aux != null) {
    		Json.put(aux.item.toJson());
    		aux = aux.proximo;
    	}
    	return Json;
    }
}
