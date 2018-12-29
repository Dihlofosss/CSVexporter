//simple file parser

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetParameterValue {

	private static Logger logger = Logger.getLogger(".cfg parser");
	public static String openConfig(String filePath, String parameterName)
	{
		//open file and look search for a required parameter
		String parameterValue = null;
		List<String> listOfLines;

		Path path = Paths.get(filePath);

		try{
			//read all lines - fine for a small cfg files
			listOfLines = Files.readAllLines(path);

			for (String line : listOfLines)
			{
				if (line.contains(parameterName))
				{
					int lineLength = line.length();
					int equalityCharPos = line.indexOf("=");
					//get the parameter value in " "
					parameterValue = line.substring(equalityCharPos + 2, lineLength - 1);
				}
			}
		}catch (Exception e) {
			logger.log(Level.WARNING, "Something happens in config reader", e);
		}
		return parameterValue;
	}
}
