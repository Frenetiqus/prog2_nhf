#ifndef QUEEN_HPP
#define QUEEN_HPP

#include "Position.hpp"
#include "Piece.hpp"

class Queen : public Piece{
public:
    Queen(char color);
    ~Queen();
    bool isLegitMove(Position, Position, Piece*[8][8]);
    char getPiece() const;
};


#endif