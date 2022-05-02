#ifndef BISHOP_HPP
#define BISHOP_HPP

#include "Position.hpp"
#include "Piece.hpp"

class Bishop : public Piece{
public:
    Bishop(char color);
    ~Bishop();
    bool isLegitMove(Position, Position, Piece*[8][8]);
    char getPiece() const;
};


#endif