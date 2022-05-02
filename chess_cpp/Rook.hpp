#ifndef ROOK_HPP
#define ROOK_HPP

#include "Position.hpp"
#include "Piece.hpp"

class Rook : public Piece{
public:
    Rook(char color);
    ~Rook();
    bool isLegitMove(Position, Position, Piece*[8][8]);
    char getPiece() const;
};


#endif