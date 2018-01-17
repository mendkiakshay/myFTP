import java.net.*;
import java.io.*;
class myFtpServer
{

public static String mkdir(String folderName)
{
  try
  {
    File directory = new File(folderName);
    directory.mkdir();
    return "success";
  }
  catch(Exception ex)
  {
    return ex.toString();
  }
}
public static String[] splitCommand(String command)
{
  return command.split(" ");
}

public static void main(String[] args)
{

int portNumber = 9999;
try
{
    ServerSocket serSocket = new ServerSocket(9999);
    Socket socket = serSocket.accept();
    InputStreamReader reader = new InputStreamReader(socket.getInputStream());
    BufferedReader buffer = new BufferedReader(reader);
    String inputString = buffer.readLine();
    System.out.println(inputString);

    if(splitCommand(inputString)[0].equals("mkdir"))
        System.out.println(mkdir(splitCommand(inputString)[1]));

}
catch(Exception ex)
{
  System.out.println("exception "+ex);
}
}
}
