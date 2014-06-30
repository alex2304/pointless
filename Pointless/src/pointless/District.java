/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pointless;

import java.util.ArrayList;

/**
 *
 * @author Пётр
 */
public class District {
    private int pCountFPlayer, pCoundSPlayer;
    private ArrayList<Integer> brdrCount;

    public District () {
        
    }
    
    public int getpCountFPlayer() {
        return pCountFPlayer;
    }

    public int getpCoundSPlayer() {
        return pCoundSPlayer;
    }

    public ArrayList<Integer> getBrdrCount() {
        return brdrCount;
    }

    public void setpCountFPlayer(int pCountFPlayer) {
        this.pCountFPlayer = pCountFPlayer;
    }

    public void setpCoundSPlayer(int pCoundSPlayer) {
        this.pCoundSPlayer = pCoundSPlayer;
    }

    public void setBrdrCount(ArrayList<Integer> brdrCount) {
        this.brdrCount = brdrCount;
    }

   
    
    
}
