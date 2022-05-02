#ifndef PIECE_HPP
#define PIECE_HPP

#include "Position.hpp"

class Piece{
    //char color;
public:
    Piece(char color);
    virtual ~Piece();
    virtual bool isLegitMove(Position, Position, Piece*[8][8])=0;
    virtual char getPiece() const =0;
    char getColor() const;
};


#endif