package Main;

import java.awt.Desktop;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerSocketProcessor;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

import Service.*;

public class Server implements Container {

	EventoService eventoService = new EventoService();
	ClienteService clienteService = new ClienteService();
	FornecedorService fornecedorService = new FornecedorService();
	InscricaoService inscricaoService = new InscricaoService(eventoService, clienteService);
	IndicadorService indicadorService = new IndicadorService(eventoService);

	public void handle(Request request, Response response) {
		try {
			String path = request.getPath().getPath();
			//	String method = request.getMethod();
			System.out.println("Request: " + request.getQuery().toString());

			if (path.startsWith("/cadastrarEvento")) {
				JSONObject j = eventoService.add(request);
				System.out.println(j);
				this.enviaResposta(Status.CREATED, response, j);

			} else if (path.startsWith("/consultarEvento")) {
				JSONArray j = eventoService.get(request);
				System.out.println("consultar: "+j);
				this.enviaResposta(Status.OK, response, j);

			} else if (path.startsWith("/atualizarEvento")) {
				JSONObject j = eventoService.update(request);
				System.out.println(j);
				this.enviaResposta(Status.OK, response, j);

			} else if (path.startsWith("/excluirEvento")) {
				JSONObject j = eventoService.remove(request);
				System.out.println(j);
				this.enviaResposta(Status.OK, response, j);



			} else if (path.startsWith("/cadastrarCliente")) {
				JSONObject j = clienteService.add(request);
				System.out.println(j);
				this.enviaResposta(Status.CREATED, response, j);

			} else if (path.startsWith("/consultarCliente")) {
				JSONArray j = clienteService.get(request);
				System.out.println(j);
				this.enviaResposta(Status.OK, response, j);

			} else if (path.startsWith("/atualizarCliente")) {
				JSONObject j = clienteService.update(request);
				System.out.println(j);
				this.enviaResposta(Status.OK, response, j);

			} else if (path.startsWith("/excluirCliente")) {
				JSONObject j = clienteService.remove(request);
				System.out.println(j);
				this.enviaResposta(Status.OK, response, j);



			} else if (path.startsWith("/cadastrarFornecedor")) {
				JSONObject j = fornecedorService.add(request);
				System.out.println("Response: " + j);
				this.enviaResposta(Status.OK, response, j);

			} else if (path.startsWith("/consultarFornecedor")) {
				JSONArray j = fornecedorService.get(request);
				System.out.println("Response: " + j);
				this.enviaResposta(Status.OK, response, j);

			} else if (path.startsWith("/atualizarFornecedor")) {
				JSONObject j = fornecedorService.update(request);
				System.out.println("Response: " + j);
				this.enviaResposta(Status.OK, response, j);

			} else if (path.startsWith("/excluirFornecedor")) {
				JSONObject j = fornecedorService.remove(request);
				System.out.println("Response: " + j);
				this.enviaResposta(Status.OK, response, j);


			
			} else if (path.startsWith("/cadastrarInscricao")) {
				JSONObject j = inscricaoService.add(request);
				System.out.println("Response: " + j);
				this.enviaResposta(Status.OK, response, j);
			} else if (path.startsWith("/consultarInscricao")) {
				JSONArray j = inscricaoService.get(request);
				System.out.println("Response: " + j);
				this.enviaResposta(Status.OK, response, j);

			} else if (path.startsWith("/atualizarInscricao")) {
				JSONObject j = inscricaoService.update(request);
				System.out.println("Response: " + j);
				this.enviaResposta(Status.OK, response, j);
				
			} else if (path.startsWith("/excluirInscricao")) {
				JSONObject j = inscricaoService.remove(request);
				System.out.println("Response: " + j);
				this.enviaResposta(Status.OK, response, j);

				
								
			} else if (path.startsWith("/consultarIndicadores")) {
				JSONArray j = indicadorService.getAll(request);
				System.out.println("Response: " + j);
				this.enviaResposta(Status.OK, response, j);

			} else if (path.startsWith("/cadastrarCronograma")) {
				JSONObject j = eventoService.receberCronograma(request);
				System.out.println("Response: " + j);
				this.enviaResposta(Status.OK, response, j);

			} else if (path.startsWith("/consultarCronograma")) {
				JSONObject j = eventoService.getAllCronograma(request);
				System.out.println("Response: " + j);
				this.enviaResposta(Status.OK, response, j);



			} else {
				this.naoEncontrado(response, path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void naoEncontrado(Response response, String path) throws Exception {
		JSONObject error = new JSONObject();
		error.put("error", "Nao encontrado.");
		error.put("path", path);
		enviaResposta(Status.OK, response, error);
	}

	private void enviaResposta(Status status, Response response, JSONObject JSON) throws Exception {
		PrintStream body = response.getPrintStream();
		long time = System.currentTimeMillis();
		response.setValue("Access-Control-Allow-Origin", "*");
		response.setValue("Content-Type", "application/json");
		response.setValue("Server", "");
		response.setDate("Date", time);
		response.setDate("Last-Modified", time);
		response.setStatus(status);
		body.println(JSON);
		body.close();
	}

	private void enviaResposta(Status status, Response response, JSONArray JSON) throws Exception {
		PrintStream body = response.getPrintStream();
		long time = System.currentTimeMillis();
		response.setValue("Access-Control-Allow-Origin", "*");
		response.setValue("Content-Type", "application/json");
		response.setValue("Server", "");
		response.setDate("Date", time);
		response.setDate("Last-Modified", time);
		response.setStatus(status);
		body.println(JSON);
		body.close();
	}

	public static void main(String[] list) throws Exception {

		int porta = 880;

		// Configura uma conexão soquete para o servidor HTTP.
		Container container = new Server();
		ContainerSocketProcessor servidor = new ContainerSocketProcessor(container);
		Connection conexao = new SocketConnection(servidor);
		SocketAddress endereco = new InetSocketAddress(porta);
		conexao.connect(endereco);

		//Desktop.getDesktop().browse(new URI("C:/Users/619352/Desktop/git/TIS-II_Escalada/index.html"));
		//Desktop.getDesktop().browse(new URI("https://pucweb-wesley-mouraria.azurewebsites.net/"));

		System.out.println("Interromper o servidor? (y/n)");

		Scanner ler = new Scanner(System.in);
		String a = "";
		while (!a.equals("y")) {
			a = ler.next();
			if (a.equals("n"))
				System.out.println("Entao nao.");
			else if (!a.equals("y") && !a.equals("n"))
				System.out.println("Sem tempo irmao.");
		}
		ler.close();
		conexao.close();
		servidor.stop();
		System.out.println("Servidor terminado.");
	}

}
