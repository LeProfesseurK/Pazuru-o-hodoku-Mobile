package ccm.cours.nicolas.tiniki.Tools;

import ccm.cours.nicolas.tiniki.Entity.EnigmeEcrite;
import ccm.cours.nicolas.tiniki.Entity.Puzzle;
import ccm.cours.nicolas.tiniki.Entity.QCMPuzzle;

public class FabriquePuzzle {

    public static Puzzle fabriquePuzzle(String type){
        Puzzle lePuzzle = null;

        switch(type){
            case "QCM":
                lePuzzle = new QCMPuzzle();
                break;
            case "Ecrit":
                lePuzzle = new EnigmeEcrite();
                break;
        }
        return lePuzzle;
    }
}
