/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pointless;

import java.util.ArrayList;
import java.awt.Color;

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
    public boolean initializeGame(int width, int height, int lineSize, Color c1, Color c2, Color fieldColor){
        horizontalPointCount = width / lineSize - 1; //количество точек по горизонтали
        verticalPointCount = height / lineSize - 1;  //количество точек по вертикали
        
        //проверка входных параметров
        if (horizontalPointCount <= 0 || verticalPointCount <= 0) {horizontalPointCount = 0; verticalPointCount = 0; return false; }
        
        //заполнение массива точек
        for (int i = 0; i < verticalPointCount; i++){   
            points.add(new ArrayList<Point>());     //cоздаём строки
            for (int j = 0; j < horizontalPointCount; j++){
                points.get(i).add( new Point((float)lineSize*(j+1), (float)lineSize*(i+1), Point.PointState.Empty, Point.HostPlayer.Free, i, j) ); //создаём столбцы для каждой из строк
            }
        }
        //установка цвета
        this.fieldColor = fieldColor;
        this.color1 = c1;
        this.color2 = c2;
        
        //отрисовка поля
        drawField();
        
        return true;
    }
    
    /** Отрисовка поля с точками и областями */
    private void drawField(){
        
    }
    
    /** Добавление новой области и её отрисовка */
    private void drawDistrict (District newDistrict) {
        districts.add(newDistrict); // необходимо удалить внутренние области
        // непосредственно отрисовка
        
        
    }
    
    /** Изменение свойств точки по приказу контроллера */
    private boolean changePoint(float x, float y, int r, Point.HostPlayer h, Point.PointState s){
        for (int i = 0; i < verticalPointCount; i++){
            for (int j = 0; j < this.horizontalPointCount; j++)
            {
                /*if ( ( points.get(i).get(j).getX() >= (x - lineSize / 3) || //условие попадания точки в квадрат
                points.get(i).get(j).getX() <= (x + lineSize / 3) ) &&  //вокруг точки, которую
                ( points.get(i).get(j).getY() >= (y - lineSize / 3) ||  //нажал пользователь
                points.get(i).get(j).getY() <= (y + lineSize / 3) ) )*/
                if ( (x - points.get(i).get(j).getX())*(x - points.get(i).get(j).getX())    //условие попадания точки в круг
                + (y - points.get(i).get(j).getY())*(y - points.get(i).get(j).getY()) <= r*r ){ //вокруг точки, которую нажал пользователь
                    points.get(i).get(j).setHostPlayer(h);  //устанавливаем игрока-хозяина для точки
                    points.get(i).get(j).setPointState(s);  //устанавливаем статус для точки
                    return true;
                } 
            }
        }
        return false;
    }
    
    //==== ОСНОВНЫЕ МЕТОДЫ ==== \\
}
