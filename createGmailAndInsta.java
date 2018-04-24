import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class createGmailAndInsta extends navigationTools {

	static ArrayList<String> passwords = new ArrayList<String>();
	static ArrayList<String> usernames = new ArrayList<String>();
	static ArrayList<String> firstnames = new ArrayList<String>();
	static ArrayList<String> lastnames = new ArrayList<String>();
	static ArrayList<String> gender	= new ArrayList<String>();
	static ArrayList<String> birthdays = new ArrayList<String>();

	static ArrayList<Person> people = new ArrayList<Person>();

	public static Scanner input = new Scanner(System.in); //scanner to be used

	public static void main(String[] args) throws InterruptedException, AWTException {
		//create bank of words to use for usernames
		int numPeople = nameGen();
		//create user names of two words followed by a number
		usernameGen(numPeople);
		//create three component passwords
		passwordGen(numPeople);

		Robot bot = new Robot();
		int mask = InputEvent.BUTTON1_DOWN_MASK;
	


		for(int i = 0; i < usernames.size(); i++){
			openBrowser();
			openNotePad();
			move(); //need to give it time to open
			getToGmailSignUp(bot, mask); //typing url stuff
			//move(); //need to give it time to open
			//gmailSignUp(bot, mask, i); //navigate and input info on gmail page
			//verifyEmail(bot, mask);
			//switchScreen(bot);
			//switchScreen(bot);
			//getToInsta(bot, mask); //this assumes that the gmail tab is already open
			//instaLogIn(bot, mask, i);
			//readUserFile("userList", bot, mask);
			//instaSignUp(bot, mask, i); //creates insta account in correlation with gmail account
			//closeScreen(bot);
		}
		//writeUNandPW(); //write to a file
	}

	public static void instaLogIn(Robot bot, int mask, int personIndex) throws InterruptedException{
		//click on log in
		bot.mouseMove(734,642);  
		move();
		bot.mousePress(mask);
		midclick();
		bot.mouseRelease(mask);
		
		//type in user info
		String username =  usernames.get(personIndex).toUpperCase();
		typeString(bot,username);
		tab(bot);
		String password = passwords.get(personIndex).toUpperCase();
		typeString(bot,password);
		tab(bot);
		tab(bot);
		enter(bot);
	}

	public static void instaSignUp(Robot bot, int mask, int personIndex) throws InterruptedException{
		//type in email (username + "@gmail")
		tab(bot); //these move it to the first start spot to type
		tab(bot);
		String email = usernames.get(personIndex).toUpperCase() + "@GMAIL.COM";//must go to uppercase so ascii values = keycode
		typeString(bot, email);

		//type fullname
		//firstname
		tab(bot);
		String firstname = firstnames.get(personIndex).toUpperCase();//must go to uppercase so ascii values = keycode
		typeString(bot, firstname);
		space(bot); //puts space between first and last
		//type last name
		String lastname = lastnames.get(personIndex).toUpperCase();//must go to uppercase so ascii values = keycode
		typeString(bot, lastname);

		//type in username
		tab(bot);
		String username = usernames.get(personIndex).toUpperCase();//must go to uppercase so ascii values = keycode
		typeString(bot, username);

		//typing in password
		tab(bot);
		tab(bot);
		String password = passwords.get(personIndex).toUpperCase();//must go to uppercase so ascii values = keycode
		System.out.println("password = " + password);
		typeString(bot, password);

		tab(bot);
		//TODO submits it
		//enter(bot);
	}

	public static void followIGUsers(Robot bot, int mask, String username) throws InterruptedException{
		//click on search bar
		bot.mouseMove(403,87);  
		move();
		bot.mousePress(mask);
		midclick();
		bot.mouseRelease(mask);

		tab(bot);

		typeString(bot, username);

		enter(bot);

	}

	private static void readUserFile(String readFile, Robot bot, int mask) throws InterruptedException{
		Path file = Paths.get(readFile);
		try (InputStream in = Files.newInputStream(file);
				BufferedReader reader = new BufferedReader
						(new InputStreamReader(in))) {
			String line = null;
			while ((line = reader.readLine()) != null) {

				//splits the current line into name and address
				String currLine = line.trim();
				//ADDING THE USER
				followIGUsers(bot, mask, currLine);

			}
		} catch (IOException x) {
			//displaying a message when there is an error
			System.err.println("Unable to read from file: " + readFile);
		}
	}

	public static void gmailSignUp(Robot bot, int mask, int personIndex) throws InterruptedException{
		//click on name bar
		bot.mouseMove(883,323);  
		move();
		bot.mousePress(mask);
		midclick();
		bot.mouseRelease(mask);

		move();

		//type first name
		String firstname = firstnames.get(personIndex).toUpperCase();//must go to uppercase so ascii values = keycode
		typeString(bot, firstname);

		//type last name
		tab(bot);
		String lastname = lastnames.get(personIndex).toUpperCase();//must go to uppercase so ascii values = keycode
		typeString(bot, lastname);

		//type in username (will be email)
		tab(bot);
		String username = usernames.get(personIndex).toUpperCase();//must go to uppercase so ascii values = keycode
		typeString(bot, username);

		//typing in password
		tab(bot);
		tab(bot);
		String password = passwords.get(personIndex).toUpperCase();//must go to uppercase so ascii values = keycode
		typeString(bot, password);

		//typing confirmation password
		tab(bot);
		typeString(bot, password);


		//generate random birthday
		tab(bot);
		int month = (int)(Math.random()*11) +2;
		System.out.print(month + " ");
		for(int i = 0; i < month; i++){
			midclick();
			down(bot); //going through pop up
		}
		enter(bot); //confirming selection
		tab(bot); //moving to next

		//typing out day
		int day = (int)(Math.random()*29) +1;
		System.out.print(day + " ");
		if(day > 9){
			//tens digit
			bot.keyPress(48+ (day/10));
			midclick();
			bot.keyRelease(48+ (day/10));
			//ones digit
			bot.keyPress(48+ (day%10));
			midclick();
			bot.keyRelease(48+ (day%10));
		}else{
			//ones value
			bot.keyPress(48+day);
		}

		//year generater
		tab(bot); //moving to next
		int year = (int)(Math.random()*53) + 1943;
		System.out.println(year);
		//thousands digit
		bot.keyPress(48+ (year/1000));
		midclick();
		bot.keyRelease(48+ (year/1000));
		//hundreds digit
		bot.keyPress(48+ ((year-1000)/100));
		midclick();
		bot.keyRelease(48+ ((year-1000)/100));
		//tens digit
		bot.keyPress(48+ ((year-1900)/10));
		midclick();
		bot.keyRelease(48+ ((year-1900)/10));
		//ones digit
		bot.keyPress(48+ (year%10));
		midclick();
		bot.keyRelease(48+ (year%10));

		//add birthday to list
		birthdays.add(Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year));

		//enter gender
		tab(bot);
		int genderNum = 4; // set to other at inialization
		String genderStr = gender.get(personIndex);
		if(genderStr.equals("male")){
			genderNum = 3; //for male
		}else{
			genderNum = 2; //for female
		}
		for(int i = 0; i < genderNum; i++){
			down(bot);
		}
		enter(bot); //make selection

		//tab through rest of info that is not nessesary
		tab(bot);
		midclick();
		tab(bot);
		midclick();
		tab(bot);
		midclick();
		tab(bot);
		midclick();
		tab(bot);
		midclick();
		enter(bot);

		move();
		//must scroll to bottom of pop up (mouse is over pop up so it's okay)
		space(bot);
		space(bot);
		space(bot);

		//move cursor over accept button
		bot.mouseMove(901,643);  
		move();

		//this hit the agree button
		//		bot.mousePress(mask);
		//		midclick();
		//		bot.mouseRelease(mask);
		//		move();
	}

	private static void writeUNandPW(){
		System.out.print("Enter filename: ");
		String writeFile = input.nextLine();

		System.out.println("Writing file: " + writeFile);

		FileOutputStream fileByteStream = null; // File output stream
		PrintWriter outFS = null;               // Output stream
		try{
			boolean sameFileName = false;
			File folder = new File(".");
			for ( File file : folder.listFiles()) {
				if ( file.getName().equals(writeFile)) {
					//if this is false we can write a new file
					sameFileName = true;
				} 
			}

			if(!sameFileName == true){
				// Try to open file
				fileByteStream = new FileOutputStream(writeFile);
				outFS = new PrintWriter(fileByteStream);
				//adds all un and pw to the new file
				for(int i = 0; i < usernames.size(); i++){
					outFS.append(firstnames.get(i) + " " + lastnames.get(i) + " " + birthdays.get(i) + " "+ gender.get(i) + " " + usernames.get(i) + " "+ passwords.get(i) + "\n");
				}
				// Done with file, so try to close it
				outFS.flush();
				// close() may throw IOException if fails
				fileByteStream.close();
			}else{
				System.out.println("This name already exists");
			}
			//enterCommand();

		}catch(IOException x){
			System.out.println("Unable to write to file: " + writeFile);
			//enterCommand();
		}
	}

	public static int nameGen(){
		System.out.print("How many people do you want? ");
		int num = input.nextInt();
		input.nextLine();

		//bank of first names *new male and female names
		String[] firstNBM = new String[]{"Jake", "Tyler", "Luke", "Evan", "Jack","Kyle", "Max", "Nate", "James","Jacob","Kevin","Steve","Jaxon","Josh","Cheerio","Will","Grayson","Tanner"}; 
		String[] firstNBF = new String[]{"Kassidy","Cate","Stefinie","Cori","Hailey","Rachel","Mckayla","Brianna","Gabby","Brittany","Shelby","Maren","Mckena","Sammy","Erika","Alexis","Amanda","Kara","Magen","Megan","Emma"};
		String[] lastNB = new String[]{"Parker","Schnedier","Kading","Gearing","Fugler","Baumert","Halverson","Garrent","Ault","VanBurren","Hollenbec","Burlingame","Bouche","Sulivan","Cross","West","Meitzer","Nordburg","Chifskoski","Vesper","Solem","Anfang","Kraus","Branaman","Ashlin","Trieloff"};
		//bank of last names

		int numGenerated = 0; //number of people that have been made
		while(numGenerated < num){
			//make new names
			String newfirstname = "";
			String newlastname = lastNB[(int)(Math.random()*lastNB.length)];
			int MorF = (int)(Math.random()*2); //randomly chooses male or female
			if(MorF == 0){
				newfirstname = firstNBM[(int)(Math.random()*firstNBM.length)];
			}else{
				newfirstname = firstNBF[(int)(Math.random()*firstNBF.length)];
			}

			boolean alreadyUsed = false; //keeps track if names have already been used
			//iterate through to check for matches
			for(int i = 0; i < firstnames.size();i++){
				if(firstnames.get(i).equals(newfirstname) && lastnames.get(i).equals(newlastname)){
					System.out.println("these names have already been used");
					alreadyUsed = true;
				}
			}//end for loop of checking
			if(alreadyUsed == false){
				firstnames.add(newfirstname);//doesn't matter if a female or male
				lastnames.add(newlastname);
				if(MorF == 0){
					gender.add("male");
				}else{
					gender.add("female");
				}
				numGenerated++;
			}
		}//end while loop
		return num;
	}

	public static void usernameGen(int num){ //returns the number of userNames actually created, value to be sent to passwordGen

		int numGenerated = 0;

		String[] com1 = new String[]{"the","and","that","nerve","who","but","this","sunflower","product","other","goods","nail","home","day","clock","birthday","time","house","pens","cups","computer","kitchen","picture","machine","stove"};
		String[] com3 = new String[]{"3579","45778","2755","8354","2889","8905","1566","5678","3457"}; //number componet of username

		while(numGenerated < num){
			String un = firstnames.get(numGenerated)+ com1[(int)(Math.random()*com1.length)] + com3[(int)(Math.random()*com3.length)];
			String charToUpper = un.substring(0,1).toUpperCase();
			un = charToUpper + un.substring(1);

			//check if already created
			if(usernames.contains(un)){
				//error message saying there is a match already
				System.out.println("User name already used");
			}else{
				usernames.add(un);
				numGenerated++;
			}
		}//end for loop
	}

	public static void passwordGen(int num){
		String[] com1 = new String[]{"1","2","3","4","5","6","7","8","9"}; //number componet of password
		String[] com2 = new String[]{"cantine","flyaway","drivers","nerve","butter","thorns","sunflower","product","otters","nail","home","day","clock","birthday","time","house","pens","cups","computer","kitchen","picture","machine","stove"};
		String[] com3 = new String[]{"tt","ss","sd","lk","ee","ls","ds","xc","ci","ek"};
		String[] com4 = new String[]{"!","@","#","$","%","&","*"}; //special character component of password

		//change first letter of the first word to uppercase
		for(int i = 0; i < num; i++){
			String pw = com1[(int)(Math.random()*com1.length)] + com2[(int)(Math.random()*com2.length)] + com2[(int)(Math.random()*com2.length)] + com3[(int)(Math.random()*com3.length)] + com4[(int)(Math.random()*com4.length)];
			String charToUpper = pw.substring(1, 2).toUpperCase();
			pw = pw.substring(0, 1) + charToUpper + pw.substring(2);
			//create new password, check if already created
			passwords.add(pw);
		}
	}



	private static void generatePeople(){
		people.clear();
		for(int i = 0; i < firstnames.size(); i++){
			people.add(new Person(firstnames.get(i), lastnames.get(i), usernames.get(i), passwords.get(i), gender.get(i), birthdays.get(i)));
		}
	}
}
