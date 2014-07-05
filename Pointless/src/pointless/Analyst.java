/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pointless;

import java.util.Random;

/**
 *
 * @author Пётр
 */
public class Analyst {
    private String message;
    public enum PlayerMove { LUCKY, UNLUCKY }
    PlayerMove move;
    
    public Analyst() {
    }
    
    public String yourWord(Player victim, PlayerMove SITUATION) {
        Random sit = new Random();
        int act;
        act=sit.nextInt();
        
        if (SITUATION == PlayerMove.LUCKY) {
            switch(act) {
                case 1:
                    message = victim.getName() + "захватил ещё несколько точек своего соперника, теперь у него их аж" + victim.getpCount();
                    break;
                case 2:
                    message = victim.getName() + "подтверждает звание барона точечной империи";
                    break;
                case 3:
                    message = victim.getName() + "сорвал куш";
                    break;
                case 4:
                    message = victim.getName() + "осуществляет свой коварнейший план и захватывает" + victim.getpCount() + "точек";
                    break;
                case 5:
                    message = victim.getName() + "наконец-то сделал что-то полезное и заставил соперника потерять" + victim.getpCount() + "точек";
                    break;    
            }
        } else {
            switch(act) {
                case 1:
                    message = victim.getName() + "тратит время понапрасну";
                    break;
                case 2:
                    message = victim.getName() + "откладывает неизбежный проигрыш";
                    break;
                case 3:
                    message = victim.getName() + "только что сделал ход. Но зачем?";
                    break;
                case 4:
                    message = victim.getName() + "пытается выжать из себя максимум возможностей";
                    break;
                case 5:
                    message = victim.getName() + "совершил бессмысленный ход";
                    break;
            }
        }
        return message;
    }
    
}
