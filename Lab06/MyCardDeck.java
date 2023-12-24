/**
 * Class to run the Deck
 */
class MyCardDeck {
    public static void main(String[] args) {
        if(args.length == 0) { // If no args, print entire deck
            OldDeck fatDeck = new OldDeck();
            System.out.println(fatDeck.print());
        } else { // Otherwise, first arg will be used to print single card
            OldCard single = new OldCard(args[0]);
            System.out.println(single.print());
        }
    }
}

/**
 * Class for one card
 */
class OldCard {
    private int value = 0;
    private String suit = "ERROR";
    public OldCard(int val, String st) { // Int and string constructor
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
    public OldCard(String str) { //String constructor
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
        switch(this.suit) {
            case "Spades":
                return "S";
            case "Hearts":
                return "H";
            case "Clubs":
                return "C";
            case "Diamonds":
                return "D";
            default:
                return this.suit;
        }
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
class OldDeck {
    private OldCard[] allMyCards = new OldCard[52];
    public OldDeck() { // Constructor for deck
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
            allMyCards[i] = new OldCard(val, suit); // Assign new card to array location
            val++;
        }
    }

    // Prints entire deck of cards separated by spaces
    public String print() {
        String result = "";
        for(OldCard i : allMyCards) {
            result += i.print()+" ";
        } return result;
    }
}
