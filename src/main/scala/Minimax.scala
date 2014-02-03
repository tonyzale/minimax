package minimax

object Minimax {
  type PlayerId = Int
}

trait GameState {
  def whosTurn: Minimax.PlayerId
  def evalForPlayer(pid: Minimax.PlayerId): Double
  def validMoves: List[GameState]
  def uid: Long
}

class MiniMaxNode (val state: GameState){
  lazy val children: List[MiniMaxNode] = state.validMoves.map(new MiniMaxNode(_))
  def Eval(depth: Int, pid: Minimax.PlayerId): Double = {
    if (depth == 0) state.evalForPlayer(pid)
    // TODO: fix double eval of winning state
    else if (state.whosTurn == pid) {
      children.maxBy(_.Eval(depth - 1, pid)).state.evalForPlayer(pid)
    } else {
      children.minBy(_.Eval(depth - 1, pid)).state.evalForPlayer(pid)
    }
  }
}

class MiniMaxTree(state: GameState, pid: Minimax.PlayerId) {
  val root = new MiniMaxNode(state)
}

object Hello extends App {
	println("hi")
}