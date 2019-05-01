import java.awt.Desktop;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;

import org.json.JSONException;
import org.json.JSONObject;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerSocketProcessor;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

//TO DO
public class Server implements Container {
	
	static EventoService eventoService;
	
	public void handle(Request request, Response response) {
		try {
			// Recupera a URL e o método utilizado.
			String path = request.getPath().getPath();
			String method = request.getMethod();
			String mensagem;

			// Verifica qual a��o está sendo chamada
			if (path.startsWith("/adicionarProduto") && "POST".equals(method)) {
				// http://127.0.0.1:880/adicionarProduto?descricao=leite&preco=3.59&quant=10&tipo=2&dataFabricacao=2017-01-01
				mensagem = estoqueService.adicionarProduto(request);
				this.enviaResposta(Status.CREATED, response, mensagem);

			} else if (path.startsWith("/consultarProduto") && "GET".equals(method)) {
				// http://127.0.0.1:880/consultarProduto?descricao=leite
				mensagem = estoqueService.consultarProduto(request);
				this.enviaResposta(Status.OK, response, mensagem);
			} else if (path.startsWith("/removerProduto") && "GET".equals(method)) {
				mensagem = estoqueService.removerProduto(request);
				if (mensagem == null)
					this.naoEncontrado(response, path);
				else {
					this.enviaResposta(Status.NO_CONTENT, response, null);
				}
			} else if (path.startsWith("/totalEmEstoque") && "GET".equals(method)) {
				// http://127.0.0.1:880/totalEmEstoque
				mensagem = estoqueService.totalEmEstoque(request);
				this.enviaResposta(Status.OK, response, mensagem);
			} else if (path.startsWith("/valorEmEstoque") && "GET".equals(method)) {
				// http://127.0.0.1:880/valorEmEstoque
				mensagem = estoqueService.valorEmEstoque(request);
				this.enviaResposta(Status.OK, response, mensagem);
			} else {
				this.naoEncontrado(response, path);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void naoEncontrado(Response response, String path) throws Exception {
		JSONObject error = new JSONObject();
		error.put("error", "Não encontrado.");
		error.put("path", path);
		enviaResposta(Status.NOT_FOUND, response, error.toString());
	}

	private void enviaResposta(Status status, Response response, String str) throws Exception {

		PrintStream body = response.getPrintStream();
		long time = System.currentTimeMillis();

		response.setValue("Content-Type", "application/json");
		response.setValue("Server", "Controle de estoqueService (1.0)");
		response.setDate("Date", time);
		response.setDate("Last-Modified", time);
		response.setStatus(status);

		if (str != null)
			body.println(str);
		body.close();
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		// json.put("id", id);
		// json.put("text", text);
		return json;
	}

	public static void main(String[] list) throws Exception {
		eventoService = new EventoService();
		int porta = 880;

		// Configura uma conexão soquete para o servidor HTTP.
		Container container = new Server();
		ContainerSocketProcessor servidor = new ContainerSocketProcessor(container);
		Connection conexao = new SocketConnection(servidor);
		SocketAddress endereco = new InetSocketAddress(porta);
		conexao.connect(endereco);

		// Testa a conexão abrindo o navegador padrão.
		Desktop.getDesktop().browse(new URI("http://127.0.0.1:" + porta));

		System.out.println("Tecle ENTER para interromper o servidor...");
		System.in.read();

		conexao.close();
		servidor.stop();

	}

}

