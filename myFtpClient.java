import java.util.*;
import java.net.*;
import java.io.*;
class myFtpClient
{
public static String takeInput() throws Exception
{
InputStreamReader reader = new InputStreamReader(System.in);
BufferedReader buffer = new BufferedReader(reader);
return buffer.readLine();
}

public static void main(String[] args) throws Exception
{
  //Created a Socket with port number 9999
  Socket clientSocket =  new Socket("localhost", 9999);
  PrintStream printStream = new PrintStream(clientSocket.getOutputStream());

  while(true)
  {
  System.out.println("Enter the command");
  String command = takeInput();
  printStream.println(command);

  }




}
}
