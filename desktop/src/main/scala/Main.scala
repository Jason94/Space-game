package net.space_game

import com.badlogic.gdx.backends.lwjgl._
import net.space_game
import net.space_game.conf.GraphicsConf

object Main extends App {
    val cfg = new LwjglApplicationConfiguration
    cfg.title = "SpaceGame"
    cfg.height = GraphicsConf.VIEW_HEIGHT
    cfg.width = GraphicsConf.VIEW_WIDTH
    cfg.forceExit = false
    new LwjglApplication(new SpaceGame, cfg)
}
