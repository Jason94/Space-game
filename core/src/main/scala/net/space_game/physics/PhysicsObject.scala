package net.space_game.physics

import com.badlogic.gdx.math.Vector2
import net.space_game.util.Helpers

trait PhysicsObject {
	
	import Helpers._
	
	/// Protected Instance Variables ///

	protected var pos: Vector2 = new Vector2(0,0)
	protected var vel: Vector2 = new Vector2(0,0)
	protected var accel: Vector2 = new Vector2(0,0)
	
	/// Physics Methods ///
	
	def updateSimplePhysics(delta: Float) {
		vel.add(accel * delta)
		pos.add(vel * delta)
	}
	
	/// Access Methods ///
	
	def getPos() = pos
	def getVel() = vel
	def getAcces() = accel
	
	def setPos(pos: Vector2) { this.pos = pos }
	
	def stop() { vel = (0,0) }
	def resetAccel() { accel = (0,0) }
	
}