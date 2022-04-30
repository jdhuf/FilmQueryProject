package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
   // app.test();
    app.test2();
  //  app.test3();
   // app.test4();
  //  app.launch();
  }

  private void test() {
    Film film = db.findFilmById(1);
    System.out.println(film);
  }
  
  private void test2() {
	List < Film> film = db.findFilmsByActorId(1);
		System.out.println(film);	  
			  
  }
  
  private void test3() {
	    Actor actor = db.findActorById(1);
	    System.out.println(actor);
	  }
  
  private void test4() {
		List < Actor> actor = db.findActorsByFilmId(23);
		System.out.println(actor);	  
			  
	  
  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
    
  }

}
