import java.net.*;
import java.io.*;
class trialServer
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
public static void main(String[] args)
{

int portNumber = 9999;
try{
ServerSocket serSocket = new ServerSocket(9999);
Socket socket = serSocket.accept();
InputStreamReader reader = new InputStreamReader(socket.getInputStream());
BufferedReader buffer = new BufferedReader(reader);
String inputString = buffer.readLine();
System.out.println(inputString);

System.out.println(mkdir("newFolder1"));

}
catch(Exception ex)
{
  System.out.println("exception "+ex);
}
}
}
