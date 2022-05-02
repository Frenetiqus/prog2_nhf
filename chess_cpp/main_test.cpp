#include "Game.hpp"
#include "Leaderboard.hpp"
#include <string>
#include "Piece.hpp"
#include "Pawn.hpp"
#include "Bishop.hpp"
#include "King.hpp"
#include "Knight.hpp"
#include "Queen.hpp"
#include "Rook.hpp"
#include "Position.hpp"

#define DOCTEST_CONFIG_IMPLEMENT_WITH_MAIN
#include "doctest.h"

using namespace std;

TEST_CASE("Jatekos osztaly tesztelese"){
    Player plr("test");
    CHECK(plr.getName() == "test");
    
    plr.setName("test2");
    CHECK(plr.getName() == "test2");

    CHECK(plr.getColor() == '0');
    CHECK(plr.getLastGameResult() == '0');
    CHECK(plr.getTime() == 0);
    plr.setColor('B');
    plr.setLastGameResult('W');
    plr.addTime(23);
    CHECK(plr.getColor() == 'B');
    CHECK(plr.getLastGameResult() == 'W');
    CHECK(plr.getTime() == 23);

    plr.resetParameters();
    CHECK(plr.getColor() == '0');
    CHECK(plr.getLastGameResult() == '0');
    CHECK(plr.getTime() == 0);
}

TEST_CASE("Dicsosegtabla mukodesenek tesztjei"){
    Leaderboard lbrd;
    Player plrA("PlayerA");
    Player plrB("PlayerB");
    // Mindket jatekos nyert de A gyorsabban ezert pontszama nagyobb lesz
    plrA.setLastGameResult('W');
    plrA.addTime(10);
    plrB.setLastGameResult('W');
    plrB.addTime(30);

    // 2 jatekos hozzaadasa
    lbrd.add(plrA);
    lbrd.add(plrB);
    Leaderboard_item* first = lbrd.getPlayer(plrA);
    Leaderboard_item* second = lbrd.getPlayer(plrB);
    CHECK(first->position == 1 && first->name == "PlayerA");
    CHECK(second->position == 2 && second->name == "PlayerB");
    CHECK(first->score > second->score);
    
    // Meg 8 jatekos hozzadasa: mind nyertek tehat felkerulnek a listara, idejuk linearisan no, es mindnek
    // nagyobb mint az A jatekose de kevesebb mint B jatekose: A elso marad es B 10. lesz
    for(int i=0; i<8; i++){
        Player new_player("Player"+to_string(i));
        new_player.setLastGameResult('W');
        new_player.addTime(i+11);
        lbrd.add(new_player);
    }

    // Rajta kell lenniuk a listan
    Leaderboard_item* plrAitem = lbrd.getPlayer(plrA);
    Leaderboard_item* plrBitem = lbrd.getPlayer(plrB);
    CHECK(plrAitem->name == "PlayerA" &&  plrAitem->position == 1);
    CHECK(plrBitem->name == "PlayerB" &&  plrBitem->position == 10);

    // Egy jatekos felvetele aminek az ideje tobb mint az utolso helyen levo jatekos ideje -> kissebb pontszam
    // ezert nem kerulhet fel a listara
    Player plrOut("PlayerOut");
    plrOut.setLastGameResult('W');
    plrOut.addTime(40);
    lbrd.add(plrOut);
    Leaderboard_item* plrOutItem = lbrd.getPlayer(plrOut);
    CHECK(plrOutItem == NULL);

    // Olyan jateko felvetele aki nem nyert -> nem kerulhet fel a listara idejetol fuggetlenul
    Player plrLoser("PlayerLoser");
    Player plrDrawer("PlayerDrawer");
    plrLoser.setLastGameResult('L');
    plrLoser.addTime(1);
    plrDrawer.setLastGameResult('D');
    plrDrawer.addTime(1);
    lbrd.add(plrLoser);
    lbrd.add(plrDrawer);
    CHECK(lbrd.getPlayer(plrLoser) == NULL);
    CHECK(lbrd.getPlayer(plrDrawer) == NULL);
}

TEST_CASE("Babuk mukodesenek tesztjei egy jatektablan"){
    // Jatektabla letrehozasa es babuk elhelyezese a tablan
    Piece* MainGameBoard[8][8];
    for (int sor = 0; sor < 8; sor++) {
        for (int oszlop = 0; oszlop < 8; oszlop++) {
            MainGameBoard[sor][oszlop] = NULL;
        }
    }
    // Fekete babuk felvetele
    for (int oszlop = 0; oszlop < 8; oszlop++) {
        MainGameBoard[6][oszlop] = new Pawn('B');
    }
    MainGameBoard[7][0] = new Rook('B');
    MainGameBoard[7][1] = new Knight('B');
    MainGameBoard[7][2] = new Bishop('B');
    MainGameBoard[7][3] = new King('B');
    MainGameBoard[7][4] = new Queen('B');
    MainGameBoard[7][5] = new Bishop('B');
    MainGameBoard[7][6] = new Knight('B');
    MainGameBoard[7][7] = new Rook('B');
    // Feher babuk felvetele
    for (int oszlop = 0; oszlop < 8; oszlop++) {
        MainGameBoard[1][oszlop] = new Pawn('W');
    }
    MainGameBoard[0][0] = new Rook('W');
    MainGameBoard[0][1] = new Knight('W');
    MainGameBoard[0][2] = new Bishop('W');
    MainGameBoard[0][3] = new King('W');
    MainGameBoard[0][4] = new Queen('W');
    MainGameBoard[0][5] = new Bishop('W');
    MainGameBoard[0][6] = new Knight('W');
    MainGameBoard[0][7] = new Rook('W');

    {
        // Parasztal valo lepes elore
        Position from(6,0), to(5,0);
        CHECK(MainGameBoard[6][0]->isLegitMove(from, to, MainGameBoard) == true);
    }
    {
        // Parasztal valo lepes atlosan nem jo
        Position from(6,0), to(5,1);
        CHECK(MainGameBoard[6][0]->isLegitMove(from, to, MainGameBoard) == false);
    }
    {
        // Parasztal lepes helyes ha atlosan masik szinu babu all
        // Egy feher paraszt adott pozicioba rakva
        MainGameBoard[5][1] = new Pawn('W');
        Position from(6,0), to(5,1);
        CHECK(MainGameBoard[6][0]->isLegitMove(from, to, MainGameBoard) == true);
        // Feher paraszt eltavolitasa
        delete MainGameBoard[5][1];
        MainGameBoard[5][1] = NULL;
    }
    {
        // Loval helyes lepes
        Position from(7,1), to1(5,2), to2(5,0);
        CHECK(MainGameBoard[7][1]->isLegitMove(from, to1, MainGameBoard) == true);
        CHECK(MainGameBoard[7][1]->isLegitMove(from, to2, MainGameBoard) == true);
    }
    {
        // Loval nem helyes lepes
        Position from(7,1), to1(5,1), to2(3,3);
        CHECK(MainGameBoard[7][1]->isLegitMove(from, to1, MainGameBoard) == false);
        CHECK(MainGameBoard[7][1]->isLegitMove(from, to2, MainGameBoard) == false);
    }
    {
        // Bastyaval nem helyes lepes mert egy gyalog blokkolja
        Position from(7,0), to(4,0);
        CHECK(MainGameBoard[7][0]->isLegitMove(from, to, MainGameBoard) == false);
    }
    {
        // Bastyaval helyes lepes ha a gyalog nincs elotte
        // Gyalog torlese
        delete MainGameBoard[6][0];
        MainGameBoard[6][0] = NULL;
        Position from(7,0), to(4,0);
        CHECK(MainGameBoard[7][0]->isLegitMove(from, to, MainGameBoard) == true);
        // Gyalog visszallitasa
        MainGameBoard[6][0] = new Pawn('B');
    }
    {
        // Kirallyal valo helytelen lepes mert gyalog blokkolja
        Position from(7,3), to(6,3);
        CHECK(MainGameBoard[7][3]->isLegitMove(from, to, MainGameBoard) == false);
    }
    {
        // Kirallyal helyes lepes ha gyalog nincs elotte
        // Gyalog torlese
        delete MainGameBoard[6][3];
        MainGameBoard[6][3] = NULL;
        Position from(7,3), to1(6,3);
        CHECK(MainGameBoard[7][3]->isLegitMove(from, to1, MainGameBoard) == true);
        // Kirallyal csak egyet lehet lepni: nem helyes lepes ha tobb mint egyet lepne
        Position to2(5,3);
        CHECK(MainGameBoard[7][3]->isLegitMove(from, to1, MainGameBoard) == false);
        // Gyalog visszahelyezese
        MainGameBoard[6][3] = new Pawn('B');
    }
    {
        // Kiralynovel valo helytelen lepes ha gyalog blokkolja
        Position from(7,4), to(6,4);
        CHECK(MainGameBoard[7][4]->isLegitMove(from, to, MainGameBoard) == false);
    }
    {
        // Kiralyno helyes mozgasai
        // Egy kiralyno elhelyezese a jatektabla kozepen
        MainGameBoard[4][4] = new Queen('B');
        Position from(4,4);
        Position to1(4,6), to2(4,2), to3(2,4), to4(5,4), to5(2,6), to6(5,3), to7(2,2), to8(5,5);
        CHECK(MainGameBoard[4][4]->isLegitMove(from, to1, MainGameBoard) == true);
        CHECK(MainGameBoard[4][4]->isLegitMove(from, to2, MainGameBoard) == true);
        CHECK(MainGameBoard[4][4]->isLegitMove(from, to3, MainGameBoard) == true);
        CHECK(MainGameBoard[4][4]->isLegitMove(from, to4, MainGameBoard) == true);
        CHECK(MainGameBoard[4][4]->isLegitMove(from, to5, MainGameBoard) == true);
        CHECK(MainGameBoard[4][4]->isLegitMove(from, to6, MainGameBoard) == true);
        CHECK(MainGameBoard[4][4]->isLegitMove(from, to7, MainGameBoard) == true);
        CHECK(MainGameBoard[4][4]->isLegitMove(from, to8, MainGameBoard) == true);
    }

}

