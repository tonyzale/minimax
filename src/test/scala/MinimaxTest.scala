package minimax

import org.scalatest.FunSuite
//import org.junit.runner.RunWith
//import org.scalatest.junit.JUnitRunner

  class TestState(ctorCallback: ()=> Unit) extends GameState {
    def whosTurn: Minimax.PlayerId = 0
    def evalForPlayer(id: Minimax.PlayerId): Double = 0
    def uid(): Long = 0
    def validMoves: List[GameState] = new TestState(ctorCallback) :: new TestState(ctorCallback) :: Nil
    ctorCallback()
  }

//@RunWith(classOf[JUnitRunner])
class MinimaxSuite extends FunSuite {    
  test("minimax lazy child evaluation works correctly") {
    var ctorCount = 0
    val testState = new TestState(() => ctorCount += 1)
    val n = new MiniMaxNode(testState)
    assert(n.state == testState)
    assert(ctorCount == 1)
    n.Eval(0, 0)
    assert(ctorCount == 1)
    n.Eval(1, 0)
    assert(ctorCount == 3)
  }
}

object TestMain extends App {
    var ctorCount = 0
    val testState = new TestState(() => ctorCount += 1)
    val n = new MiniMaxNode(testState)
    assert(n.state == testState)
    assert(ctorCount == 1)
    n.Eval(0, 0)
    assert(ctorCount == 1)
    n.Eval(1, 0)
    assert(ctorCount == 3)
}
