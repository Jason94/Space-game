package net.space_game.player

import net.space_game.physics.{PhysicsObject,MassObject}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

abstract class PlayerShip extends MassObject {
	
	/// Protected Instance Variables ///
	
	/// Public API ///
	
	def update(delta: Float)
	def drawDebug(shapeRenderer: ShapeRenderer)
		
}