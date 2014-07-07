/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pointless;

/**
 *
 * @author Пётр
 */
public class Player {
    /** Имя игрока (вводится пользователем) */
    private String name;
    /** Айдишник игрока и количество захваченных точек противника */
    private int id, pCount;
    /** Количество игроков */
    private static int pId = 0;
    /** Номер хода игрока */
    private int stepNumber;
    
    public Player() {
        pCount = 0;
    }
    
    public Player(String newName) {
        name = newName;
        pId++;
        id=pId;
        stepNumber = 0;
        pCount = 0;
    }
    
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getpCount() {
        return pCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setpCount(int pCount) {
        this.pCount = pCount;
    }

    /**
     * Номер хода игрока
     * @return the stepNumber
     */
    public int getStepNumber() {
        return stepNumber;
    }

    /**
     * Номер хода игрока
     * @param stepNumber the stepNumber to set
     */
    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }
    
}
