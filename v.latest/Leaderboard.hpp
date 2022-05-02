#ifndef LEADERBOARD_HPP
#define LEADERBOARD_HPP

#include <iostream>
#include "Player.hpp"

struct Leaderboard_item{
    int position;
    int score;
    std::string name;
    Leaderboard_item* next;
};

class Leaderboard{
    // Leaderboard_item* first;
    // static std::string file_path;
private:
    int calculateScore(Player);
public:
    Leaderboard();
    ~Leaderboard();
    void add(Player);
    void readFromFile();
    void writeToFile();
    void print();
    Leaderboard_item* getPlayer(Player);
};


#endif