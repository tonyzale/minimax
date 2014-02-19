package minimax
import scala.annotation.tailrec

object Minimax {
  type PlayerId = Int

  @tailrec def Play[T <: GameState[T]](state: T, evalFunc: (T, Minimax.PlayerId) => Double) {
    val node = new MiniMaxNode(state.whosTurn, state, 4, evalFunc)
    println(s"${state.whosTurn} Turn:")
    val nextState: T = node.eval._1
    println("#####")
    nextState.PrettyPrint
    println("#####")
    if (!nextState.validMoves.isEmpty) Play(nextState, evalFunc)
  }
}

trait GameState[T <: GameState[T]] {
  def whosTurn: Minimax.PlayerId
  def validMoves: List[T]
  def uid: Long
  override def toString: String = {s"{Id:$uid P:$whosTurn}" }
  def PrettyPrint = println(this.toString)
}

class MiniMaxNode[T <: GameState[T]] (val pid: Minimax.PlayerId, val state: T, val depth: Int, val evalFunc: (T, Minimax.PlayerId) => Double){
  lazy val children: List[MiniMaxNode[T]] = state.validMoves.map(c => new MiniMaxNode(pid, c, depth - 1, evalFunc))
  lazy val eval: (T, Double) = {
    if (depth == 0 || children.isEmpty) {
      (state, evalFunc(state, pid))
    } else {
      val winningChild = if (state.whosTurn == pid) children.maxBy(_.eval._2) else children.minBy(_.eval._2)
      (winningChild.state, winningChild.eval._2)
    }
  }
  override def toString: String = {s"[MinimaxNode $state $eval]"}
}