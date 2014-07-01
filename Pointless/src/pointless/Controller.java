/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pointless;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Leha
 */
public class Controller {
    private boolean gameStart;
    private Player P1, P2;
    private Field field;
    
    /** Ход игрока. PlayerId указывается в main */
    public boolean action(int playerId, float pX, float pY, int r) {
        Point tmp = pointCheck(pX,pY,r);
        if (tmp != null) {
            switch (playerId){
                    case 1: {
                        getField().changePoint(tmp, Point.HostPlayer.Player1, Point.PointState.ACTIVE);
                        break;
                    }
                    case 2: {
                        getField().changePoint(tmp, Point.HostPlayer.Player2, Point.PointState.ACTIVE);
                        break;
                    }
                    default: {
                        //gg
                        break;
                    }
            }
            return true;
        }
        else {
            return false;
        }
 
    }
    
    public Point pointCheck (float pX, float pY, int r) {
        Point temp = getField().getPointByCoor(pX, pY, r);
        if (temp != null)
            if (temp.getPointState() == Point.PointState.EMPTY) return temp;
        return null;
    }
    
    /** проверка на образование области 
    * здесь же создаётся "обводка"*/
    public boolean districtCheck(int pX, int pY) {
        return true;
    }
    
    /** подсчёт точек игрока с номером playerId
     * в каком классе лучше делать подсчёт? */
    public int pCount (int playerId, District countDistrict) {
        return 0;      
    }
    // каким образом делать массив пограничных точек?
    
    
    /** Инициализация игры. Передаёт данные для создания поля. Если поле создано, то создаёт игроков.
     * @return можно ли начинать игру */
    public boolean initGame(int width, int height, int lineSize, Graphics g, Color p1, Color p2, Color fieldColor, String name1, String name2) {
                
        this.setField(new Field());
        gameStart = this.getField().initializeGame(width, height, lineSize, p1, p2, fieldColor, g);
        
        if (gameStart) {
            this.P1 = new Player(name1);
            this.P2 = new Player(name2);
        }
        
        return gameStart;
    }

    /**
     * @return the field
     */
    public Field getField() {
        return field;
    }

    /**
     * @param field the field to set
     */
    public void setField(Field field) {
        this.field = field;
    }
    
    
}
