package net.space_game.planets

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.Color
import net.space_game.physics.MassObject

class Planet(val mass: Float, val r: Float, val color: Color = Color.ORANGE) extends MassObject {
	
	/// Public Methods ///
	
  	// Drawing
	def drawDebug(shapeRenderer: ShapeRenderer) {
		shapeRenderer.setColor(color)
		shapeRenderer.circle(pos.x, pos.y, r)
	}

}