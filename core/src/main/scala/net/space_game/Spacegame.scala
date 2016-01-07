package net.space_game

import com.badlogic.gdx.Game
import net.space_game.screens.GameScreen

class SpaceGame extends Game {
		
    override def create() {
    	setScreen(new GameScreen())    	
    }
}
