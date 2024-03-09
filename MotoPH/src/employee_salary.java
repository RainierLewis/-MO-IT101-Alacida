import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.util.Arrays;

public class employee_salary {
	public static String employee_hourly_rate;
	public static String[] employee;
	public static String worked_hours;
	public static void main(String [] args) {
		
		boolean conts = true;
		while (conts) {
			System.out.println("======= Salary ===========");
			define_data_type();
			
//			System.out.println("hourly rate : "+employee_hourly_rate);
//			System.out.println(Arrays.toString(employee));
			
			// return 0 if walanag laman
			// return n kapag may laman
			if(employee.length != 0) {
				System.out.println("Employee ID: " + employee[0]);
				System.out.println("Name: " + employee[1] + ", " + employee[2]);
	
				System.out.println("Calculation");
				String benefits = get_benefits_calculation();
				
				
			} else {
				System.out.println("No data found. Please try again");
			}
			System.out.println("======= MotoPH ===========");
			System.out.println("**********************************");
		}
	}
	
	public static void reset_data() {
		employee_hourly_rate = "";
		employee = new String [0];
	}
	
	public static void define_data_type() {
		reset_data();
		System.out.print("Enter an Employee number: ");
		
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String userInput = inputReader.readLine();
			
			String employee_detail = get_employee_details(userInput);
			
			if(!employee_detail.equals("")) {
				String[] row = employee_detail.split(",");
				employee = row;
				employee_hourly_rate = row[18];
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public static String sanitize_data(String info) {
		return info.replace(";x;", ",").replace("\"", "");
	}
	public static String get_attendance_record(String employee_id) {
		// identify the file directory
		String file = System.getProperty("user.dir") + "/src/attendance_record.csv";
		return employee_hourly_rate;
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
	
	public static String get_benefits_calculation() {
		/* SSS contribution
		22,250	-	22,750	1,012.50
		22,750	-	23,250	1,035.00
		23,250	-	23,750	1,057.50
		23,750	-	24,250	1,080.00
		24,250	-	24,750	1,102.50
		24,750	-	Over	1,125.00 */
		// Philheath Premium = 3%/2
		// Pag-ibig = 2%
		
		String d_montly_salary = employee[13].replace(";x;", "").replace("\"", "");
		System.out.println("monthly salary: "+d_montly_salary);
		 
		
		String d_payroll = employee_hourly_rate  ;
		System.out.println("Hourly Rate: "+d_payroll);
		return null;
	}
    
}
