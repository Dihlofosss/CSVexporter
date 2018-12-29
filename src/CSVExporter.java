//small program as a try to write some data
//(marsRover JUnit test parameters for example)
//in csv table

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVExporter {
	private static final String cfgFilePath = "config/csv.cfg";
	private static Logger logger = Logger.getLogger("CSV Exporter");
	private static String delimiter = ";";


	public static void main(String[] args)
	{
		ArrayList<String> header = new ArrayList<>();
		ArrayList<String> parameterRow = new ArrayList<>();

		String csvFilePath = GetParameterValue.openConfig(cfgFilePath, "file_path");
		int numberOfTest = Integer.valueOf(GetParameterValue.openConfig(cfgFilePath, "number_of_tests"));
		System.out.println(csvFilePath);
		Path path = Paths.get(csvFilePath);

		header.add("Parameter name:");
		for (int i = 1; i <= numberOfTest; i++)
		{
			header.add("Test ID:" + i);
		}

		//try to put all in lines in the
		try{
			BufferedWriter writer = Files.newBufferedWriter(path);
			addRow(header, writer);
			writer.close();
		}catch (Exception e) {
			logger.log(Level.WARNING, "Something happens in buffer writer", e);
		}
	}

	private static void addRow(ArrayList<String> array, BufferedWriter writer)
	{
		try{
			for (String element: array) {
				writer.write(element);
				writer.write(delimiter);
			}
			writer.newLine();

		}catch (Exception e) {
			logger.log(Level.WARNING, "Something happens during row adding", e);
		}
	}
}