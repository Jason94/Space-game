package net.space_game.physics

import com.badlogic.gdx.math.Vector2
import net.space_game.util.Helpers
import net.space_game.conf.PhysicsConf

trait MassObject extends PhysicsObject {
  
	import Helpers._
	import PhysicsConf._
	
	val mass: Float
	
	/// Mass Physics Methods ///
	def calculateGravityFrom(o: MassObject): Force = {
		val r = pos.dst(o.pos)
		val norm = G * mass * o.mass / (r*r)
		(pos - o.pos).nor() * -norm
	}
	
	def applyForce(force: Force) {
		accel.add(force * (1/mass))
	}
}