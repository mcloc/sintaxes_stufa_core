package br.com.vger.stufa.ipc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class IPCServer {
	private ServerSocket serverSocket;
	protected List<IPCMessage> messagesList;

	public void start(int port, List<IPCMessage> messagesList) throws IOException {
		this.messagesList = messagesList;
		serverSocket = new ServerSocket(port);
		while (true)
			new ClientHandler(serverSocket.accept(), messagesList).start();
	}

	public void stop() throws IOException {
		serverSocket.close();
	}

	private static class ClientHandler extends Thread {
		private Socket clientSocket;
		private PrintWriter out;
		private BufferedReader in;
		protected List<IPCMessage> messagesList;

		public ClientHandler(Socket socket, List<IPCMessage> messagesList) {
			this.clientSocket = socket;
			this.messagesList = messagesList;
		}

		public void run() {
			try {
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					if (inputLine.contains("\u0004")) {
						out.println("bye");
						break;
					}
					inputLine.replace("\n", "");
					System.out.println("raw: " + inputLine);
					messagesList.add(new IPCMessage(inputLine));
//					out.println(inputLine);
				}
				in.close();
				out.close();
				clientSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
