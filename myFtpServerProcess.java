package GitSynFiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class myFtpServerProcess {

	public String mkdir(String folderName) {
		try {
			File file1 = new File(".");

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
	public void ls(File dir){
		//File dir = new File(".");
		File[] files = dir.listFiles();
		for (File file : files) {
			System.out.println(file.getName());
		}
	}
	
	public void ls(){
		File dir = new File(System.getProperty("user.dir"));
		File[] files = dir.listFiles();
		for (File file : files) {
			System.out.println(file.getName());
		}
	}
	
	//Method to print current dir- pwd
	public void pwd(File file){
		System.out.println("Current Working Directory: " + file.getAbsolutePath());
	}
	
	//Mehtod to move file- put command
	public void put(Path f1){
		Path file2 = FileSystems.getDefault().getPath("./f1");
		try {
			Files.move(f1, file2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("File is moved");
	}

}
