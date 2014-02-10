package minimax

object Minimax {
  type PlayerId = Int
}

trait GameState {
  def whosTurn: Minimax.PlayerId
  def evalForPlayer(pid: Minimax.PlayerId): Double
  def validMoves: List[GameState]
  def uid: Long
  override def toString: String = {s"GameState Id $uid $whosTurn ${evalForPlayer(0)}" }
}

class MiniMaxNode (val state: GameState){
  lazy val children: List[MiniMaxNode] = state.validMoves.map(new MiniMaxNode(_))
  lazy val childrenWithEval: List[(MiniMaxNode, Double)] = state.validMoves.map(el => (new MiniMaxNode(el), 0.0))
  def Eval(depth: Int, pid: Minimax.PlayerId): (GameState, Double) = {
    if (depth == 0) {
      println("depth 0 with: " + state)
      (state, state.evalForPlayer(pid))
    } else if (state.whosTurn == pid) {
      println(s"max eval of $state")
      println(s"children $childrenWithEval")
      //val result = children.maxBy(_.Eval(depth - 1, pid)._2)
      
      val foldResult = childrenWithEval.fold((null, 0.0))((a, b) => {
        if (a._1 == null) b
        else {
          val aResult = a._1.Eval(depth - 1, pid)
          val bResult = b._1.Eval(depth - 1, pid)
          if (aResult._2 > bResult._2) {
            (a._1, aResult._2)
          } else {
            (b._1, bResult._2)
          }
        }
      })
      
      println(s"**result for $state : $foldResult")
      (foldResult._1.state, foldResult._2)
    } else {
      println(s"min eval of $state")
      println(s"children childrenWithEval")
      //val result = children.minBy(_.Eval(depth - 1, pid)._2).state
      
      val foldResult = childrenWithEval.fold((null, 0.0))((a, b) => {
        if (a._1 == null) b
        else {
          val aResult = a._1.Eval(depth - 1, pid)
          val bResult = b._1.Eval(depth - 1, pid)
          if (aResult._2 < bResult._2) {
            (a._1, aResult._2)
          } else {
            (b._1, bResult._2)
          }
        }
      })
      
      println(s"**result for $state : $foldResult")
      (foldResult._1.state, foldResult._2)
    }
  }
  override def toString: String = {s"Node w/ $state"}
}

class MiniMaxTree(state: GameState, pid: Minimax.PlayerId) {
  val root = new MiniMaxNode(state)
}

object Hello extends App {
	println("hi")
}