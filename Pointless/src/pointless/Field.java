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
public class Field {
    //==== ДАННЫЕ КЛАССА ==== \\
    /**
     * Размеры поля по горизонтали и вертикали соответственно 
     */
    private int Width, Height;  
    /**
     * Массив объектов класса область (занятые области) 
     */
    private ArrayList<District> districts;  
    /**
     * Массив объектов класса точка (все точки поля) 
     */
    private ArrayList<ArrayList<Point>> points; 
    /**
     * Цвета игроков
     */
    private Color color1, color2;
    /**
     * Цвет поля
     */
    private Color fieldColor;
    
    /**
     * Количество точек в поле
     */
    private int horizontalPointCount, verticalPointCount;
    /**
     * Расстояние между точками
     */
    private int lineSize;
    /**
     * Радиус активной точки
     */
    private int activeRadius;
    //==== ДАННЫЕ КЛАССА ==== \\
    private int notActiveRadius;

    
    //==== МОДИФИКАТОРЫ И СЕЛЕКТОРЫ ==== \\
    /**
     * @return the Width
     */
    public int getWidth() {
        return Width;
    }
    /**
     * @param Width the pWidth to set
     */
    public void setWidth(int Width) {
        this.Width = Width;
    }
    /**
     * @return the Height
     */
    public int getHeight() {
        return Height;
    }
    /**
     * @param Height the Height to set
     */
    public void setHeight(int Height) {
        this.Height = Height;
    }
    /**
     * @return the districts
     */
    public ArrayList<District> getDistricts() {
        return districts;
    }
    /**
     * @param districts the districts to set
     */
    public void setDistricts(ArrayList<District> districts) {
        this.districts = districts;
    }
    /**
     * @return the points
     */
    public ArrayList<ArrayList<Point>> getPoints() {
        return points;
    }
    /**
     * @param points the points to set
     */
    public void setPoints(ArrayList<ArrayList<Point>> points) {
        this.points = points;
    }
    /**
     * @return the Color1
     */
    public Color getColor1() {
        return color1;
    }
    /**
     * @param color1 the color to set
     */
    public void setColor1(Color color) {
        this.color1 = color;
    }
    /**
     * @return the Color2
     */
    public Color getColor2() {
        return color2;
    }
    /**
     * @param color2 the color to set
     */
    public void setColor2(Color color) {
        this.color2 = color;
    }
    /**
     * Цвет поля
     * @return the fieldColor
     */
    public Color getFieldColor() {
        return fieldColor;
    }
    /**
     * Цвет поля
     * @param fieldColor the fieldColor to set
     */
    public void setFieldColor(Color fieldColor) {
        this.fieldColor = fieldColor;
    }
    
    public int getVerticalPointCount() {
        return verticalPointCount;
    }

    public void setVerticalPointCount(int verticalPointCount) {
        this.verticalPointCount = verticalPointCount;
    }
     /**
     * Радиус активной точки
     * @return the activeRadius
     */
    public int getActiveRadius() {
        return activeRadius;
    }

    /**
     * Радиус активной точки
     * @param activeRadius the activeRadius to set
     */
    public void setActiveRadius(int activeRadius) {
        this.activeRadius = activeRadius;
    }

    /**
     * Количество точек в поле
     * @return the horizontalPointCount
     */
    public int getHorizontalPointCount() {
        return horizontalPointCount;
    }
     //==== МОДИФИКАТОРЫ И СЕЛЕКТОРЫ ==== \\
    
    
    //==== КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ ==== \\
    /**
     * конструктор по-умолчанию
     */
    public Field(){
        this.districts = new ArrayList<District>();
        this.points = new ArrayList<ArrayList<Point>>();
        Width = 0; 
        Height = 0;
        color1 = Color.BLACK;
        color2 = Color.BLACK;
        fieldColor = Color.BLACK;
        horizontalPointCount = 0;
        verticalPointCount = 0;
    }
    /**
     * конструктор с инициализацией размеров поля
     */
    public Field(int w, int h){
        this.districts = new ArrayList<District>();
        this.points = new ArrayList<ArrayList<Point>>();
        Width = w; 
        Height = h;
        color1 = Color.BLACK;
        color2 = Color.BLACK;
        fieldColor = Color.BLACK;
        horizontalPointCount = 0;
        verticalPointCount = 0;
    }
    /**
     * конструктор с полной инициализацией
     */
    public Field(int w, int h, Color c1, Color c2, Color fieldColor){
        this.districts = new ArrayList<District>();
        this.points = new ArrayList<ArrayList<Point>>();
        Width = w; 
        Height = h;
        color1 = c1;
        color2 = c2;
        this.fieldColor = fieldColor;
        horizontalPointCount = 0;
        verticalPointCount = 0;
    }
    //==== КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ ==== \\
    
    
    //==== ОСНОВНЫЕ МЕТОДЫ ==== \\
    /** Инициализация поля, готового к игре */
    public boolean initializeGame(int width, int height, int lineSize, Color c1, Color c2, Color fieldColor, Graphics g, int notActiveRadius, int activeRadius){
        horizontalPointCount = width / lineSize - 1; //количество точек по горизонтали
        verticalPointCount = height / lineSize - 1;  //количество точек по вертикали
        
        //проверка входных параметров
        if (getHorizontalPointCount() <= 0 || verticalPointCount <= 0) {horizontalPointCount = 0; verticalPointCount = 0; return false; }
        
        //заполнение массива точек
        for (int i = 0; i < verticalPointCount; i++){   
            points.add(new ArrayList<Point>());     //cоздаём строки
            for (int j = 0; j < getHorizontalPointCount(); j++){
                points.get(i).add( new Point((float)lineSize*(j+1), (float)lineSize*(i+1), Point.PointState.EMPTY, Point.HostPlayer.Free, i, j, notActiveRadius) ); //создаём столбцы для каждой из строк
            }
        }
        //установка цвета
        this.fieldColor = fieldColor;
        this.color1 = c1;
        this.color2 = c2;
        
        //установка размеров поля и расстояния между клетками
        this.Width = width;
        this.Height = height;
        this.lineSize = lineSize;
        this.activeRadius = activeRadius;
        this.notActiveRadius = notActiveRadius;
        
        //отрисовка поля
        clearField(g);
        drawField(g);
        
        return true;
    }
    
    private void clearField(Graphics g){
        g.clearRect(0, 0, this.Width, this.Height);
        g.setColor(this.fieldColor);
        g.fillRect(0, 0, this.Width, this.Height);
    }
    
    /** Отрисовка поля с точками и областями */
    public void drawField(Graphics g){
        int offsetActive = 4, offsetNotActive = 2;
        
        clearField(g);
        
        g.setColor(Color.decode("#799EE7"));
        for (int i = 0; i <= this.verticalPointCount; i++){
            g.drawLine(0, i*this.lineSize, this.Width, i*this.lineSize);
        }
        g.setColor(Color.decode("#799EE7"));//A3C1E7
        for (int i = 0; i <= this.getHorizontalPointCount(); i++){
            g.drawLine(i*this.lineSize, 0, i*this.lineSize, this.Height);
        }
        g.setColor(Color.WHITE);
        for (int i = 0; i < this.verticalPointCount; i++){
            for (int j = 0; j < this.getHorizontalPointCount(); j++){
                if (this.points.get(i).get(j).getPointState() == Point.PointState.ACTIVE) {
                    this.points.get(i).get(j).setRadius(this.activeRadius);
                    if (this.points.get(i).get(j).getHostPlayer() == Point.HostPlayer.Player1) {
                        g.setColor(this.color1);
                    } else g.setColor(this.color2);
                    g.fillOval((int)this.points.get(i).get(j).getX()-offsetActive, (int)this.points.get(i).get(j).getY()-offsetActive, this.points.get(i).get(j).getRadius(), this.points.get(i).get(j).getRadius());
                } else if (this.points.get(i).get(j).getPointState() == Point.PointState.NOTAVAILABLE){
                    g.setColor(Color.GREEN);
                    g.fillOval((int)this.points.get(i).get(j).getX()-offsetNotActive, (int)this.points.get(i).get(j).getY()-offsetNotActive, this.points.get(i).get(j).getRadius(), this.points.get(i).get(j).getRadius());
                } else {
                    g.setColor(Color.WHITE);
                    g.fillOval((int)this.points.get(i).get(j).getX()-offsetNotActive, (int)this.points.get(i).get(j).getY()-offsetNotActive, this.points.get(i).get(j).getRadius(), this.points.get(i).get(j).getRadius());
                }
                
            }
        }
    }
    
    /** Добавление новой области и её отрисовка */
    public void createNewDistrict (District newDistrict) {
        districts.add(newDistrict); // необходимо удалить внутренние области
        for (int i = 0; i < newDistrict.getPointsArray().size(); i++){
            this.getPointByCoor(newDistrict.getPointsArray().get(i).getX(), newDistrict.getPointsArray().get(i).getY()).setPointState(Point.PointState.NOTAVAILABLE);
        }
    }
  
    /** Позволяет получить точку, попадающую в заданную область */
    public Point getPointByCoor(float x, float y){
        for (int i = 0; i < verticalPointCount; i++){
            for (int j = 0; j < this.getHorizontalPointCount(); j++)
            {
                /*if ( ( points.get(i).get(j).getX() >= (x - lineSize / 3) || //условие попадания точки в квадрат
                points.get(i).get(j).getX() <= (x + lineSize / 3) ) &&  //вокруг точки, которую
                ( points.get(i).get(j).getY() >= (y - lineSize / 3) ||  //нажал пользователь
                points.get(i).get(j).getY() <= (y + lineSize / 3) ) )*/
                if ( (x - points.get(i).get(j).getX())*(x - points.get(i).get(j).getX())    //условие попадания точки в круг
                + (y - points.get(i).get(j).getY())*(y - points.get(i).get(j).getY()) <= this.activeRadius*this.activeRadius ){ //вокруг точки, которую нажал пользователь
                    return points.get(i).get(j);
                } 
            }
        }
        return null;
    }
    
    /** Изменение свойств точки по приказу контроллера */
    public boolean changePoint(Point p, Point.HostPlayer h, Point.PointState s){
        if (p != null){
                    p.setHostPlayer(h);  //устанавливаем игрока-хозяина для точки
                    p.setPointState(s);  //устанавливаем статус для точки
                    if (h != Point.HostPlayer.Free && s != Point.PointState.EMPTY)
                        p.setRadius(this.activeRadius); else p.setRadius(this.notActiveRadius);
                    return true;
         }
        return false;
    }
    
    //==== ОСНОВНЫЕ МЕТОДЫ ==== \\
}
