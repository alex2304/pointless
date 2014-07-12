/**
 * Класс Область. Изначально задумывался как класс,
 * хранящий захваченные области и объединяющие их,
 * но теперь почти бесполезен. Хранит массив точек, составляющих замкн. область.
*/

package pointless;

import java.util.ArrayList;
                                                                                                                                                                                                                                                                                                                                                                                                                            

/**
 *
 * @author Пётр
 */
public class District {
    //==== ДАННЫЕ КЛАССА ==== \\
    /**количество точек внутри области игрока, захватившего область, и другого игрока */
    private int pCountFPlayer, pCountSPlayer;
    /**номер игрока, захватившего поле */
    private int ownerId;
    /**список точек, составляющих замкнутую область */
    private ArrayList<Point> pointsArray;
    //==== ДАННЫЕ КЛАССА ==== \\

    
    //==== КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ ==== \\
    public District (final int ownerId, ArrayList<Point> p) {
        this.ownerId = ownerId;
        pointsArray = p;
        pCountFPlayer = 0;
        pCountSPlayer = 0;
    }
    public District (final int ownerId, final int pCountFPlayer, final int pCountSPlayer, final ArrayList<Point> brdrCount) {
        this.ownerId = ownerId;
        this.pointsArray = brdrCount;
        this.pCountFPlayer = pCountFPlayer;
        this.pCountSPlayer = pCountSPlayer;
    }
    //==== КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ ==== \\
    
    
    //==== МОДИФИКАТОРЫ И СЕЛЕКТОРЫ ==== \\
    public int getpCountFPlayer() {
        return pCountFPlayer;
    }
    public int getpCountSPlayer() {
        return pCountSPlayer;
    }
    public ArrayList<Point> getPointsArray() {
        return pointsArray;
    }
    public void setpCountFPlayer(final int pCountFPlayer) {
        this.pCountFPlayer = pCountFPlayer;
    }
    public void setpCountSPlayer(final int pCountSPlayer) {
        this.pCountSPlayer = pCountSPlayer;
    }
    public void setPointsArray(final ArrayList<Point> brdrCount) {
        this.pointsArray = brdrCount;
    }
     public int isOwnerId() {
        return getOwnerId();
    }
    public void setOwnerId(final int ownerId) {
        this.ownerId = ownerId;
    }
    /**
     * номер игрока, захватившего поле
     * @return the ownerId
     */
    public int getOwnerId() {
        return ownerId;
    }
    //==== МОДИФИКАТОРЫ И СЕЛЕКТОРЫ ==== \\
     
}
