#ifndef PLAYER_HPP
#define PLAYER_HPP

#include <iostream>

class Player{
    // std::string name;
    // char color;
    // char lastGameResult;
    // int timeOfThinking;
public:
    Player(std::string);
    ~Player();
    void setName(std::string);
    void setColor(char);
    void setLastGameResult(char);
    void addTime(int);
    std::string getName();
    char getColor();
    int getTime();
    char getLastGameResult();
    void resetParameters();
};


#endif