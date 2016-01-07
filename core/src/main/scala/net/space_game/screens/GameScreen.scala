package net.space_game.screens

import com.badlogic.gdx.{ScreenAdapter,Gdx}
import com.badlogic.gdx.graphics.{Camera,OrthographicCamera,Color,GL20}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.{FitViewport,Viewport}
import net.space_game.conf.GraphicsConf
import net.space_game.util.Helpers
import net.space_game.player.{PlayerShip,Starship}
import net.space_game.planets.Planet
import net.space_game.control.StarshipController
import com.badlogic.gdx.graphics.FPSLogger

class GameScreen() extends ScreenAdapter {
	
	import GraphicsConf._
	import Helpers._
	
	/// Private Instance Variables ///
	
	// Logic
	private var player: PlayerShip = null
	private var planets: List[Planet] = List()
	
	// Camera & screen
	private var viewport: Viewport = null
	private var camera: Camera = null
	
	// Drawing
	private var shapeRenderer: ShapeRenderer = null
	private var batch: SpriteBatch = null
	
	/// Screen Adapter Overrides ///
	
	override def resize(w: Int, h: Int) {
		viewport.update(w, h)
	}
	
	override def show() {
		// Camera & Screen
		camera = new OrthographicCamera()
		camera.position.set(VIEW_WIDTH / 2, VIEW_HEIGHT / 2, 0)
		camera.update()
		
		viewport = new FitViewport(VIEW_WIDTH, VIEW_HEIGHT, camera)
		
		// Sprites & Drawing
		shapeRenderer = new ShapeRenderer()
		batch = new SpriteBatch()
				
		// Game Content
		val playerStarship = new Starship()
		player = playerStarship
		player.setPos(new Vector2(100,100))
		
		planets = List(
			new Planet(1000, 25),
			new Planet(2000,50,Color.MAROON),
			new Planet(4000,100,Color.OLIVE)
		)
		
		planets(0).setPos((300,300))
		planets(1).setPos((700,100))
		planets(2).setPos((800,600))
			
		// Controls
		Gdx.input.setInputProcessor(new StarshipController(playerStarship))
	}
	
	override def render(delta: Float) {
		update(delta)
		clearScreen()
		draw()
		drawDebug()
	}
	
	/// Private Helper Functions ///
	
	// Logic
	private def update(delta: Float) {
		// Calculate the starship's acceleration anew
		player.resetAccel()
		
		// Calculate the gravity force on the player
		val netForce = planets.foldRight(new Vector2(0,0)) { (planet, tot) =>
			tot + player.calculateGravityFrom(planet)
		}
		player.applyForce(netForce)		
		
		// This done, update the player		
		player.update(delta)
	}
	
	// Drawing
	private def clearScreen() {
		Helpers.clearOneColor(Color.BLACK)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
	}
	
	private def draw() {
		batch.setProjectionMatrix(camera.projection)
		batch.setTransformMatrix(camera.view)
		batch.begin()
		batch.end()
	}
	
	private def drawDebug() {
		shapeRenderer.setProjectionMatrix(camera.projection)
		shapeRenderer.setTransformMatrix(camera.view)
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
		
		player.drawDebug(shapeRenderer)
		planets.foreach(_.drawDebug(shapeRenderer))
		
		shapeRenderer.end()
	}
}