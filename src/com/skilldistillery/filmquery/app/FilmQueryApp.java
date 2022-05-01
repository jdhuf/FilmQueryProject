package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	Scanner kb = new Scanner(System.in);

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		// app.test2();
		// app.test3();
		// app.test4();
		// app.test5();
		app.launch();
	}

	private void test() {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private void test2() {
		List<Film> film = db.findFilmsByActorId(1);
		System.out.println(film);

	}

	private void test3() {
		Actor actor = db.findActorById(1);
		System.out.println(actor);
	}

	private void test4() {
		List<Actor> actor = db.findActorsByFilmId(23);
		System.out.println(actor);

	}

	private void test5() {
		List<Film> film = db.findFilmsBySearchKeyword("Anaconda");
		System.out.println(film);
	}

	private void launch() {

//		Scanner input = new Scanner(System.in);
//		startUserInterface(input);
//		input.close();

		int userChoice = 3;

		while (true) {
			userChoice = getMenuChoice();
			if (userChoice == 3) {

				break;
			}

			if (userChoice == 1) {
				
				System.out.println("Please enter a valid film id.\n"
						+ "(Valid film ids are integer numbers from 1 to 1000.)");
				
				Film film = db.findFilmById(kb.nextInt());
				
				if (film == null) {
					System.out.println("Film not found. \n"
							+ "Please be sure to enter a valid film id when looking up a film by its id.");
					System.out.println();
				}
				
				else {
					
					System.out.println(film);
					System.out.println();
				
				}

			}

			if (userChoice == 2) {
				
				System.out.println("Please enter a keyword to search for a film.");
				
				List<Film> film = db.findFilmsBySearchKeyword(kb.next());
				System.out.println(film);
				System.out.println();
			

			}

			if (userChoice == 3) {


			}

		}
		
		System.out.println("Goodbye!");
	
	}


	private int getMenuChoice() {
		System.out.println("======= Film Query Menu ============== \n\n"
				+ "Please enter one of the numbers below.");
		System.out.println("1: Look up a film by its id");
		System.out.println("2: Look up a film by a search keyword");
		System.out.println("3: Exit the application");

		int userMenuSelection;

		try {
			userMenuSelection = kb.nextInt();
			if (userMenuSelection > 3 || userMenuSelection < 0) {
				userMenuSelection = 3;
			}
		} catch (Exception e) {
			userMenuSelection = 3;
		}
		return userMenuSelection;
	}

	

//	private void startUserInterface(Scanner input) {
//
//	}
}
