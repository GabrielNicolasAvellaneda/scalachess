package net.brianvaughan.scala.chess

import org.scalatest._
import org.scalatest.matchers._


class ChessSpec extends FlatSpec with ShouldMatchers {
  "A newly created default chess board" should "have appropriate pieces" in {
    val board = BoardState.startingBoard
    board.pieceAt(0,1) should equal (Some(Pawn(Color.White)))

  }

  it should "throw IllegalArgumentException if an illegal move is attempted" in 
  {
    val board = BoardState.startingBoard

    evaluating { 
      board.movePiece(0,1,1,2) 
    } should produce [IllegalArgumentException]
  }

  it should "throw Illegal Argument exception if an opponents piece is moved" in
  {
    val board = BoardState.startingBoard
    evaluating { 
      board.movePiece(0,6,0,5) 
    } should produce [IllegalArgumentException]
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

  "a particular game of chess" should "detect check, but not check mate" in {
    var board = BoardState.startingBoard
    board = board.movePiece(1,0,0,2)//knight to a3
    board = board.movePiece(0,6,0,5)//pawn to a6
    board = board.movePiece(0,2,1,4)//knight to b5
    board = board.movePiece(1,6,1,5)//another random pawn
    board = board.movePiece(1,4,2,6)//check!
    
    board.inCheck should equal(true)
    board.inCheckMate should equal(false)

  }

  "a foolsmate game" should "be detected as checkmate" in {
    var board = BoardState.startingBoard
    board = board.movePiece(4,1,4,3)//e4
    board = board.movePiece(4,6,4,4)//e5
    board = board.movePiece(5,0,2,3)//bishop to c4
    board = board.movePiece(5,7,2,4)//mimicking again
    board = board.movePiece(3,0,5,2)//queen to f3!
    board = board.movePiece(0,6,0,5)//a6
    board = board.movePiece(5,2,5,6)//checkmate!
    
    board.inCheckMate should equal(true)
  }


}




