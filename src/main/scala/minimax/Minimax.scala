package minimax
import scala.annotation.tailrec

object Minimax {
  type PlayerId = Int

  @tailrec def Play(state: GameState) {
    val node = new MiniMaxNode(state)
    println(s"${state.whosTurn} Turn:")
    val nextState: GameState = node.Eval(4, state.whosTurn).state
    println("#####")
    nextState.PrettyPrint
    println("#####")
    if (!nextState.validMoves.isEmpty) Play(nextState)
  }
}

trait GameState {
  def whosTurn: Minimax.PlayerId
  def evalForPlayer(pid: Minimax.PlayerId): Double
  def validMoves: List[GameState]
  def uid: Long
  override def toString: String = {s"GameState Id:$uid P:$whosTurn eval:${evalForPlayer(0)}" }
  def PrettyPrint = println(this.toString)
}

class MiniMaxNode (val state: GameState, val computedEval: Option[Double]){
  def this(state: GameState) = this(state, None)
  lazy val children: List[MiniMaxNode] = state.validMoves.map(new MiniMaxNode(_))
  def Eval(depth: Int, pid: Minimax.PlayerId): MiniMaxNode = {
    //println(s"depth $depth eval for $pid")
    if (depth == 0 || children.isEmpty) {
      if (computedEval.isDefined) this else new MiniMaxNode(state, Some(state.evalForPlayer(pid)))
    } else {
      val l = children.map(e => if (e.computedEval.isDefined) e else new MiniMaxNode(e.state, e.Eval(depth - 1, pid).computedEval))
      // println(s"child len/vals: ${l.length} $l")
      if (state.whosTurn == pid) {
        l.maxBy(e => e.computedEval)
      } else {
        l.minBy(e => e.computedEval)
      }
    }
  }
  override def toString: String = {s"MinimaxNode $state $computedEval"}
}