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
    private int pointsCount;  //количество шагов, сделанных по полю при поиске области
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
    
    /**
     * Офигительная функция.
     * Получает на вход список точек, в которых точно есть хотя бы одна замкнутая область.
     * @return та же область с убранными внешними точками, не входящими в область!!!
     */
    private void removeGarbagePoints(){
        boolean flag = true;
        int c = 0;
        while (flag){
            flag = false;
            for (int i = 0; i < districtPoints.size(); i++){
                if ( (c = countOfNeighborsInList(districtPoints, districtPoints.get(i))) <= 1 ){  //если у точки только один сосед - она определённо не область!
                    districtPoints.remove(i);
                    flag = true;
                    break;
                }
            }
        }
    }
    
    /** @return количество точек из заданного списка, с которыми данная точка имеет связь */
    private int countOfNeighborsInList(ArrayList<Point> points, Point p){
        int result = 0; //результат
        if (p != null){
            int[] old = new int[2]; //создаём массив из (i, j)
            old[0] = p.getI();
            old[1] = p.getJ();
            int[] _new = new int[2];   //создаём массив, который будет меняться
            _new[0] = p.getI();
            _new[1] = p.getJ();
            
            for (Ways value : Ways.values()) {                
                _new = Ways.getIJ(value, old);
                for (int i = 0; i < points.size(); i++){
                    if (_new[0] == points.get(i).getI() && _new[1] == points.get(i).getJ() && p != points.get(i) ){
                        result++;
                    }
                }
            }
        }
        return result;
    }
    
    /** @return количество точек, с которыми данная точка имеет связь */
    private int countOfNeighboringPoints(Point p){
        int result = 0; //результат
        if (p != null){
            int[] old = new int[2]; //создаём массив из (i, j)
            old[0] = p.getI();
            old[1] = p.getJ();
            int[] _new = new int[2];   //создаём массив, который будет меняться
            _new[0] = p.getI();
            _new[1] = p.getJ();
            
            for (Ways value : Ways.values()) {                
                _new = Ways.getIJ(value, old);
                if (checkThePoint( _new )) {
                    result++;
                }
            }
        }
        return result;
    }
    
    /**функция проверяет массив посещённых точек и сообщает, была ли конкретная точка посещена */
    private boolean isPointVisited(Point p){
        for (int i = 0; i < visitedPoints.size(); i++){
            if (p == visitedPoints.get(i)) return true;
        }
        return false;
    }
    
    /**
     * 
     * @return array [0..3]:
     * [0] = минимальное по вертикали
     * [1] = минимальное по горизонтали
     * [2] = максимальное по вертикали
     * [3] = максимальное по горизонтали
     */
    private int[] getCoordArray(){
        int[] result = new int[4];
        result[0] = field.getVerticalPointCount();
        result[1] = field.getHorizontalPointCount();
        result[2] = 0;
        result[3] = 0;
        Point p;
        
        for (Point districtPoint : districtPoints) {
            p = districtPoint;
            if (p.getI() < result[0]) result[0] = p.getI();
            if (p.getJ() < result[1]) result[1] = p.getJ();
            if (p.getI() > result[2]) result[2] = p.getI();
            if (p.getJ() > result[3]) result[3] = p.getJ();
        }
        
        return result;
    }
    
    /** 
     * Проверяет, является 
     */
    
    /** Самая замечательная функция :3 
     *  Вычисляет по готовой области, подходят ли точки.
     */
    private int checkCapturedPoints(){
        int result = 0;  //количество точек, захваченных активным игроком
        int[] coord = new int[4], lengthI, lengthJ;
        
        coord = getCoordArray();
        
        //няшный трёхмерный массив :3
        //[N, B, R, T, L]
        
        ArrayList<ArrayList<ArrayList<Boolean>>> boolPoints = new ArrayList<ArrayList<ArrayList<Boolean>>>();
        for (int i = 0; i < coord[2] - coord[0]; i++){
            boolPoints.add(new ArrayList<ArrayList<Boolean>>());
            for (int j = 0; j < coord[3] - coord[1]; j++){
                boolPoints.get(i).add(new ArrayList<Boolean>());
                for (int k = 0; k < 5; k++){
                    boolPoints.get(i).get(j).add(new Boolean(false));
                }
            }
        }
        
        int i = coord[0];
        int j = coord[1];
        Point p;
        
        while (i < coord[2] + 1){
            while (j < coord[3] + 1){
                p = field.getPoints().get(i).get(j);
                if (!boolPoints.get(i - coord[0]).get(j - coord[1]).get(0)){//проверяем, нужно ли вообще трогать точку
                    if (p.getHostPlayer() != Point.HostPlayer.Free && p.getHostPlayer() != activePlayer && p.getPointState() == Point.PointState.ACTIVE){  //если точка нам в принципе интересна
                         
                        
                    } else boolPoints.get(i - coord[0]).get(j - coord[1]).set(0, Boolean.TRUE);  //устанавливаем флаг непригодности
                } else
                {
                    if (p.getIsCounted() && )
                }
                j++;
            }
            
            i++;
        }
        
        return result;
    }
    
    /** алгоритм нахождения всех замкнутных областей области */
    private void districtBackTrack(int i, int j, Ways lastWay){
        //if (!isOblast){
            
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
                        isOblast = true;    //если мы вернулись в первую точку по циклу
                        break;
                    } else if (!isPointVisited(field.getPoints().get(_new[0]).get(_new[1]))) { //если мы не в первой точке, но следующей точки ещё не было)
                        visitedPoints.add(field.getPoints().get(_new[0]).get(_new[1]));
                        districtPoints.add(field.getPoints().get(_new[0]).get(_new[1]));
                        districtBackTrack(_new[0], _new[1], Ways.invertWay(value));
                    } else {
                        boolean b = true;
                    }
                }
            }
            
        //}
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
                            if (isOblast){                                              //если область была найдена
                                removeGarbagePoints();                      //удаляем все точки, не относящиеся к области
                                this.field.createNewDistrict(new District(1, districtPoints)); //создаём новое поле 1 игрока из полученных точек
                            }    
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
                            if (isOblast){                                              //если область была найдена
                                removeGarbagePoints();                      //удаляем все точки, не относящиеся к области
                                this.field.createNewDistrict(new District(2, districtPoints)); //создаём новое поле 1 игрока из полученных точек
                            }
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
    
    public int countOfCapturedPoints(){
        return 0;
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
