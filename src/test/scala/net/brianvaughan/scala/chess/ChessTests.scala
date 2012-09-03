package net.brianvaughan.scala.chess

import org.scalatest._
import org.scalatest.matchers._


class ChessSpec extends FlatSpec with ShouldMatchers {

  "A new chess baord" should "have appropriate pieces" in {
    val board = BoardState.startingBoard
    board.pieceAt(0,1) should equal (Some(Piece(PieceType.Pawn,Color.White)))

  }

  it should "throw IllegalArgumentException if an illegal move is attempted" in 
  {
    val board = BoardState.startingBoard

    evaluating { board.movePiece(0,1,1,2) } should produce [IllegalArgumentException]
  }
  
  it should "have 20 possible moves" in
  {
    val board = BoardState.startingBoard
    board.allLegalMoves.length should equal(20)

  }
  it should "find a king in the right place" in
  {
    val board = BoardState.startingBoard
    board.findKingIndex should equal( board.index(4,0) )
  }

}
