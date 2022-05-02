#ifndef GAME_HPP
#define GAME_HPP

#include <iostream>
#include "Player.hpp"
#include "Board.hpp"
#include "Leaderboard.hpp"

class Game{
    // Board* GameBoard;
    // Leaderboard* dicsoseglista;
    // Player* playerA;
    // Player* playerB;
private:
    void inditoMenu(std::string);
    void parameterMenu(std::string);
    void dicsoseglistaMenu(std::string);
public:
    Game(){
        // Letrehozza a jatekosokat
        // Letrehozza a dicsoseglistat es beolvassa 
    }
    ~Game();
    void run(){
        inditoMenu("Udvozollek a jatekban!");
    }
};

#endif