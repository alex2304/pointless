/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pointless;

/**
 *
 * @author Leha
 */
public class Point {
    /**статус точки (0 = не поставлена; 1 = активна; 2 = недоступна;) */
    public enum PointState { EMPTY, ACTIVE, NOTAVAILABLE }
    private PointState pointState;    
    
    /**координата X точки */
    private float X;            
    
    /**координата Y точки */
    private float Y;             
    
    /**номер игрока, "владеющего" точкой (0, 1 или 2; если 0 - точка "свободна") */
    public enum HostPlayer { Free, Player1, Player2 }
    private HostPlayer hostPlayer;  
    
    /**номера точки по горизонтали и вертикали в массиве точек */
    private int i, j;

    /**
     * @return the pointState
     */
    public PointState getPointState() {
        return pointState;
    }

    /**
     * @param pointState the pointState to set
     */
    public void setPointState(PointState pointState) {
        this.pointState = pointState;
    }

    /**
     * @return the X
     */
    public float getX() {
        return X;
    }

    /**
     * @param X the X to set
     */
    public void setX(float X) {
        this.X = X;
    }

    /**
     * @return the Y
     */
    public float getY() {
        return Y;
    }

    /**
     * @param Y the Y to set
     */
    public void setY(float Y) {
        this.Y = Y;
    }

    /**
     * @return the N
     */
    public HostPlayer getHostPlayer() {
        return hostPlayer;
    }

    /**
     * @param N the N to set
     */
    public void setHostPlayer(HostPlayer N) {
        this.hostPlayer = N;
    }
    
    /**
     * конструктор по-умолчанию
     */
    public Point() {
        hostPlayer = HostPlayer.Free;
        X = 0;
        Y = 0;
        pointState = PointState.EMPTY;
        i = 0;
        j = 0;
    }
    
    /**
     * полная инициализация
     */
    public Point(float x, float y, PointState p, HostPlayer h, int i, int j) {
        hostPlayer = h;
        X = x;
        Y = y;
        pointState = p;
        this.i = i;
        this.j = j;
    }

    /**
     * номера точки по горизонтали и вертикали в массиве точек
     * @return the i
     */
    public int getI() {
        return i;
    }

    /**
     * номера точки по горизонтали и вертикали в массиве точек
     * @param i the i to set
     */
    public void setI(int i) {
        this.i = i;
    }

    /**
     * номера точки по горизонтали и вертикали в массиве точек
     * @return the j
     */
    public int getJ() {
        return j;
    }

    /**
     * номера точки по горизонтали и вертикали в массиве точек
     * @param j the j to set
     */
    public void setJ(int j) {
        this.j = j;
    }
}
