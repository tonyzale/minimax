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
  
  //                 Root
  // my moves    1.0      0
  // opp moves   -1.0     0.5
  // should choose "0"
  test("minimax chooses correct move") {
    class ConstEvalState(eval: Double, children: List[ConstEvalState]) extends GameState {
      def whosTurn: Minimax.PlayerId = 0
      def evalForPlayer(id: Minimax.PlayerId): Double = eval
      def uid(): Long = 0
      def validMoves: List[GameState] = children      
    }
    
    val root = new MiniMaxNode(new ConstEvalState(0.0,
        new ConstEvalState(1.0, new ConstEvalState(-1.0, Nil) :: Nil) :: new ConstEvalState(0.0, new ConstEvalState(0.5, Nil) :: Nil) :: Nil))
    assert(root.Eval(2, 0) == root.children.toVector(1).state)
  }
}
