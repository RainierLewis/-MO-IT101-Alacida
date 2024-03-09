import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class PayrollSystem {
	public static String employee_hourly_rate;
	public static String[] employee;
	
	public static void main(String[] args) {
		
		  Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter hourly rate: ");
	        double hourlyRate = scanner.nextDouble();

	        System.out.print("Enter worked hours: ");
	        double workedHours = scanner.nextDouble();

	        double totalPay = hourlyRate * workedHours;
	        System.out.println("Total pay: " + totalPay);

	        scanner.close();
	    }
	public static String get_employee_details(String employee_id) {
		// identify the file directory
		String file = System.getProperty("user.dir") + "/src/employee_details.csv";
		
		// initialize the variables
		BufferedReader reader = null;
		String line = "";
		String employee_found = "";
		
		try {
			// get the csv file from directory and load it onto your java project
			reader = new BufferedReader(new FileReader(file));
			
			// tell the system to iterate on each line of the csv
			while ((line = reader.readLine()) != null) {
				
				String repl = line.replaceAll(",(?!(([^\"]*\"){2})*[^\"]*$)", ";x;");
				String[] row = repl.split(",");

				if (Integer.parseInt(row[0]) == Integer.parseInt(employee_id)) {
					employee_found = repl;
				}

			}
		} catch (Exception e) {e.printStackTrace(); }
		
		return employee_found;
		
	}
	}