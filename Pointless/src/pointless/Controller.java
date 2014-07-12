/**
 * Класс Контроллер. Основной класс, отвечает за весь процесс игры.
 * Хранит данные об игроках, а также экземпляр Поля.
 */

package pointless;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Leha
 */
public class Controller {
    //==== ДАННЫЕ КЛАССА ==== \\
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
    private Analyst.PlayerMove lastStep; //результат последнего состоявшегося хода игрока
    //==== ДАННЫЕ КЛАССА ==== \\
    
    
    //==== МОДИФИКАТОРЫ И СЕЛЕКТОРЫ ==== \\
    /**
     * @return the P1
     */
    public Player getP1() {
        return P1;
    }
    /**
     * @return the P2
     */
    public Player getP2() {
        return P2;
    }
    /**
     * @return the lastStep
     */
    public Analyst.PlayerMove getLastStep() {
        return lastStep;
    }
    /**
     * @param lastStep the lastStep to set
     */
    public void setLastStep(Analyst.PlayerMove lastStep) {
        this.lastStep = lastStep;
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
    //==== МОДИФИКАТОРЫ И СЕЛЕКТОРЫ ==== \\
    
    
    //Класс Ways, характеризующий направления движения
    enum Ways {
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
    
    
    //==== ОСНОВНЫЕ МЕТОДЫ ==== \\
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
    /** @return количество точек, с которыми данная точка имеет связь  на поле точек */
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
     * Проверяет, входит ли текущая точка в массив границ
     */
    private boolean isPointInDistrict(Point p){
        if (p != null){
            for (Point districtPoint : districtPoints) {
                //может, лучше сравнивать объекты?
                if (p.getI() == districtPoint.getI() && p.getJ() == districtPoint.getJ()) {
                    return true;
                }
            }
        }
        return false;
    }
    /** Самая замечательная функция :3 
     *  Вычисляет по готовой области, подходят ли точки.
     */
    private boolean checkCapturedPoints(){
        int plusPoints = 0, minusPoints = 0;  //количество точек, захваченных активным игроком
        int[] coord = new int[4], lengthI, lengthJ;
        
        coord = getCoordArray();
        
        //няшный трёхмерный массив :3
        //[N, B, R, T, L, QR, QB]
        
        ArrayList<ArrayList<ArrayList<Boolean>>> boolPoints = new ArrayList<ArrayList<ArrayList<Boolean>>>();
        for (int i = 0; i < coord[2] - coord[0]+1; i++){
            boolPoints.add(new ArrayList<ArrayList<Boolean>>());
            for (int j = 0; j < coord[3] - coord[1]+1; j++){
                boolPoints.get(i).add(new ArrayList<Boolean>());
                for (int k = 0; k < 7; k++){
                    boolPoints.get(i).get(j).add(new Boolean(false));
                }
            }
        }
        
        int i = coord[0];
        int j = coord[1];
        int bool_i, bool_j;
        Point p;
        
        while (i < coord[2] + 1){
            j = coord[1];
            while (j < coord[3] + 1){
                bool_i = i - coord[0]; //координата i для булевского массива
                bool_j = j - coord[1]; //координата j для булевского массива
                p = field.getPoints().get(i).get(j);
                    
                        //ОБЯЗАТЕЛЬНАЯ проверка верхней и левой границ
                        if (!isPointInDistrict(p)){//если точка сама не граница
                            
                            //проверка ЛЕВОЙ ГРАНИЦЫ
                            if (j != coord[1]){//если точка не крайняя слева
                                if (!isPointInDistrict(field.getPoints().get(i).get(j-1))){ //если соседняя слева точка не граница
                                    boolPoints.get(bool_i).get(bool_j).set(4, boolPoints.get(bool_i).get(bool_j - 1).get(4)); //устанавливаем этой точке такой же флаг L, как у предыдущей
                                    if (!boolPoints.get(bool_i).get(bool_j).get(4)) { boolPoints.get(bool_i).get(bool_j).set(0, Boolean.TRUE); } //если L = false, то N = true - тогда можно переходить к следующей точке
                                } else //соседняя слева точка - граница, сама точка не крайняя слева и не граница
                                {
                                    boolPoints.get(bool_i).get(bool_j).set(4, Boolean.TRUE); //граница слева найдена - ставим True
                                }
                            } else //крайняя точка слева, но не граница
                            {
                                boolPoints.get(bool_i).get(bool_j).set(0, Boolean.TRUE); //Значит, у точки нет границы слева!
                            }
                            
                            //проверка ВЕРХНЕЙ ГРАНИЦЫ
                            if (i != coord[0]) { //если точка не крайняя сверху
                                if (!isPointInDistrict(field.getPoints().get(i-1).get(j))){ //если соседняя сверху точка не граница
                                    boolPoints.get(bool_i).get(bool_j).set(3, boolPoints.get(bool_i - 1).get(bool_j).get(3)); //устанавливаем этой точке такой же флаг T, как у предыдущей
                                    if (!boolPoints.get(bool_i).get(bool_j).get(3)) { boolPoints.get(bool_i).get(bool_j).set(0, Boolean.TRUE); } //если T = false, то N = true - тогда можно переходить к следующей точке
                                } else //соседняя сверху точка - граница, сама точка не крайняя сверху и не граница
                                {
                                    boolPoints.get(bool_i).get(bool_j).set(3, Boolean.TRUE); //граница сверху найдена - ставим True
                                }
                            } else //крайняя точка сверху, но не граница
                            {
                                boolPoints.get(bool_i).get(bool_j).set(0, Boolean.TRUE); //Значит, у точки нет границы сверху!
                            }
                            
                        } else //точка граница
                        {   //а не нужно ли для таких точек искать границы, как и для остальных, чтоб все знали?)
                            boolPoints.get(bool_i).get(bool_j).set(0, Boolean.TRUE); //Обозначаем, что нам не нужно её проверять - она граница!
                        }
                        
                        //проверили верхнюю и левую границу - обязательные проверки.
                        //если выясняется, что точка не имеет какой-либо из этих границ
                        //или сама граница - переходим к следующей точке
                        
                        //проверка ПРАВОЙ ГРАНИЦЫ
                        if (boolPoints.get(bool_i).get(bool_j).get(0).booleanValue() == false){ //если N не установлен
                            
                            //проверка правой границы
                            if (boolPoints.get(bool_i).get(bool_j).get(2).booleanValue() == true) { //если есть правая граница
                                // выходим, правая граница есть
                            } 
                            else {
                                if (boolPoints.get(bool_i).get(bool_j).get(5).booleanValue() == true) { // если правой границы нет и в этой точке мы уже были
                                    // выходим, так как правой границы нет
                                    boolPoints.get(bool_i).get(bool_j).set(0, Boolean.TRUE);
                                }
                                else { // если правой границы нет и в точке ещё не были
                                    if ( j == coord[3] ){ //если точка последняя справа и она не граница - у неё нет шансов.
                                        boolPoints.get(bool_i).get(bool_j).set(0, Boolean.TRUE);
                                    } else {
                                        int temp = 0;
                                        for (int k = bool_j; k < (coord[3]-coord[1] + 1); k++) {
                                            boolPoints.get(bool_i).get(k).set(5, Boolean.TRUE); // помечаем текующую точку как точку, в которой мы уже были
                                            if (isPointInDistrict(field.getPoints().get(i).get(k+coord[1]))) { // true если граница
                                                temp = k;
                                                break;
                                            }
                                            else { //иначе не граница
                                            
                                            }
                                        }
                                        if (temp !=0) {
                                            for (int l = bool_j; l < temp; l++) {
                                                boolPoints.get(bool_i).get(l).set(2, Boolean.TRUE); // расставляем флаг R=true до границы
                                            }
                                        }
                                    }  
                                }
                            }
                        }
                        
                        //проверка НИЖНЕЙ ГРАНИЦЫ
                        if (boolPoints.get(bool_i).get(bool_j).get(0).booleanValue() == false){ //если N не установлен
                            
                            //проверка нижней границы
                            if (boolPoints.get(bool_i).get(bool_j).get(1).booleanValue() == true) { //если есть нижняя граница
                                // выходим, нижняя граница есть
                            } 
                            else {
                                if (boolPoints.get(bool_i).get(bool_j).get(6).booleanValue() == true) { // если нижней границы нет и в этой точке мы уже были
                                    // выходим, так как нижней границы нет
                                    boolPoints.get(bool_i).get(bool_j).set(0, Boolean.TRUE); //ставим N = true (точка бесполезна)
                                }
                                else { // если нижней границы нет и в точке ещё не были
                                    if (i == coord[2]){ //если точка крайняя снизу и не граница - у неё нет шансов!
                                        boolPoints.get(bool_i).get(bool_j).set(0, Boolean.TRUE);
                                    } else {
                                        int temp = 0;
                                        for (int k = bool_i; k < (coord[2]-coord[0] + 1); k++) {
                                            boolPoints.get(k).get(bool_j).set(6, Boolean.TRUE); // помечаем текующую точку как точку, в которой мы уже были
                                            if (isPointInDistrict(field.getPoints().get(k+coord[0]).get(j))) { // true если граница
                                                temp = k;
                                                break;
                                            }
                                            else { //иначе не граница
                                            
                                            }
                                        }
                                        if (temp !=0) {
                                            for (int l = bool_i; l < temp; l++) {
                                                boolPoints.get(l).get(bool_j).set(1, Boolean.TRUE); // расставляем флаг B=true до границы
                                            }
                                        }
                                    }
                                }
                            }
                            
                        }  
                        
                //на данном этапе доподлинно известно,
                //лежит точка внутри заданной области или нет.
                        
                //проверка и подсчёт точки (если она точно попала в область)
                if (boolPoints.get(bool_i).get(bool_j).get(0).booleanValue() == false){
                    
                    if (p.getPointState() != Point.PointState.EMPTY && p.getHostPlayer() != Point.HostPlayer.Free){ //если точка не пуста
                        
                        if (p.getHostPlayer() == activePlayer){ //если точка принадлежит игроку, захватившему область
                            if (p.getIsCounted()){ //если точка засчитана другому игроку
                                p.setIsCounted(false);
                                minusPoints++; //P2.setpCount(P2.getpCount() - 1); else  P1.setpCount(P1.getpCount() - 1);
                            }
                        } else { //если точка не принадлежит игроку, захватившему область1
                             if (!p.getIsCounted()){ //если точка ещё не посчитана
                                p.setIsCounted(true);
                                plusPoints++;
                                //if (activePlayer == Point.HostPlayer.Player1) P1.setpCount(P1.getpCount() + 1); else  P2.setpCount(P2.getpCount() + 1);
                             }
                        }
                        p.setPointState(Point.PointState.NOTAVAILABLE);
                        
                    }
                    
                }     
                j++;
            }
            
            i++;
        }
        
        
        
        if (plusPoints != 0){
            if (activePlayer == Point.HostPlayer.Player1) {
                getP1().setpCount(getP1().getpCount() + plusPoints);
                getP2().setpCount(getP2().getpCount() - minusPoints);
            } else  {
                getP2().setpCount(getP2().getpCount() + plusPoints);
                getP1().setpCount(getP1().getpCount() - minusPoints);
            }
            return true;
        }
        
        return false;
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
                        getP1().setStepNumber(getP1().getStepNumber() + 1);       //увеличиваем число ходов 1 игрока 
                        activePlayer = Point.HostPlayer.Player1;        //запоминаем игрока, сделавшего последний ход.
                        if (getP1().getStepNumber() >= 4){
                            visitedPoints = new ArrayList<Point>();     //создаём пустой массив, в котором будут посещённые точки
                            districtPoints = new ArrayList<Point>();    //создаём точки под замкнутую область
                            isOblast = false;
                            this.districtBackTrack(activeNumberI, activeNumberJ, null);  //вызов back-track функци
                            removeGarbagePoints();                      //удаляем все точки, не относящиеся к области
                            if (checkCapturedPoints()){  //проверяем, подходит ли область
                                this.field.createNewDistrict(new District(1, districtPoints)); //создаём новое поле 1 игрока из полученных точек
                                lastStep = Analyst.PlayerMove.LUCKY;
                            } else {
                                lastStep = Analyst.PlayerMove.UNLUCKY;
                            }
                        }    
                        break;
                    }
                    case 2: {
                        getField().changePoint(tmp, Point.HostPlayer.Player2, Point.PointState.ACTIVE); //изменяем статус точки (принадл. 2 игроку)
                        getP2().setStepNumber(getP2().getStepNumber() + 1);       //увеличиваем число ходов 2 игрока 
                        activePlayer = Point.HostPlayer.Player2;        //запоминаем игрока, сделавшего последний ход.
                        if (getP2().getStepNumber() >= 4){
                            visitedPoints = new ArrayList<Point>();     //создаём пустой массив, в котором будут посещённые точки
                            districtPoints = new ArrayList<Point>();
                            isOblast = false;
                            this.districtBackTrack(activeNumberI, activeNumberJ, null); //вызов back-track функции
                            removeGarbagePoints();                      //удаляем все точки, не относящиеся к области
                            if (checkCapturedPoints()){ //проверяем, подходит ли область
                                this.field.createNewDistrict(new District(2, districtPoints)); //создаём новое поле 2 игрока из полученных точек
                                lastStep = Analyst.PlayerMove.LUCKY;
                            } else {
                                lastStep = Analyst.PlayerMove.UNLUCKY;
                            }
                            
                        }
                        break;
                    }
            }
            return true;
        }
    return false;
    }
    /**
     * Проверяет, является ли точка с координатами (px, py) свободной
     * @param pX
     * @param pY
     * @return объект точка
     */
    public Point pointCheck (float pX, float pY) {
        Point temp = this.getField().getPointByCoor(pX, pY);
        if (temp != null)
            if (temp.getPointState() == Point.PointState.EMPTY) return temp;
        return null;
    }
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
    //==== ОСНОВНЫЕ МЕТОДЫ ==== \\
    
}
