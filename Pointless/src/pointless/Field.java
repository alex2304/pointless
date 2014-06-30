/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pointless;

import java.util.ArrayList;

/**
 *
 * @author Leha
 */
public class Field {
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
    private ArrayList<Point> points; 
    
    /**
     * Цвета игроков
     */
    private String color1, color2;

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
    public ArrayList<Point> getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }
    
    /**
     * @return the Color1
     */
    public String getColor1() {
        return color1;
    }

    /**
     * @param color1 the color to set
     */
    public void setColor1(String color) {
        this.color1 = color;
    }
    
    /**
     * @return the Color2
     */
    public String getColor2() {
        return color2;
    }

    /**
     * @param color2 the color to set
     */
    public void setColor2(String color) {
        this.color2 = color;
    }
    
    /**
     * конструктор по-умолчанию
     */
    public Field(){
        this.districts = new ArrayList<District>();
        this.points = new ArrayList<Point>();
        Width = 0; 
        Height = 0;
        color1 = "";
        color2 = "";
        
    }
    
    /**
     * конструктор с инициализацией размеров поля
     */
    public Field(int w, int h){
        this.districts = new ArrayList<District>();
        this.points = new ArrayList<Point>();
        Width = w; 
        Height = h;
        color1 = "";
        color2 = "";
    }
    
    /**
     * конструктор с полной инициализацией
     */
    public Field(int w, int h, String c1, String c2){
        this.districts = new ArrayList<District>();
        this.points = new ArrayList<Point>();
        Width = w; 
        Height = h;
        color1 = c1;
        color2 = c2;
    }
    
    
}
