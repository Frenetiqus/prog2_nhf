#ifndef KNIGHT_HPP
#define KNIGHT_HPP

#include "Position.hpp"
#include "Piece.hpp"

class Knight : public Piece{
public:
    Knight(char color);
    ~Knight();
    bool isLegitMove(Position, Position, Piece*[8][8]);
    char getPiece() const;
};


#endif