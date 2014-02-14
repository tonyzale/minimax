package minimax

case class TicTacToePlayer(pid: Minimax.PlayerId)
case class SquareState(owner: Option[TicTacToePlayer]) {
  def char(): String = {
    if (owner.isDefined) owner.get.pid.toString else "-"
  }
}

object TicTacToeState {
  type Board = Vector[ Vector[SquareState] ]
  val unowned = SquareState(null)
  val emptyBoard = Vector(Vector(unowned, unowned, unowned), Vector(unowned, unowned, unowned), Vector(unowned, unowned, unowned))
  val p1 = TicTacToePlayer(0)
  val p2 = TicTacToePlayer(1)
}

class TicTacToeState(playerTurn: TicTacToePlayer, val board: Vector[Vector[SquareState]]) extends GameState {
  def whosTurn: Minimax.PlayerId = playerTurn.pid
  def evalForPlayer(pid: Minimax.PlayerId): Double = {
    winForPlayer().map(x => if (x.pid == pid) 1.0 else -1.0).getOrElse(0)
  }
  def winForPlayer(): Option[TicTacToePlayer] = {
    for (i <- 0 to 2) {
    	if (board(i)(0) == board(i)(1) && board(i)(0) == board(i)(2) && board(i)(0) != TicTacToeState.unowned) {
    		return board(i)(0).owner
    	}
    	if (board(0)(i) == board(1)(i) && board(0)(i) == board(2)(i) && board(0)(i) != TicTacToeState.unowned) {
    		return board(0)(i).owner
    	}
    }
    if (board(0)(0) == board(1)(1) && board(0)(0) == board(2)(2) && board(0)(0) != TicTacToeState.unowned) {
    	return board(0)(0).owner
    }
    if (board(0)(2) == board(1)(1) && board(0)(2) == board(2)(0) && board(0)(2) != TicTacToeState.unowned) {
    	return board(0)(2).owner
    }
    null
  }
  def validMoves: List[GameState] = Nil
  def uid: Long = {
    board.flatMap(_.map(_.char)).reduce((a, b) => a + b).hashCode
  }
  override def PrettyPrint = {
    board.foreach(r => {r.foreach(c => print(c.char)); println("");})
  }
  assert(board.length == 3)
  assert(board(0).length == 3)
  assert(board(1).length == 3)
  assert(board(2).length == 3)
  
}