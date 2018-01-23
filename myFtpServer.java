
import java.net.*;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;

class myFtpServer {

	public static String[] splitCommand(String command) {
		return command.split(" ");
	}

	public static void main(String[] args) {
		String portNo = args[0];
		int portNumber = Integer.parseInt(portNo);
		myFtpServerProcess mycommand = new myFtpServerProcess();
		System.out.println("Server Started");

		try {
			ServerSocket serSocket = new ServerSocket(portNumber);
			//ServerSocket serSocket = new ServerSocket(9999);
			Socket socket = serSocket.accept();

			while (true) {
				InputStreamReader reader = new InputStreamReader(socket.getInputStream());
				BufferedReader buffer = new BufferedReader(reader);
				String inputString = buffer.readLine();
				System.out.println(inputString);

				if (splitCommand(inputString)[0].equalsIgnoreCase("mkdir")) {
					System.out.println(mycommand.mkdir(splitCommand(inputString)[1]));
				}

				if (splitCommand(inputString)[0].equalsIgnoreCase("cd")) {
					if (!splitCommand(inputString)[1].equalsIgnoreCase(".."))
						System.out.println(mycommand.setCurrent(splitCommand(inputString)[1]));
					else
						System.out.println(mycommand.setPrevious());
				}

				if(splitCommand(inputString)[0].equalsIgnoreCase("delete")){
					System.out.println(mycommand.delete(splitCommand(inputString)[1]));
				}

				if(splitCommand(inputString)[0].equalsIgnoreCase("ls")){

					File[] files;
					if (splitCommand(inputString).length == 1)
					{
						files = mycommand.ls();
						for (File file : files) {
							System.out.println(file.getName());
						}
					}
					else
						{
							files = mycommand.ls(new File(splitCommand(inputString)[1]));
							for (File file : files) {
								System.out.println(file.getName());
							}
						}
					}

				if(splitCommand(inputString)[0].equalsIgnoreCase("pwd")){
					//mycommand.pwd(new File(""));
					System.out.println(mycommand.pwd(new File("")));
				}

				if(splitCommand(inputString)[0].equalsIgnoreCase("put")){
					mycommand.put(FileSystems.getDefault().getPath(splitCommand(inputString)[1]));
				}

				if(inputString.equalsIgnoreCase("quit")){
					socket.close();
					break;
				}

			}
		} catch (Exception ex) {
			System.out.println("exception " + ex);
		}
	}
}
