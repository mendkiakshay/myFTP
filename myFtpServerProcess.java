import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class myFtpServerProcess {

	public String mkdir(String folderName) {
		try {
			//File file1 = new File(".");

			File file1 = new File(System.getProperty("user.dir"));
			File directory = new File(file1.getAbsolutePath() + "/" + folderName);
			directory.mkdir();
			return "success";
		} catch (Exception ex) {
			return ex.toString();
		}
	}

	///
	/// Method that sets current directory with the new Folder
	///
	public String setCurrent(String folderName) {
		try {
			// creates new directory for new folder
			File directory = new File(folderName);
			System.setProperty("user.dir", directory.getAbsolutePath()); // Sets
																			// this
																			// directory
																			// as
																			// current
																			// directory
			File file = new File(System.getProperty("user.dir"));
			return file.getAbsolutePath(); // returns Path of new directory
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return "error";
		}
	}

	///
	/// Method that sets current directory to its previous directory
	///
	public String setPrevious() {
		try {
			File file = new File(System.getProperty("user.dir"));
			String parentPath = file.getAbsoluteFile().getParentFile().getAbsolutePath(); // Gets
																							// parent
																							// folder's
																							// path
			System.setProperty("user.dir", parentPath); // Sets current director
														// as parent's
			file = new File(System.getProperty("user.dir")); // Gets current
																// path
			return file.getAbsolutePath();
		} catch (Exception ex) {
			return ex.toString();
		}
	}

	//Method to delete a file
	public String delete(String fileName){
		File f = new File(fileName);

		if (f.delete()) {
			return "File is deleted";
		}
		else
			return "Problem deleting File";
	}

	//Method to list files and subdir- ls command
	public File[] ls(File dir){
		//File dir = new File(".");
		File[] files = dir.listFiles();
		// for (File file : files) {
		// 	System.out.println(file.getName());
		// }

		return files;
	}

	public File[] ls(){
		File dir = new File(System.getProperty("user.dir"));
		File[] files = dir.listFiles();
		// for (File file : files) {
		// 	System.out.println(file.getName());
		// }
		return files;
	}

	//Method to print current dir- pwd
	public String pwd(File file){
		//System.out.println("Current Working Directory: " + file.getAbsolutePath());

		file = new File(System.getProperty("user.dir"));
		//System.out.println("Current Working Directory: " + file.getAbsolutePath());
		return "Current Working Directory: " + file.getAbsolutePath();
	}

	//Mehtod to move file- get command
	public void get(String filename, String clientpath){
		Path file1 = FileSystems.getDefault().getPath(System.getProperty("user.dir")+"\\"+filename);
		Path file2 = FileSystems.getDefault().getPath(clientpath+"\\"+filename);
		try {
			Files.move(file1, file2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("File is moved");
	}
	
	//Mehtod to move file- put command
	public void put(String filename, String clientpath){
		Path file1 = FileSystems.getDefault().getPath(clientpath+"\\"+filename);
		Path file2 = FileSystems.getDefault().getPath("./"+filename);
		try {
			Files.move(file1, file2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("File is moved");
	}

}
