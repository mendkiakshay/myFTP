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
				String clientdirpath = buffer.readLine();

				PrintStream printStream = new PrintStream(socket.getOutputStream());

				if (splitCommand(inputString)[0].equalsIgnoreCase("mkdir"))
				{
					//System.out.println(mycommand.mkdir(splitCommand(inputString)[1]));
					printStream.println(mycommand.mkdir(splitCommand(inputString)[1]));
				}

				if (splitCommand(inputString)[0].equalsIgnoreCase("cd")) {
					if (!splitCommand(inputString)[1].equalsIgnoreCase(".."))
					{
						//System.out.println(mycommand.setCurrent(splitCommand(inputString)[1]));
						printStream.println(mycommand.setCurrent(splitCommand(inputString)[1]));
					}
					else
					{
						//System.out.println(mycommand.setPrevious());
						printStream.println(mycommand.setPrevious());
					}
			}

				if(splitCommand(inputString)[0].equalsIgnoreCase("delete")){
				//	System.out.println(mycommand.delete(splitCommand(inputString)[1]));
					printStream.println(mycommand.delete(splitCommand(inputString)[1]));
				}

				if(splitCommand(inputString)[0].equalsIgnoreCase("ls")){

					File[] files;
					String allPath="";
					if (splitCommand(inputString).length == 1)
					{
						files = mycommand.ls();

						for (File file : files) {
							System.out.println(file.getName());
							allPath = allPath+"  "+file.getName()+'\t';
						}
					}
					else
						{
							files = mycommand.ls(new File(splitCommand(inputString)[1]));
							for (File file : files) {
								//System.out.println(file.getName());
								allPath = allPath+"  "+file.getName()+'\t';
							}
						}
						printStream.println(allPath);
						printStream.flush();
					}

				if(splitCommand(inputString)[0].equalsIgnoreCase("pwd")){
					//mycommand.pwd(new File(""));
					//System.out.println(mycommand.pwd(new File("")));

					printStream.println(mycommand.pwd(new File("")));
				}

				if(splitCommand(inputString)[0].equalsIgnoreCase("get")){
				printStream.println(mycommand.get(splitCommand(inputString)[1], clientdirpath));
					//System.out.println(clientdirpath);
				}

				if(splitCommand(inputString)[0].equalsIgnoreCase("put")){
				printStream.println(mycommand.put(splitCommand(inputString)[1], clientdirpath));
				}

				if(inputString.equalsIgnoreCase("quit")){
					socket.close();
					break;
				}
				printStream.flush();
			}
		} catch (Exception ex) {
			System.out.println("exception " + ex);
		}
	}
}
