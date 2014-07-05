/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pointless;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leha
 */
public class Controller {
    private boolean gameStart;
    private Player P1, P2;
    private Field field;
    
    /**переменные, использующиеся только при поиске замкнутой области */
    private int activeNumberI, activeNumberJ; //координаты точки в массиве, в которую была поставлена точка
    private ArrayList<Point> districtPoints; //Массив точек - кандидатов на замкнутую область
    private ArrayList<Point> visitedPoints; //Массив посещённых точек
    private Point.HostPlayer activePlayer;  //Игрок, сделавший ход
    private int stepCount;  //количество шагов, сделанных по полю при поиске области
    boolean isOblast; //есл и к концу цикла он останется false - мы в тупике.
    //если tempI = activeNumberI && tempJ = activeNumberJ - всё збс
    enum Ways {
        T, R, B, L, TR, BR, BL, TL;
        public static int[] getIJ(Ways w, int[] in){ //переводит константу enum в массив из (i, j)
            int[] r = new int[2];
            r[0] = in[0];
            r[1] = in[1];
            switch (w){
                case T: r[0] += -1; r[1] += 0; break;
                case R: r[0] += 0; r[1] += 1; break;
                case B: r[0] += 1; r[1] += 0; break;
                case L: r[0] += 0; r[1] += -1; break;
                    
                case TR: r[0] += -1; r[1] += 1; break;
                case BR: r[0] += 1; r[1] += 1; break;
                case BL: r[0] += 1; r[1] += -1; break;
                case TL: r[0] += -1; r[1] += -1; break;
                    
                default: break;
            }
            return r;
        }
        
        public static Ways invertWay(Ways normal){
            if (normal == null) return null;
            Ways invert;
            switch (normal){
                case T: invert = Ways.B; break;
                case R: invert = Ways.L; break;
                case B: invert = Ways.T; break;
                case L: invert = Ways.R; break;
                    
                case TR: invert = Ways.BL; break;
                case BR: invert = Ways.TL; break;
                case BL: invert = Ways.TR; break;
                case TL: invert = Ways.BR; break;
                    
                default: invert = null; break; //wtf?!
            }
            
            return invert;
        }
    }
    /*
        T = (i - 1, j)
        R = (i, j +1)
        B = (i + 1, j)
        L = (i, j -1)
        
        TR = (i - 1, j + 1)
        BR = (i + 1, j + 1)
        BL = (i + 1, j - 1)
        TL = (i - 1, j - 1)
    */
    
    /**функция проверяет массив посещённых точек и сообщает, была ли конкретная точка посещена */
    private boolean isPointVisited(Point p){
        for (int i = 0; i < visitedPoints.size(); i++){
            if (p == visitedPoints.get(i)) return true;
        }
        return false;
    }
    
    /** алгоритм нахождения ЛЮБОЙ замкнутой области */
    private void districtBackTrack(int i, int j, Ways lastWay){
        if (!isOblast){
            
            int[] old = new int[2]; //создаём массив из (i, j)
            old[0] = i;
            old[1] = j;
            int[] _new = new int[2];   //создаём массив, который будет меняться
            _new[0] = i;
            _new[1] = j;
            
            if ( lastWay == null  ){
                districtPoints.add(field.getPoints().get(i).get(j));
                visitedPoints.add(field.getPoints().get(i).get(j));
            }
            
            for (Ways value : Ways.values()) {
                _new = Ways.getIJ(value, old);
                if (checkThePoint( _new ) && lastWay != value) {
                    //если точка по очередному направлению доступна для движения и это не путь назад
                    //isReturn = true;
                    //if (Ways.getIJ( Ways.values()[k], t )[0] == activeNumberI && Ways.getIJ( Ways.values()[k], t )[1] ==  activeNumberJ && lastWay != null && lastWay != Ways.invertWay(lastWay)){ //если пришли к началу пути
                    //    break;
                    //}
                    //JOptionPane.showMessageDialog(null,"("+i+", "+j+")");
                    if ((_new[0] == activeNumberI && _new[1] == activeNumberJ) && (Ways.getIJ( lastWay, old )[0] != activeNumberI || Ways.getIJ( lastWay, old )[1] != activeNumberJ)) {
                        isOblast = true;
                        break;
                    } else if (!isPointVisited(field.getPoints().get(_new[0]).get(_new[1]))) {
                        districtPoints.add(field.getPoints().get(_new[0]).get(_new[1]));
                        visitedPoints.add(field.getPoints().get(_new[0]).get(_new[1]));
                        districtBackTrack(_new[0], _new[1], Ways.invertWay(value));
                    } else {
                        boolean b = true;
                    }
                }
            }
            
        }
    }
    
    
    /** метод проверяет, может ли точка быть частью замкнутой области */
    private boolean checkThePoint(int[] v){
        if (v[1] < field.getHorizontalPointCount() && v[1] >= 0 && v[0] < field.getVerticalPointCount() && v[0] >= 0){ //проверка выхода координат за границы
            Point p = field.getPoints().get(v[0]).get(v[1]);
            return (p.getHostPlayer() == this.activePlayer && p.getPointState() == Point.PointState.ACTIVE); //проверка прочих параметров (точка активна и принадлежит игроку activePlayer
        }
        return false;
    }
    
    /** Ход игрока. PlayerId указывается в main */
    public boolean action(int playerId, float pX, float pY) {
        Point tmp = pointCheck(pX,pY);
        if (tmp != null) {
            this.activeNumberI = tmp.getI();    //сохраняем индексы
            this.activeNumberJ = tmp.getJ();    //нажатой точки
            switch (playerId){
                    case 1: {
                        getField().changePoint(tmp, Point.HostPlayer.Player1, Point.PointState.ACTIVE); //изменяем статус точки (принадл. 1 игроку)
                        P1.setStepNumber(P1.getStepNumber() + 1);       //увеличиваем число ходов 1 игрока 
                        activePlayer = Point.HostPlayer.Player1;        //запоминаем игрока, сделавшего последний ход.
                        if (P1.getStepNumber() >= 4){
                            visitedPoints = new ArrayList<Point>();     //создаём пустой массив, в котором будут посещённые точки
                            districtPoints = new ArrayList<Point>();    //создаём точки под замкнутую область
                            isOblast = false;
                            this.districtBackTrack(activeNumberI, activeNumberJ, null);  //вызов back-track функции
                            if (isOblast)
                                this.field.createNewDistrict(new District(1, districtPoints)); //создаём новое поле 1 игрока из полученных точек
                        }
                        break;
                    }
                    case 2: {
                        getField().changePoint(tmp, Point.HostPlayer.Player2, Point.PointState.ACTIVE); //изменяем статус точки (принадл. 2 игроку)
                        P2.setStepNumber(P2.getStepNumber() + 1);       //увеличиваем число ходов 2 игрока 
                        activePlayer = Point.HostPlayer.Player2;        //запоминаем игрока, сделавшего последний ход.
                        if (P2.getStepNumber() >= 4){
                            visitedPoints = new ArrayList<Point>();     //создаём пустой массив, в котором будут посещённые точки
                            districtPoints = new ArrayList<Point>();
                            isOblast = false;
                            this.districtBackTrack(activeNumberI, activeNumberJ, null); //вызов back-track функции
                            if (isOblast)
                                this.field.createNewDistrict(new District(2, districtPoints)); //создаём новое поле 2 игрока из полученных точек
                        }
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
    
    public Point pointCheck (float pX, float pY) {
        Point temp = this.getField().getPointByCoor(pX, pY);
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
    public boolean initGame(int width, int height, int lineSize, int notActiveRadius, int activeRadius, Graphics g, Color p1, Color p2, Color fieldColor, String name1, String name2) {
                
        this.setField(new Field());
        gameStart = this.getField().initializeGame(width, height, lineSize, p1, p2, fieldColor, g, notActiveRadius, activeRadius);
        
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
