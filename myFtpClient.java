
import java.util.*;
import java.net.*;
import java.io.*;

class myFtpClient {
	public static String takeInput() throws Exception {
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader buffer = new BufferedReader(reader);
		return buffer.readLine();
	}

	public static void main(String[] args) throws Exception {
		String machineName = args[0];
		String portNo = args[1];
		int portNumber = Integer.parseInt(portNo);

		// Created a Socket with port number 9999
		Socket clientSocket = new Socket(machineName, portNumber);
		//Socket clientSocket = new Socket("localhost", 9999);
		PrintStream printStream = new PrintStream(clientSocket.getOutputStream());

		while (true) {
			System.out.print("mytftp> ");
			String command = takeInput();
			printStream.println(command);
			if(command.equalsIgnoreCase("quit")){
				break;
			}
		}

	}
}
