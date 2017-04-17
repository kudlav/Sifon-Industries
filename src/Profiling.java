import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Class created for profiling MathLib methods via standard deviation calculation.
 */
public class Profiling {

	Scanner in = null;

	/**
	 * Main method.
	 * <p>
	 * Make file named "test" with 100 random numbers and create {@link Profiling} class.
	 * @param args
	 */
	public static void main(String[] args) {
		generateFile("test",100);
		new Profiling();
	}
	
	/***
	 * Constructor of {@link Profiling} class.
	 * <p>
	 * Open input scanner, ask for file name from which read numbers and calculate standard deviation 1000 times.
	 * 
	 * @author Rengyr
	 */
	public Profiling(){
		in = new Scanner(System.in);
		
		File file;
		ArrayList<Double> numbers = new ArrayList<Double>();
		file = readFileName();
		readNumbers(file, numbers);
		for (int i = 0; i < 1000; i++) {
			deviation(numbers);		//calculate deviation multiple times to get more precise results with profiling
		}
		profEnd(0, true);
	}

	/**
	 * Method prompts user to enter file name, checks if file exits and return {@link java.io.File File}.
	 * 
	 * @return returns loaded {@link java.io.File File}
	 *
	 * @author Rengyr
	 */
	private File readFileName(){
		System.out.print("Write name of file: ");
		String name = in.nextLine();
		File file = new File(name);
		if (!file.exists()){
			System.out.println("File not found!");
			profEnd(-1);
		}
		return file;
	}
	
	/**
	 * Read numbers and parse numbers from {@link java.io.File File} and adds them to {@link java.util.ArrayList}.
	 * 
	 * @param file file from which load numbers
	 * @param list list to save numbers
	 * 
	 * @author Rengyr
	 */
	private void readNumbers(File file, ArrayList<Double> list){
		BufferedReader input = null;
		try {
			input = new BufferedReader (new FileReader(file));
			String line;
			while ((line = input.readLine())!=null){
				try {
					list.add(Double.parseDouble(line));
				} catch (NumberFormatException e) {
					System.out.println("Found line with non-number string!");
					profEnd(-1);
				}
			}	
		} catch (IOException e){
			
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {}
			}
		}
	}
	
	/**
	 * Exit process with status specified by parameter and wait for user key to exit if fast = true.
	 * 
	 * @param err exit status
	 * @param fast if true, method will wait user key to exit
	 * 
	 * @author Rengyr
	 */
	private void profEnd(int err, boolean fast){
		if (!fast){
			System.out.println("Press any key to exit.");
			in.nextLine();	
		}
		in.close();
		System.exit(err);
	}
	
	/**
	 * Overloaded function {@link #profEnd(int, boolean)}.
	 * 
	 * @param err exit status
	 * 
	 * @author Rengyr
	 */
	private void profEnd(int err){
		profEnd(err, false);
	}
	/**
	 * Calculate the arithmetic mean of list.
	 * 
	 * @param num list of numbers
	 * @return arithmetic mean
	 * 
	 * @author AdamKuba
	 */
	private double arithmeticMean(ArrayList<Double> num){
		double sum = 0;
		for (int i = 0; i < num.size(); i++) {
			sum=MathLib.add(sum, num.get(i));
		}
		return MathLib.idiv(sum, num.size());
		
	}
	
	/**
	 * Calculate the standard deviation of list using the formula of <a href="https://en.wikipedia.org/wiki/Standard_deviation">Standard_deviation</a>.  
	 * 
	 * @param num list of numbers
	 * @return standard deviation
	 * 
	 * @author AdamKuba
	 */
	private double deviation(ArrayList<Double> num){
		double sum = 0;
		for (int i = 0; i < num.size(); i++) {
			sum=MathLib.add(sum, MathLib.imul(num.get(i), num.get(i)));
		}
		double mean = arithmeticMean(num);
		double foo = MathLib.imul(num.size(), MathLib.imul(mean, mean));
		double bar = MathLib.sub(sum, foo);
		double foobar = MathLib.idiv(bar, num.size()-1);
		return MathLib.nRoot(2, foobar);
		
	}
	
	
	/**
	 * Creates file with numbers. Numbers are random from interval (-5000,5000).
	 * 
	 * @param fileName name of file
	 * @param count number of numbers in file
	 * 
	 * @author AdamKuba
	 */
	private static void generateFile(String fileName, int count){
		try{
		    PrintWriter writer = new PrintWriter(fileName);
		    Random rn = new Random();
		    for (int i = 0; i < count; i++) {
		    	writer.println(rn.nextInt(10000)-5000);	
			}
		    writer.close();
		} catch (IOException e) {

		}
		
	}
}
/*** End of Profiling.java file ***/