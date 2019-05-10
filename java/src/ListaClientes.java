public class ListaClientes {

    private CelulaCliente primeiro;
    private CelulaCliente ultimo;

    public ListaClientes() {
        ultimo = primeiro = new CelulaCliente();
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
        CelulaCliente aux = primeiro.proximo;
        String texto = "";
        while (aux != null) {
            texto += aux.item.getNome() + ": " + aux.item.getCpf();
            aux = aux.proximo;
            if (aux == null) {
                texto += ".";
            } else {
                texto += ", ";
            }
        }
        return texto;
    }

    public void create(Cliente cliente) {
        ultimo.proximo = new CelulaCliente(cliente);
        ultimo = ultimo.proximo;
    }

    public Cliente read(String nome) {
      CelulaCliente aux = primeiro.proximo;
      while (aux != null) {
          if (aux.item.getNome().equals(nome)) {
              return aux.item;
          } else {
              aux = aux.proximo;
          }
      }
      return null;
  }
    
//    public Cliente read(float cpf) {
//        CelulaCliente aux = primeiro.proximo;
//        while (aux != null) {
//            if (aux.item.getCpf() == cpf) {
//                return aux.item;
//            } else {
//                aux = aux.proximo;
//            }
//        }
//        return null;
//    }
//
//    //TODO - Terminar o metodo de update
//    public Cliente update(float cpf) {
//    	Cliente cliente = read(cpf);
//    	return cliente;
//    }

    public Cliente delete(String nome) {
    	//public Cliente delete(float cpf) {
        CelulaCliente aux = primeiro.proximo, sombra = primeiro;
        while (aux != null) {
        	if (aux.item.getNome().equals(nome)) {
//            if (aux.item.getCpf() == cpf) {
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
}
