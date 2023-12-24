import java.net.Socket;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * "Acey Deucey" Game player with connection to dealer over internet
 * @author abendrothj
 */
public class Acey {
    private static String ipAddress = "";
    private static String ipPort = "";
    private static Socket socket = null;
    private static DataInputStream dis = null;
    private static DataOutputStream dos = null;
    private static int chipsInPot = 0;
    private static int myStack = 0;
    private static int bet = 0;

    public static void main(String[] args) throws IOException {
        // Connecting to dealer
        try {
            ipAddress = args[0];
            ipPort = args[1];
        } catch(Exception e) {
            System.err.println("Need cmd line arguments!");
            System.exit(0);
        }
        Acey.socket = new Socket(ipAddress, Integer.parseInt(ipPort));
        Acey.dis = new DataInputStream(socket.getInputStream());
        Acey.dos = new DataOutputStream(socket.getOutputStream());

        while(socket.isConnected()) {
            String str = read();
            if(str.equals("login")) { // Player login
                write("abendrothj:Jake");
            }
            if(str.startsWith("play")) {
                play(str);
            }
            if(str.startsWith("done")) { // Exit program when signalled by dealer
                String error = str.substring(5);
                System.out.println(error);
                socket.close();
                break;
            }
        }
    }

    /**
     * Takes the below format as input and places a logical bet accordingly.
     * play:chipsInPot:chipsInMyStack:FirstCard:SecondCard
     * @param str Input string from dealer
     * @throws IOException If dealer input is incorrect
     */
    private static void play(String str) throws IOException {
        String[] spl = str.split(":");
        chipsInPot = Integer.parseInt(spl[1]);
        myStack = Integer.parseInt(spl[2]);
        String firstC = spl[3];
        String secondC = spl[4];
        String[] cards = str.substring(str.indexOf(":", str.indexOf(spl[5]))+1).split(":");
        countMaster(firstC, secondC, cards);
        if(convert(firstC) == convert(secondC)) { // If numerical value of cards is equal
            int diff = 14 - convert(firstC);
            if(chipsInPot >= bet && (myStack > bet || bet == 0)) {
                write(gambler(diff)+bet);
            }
        } else { // first two cards are not equal
            if(Math.abs(convert(firstC)-convert(secondC)) > 7) {
                bet = 3;
                // Cards are > 7 away, good chance of win
            }
            if(chipsInPot >= bet && myStack > bet) {
                write("mid:"+bet);
            } else {
                write("mid:"+0);
            }
        }
    }

    /**
     * Handles the size of the bet going to be placed and predicts high/low
     * @param diff Difference between the highest card value and drawn card value
     * @return Whether the next card will be higher or lower than the drawn card (String)
     */
    private static String gambler(int diff) {
        if(diff > 6) { // Card value 2-7 (14-8 = 6)
            if(diff > 9) { bet = 3; }// Card value 2-4 increase bet
            if(myStack <= bet) {
                bet = bet-Math.abs(bet-myStack);
            }
            return "high:";
        } else { // Card value 8-14
            if(diff < 4) { bet = 3; } // Card value is 11-14 increase bet
            if(myStack <= bet) {
                bet = bet-Math.abs(bet-myStack);
            }
            return "low:";
        }
    }

    /**
     * Adjusts the bet based on what cards are left to be drawn.
     * @param firstC first Card
     * @param secondC second Card
     * @param cards String array of counted cards
     */
    private static void countMaster(String firstC, String secondC, String[] cards) {
        if(convert(firstC) == convert(secondC)) {
            int highCards = 0;
            int lowCards = 0;
            for(String i : cards) {
                if(convert(i) > 9) {
                    highCards++;
                } if(convert(i) < 7){
                    lowCards++;
                }
            } if (convert(firstC) < 8 && highCards > 50) {
                bet = (int) (0.5*bet);
            } if(convert(firstC) > 9 && lowCards > 50) {
                bet = (int) (0.5*bet);
            }
        } else {
            String lower;
            String higher;
            int between = 0;
            int notInBetween = 0;
            if(convert(firstC) < convert(secondC)) {
                lower = firstC;
                higher = secondC;
            } else {
                higher = firstC;
                lower = secondC;
            }
            for(String i : cards) {
                if(convert(higher) > convert(i) && convert(lower) < convert(i)) {
                    between++;
                } else {
                    notInBetween++;
                }
            } if(between > 50) { bet = (int)(bet*0.5); }
            if(notInBetween/(double)between >= 2) {
                if(myStack >= bet*2 && chipsInPot >= bet*2) {
                    bet *= 2;
                }
            }
            else if(notInBetween/(double)between >= 1.55) {
                if(myStack >= bet*2 && chipsInPot >= bet*2) { bet *= 2; }
            }

        }
    }

    /**
     * Writes string to dealer over socket
     * @param s String to write
     * @throws IOException if socket is not connected
     */
    private static void write(String s) throws IOException {
        dos.writeUTF(s);
        dos.flush();
    }

    /**
     * Reads string from dealer
     * @return String
     * @throws IOException if socket is not connected
     */
    private static String read() throws IOException {
        return dis.readUTF();
    }

    /**
     * Helper method to convert card letter values to their numerical value
     * @param c String to convert (Card ex. KS)
     * @return integer value of card
     */
    private static int convert(String c) {
        String input;
        if(c.length() == 3) { input = "10"; }
        else { input = c.substring(0, 1); }
        if(input.equals("J")) {
            return 11;
        } if(input.equals("Q")) {
            return 12;
        } if(input.equals("K")) {
            return 13;
        } if(input.equals("A")) {
            return 14;
        } else { return Integer.parseInt(input); }
    }
}
