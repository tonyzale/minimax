package com.tonyzale.minimax

import org.scalatest.FunSuite
 
class MinimaxSuite extends FunSuite {  
  class TestState extends GameState {
    def evalForPlayer(id: Int): Double = 0
    def uid(): Long = 0
    def validMoves(id: Int): List[this.type] = Nil
  }
  
  test("minimax test") {
    val testState = new TestState
    val n = new MiniMaxNode(testState)
    assert(n.state == testState)
  }
}

