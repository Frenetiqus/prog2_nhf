#ifndef PAWN_HPP
#define PAWN_HPP

#include "Position.hpp"
#include "Piece.hpp"

class Pawn : public Piece{
public:
    Pawn(char color);
    ~Pawn();
    bool isLegitMove(Position, Position, Piece*[8][8]);
    char getPiece() const;
};


#endif