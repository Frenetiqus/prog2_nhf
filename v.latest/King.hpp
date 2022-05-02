#ifndef KING_HPP
#define KING_HPP

#include "Position.hpp"
#include "Piece.hpp"

class King : public Piece{
public:
    King(char color);
    ~King();
    bool isLegitMove(Position, Position, Piece*[8][8]);
    char getPiece() const;
};


#endif