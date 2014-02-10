package minimax

object Minimax {
  type PlayerId = Int
}

trait GameState {
  def whosTurn: Minimax.PlayerId
  def evalForPlayer(pid: Minimax.PlayerId): Double
  def validMoves: List[GameState]
  def uid: Long
  override def toString: String = {s"GameState Id:$uid P:$whosTurn eval:${evalForPlayer(0)}" }
}

class MiniMaxNode (val state: GameState, val computedEval: Option[Double]){
  def this(state: GameState) = this(state, None)
  lazy val children: List[MiniMaxNode] = state.validMoves.map(new MiniMaxNode(_))

  def Eval(depth: Int, pid: Minimax.PlayerId): MiniMaxNode = {
    if (depth == 0 || children.isEmpty) {
      if (computedEval.isDefined) this else new MiniMaxNode(state, Some(state.evalForPlayer(pid)))
    } else {
      def foldFunc(comp: (Double, Double) => Boolean)(a:MiniMaxNode, b:MiniMaxNode): MiniMaxNode = {
        if (a == null) {
          if (b.computedEval.isDefined) b else b.Eval(depth - 1, pid)
        } else {
          val aResult = if (a.computedEval.isDefined) a else new MiniMaxNode(a.state, a.Eval(depth - 1, pid).computedEval)
          val bResult = if (b.computedEval.isDefined) b else new MiniMaxNode(b.state, b.Eval(depth - 1, pid).computedEval)
          if (comp(aResult.computedEval.get, bResult.computedEval.get)) {
            aResult
          } else {
            bResult
          }
        }        
      }
      println(s"folding on $state with children: $children")
      val foldResult = children.fold(null)(if (state.whosTurn == pid) foldFunc((a, b) => a > b) else foldFunc((a, b)=> a < b))
      println(s"**result for $state: $foldResult")
      foldResult
    }
  }
  override def toString: String = {s"MinimaxNode $state $computedEval"}
}

class MiniMaxTree(state: GameState, pid: Minimax.PlayerId) {
  val root = new MiniMaxNode(state)
}

object Hello extends App {
	println("hi")
}