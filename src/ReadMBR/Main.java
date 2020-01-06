package ReadMBR;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p/>MBR Read</p>
 * Example:
 * sudo java -jar ./git/etu_os/bin/MbrRead.jar /dev/sda
 * @author Pavel Muhortov
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = args[0];
		int fileLength = 512;
			
		try(FileInputStream file=new FileInputStream(fileName)){
			byte fileBytes[] = new byte [fileLength];
			fileBytes = file.readNBytes(fileLength);
			StringBuilder fileHex = new StringBuilder();
			for (byte b : fileBytes) {
				fileHex.append(String.format("%02X ", b));
			}
			System.out.println(fileHex.toString());
		}
		catch(IOException ex){     
			System.out.println(ex.getMessage());
		}
	}
}
