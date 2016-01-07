package net.space_game.player

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.{Vector2,MathUtils}
import com.badlogic.gdx.graphics.Color
import net.space_game.conf.StarshipConf
import net.space_game.util.Helpers

object Starship {
	sealed abstract class RotationState
	case object None extends RotationState
	case object Left extends RotationState
	case object Right extends RotationState
}

/**
 * A player ship that turns and thrusts forward.
 */
class Starship(val mass: Float = StarshipConf.STARSHIP_MASS) extends PlayerShip {
  
	import Helpers._
	import StarshipConf._
	import MathUtils._
	import Starship._
	
	/// Private instance methods ///
	
	private var heading: Float = 80
	private var rotationState: RotationState = None
	private var thrusting: Boolean = false
	
	/// Public Methods ///
	
	// Logic
	def update(delta: Float) {
		if(rotationState == Left)
			heading += delta * ROTATION_PER_SECOND
		else if(rotationState == Right)
			heading -= delta * ROTATION_PER_SECOND
		
		if(thrusting)
			applyForce((THRUST_FORCE, 0F).setAngle(heading))
			
		this.updateSimplePhysics(delta)
	}
	
	def stopRotating() { rotationState = None }
	def rotateRight() { rotationState = Right }
	def rotateLeft() { rotationState = Left }
	
	def isRotating() = rotationState != None
	def getRotationState() = rotationState
	
	def thrust() { thrusting = true }
	def stopThrusting() { thrusting = false }
	def isThrusting() = thrusting
	
	// Drawing
	def drawDebug(shapeRenderer: ShapeRenderer) {		
		val h = SIZE / 2
		val theta = heading * degreesToRadians
		val phi = (90 - heading) * degreesToRadians
		
		val p2x = pos.x + h * cos(theta)
		val p2y = pos.y + h * sin(theta)
		val tx = pos.x - h * cos(theta)
		val ty = pos.y - h * sin(theta)
		val p1x = tx + h/2 * cos(phi)
		val p1y = ty - h/2 * sin(phi)
		val p3x = tx - h/2 * cos(phi)
		val p3y = ty + h/2 * sin(phi)
		
		shapeRenderer.setColor(Color.WHITE)
		shapeRenderer.line(p1x, p1y, p2x, p2y)
		shapeRenderer.line(p2x, p2y, p3x, p3y)
		shapeRenderer.line(p3x, p3y, p1x, p1y)
		shapeRenderer.circle(pos.x, pos.y, 3)
	}
}