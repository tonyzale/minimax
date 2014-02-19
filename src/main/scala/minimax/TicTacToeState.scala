package minimax

case class TicTacToePlayer(pid: Minimax.PlayerId)
case class SquareState(owner: Option[TicTacToePlayer]) {
  def char(): String = {
    if (owner.isDefined) owner.get.pid.toString else "-"
  }
}

object TicTacToeState {
  type Board = Vector[ Vector[SquareState] ]
  val unowned = SquareState(None)
  val emptyBoard = Vector(Vector(unowned, unowned, unowned), Vector(unowned, unowned, unowned), Vector(unowned, unowned, unowned))
  val p1 = TicTacToePlayer(0)
  val p2 = TicTacToePlayer(1)
  val startState = new TicTacToeState(p1, emptyBoard)
  def evalForPlayer(state: TicTacToeState, pid: Minimax.PlayerId): Double = {
    state.winForPlayer().map(x => if (x.pid == pid) 1.0 else -1.0).getOrElse(0.0)
  }
}

class TicTacToeState(playerTurn: TicTacToePlayer, val board: Vector[Vector[SquareState]]) extends GameState[TicTacToeState] {
  def whosTurn: Minimax.PlayerId = playerTurn.pid
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
    None
  }
  lazy val validMoves: List[TicTacToeState] = {
    if (winForPlayer.isDefined) {
      Nil
    } else {
      var moves: List[TicTacToeState] = Nil
      val nextPlayer = if (playerTurn == TicTacToeState.p1) TicTacToeState.p2 else TicTacToeState.p1
      for (i <- 0 to 2) {
    	  for (j <- 0 to 2) {
    		  if (board(i)(j).owner.isEmpty) {
    			val newBoard = board.updated(i, board(i).updated(j, SquareState(Option(playerTurn))))
    			moves = new TicTacToeState(nextPlayer, newBoard) :: moves
    		  }
    	  }
      }
      moves
    }
  }
  def uid: Long = {
    board.flatMap(_.map(_.char)).reduce((a, b) => a + b).hashCode
  }
  override def PrettyPrint = {
    board.foreach(r => {r.foreach(c => print(c.char)); println("");})
    println("***")
  }
  assert(board.length == 3)
  assert(board(0).length == 3)
  assert(board(1).length == 3)
  assert(board(2).length == 3)
}

object PlayTicTacToe extends App {
	Minimax.Play(TicTacToeState.startState, TicTacToeState.evalForPlayer)
}