import java.util.ArrayList;
import java.util.List;

/**
 * Class to run the Deck
 */
class NewCardDeck {
    public static void main(String[] args) {
        Deck cards = new Deck();
        cards.shuffle();
        System.out.println(cards.deal());
    }
}
/**
 * Class for one card
 */
class Card {
    private int value = 0;
    private String suit = "ERROR";
    public Card(int val, String st) { // Int and string constructor
        char[] allowed = {'s', 'S', 'h', 'H', 'c', 'C', 'd', 'D'};
        if(val > 1 && val < 15) {
            this.value = val;
        }
        for(char c : allowed) {
            if (st.charAt(0) == c) {
                this.suit = convert(st.charAt(0));
                break;
            }
        }
    }
    public Card(String str) { //String constructor
        String val = str.substring(0, 1);
        if(val.matches("^[0-9]+$") && str.charAt(1) != '0') {
            this.value = Integer.parseInt(val);
        } if(str.startsWith("10")) {
            this.value = 10;
        } char[] royals = {'j', 'J', 'q', 'Q', 'k', 'K', 'a', 'A'};
        for(char i : royals) {
            if(val.charAt(0) == i) {
                this.value = Integer.parseInt(convert(i));
            }
        } if(str.startsWith("10")) {
            this.suit = convert(str.charAt(2));
        } else {
            this.suit = convert(str.charAt(1));
        }
    }

    public int Value() {
        return this.value;
    }
    public String Suit() { // Prints the suit in capital first letter form
        return switch (this.suit) {
            case "Spades" -> "S";
            case "Hearts" -> "H";
            case "Clubs" -> "C";
            case "Diamonds" -> "D";
            default -> this.suit;
        };
    }
    public String print() { // Prints value + suit of the card
        String s = "";
        if(this.value > 1 && this.value < 11) { // If between 2-10, simple print
            return this.value+Suit();
        } if(this.value > 10) { // If greater than 10, need to convert to letter form
            switch(this.value) {
                case 11:
                    s += "J";
                    break;
                case 12:
                    s += "Q";
                    break;
                case 13:
                    s += "K";
                    break;
                case 14:
                    s += "A";
                    break;
                default:
                    break;
            }
            switch(this.suit) {
                case "Spades":
                    s += "S";
                    break;
                case "Hearts":
                    s += "H";
                    break;
                case "Clubs":
                    s += "C";
                    break;
                case "Diamonds":
                    s += "D";
                    break;
                default:
                    break;
            }
        } return s;
    }
    private String convert(char bam) { // Helper method for conversions, saves time
        if(bam == 's' || bam == 'S') {
            return "Spades";
        } if(bam == 'h' || bam == 'H') {
            return "Hearts";
        } if(bam == 'c' || bam == 'C') {
            return "Clubs";
        } if(bam == 'd' || bam == 'D') {
            return "Diamonds";
        } if(bam == 'j' || bam == 'J') {
            return "11";
        } if(bam == 'q' || bam == 'Q') {
            return "12";
        } if(bam == 'k' || bam == 'K') {
            return "13";
        } if(bam == 'a' || bam == 'A') {
            return "14";
        } else { return "ERROR"; }
    }
}

/**
 * Class for the deck of cards
 */
class Deck {
    private final Card[] allMyCards = new Card[52];
    public Deck() { // Constructor for deck
        String suit = "Spades";
        int val = 2;
        for(int i = 0; i < 52; i++) { // for each card
            if(i == 13) { // switch suit and reset value
                suit = "Hearts";
                val = 2;
            }
            if(i == 26) {
                suit = "Clubs";
                val = 2;
            }
            if(i == 39) {
                suit = "Diamonds";
                val = 2;
            }
            allMyCards[i] = new Card(val, suit); // Assign new card to array location
            val++;
        }
    }
    private int count = 0;
    private final List<Card> dealt = new ArrayList<>();
    public String deal() {
        if(count < 52) {
            dealt.add(allMyCards[count]);
            if(count == 0) {
                count++;
                return allMyCards[count-1].print();
            }
            if(!dealt.contains(allMyCards[count])) {
                dealt.add(allMyCards[count]);
                count++;
                return allMyCards[count-1].print();
            }
        } return null;
    }
    public void shuffle() {

        for(int i = 0; i < 500; i++) {
            int rand = (int)(Math.random()*52.0);
            int rand2 = (int)(Math.random()*52.0);
            Card placeholder = allMyCards[rand];
            allMyCards[rand] = allMyCards[rand2];
            allMyCards[rand2] = placeholder;
        }
        this.dealt.clear();
        this.count = 0;
    }

    // Prints entire deck of cards separated by spaces
    public String print() {
        String result = "";
        for(Card i : allMyCards) {
            result += i.print()+" ";
        } return result;
    }
}
