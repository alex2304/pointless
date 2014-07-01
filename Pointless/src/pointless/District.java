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
    /**количество точек внутри области игрока, захватившего область, и другого игрока */
    private int pCountFPlayer, pCountSPlayer;
    
    /**номер игрока, захватившего поле */
    private int ownerId;
    
    /**список точек, составляющих замкнутую область */
    private ArrayList<Integer> brdrCount;

    public District (final int ownerId) {
        this.ownerId = ownerId;
        brdrCount = new ArrayList<Integer>();
        pCountFPlayer = 0;
        pCountSPlayer = 0;
    }
    
    public District (final int ownerId, final int pCountFPlayer, final int pCountSPlayer, final ArrayList<Integer> brdrCount) {
        this.ownerId = ownerId;
        this.brdrCount = brdrCount;
        this.pCountFPlayer = pCountFPlayer;
        this.pCountSPlayer = pCountSPlayer;
    }
    
    public int getpCountFPlayer() {
        return pCountFPlayer;
    }

    public int getpCountSPlayer() {
        return pCountSPlayer;
    }

    public ArrayList<Integer> getBrdrCount() {
        return brdrCount;
    }

    public void setpCountFPlayer(final int pCountFPlayer) {
        this.pCountFPlayer = pCountFPlayer;
    }

    public void setpCountSPlayer(final int pCountSPlayer) {
        this.pCountSPlayer = pCountSPlayer;
    }

    public void setBrdrCount(final ArrayList<Integer> brdrCount) {
        this.brdrCount = brdrCount;
    }
    
     public int isOwnerId() {
        return ownerId;
    }

    public void setOwnerId(final int ownerId) {
        this.ownerId = ownerId;
    }
     
}
