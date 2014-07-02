/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pointless;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

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
    //==== ДАННЫЕ КЛАССА ==== \\

    
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
    public boolean initializeGame(int width, int height, int lineSize, Color c1, Color c2, Color fieldColor, Graphics g){
        horizontalPointCount = width / lineSize - 1; //количество точек по горизонтали
        verticalPointCount = height / lineSize - 1;  //количество точек по вертикали
        
        //проверка входных параметров
        if (horizontalPointCount <= 0 || verticalPointCount <= 0) {horizontalPointCount = 0; verticalPointCount = 0; return false; }
        
        //заполнение массива точек
        for (int i = 0; i < verticalPointCount; i++){   
            points.add(new ArrayList<Point>());     //cоздаём строки
            for (int j = 0; j < horizontalPointCount; j++){
                points.get(i).add( new Point((float)lineSize*(j+1), (float)lineSize*(i+1), Point.PointState.EMPTY, Point.HostPlayer.Free, i, j) ); //создаём столбцы для каждой из строк
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
        
        //отрисовка поля
        drawField(g);
        
        return true;
    }
    
    /** Отрисовка поля с точками и областями */
    public void drawField(Graphics g){
        //float x1 = 0, y1 = ;
        g.setColor(Color.BLACK);
        for (int i = 0; i <= this.verticalPointCount; i++){
            g.drawLine(0, i*this.lineSize, this.Width, i*this.lineSize);
        }
        g.setColor(Color.BLACK);
        for (int i = 0; i <= this.horizontalPointCount; i++){
            g.drawLine(i*this.lineSize, 0, i*this.lineSize, this.Height);
        }
        g.setColor(Color.WHITE);
        for (int i = 0; i < this.verticalPointCount; i++){
            for (int j = 0; j < this.horizontalPointCount; j++){
                if (this.points.get(i).get(j).getPointState() == Point.PointState.ACTIVE) {
                    if (this.points.get(i).get(j).getHostPlayer() == Point.HostPlayer.Player1) {
                        g.setColor(this.color1);
                    } else g.setColor(this.color2);
                } else g.setColor(Color.WHITE);
                g.fillOval((int)this.points.get(i).get(j).getX()-2, (int)this.points.get(i).get(j).getY()-2, 5, 5);
            }
        }
    }
    
    /** Добавление новой области и её отрисовка */
    public void drawDistrict (District newDistrict) {
        districts.add(newDistrict); // необходимо удалить внутренние области
        // непосредственно отрисовка
        
        
    }
    
    /** Позволяет получить точку, попадающую в заданную область */
    public Point getPointByCoor(float x, float y, int r){
        for (int i = 0; i < verticalPointCount; i++){
            for (int j = 0; j < this.horizontalPointCount; j++)
            {
                /*if ( ( points.get(i).get(j).getX() >= (x - lineSize / 3) || //условие попадания точки в квадрат
                points.get(i).get(j).getX() <= (x + lineSize / 3) ) &&  //вокруг точки, которую
                ( points.get(i).get(j).getY() >= (y - lineSize / 3) ||  //нажал пользователь
                points.get(i).get(j).getY() <= (y + lineSize / 3) ) )*/
                if ( (x - points.get(i).get(j).getX())*(x - points.get(i).get(j).getX())    //условие попадания точки в круг
                + (y - points.get(i).get(j).getY())*(y - points.get(i).get(j).getY()) <= r*r ){ //вокруг точки, которую нажал пользователь
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
                    return true;
         }
        return false;
    }
    
    //==== ОСНОВНЫЕ МЕТОДЫ ==== \\
}
