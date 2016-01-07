package net.space_game.util

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2

object Helpers {

	def clearOneColor(c: Color) {
		Gdx.gl.glClearColor(c.r, c.g, c.b, c.a)
	}
	
	implicit def twoTupInt2Vector(pos: (Int,Int)): Vector2 = new Vector2(pos._1, pos._2)
	implicit def twoTupFloat2Vector(pos: (Float,Float)): Vector2 = new Vector2(pos._1, pos._2)
	
	implicit class RichVector2(v: Vector2) {
		def +(x: Float, y: Float): Vector2 = v.cpy.add(x,y)
		def +(o: Vector2): Vector2 = v.cpy.add(o)
		
		def -(o: Vector2): Vector2 = v.cpy.sub(o)
		
		def *(f: Float): Vector2 = v.cpy.scl(f)
	}
}