import java.util.Random;

/**
 * Back alley dice game
 * @author abendrothj
 */
public class BackAlley {
    public static void main(String[] args) {
        Dice dice = new Dice(2);
        int point = 0;
        int bal = 20;
        while(bal > 0) {
            int rolled = dice.roll();
            if(rolled == 7 || rolled == 11) {
                bal += 1;
                System.out.println("$"+bal+" win");
            }
            else if(rolled == 2 || rolled == 3 || rolled == 12) {
                bal -= 1;
                System.out.println("$"+bal+" lose");
            } else {
                point = rolled;
                int next = 0;
                boolean pointCrossed = false;
                while(next != 7) {
                    next = dice.roll();
                    if(next == point) { pointCrossed = true; }
                    if(next == 7 && !pointCrossed) {
                        bal -= 1;
                        System.out.println("$"+bal+" lose");
                    }
                    if(next == 7 && pointCrossed) {
                        bal += 1;
                        System.out.println("$"+bal+" win");
                    }
                }
            }
        }
    }
}

/**
 * Class for one Die, with basic roll method
 */
class Die {
    public int roll() {
        Random rand = new Random();
        return rand.nextInt(6)+1;
    }
}

/**
 * Class for multiple Dice scalable with constructor, and simultaneous rolling.
 */
class Dice extends Die {
    private int amount = 0;
    public Dice(int quantity) {
        this.amount = quantity;
    }
    @Override
    public int roll() {
        int result = 0;
        for(int i = 0; i < amount; i++) {
            result += super.roll();
        } return result;
    }
}