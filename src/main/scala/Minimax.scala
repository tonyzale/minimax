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
      def foldFunc(comp: (Double, Double) => Boolean)(a: MiniMaxNode, b: MiniMaxNode): MiniMaxNode = {
        if (comp(a.computedEval.get, b.computedEval.get)) {
          a
        } else {
          b
        }
      }
      children.map(e => if (e.computedEval.isDefined) e else new MiniMaxNode(e.state, e.Eval(depth - 1, pid).computedEval)).reduce(if (state.whosTurn == pid) foldFunc((a, b) => a > b) else foldFunc((a, b)=> a < b))
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