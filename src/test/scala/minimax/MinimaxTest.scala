package minimax

import org.scalatest.FunSuite

  class TestState(ctorCallback: ()=> Unit) extends GameState[TestState] {
    def whosTurn: Minimax.PlayerId = 0
    def uid(): Long = 0
    def validMoves: List[TestState] = List(new TestState(ctorCallback), new TestState(ctorCallback))
    ctorCallback()
  }

class MinimaxSuite extends FunSuite {
  test("minimax lazy child evaluation works correctly") {
    var ctorCount = 0
    val testState = new TestState(() => ctorCount += 1)
    val n = new MiniMaxNode(0, testState, 0, (s: TestState, pid:Minimax.PlayerId) => 0.0)
    assert(n.state == testState)
    assert(ctorCount == 1)
    assert(n.eval._2 == 0.0)
    assert(ctorCount == 1)
    val n2 = new MiniMaxNode(0, testState, 1, (s: TestState, pid:Minimax.PlayerId) => 0.0)
    assert(n2.eval._2 == 0.0)
    assert(ctorCount == 3)
  }

  var constEvalStateCount = 0
      class ConstEvalState(playerTurn: Minimax.PlayerId, val eval: Double, var children: List[ConstEvalState]) extends GameState[ConstEvalState] {
      def this(playerTurn: Minimax.PlayerId, eval: Double) = this(playerTurn, eval, Nil)
      def this(playerTurn: Minimax.PlayerId) = this(playerTurn, 0.0, Nil)
      def whosTurn: Minimax.PlayerId = playerTurn
      def uid(): Long = myId
      def validMoves: List[ConstEvalState] = children   
      val myId = constEvalStateCount
      constEvalStateCount = constEvalStateCount + 1
      override def toString: String = {s"{Id:$uid P:$whosTurn eval:$eval}" }
    }
  
  //                 Root
  // my moves    1.0      0
  // opp moves   -1.0     0.5
  // should choose "0"

  test("simple minimax chooses correct move") {    
    val rootState = new ConstEvalState(0)
    val a1 = new ConstEvalState(1, 1.0, List(new ConstEvalState(0, -1.0)))
    val a2 = new ConstEvalState(1, -2.0, List(new ConstEvalState(0, 0.5)))
    rootState.children = List(a1, a2)
    val root = new MiniMaxNode(0, rootState, 2, (s: ConstEvalState, pid: Minimax.PlayerId) => s.eval)
    assert(root.eval._1 == a2)
    assert(root.eval._2 == 0.5)
  }

  test("minimax solves wikipedia example") {
    val root2 = new ConstEvalState(0)
    val l1c = List.fill(2)(new ConstEvalState(1))
    root2.children = l1c
    val l2c = List.fill(4)(new ConstEvalState(0))
    l1c(0).children = l2c.take(2)
    l1c(1).children = l2c.drop(2)
    val l3c = List.fill(6)(new ConstEvalState(1))
    l2c(0).children = l3c.take(2)
    l2c(1).children = l3c.slice(2, 3)
    l2c(2).children = l3c.slice(3, 5)
    l2c(3).children = l3c.slice(5, 6)
    val l4c = List(new ConstEvalState(0, 10), new ConstEvalState(0, 1000), new ConstEvalState(0, 5), new ConstEvalState(0, -10),
                              new ConstEvalState(0, 7), new ConstEvalState(0, 5), new ConstEvalState(0, -1000), new ConstEvalState(0, -7), new ConstEvalState(0, -5))
    l3c(0).children = l4c.take(2)
    l3c(1).children = List(l4c(2))
    l3c(2).children = List(l4c(3))
    l3c(3).children = l4c.slice(4, 6)
    l3c(4).children = List(l4c(6))
    l3c(5).children = l4c.takeRight(2)
    val rootEval = new MiniMaxNode(0, root2, 4, (s: ConstEvalState, pid: Minimax.PlayerId) => s.eval).eval
    assert(rootEval._1 == l1c(1))
    assert(rootEval._2 == -7)
  }
  
  test("tic tac toe") {
    val state = new TicTacToeState(new TicTacToePlayer(0), TicTacToeState.emptyBoard)
    state.PrettyPrint
    state.validMoves.foreach(_.PrettyPrint)
    println("CHILD MOVES")
    state.validMoves(0).validMoves.foreach(_.PrettyPrint)
  }
}
