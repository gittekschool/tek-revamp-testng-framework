package tek.framework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileUtility {

	public static FileInputStream getFileInputStream(String filePath) throws FileNotFoundException {
		File file = new File(filePath);
		return new FileInputStream(file);
	}
}
