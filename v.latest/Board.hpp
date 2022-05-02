#ifndef BOARD_HPP
#define BOARD_HPP

#include "Piece.hpp"
#include "Player.hpp"

class Board{
    // Piece* MainGameBoard[8][8];
    // Player playerA;
    // Player playerB;
    // Player playerCurrent;
private:
    void printBoard();
    bool isInCheck(char);
    bool canMove(char);
    bool isOver();
    void playerMove();
public:
    Board(Player, Player);
    ~Board();
    void startGame();
};


#endif