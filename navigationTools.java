/*
*************************************************************************
* Created by: Kyle Schneider on June 23rd, 2016
* _______________________
*
* This program is free software: you can redistribute it and/or modify it.
* This program is distributed in the hope that it will be useful but WITHOUT ANY WARRANTY!
* PLEASE use this for good!
************************************************************************
*/

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class navigationTools {

	public static void typeString(Robot bot, String string) throws InterruptedException{
		for(int i = 0; i < string.length(); i++){
			char letter = string.charAt(i); //getkyles letter
			if((int)(letter) == 33 || (int)(letter) == 35 ||(int)(letter) == 36 || (int)(letter) == 37 || (int)(letter) == 38 || (int)(letter) == 42 || (int)(letter) == 64 || (int)(letter) == 58 || (int)(letter) == 47 || (int)(letter) == 46){
				//special characters
				System.out.println(string.charAt(i));
				switch((int)(letter)){
				case 46: //.
					bot.keyPress(KeyEvent.VK_PERIOD);
					midclick();
					bot.keyRelease(KeyEvent.VK_PERIOD);
					break;
				case 58: //shift + ; which results in :
					bot.keyPress(KeyEvent.VK_SHIFT);
					midclick();
					bot.keyPress(KeyEvent.VK_SEMICOLON);
					midclick();
					bot.keyRelease(KeyEvent.VK_SEMICOLON);
					midclick();
					bot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 47: //'/'
					bot.keyPress(KeyEvent.VK_SLASH);
					midclick();
					bot.keyRelease(KeyEvent.VK_SLASH);
					break;
				case 33: //SHIFT + 1 which results in !
					bot.keyPress(KeyEvent.VK_SHIFT);
					midclick();
					bot.keyPress(KeyEvent.VK_1);
					midclick();
					bot.keyRelease(KeyEvent.VK_1);
					midclick();
					bot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 35: //SHIFT + 3 which results in #
					bot.keyPress(KeyEvent.VK_SHIFT);
					midclick();
					bot.keyPress(KeyEvent.VK_3);
					midclick();
					bot.keyRelease(KeyEvent.VK_3);
					midclick();
					bot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 36: //SHIFT + 4 which results in $
					bot.keyPress(KeyEvent.VK_SHIFT);
					midclick();
					bot.keyPress(KeyEvent.VK_4);
					midclick();
					bot.keyRelease(KeyEvent.VK_4);
					midclick();
					bot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 37: //SHIFT + 5 which results in %
					bot.keyPress(KeyEvent.VK_SHIFT);
					midclick();
					bot.keyPress(KeyEvent.VK_5);
					midclick();
					bot.keyRelease(KeyEvent.VK_5);
					midclick();
					bot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 38: //SHIFT + 7 which results in &
					bot.keyPress(KeyEvent.VK_SHIFT);
					midclick();
					bot.keyPress(KeyEvent.VK_7);
					midclick();
					bot.keyRelease(KeyEvent.VK_7);
					midclick();
					bot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 42: //SHIFT + 8 which results in *
					bot.keyPress(KeyEvent.VK_SHIFT);
					midclick();
					bot.keyPress(KeyEvent.VK_8);
					midclick();
					bot.keyRelease(KeyEvent.VK_8);
					midclick();
					bot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 64: //SHIFT + 2 which results in @
					bot.keyPress(KeyEvent.VK_SHIFT);
					midclick();
					bot.keyPress(KeyEvent.VK_2);
					midclick();
					bot.keyRelease(KeyEvent.VK_2);
					midclick();
					bot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				}
			}else{
				System.out.println(string.charAt(i));
				//Execute normal typing
				bot.keyPress((int)(letter)); //ascii value is same as keycode
				midclick();
				bot.keyRelease((int)(letter));
			}
		}
	}

	public static void tab(Robot bot) throws InterruptedException{
		bot.keyPress(KeyEvent.VK_TAB);
		midclick();
		bot.keyRelease(KeyEvent.VK_TAB);
	}

	public static void down(Robot bot) throws InterruptedException{
		bot.keyPress(KeyEvent.VK_DOWN);
		midclick();
		bot.keyRelease(KeyEvent.VK_DOWN);
	}

	public static void space(Robot bot) throws InterruptedException{
		bot.keyPress(32); //space bar
		midclick();
		bot.keyRelease(32);
	}

	public static void switchScreen(Robot bot) throws InterruptedException{
		//switches over to browser
		bot.keyPress(KeyEvent.VK_ALT);
		midclick();
		bot.keyPress(KeyEvent.VK_TAB);
		midclick();
		bot.keyRelease(KeyEvent.VK_TAB);
		midclick();
		bot.keyRelease(KeyEvent.VK_ALT);
	}

	public static void enter(Robot bot) throws InterruptedException{
		bot.keyPress(KeyEvent.VK_ENTER);
		midclick();
		bot.keyRelease(KeyEvent.VK_ENTER);
	}

	public static void move() throws InterruptedException{
		TimeUnit.SECONDS.sleep(1);
	}

	public static void midclick() throws InterruptedException{
		TimeUnit.MILLISECONDS.sleep((int)(Math.random()*30) + 30);
	}

	public static void closeScreen(Robot bot) throws InterruptedException{
		//switches over to browser
		bot.keyPress(KeyEvent.VK_CONTROL);
		midclick();
		bot.keyPress(KeyEvent.VK_W);
		midclick();
		bot.keyRelease(KeyEvent.VK_W);
		midclick();
		bot.keyPress(KeyEvent.VK_W);
		midclick();
		bot.keyRelease(KeyEvent.VK_W);
		midclick();
		bot.keyRelease(KeyEvent.VK_CONTROL);
	}

	private static void enterCommand(){
		Scanner input = new Scanner(System.in);
		System.out.print("Press Enter to continue.");
		if(input.hasNextLine()){
			input.nextLine();
			return;
		}
	}
	
	public static void openBrowser(){
		Runtime run = Runtime.getRuntime();
		String browser = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
		try{
			//opens up chrome
			run.exec(new String[]{browser});
		}catch(IOException e){
			System.out.println("Shit happened.");
		}
	}

	public static void openNotePad(){
		Runtime run = Runtime.getRuntime();
		try{
			//opens up notePad
			run.exec("notepad");
		}catch(IOException e){
			System.out.println("Shit happened.");
		}
	}

	public static void getToGmailSignUp(Robot bot, int mask) throws InterruptedException{
		//click on url bar
		bot.mouseMove(641,49);  
		move();
		bot.mousePress(mask);
		midclick();
		bot.mouseRelease(mask);

		//shorter way to type url
		String url = "https://accounts.google.com/SignUp";
		url = url.toUpperCase();
		typeString(bot,url);

		//sleeping after typeing to pressing enter
		move();
		bot.keyPress(KeyEvent.VK_ENTER);
		midclick();
		bot.keyRelease(KeyEvent.VK_ENTER);

		//give it time to load
		move();
		move();
		move();
		move();
		move();
		move();

	}



	public static void verifyEmail(Robot bot, int mask) throws InterruptedException{
		//2
		bot.keyPress(KeyEvent.VK_2);
		midclick();
		bot.keyRelease(KeyEvent.VK_2);
		//6
		bot.keyPress(KeyEvent.VK_6);
		midclick();
		bot.keyRelease(KeyEvent.VK_6);
		//2
		bot.keyPress(KeyEvent.VK_2);
		midclick();
		bot.keyRelease(KeyEvent.VK_2);
		//4
		bot.keyPress(KeyEvent.VK_4);
		midclick();
		bot.keyRelease(KeyEvent.VK_4);
		//7
		bot.keyPress(KeyEvent.VK_7);
		midclick();
		bot.keyRelease(KeyEvent.VK_7);
		//3
		bot.keyPress(KeyEvent.VK_3);
		midclick();
		bot.keyRelease(KeyEvent.VK_3);
		//9
		bot.keyPress(KeyEvent.VK_9);
		midclick();
		bot.keyRelease(KeyEvent.VK_9);
		//1
		bot.keyPress(KeyEvent.VK_1);
		midclick();
		bot.keyRelease(KeyEvent.VK_1);
		//0
		bot.keyPress(KeyEvent.VK_0);
		midclick();
		bot.keyRelease(KeyEvent.VK_0);
		//8
		bot.keyPress(KeyEvent.VK_8);
		midclick();
		bot.keyRelease(KeyEvent.VK_8);

		tab(bot);
		tab(bot);
		enter(bot);
	}

	public static void getToInsta(Robot bot, int mask) throws InterruptedException{		
		//click on url bar
		bot.mouseMove(240, 40);
		move();
		bot.mousePress(mask);
		midclick();
		bot.mouseRelease(mask);

		String url = "http://www.instagram.com/accounts/emailsignup";
		url = url.toUpperCase();
		typeString(bot, url);

		bot.keyPress(KeyEvent.VK_ENTER);
		midclick();
		bot.keyRelease(KeyEvent.VK_ENTER);

		//gives time to load
		move();
		move();
		move();
		move();
		move();
		move();
	}


}