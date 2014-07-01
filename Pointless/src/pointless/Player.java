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
    private String name;
    private int id, pCount;
    private static int pId;
    public Player() {
        
    }
    
    public Player(String newName) {
        name = newName;
        pId++;
        id=pId;
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
    
}
