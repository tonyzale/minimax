package com.tonyzale.minimax

trait GameState {
  def evalForPlayer(player: Int): Double
  def validMoves(player: Int): List[this.type]
  def uid: Long
}

class MiniMaxNode (val state: GameState){
  val children: List[MiniMaxNode] = Nil
  
}

object Hello extends App {
	println("hi")
}