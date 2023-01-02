package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import app.model.Game;
import app.model.Animal;
import app.model.State;
import app.model.Livingroom;
import app.model.Kitchen;
import app.model.Garden;
import app.model.Bathroom;
import app.model.Bedroom;
import app.model.MiniGame;
import app.model.ThrowAndFetch;

class TestModel {
	//app.controller.Pet pet;
	//app.controller.Room room;
	Game game;
	Animal pet;
	Livingroom livingRoom;
	Kitchen kitchen;
	Garden garden;
	Bathroom bathroom;
	Bedroom bedroom;
	ThrowAndFetch miniGame;
	
	@BeforeEach
	void setUp() {
		//room = new app.controller.Room("livingRoom");
		//pet = new app.controller.Pet("cat");
		//game = new app.model.Game(null,null);
		pet = new Animal("cat");
		pet.init(new State("hunger"), new State("thirst"), new State("weight"), new State("hygiene"), new State("moral"));
		
		game = new Game(pet,garden);
		
		miniGame = new ThrowAndFetch(pet);
	}
	
	@Test
	@DisplayName("Test launch")
	void testLaunch() {
		int t = 0;
		assertEquals(t,0);
	}	
	
	@Test
	@DisplayName("Test pet")
	void testPet() {
		assertEquals(5,pet.getStatsNumber(),"Pas le bon nombre de stat");
		assertEquals(false,pet.isTakingShower(),"Erreur le pet prends une douche");
		assertEquals(false,pet.isEating(),"Erreur le pet mange");
		pet.toogleEating();
		assertEquals(true,pet.isEating(),"Erreur le pet ne mange pas");
		pet.setMalusForAllStates("dying", 2.);
		pet.getHealth().setValue(0.);
		pet.getWeight().setValue(0.);
		pet.getThirst().setValue(0.);
		pet.getHunger().setValue(0.);
		assertEquals(true,pet.isDead(),"Le pet n'est pas mort");
	}
	
		
	@Test
	@DisplayName("Test sur Room")
	void testModel() {
		assertEquals(garden,game.getCurrentModelRoom(),"Pas la même room");
		assertEquals(pet,game.getModelPet(),"Pas le bon pet");
	}
	
	@Test
	@DisplayName("Test sur MiniGame")
	void testMiniGame() {
		assertEquals(0,miniGame.getDistance(),"Le baton n'est pas à 0");
		miniGame.throwStick();
		assertNotEquals(0,miniGame.getDistance(),"Le baton est à 0");
	}
}
