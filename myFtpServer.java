import java.net.*;
import java.io.*;
class myFtpServer
{

public static String currentPath;

public static String mkdir(String folderName)
{
  try
  {
    File file1 = new File(".");

    File directory = new File(file1.getAbsolutePath()+"/"+folderName);
    directory.mkdir();
    return "success";
  }
  catch(Exception ex)
  {
    return ex.toString();
  }
}
///
///Method that sets current directory with the new Folder
///
public static String setCurrent(String folderName)
{
  try
  {
    //creates new directory for new folder
    File directory = new File(folderName);
    System.setProperty("user.dir", directory.getAbsolutePath()); //Sets this directory as current directory
    File file = new File(System.getProperty("user.dir"));
    return  file.getAbsolutePath();                              //returns Path of new directory
  }
  catch(Exception ex)
  {
    System.out.println(ex.toString());
    return "error";
  }
}
///
///Method that sets current directory to its previous directory
///
public static String setPrevious()
{
  try
  {
    File file = new File(System.getProperty("user.dir"));
    String parentPath = file.getAbsoluteFile().getParentFile().getAbsolutePath();  //Gets parent folder's path
    System.setProperty("user.dir", parentPath);         //Sets current director as parent's
    file = new File(System.getProperty("user.dir"));    //Gets current path
    return  file.getAbsolutePath();
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

    while(true)
    {
      InputStreamReader reader = new InputStreamReader(socket.getInputStream());
      BufferedReader buffer = new BufferedReader(reader);
      String inputString = buffer.readLine();
      System.out.println(inputString);

      if(splitCommand(inputString)[0].equals("mkdir"))
      {
        System.out.println(mkdir(splitCommand(inputString)[1]));
      }
      if(splitCommand(inputString)[0].equals("cd"))
      {
        if(!splitCommand(inputString)[1].equals(".."))
        System.out.println(setCurrent(splitCommand(inputString)[1]));
        else
        System.out.println(setPrevious());
      }

    }
}
catch(Exception ex)
{
  System.out.println("exception "+ex);
}
}
}
