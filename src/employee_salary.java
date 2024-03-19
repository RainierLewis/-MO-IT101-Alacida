import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class employee_salary {
	public static String employee_hourly_rate;
	public static String[] employee;
	public static String worked_hours;
	public static double	salary_per_day;
	public static double	salary_per_week;
	
	public static void main(String [] args) {
		
		boolean conts = true;
		while (conts) {
			System.out.println("======= Employee Details ===========");
			define_data_type();
			
			// return 0 if walang laman
			// return n kapag may laman
			if(employee.length != 0) {
				System.out.println("Employee ID: " + employee[0]);
				System.out.println("Name: " + employee[1] + ", " + employee[2]);
				System.out.println("Birthday: " + employee[3]);
				System.out.println("Address: " + sanitize_data(employee[4]));
				System.out.println("Position: " + employee[11]);
				System.out.println("Contact #: " + employee[5]);
				System.out.println("SSS #: " + employee[6]);
				System.out.println("Philhealth #: " + employee[7]);
				System.out.println("TIN #: " + employee[8]);
				System.out.println("Pag-ibig #: " + employee[9]);
				
			// print basic calculation (mothly salary, hourly rate, salary per day, weekly salary
				System.out.println("============= Payroll =============");
				String benefits = get_compensation_calculation();
	
				
				
				
			} else {
				System.out.println("No data found. Please try again");
			}
			System.out.println("=========== MotoPH ===========");
			System.out.println("**********************************");
		}
	}
	
	public static void reset_data() {
		employee_hourly_rate = "";
		employee = new String [0];
	}
	
	public static void define_data_type() {
		reset_data();
		System.out.print("Enter Employee ID: ");
		
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String userInput = inputReader.readLine();
			
			String employee_detail = get_employee_details(userInput);
			
			if(!employee_detail.equals("")) {
				String[] row = employee_detail.split(",");
				employee = row;
				employee_hourly_rate = row[18];
				// Hourly rate ni employee multiply by 8 (8hours per day)
				salary_per_day = (int) (Double.parseDouble(row[18]) * 8);
				// weekly rate ni employee multiply by 8 * 5
				salary_per_week = (int) (Double.parseDouble(row[18]) * 8 * 5);
			
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static String sanitize_data(String info) {
		return info.replace(";x;", ",").replace("\"", "");
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
	
	public static String get_compensation_calculation() {
	
		String d_monthly_salary = employee[13].replace(";x;", "").replace("\"", "");
		System.out.println("Monthly salary: "+d_monthly_salary);
		 
		String d_payroll = employee_hourly_rate  ;
		System.out.println("Hourly Rate: "+d_payroll);
		DecimalFormat df = new DecimalFormat("####0.00"); //round of into 2 decimals only
		double per_day = salary_per_day ;
		System.out.println("Salary Per Day: "+ df.format(per_day));
		
		double per_week = salary_per_week  ;
		System.out.println("Weekly Salary: "+ df.format(per_week));
		
		double basicSalary_per_week = (per_week); // Basic weekly salary
		double totalSalary = basicSalary_per_week;
		
		double totalMsalary = basicSalary_per_week *4;
		
		
		// compensation (rice subsidy, phone , clothing allowance)
	
		// if monthly salary is 60k-90k salary will add 1,500 + 2,000 + 1,000
		if (Integer.parseInt(d_monthly_salary) >= 60000 && Integer.parseInt(d_monthly_salary) <= 90000) {
		    totalSalary += 1500/4; // Add rice subsidy
		    totalSalary += 2000/4; // Add phone allowance
		    totalSalary += 1000/4; // Add clothing allowance
		    
		    totalMsalary += 1500;
		    totalMsalary += 2000;
		    totalMsalary += 1000;
		    
		    System.out.println("Compensation:");
		    System.out.println("Rice Subsidy:" + df.format(1500/4));
			System.out.println("Phone Allowance:" + df.format(2000/4));
			System.out.println("Clothing Allowance:" + df.format(1000/4));
		System.out.println("Total Weekly Compensation: " + df.format(totalSalary));}
		
		// if monthly salary is 50,825-53,500 salary will add 1,500 + 1,000 + 1,000
		if (Integer.parseInt(d_monthly_salary) >= 50825 && Integer.parseInt(d_monthly_salary) <= 53500) {
		    totalSalary += 1500/4; // Add rice subsidy
		    totalSalary += 1000/4; // Add phone allowance
		    totalSalary += 1000/4; // Add clothing allowance
		    
		    System.out.println("Compensation:");
		    System.out.println("Rice Subsidy:" + df.format(1500/4));
			System.out.println("Phone Allowance:" + df.format(1000/4));
			System.out.println("Clothing Allowance:" + df.format(1000/4));
		System.out.println("Total Weekly Compensation: " + df.format(totalSalary));}
		
		// if monthly salary is 38,475-42,975 salary will add 1,500 + 800 + 800
		if (Integer.parseInt(d_monthly_salary) >= 38475 && Integer.parseInt(d_monthly_salary) <= 42975) {
		    totalSalary += 1500/4; // Add rice subsidy
		    totalSalary += 800/4; // Add phone allowance
		    totalSalary += 800/4; // Add clothing allowance
		    
		    System.out.println("Compensation:");
		    System.out.println("Rice Subsidy:" + df.format(1500/4));
			System.out.println("Phone Allowance:" + df.format(800/4));
			System.out.println("Clothing Allowance:" + df.format(800/4));
		System.out.println("Total Weekly Compensation: " + df.format(totalSalary));}
		
		// if monthly salary is 22,500-24,000 salary will add 1,500 + 500 + 500
		if (Integer.parseInt(d_monthly_salary) >= 22500 && Integer.parseInt(d_monthly_salary) <= 24000) {
		    totalSalary += 1500/4; // Add rice subsidy
		    totalSalary += 500/4; // Add phone allowance
		    totalSalary += 500/4; // Add clothing allowance
		    
		    System.out.println("Compensation:");
			System.out.println("Rice Subsidy: +" + df.format(1500/4));
			System.out.println("Phone Allowance: +" + df.format(500/4));
			System.out.println("Clothing Allowance: +" + df.format(500/4)); 
		    System.out.println("Total Weekly Compensation: " + df.format(totalSalary));}
		
		//Benefits Deduction
		/* SSS contribution
		22,250	-	22,750	1,012.50
		22,750	-	23,250	1,035.00
		23,250	-	23,750	1,057.50
		23,750	-	24,250	1,080.00
		24,250	-	24,750	1,102.50
		24,750	-	Over	1,125.00 */
		// Philheath Premium = 3%/2
		// Pag-ibig = 2%
	
	
				if (Integer.parseInt(d_monthly_salary) >= 22500 && Integer.parseInt(d_monthly_salary) <= 22750) {
	
			double deduction_SSS = 1012.50/4;
			double deductionPercentage1 = 0.015;
			double deductionPercentage2 = 0.02;
			double deductionSSS = deduction_SSS;
			double deductionPhilhealth = totalSalary * deductionPercentage1;
			double deductionPagibig = totalSalary * deductionPercentage2;
			
			System.out.println("Deduction:");
			System.out.println("SSS: -"+ df.format(deduction_SSS));
			System.out.println("Philhealth: -"+ df.format(deductionPhilhealth));
			System.out.println("Pagibig: -"+ df.format(deductionPagibig));
			System.out.println("Total Weekly Deduction:"+ df.format(deduction_SSS+deductionPhilhealth+deductionPagibig));

			System.out.println("-----------------");
			System.out.println("Net Weekly Pay: " + df.format(totalSalary - deductionSSS - deductionPhilhealth - deductionPagibig));
			System.out.println("Net Monthly Pay: " + df.format(totalMsalary - deductionSSS*4 - deductionPhilhealth - deductionPagibig));

				}
		
				if (Integer.parseInt(d_monthly_salary) >= 22751 && Integer.parseInt(d_monthly_salary) <= 23250) {
			
			double deduction_SSS = 1035.00/4;
			double deductionPercentage1 = 0.015;
			double deductionPercentage2 = 0.02;
			double deductionSSS = deduction_SSS;
			double deductionPhilhealth = totalSalary * deductionPercentage1;
			double deductionPagibig = totalSalary * deductionPercentage2;
			
			System.out.println("Deduction:");
			System.out.println("SSS: -"+ df.format(deduction_SSS));
			System.out.println("Philhealth: -"+ df.format(deductionPhilhealth));
			System.out.println("Pagibig: -"+ df.format(deductionPagibig));
			System.out.println("Total Weekly Deduction:"+ df.format(deduction_SSS+deductionPhilhealth+deductionPagibig));

			System.out.println("-----------------");
			System.out.println("Net Weekly Pay: " + df.format(totalSalary - deductionSSS - deductionPhilhealth - deductionPagibig));
			System.out.println("Net Monthly Pay: " + df.format(totalMsalary - deductionSSS*4 - deductionPhilhealth - deductionPagibig));

				}
		
				if (Integer.parseInt(d_monthly_salary) >= 23251 && Integer.parseInt(d_monthly_salary) <= 23750) {
			
			double deduction_SSS = 1057.50/4;
			double deductionPercentage1 = 0.015;
			double deductionPercentage2 = 0.02;
			double deductionSSS = deduction_SSS;
			double deductionPhilhealth = totalSalary * deductionPercentage1;
			double deductionPagibig = totalSalary * deductionPercentage2;
			
			System.out.println("Deduction:");
			System.out.println("SSS: -"+ df.format(deduction_SSS));
			System.out.println("Philhealth: -"+ df.format(deductionPhilhealth));
			System.out.println("Pagibig: -"+ df.format(deductionPagibig));
			System.out.println("Total Weekly Deduction:"+ df.format(deduction_SSS+deductionPhilhealth+deductionPagibig));

			System.out.println("-----------------");
			System.out.println("Net Weekly Pay: " + df.format(totalSalary - deductionSSS - deductionPhilhealth - deductionPagibig));
			System.out.println("Net Monthly Pay: " + df.format(totalMsalary - deductionSSS*4 - deductionPhilhealth - deductionPagibig));

				}
				if (Integer.parseInt(d_monthly_salary) >= 23751 && Integer.parseInt(d_monthly_salary) <= 24250) {
			
			double deduction_SSS = 1080.00/4;
			double deductionPercentage1 = 0.015;
			double deductionPercentage2 = 0.02;
			double deductionSSS = deduction_SSS;
			double deductionPhilhealth = totalSalary * deductionPercentage1;
			double deductionPagibig = totalSalary * deductionPercentage2;
			
			System.out.println("Deduction:");
			System.out.println("SSS: -"+ df.format(deduction_SSS));
			System.out.println("Philhealth: -"+ df.format(deductionPhilhealth));
			System.out.println("Pagibig: -"+ df.format(deductionPagibig));
			System.out.println("Total Weekly Deduction:"+ df.format(deduction_SSS+deductionPhilhealth+deductionPagibig));

			System.out.println("-----------------");
			System.out.println("Net Weekly Pay: " + df.format(totalSalary - deductionSSS - deductionPhilhealth - deductionPagibig));
			System.out.println("Net Monthly Pay: " + df.format(totalMsalary - deductionSSS*4 - deductionPhilhealth - deductionPagibig));

				}
					if (Integer.parseInt(d_monthly_salary) >= 24251 && Integer.parseInt(d_monthly_salary) <= 24750) {
					
			double deduction_SSS = 1102.50/4;
			double deductionPercentage1 = 0.015;
			double deductionPercentage2 = 0.02;
			double deductionSSS = deduction_SSS;
			double deductionPhilhealth = totalSalary * deductionPercentage1;
			double deductionPagibig = totalSalary * deductionPercentage2;
					
			System.out.println("Deduction:");
			System.out.println("SSS: -"+ df.format(deduction_SSS));
			System.out.println("Philhealth: -"+ df.format(deductionPhilhealth));
			System.out.println("Pagibig: -"+ df.format(deductionPagibig));
			System.out.println("Total Weekly Deduction:"+ df.format(deduction_SSS+deductionPhilhealth+deductionPagibig));

			System.out.println("-----------------");
			System.out.println("Net Weekly Pay: " + df.format(totalSalary - deductionSSS - deductionPhilhealth - deductionPagibig));
			System.out.println("Net Monthly Pay: " + df.format(totalMsalary - deductionSSS*4 - deductionPhilhealth - deductionPagibig));

		}
					if (Integer.parseInt(d_monthly_salary) >= 24751 && Integer.parseInt(d_monthly_salary) <= 90000) {
					
			double deduction_SSS = 1035.50/4;
			double deductionPercentage1 = 0.015;
			double deductionPercentage2 = 0.02;
			double deductionSSS = deduction_SSS;
			double deductionPhilhealth = totalSalary * deductionPercentage1;
			double deductionPagibig = totalSalary * deductionPercentage2;
						
			System.out.println("Deduction:");
			System.out.println("SSS: -"+ df.format(deduction_SSS));
			System.out.println("Philhealth: -"+ df.format(deductionPhilhealth));
			System.out.println("Pagibig: -"+ df.format(deductionPagibig));
			System.out.println("Total Weekly Deduction:"+ df.format(deduction_SSS+deductionPhilhealth+deductionPagibig));

			System.out.println("-----------------");
			System.out.println("Net Weekly Pay: " + df.format(totalSalary - deductionSSS - deductionPhilhealth - deductionPagibig));
			System.out.println("Net Monthly Pay: " + df.format(totalMsalary - deductionSSS*4 - deductionPhilhealth - deductionPagibig));

					}
		
		return null;
	}

		
	    
	}

