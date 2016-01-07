package net.space_game.control

import com.badlogic.gdx.InputAdapter
import net.space_game.player.Starship
import com.badlogic.gdx.{Gdx,Input}
import net.space_game.util.Helpers

class StarshipController(starship: Starship) extends InputAdapter {
 
	import Helpers._
	import Input.Keys._
	
	override def keyDown(code: Int): Boolean = {
		code match {
			case LEFT => starship.rotateLeft()
			case RIGHT => starship.rotateRight()
			case SPACE => starship.thrust()
			case R => {
				starship.resetAccel()
				starship.stop()
				starship.setPos((20,20))
			}
			case _ => 
		}
		true
	}
	
	override def keyUp(code: Int): Boolean = {
		if(!Gdx.input.isKeyPressed(LEFT) && !Gdx.input.isKeyPressed(RIGHT) && starship.isRotating)
			starship.stopRotating()
		code match {
			case SPACE => starship.stopThrusting()
			case _ =>
		}
		true
	}
	
}