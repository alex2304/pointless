/**
 * Класс Игрок.
 * Характеризует одного из игрока, как то:
 * его счёт, имя, идентификатор.
 */

package pointless;

/**
 *
 * @author Пётр
 */
public class Player {
    //==== ДАННЫЕ КЛАССА ==== \\
    /** Имя игрока (вводится пользователем) */
    private String name;
    /** Айдишник игрока и количество захваченных точек противника */
    private int id, pCount;
    /** Количество игроков */
    private static int pId = 0;
    /** Номер хода игрока */
    private int stepNumber;
    //==== ДАННЫЕ КЛАССА ==== \\

    
    //==== КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ ==== \\
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
    //==== КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ ==== \\
    
    
    //==== МОДИФИКАТОРЫ И СЕЛЕКТОРЫ ==== \\
    /**
     * Имя игрока
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * Номер игрока
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * Количество точек
     * @return the pCount
     */
    public int getpCount() {
        return pCount;
    }
    /**
     * Имя игрока
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Id игрока
     * @return the id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Количество точек
     * @param pCount the pCount to set
     */
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
    //==== МОДИФИКАТОРЫ И СЕЛЕКТОРЫ ==== \\
    
}
