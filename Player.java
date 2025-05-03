import java.util.LinkedList;

public class Player {
    String name;
    LinkedList<Card> hand;
    Boolean active;
    Role role;
    //Board

    public Player(String n){
        name=n;
        hand=new LinkedList<>();
        active=true;
        role=Role.NONE;
    }

    public static Player[] createPlayers(){
        Player p1=new Player("player1");
        Player p2=new Player("player2");
        Player p3=new Player("player3");
        Player p4=new Player("player4");
        return new Player[]{p1, p2, p3, p4};
    }

    public static void printRoles(Player[] players){
        for(int i=0;i< players.length;i++){
            System.out.println("Player"+(i+1)+" is "+players[i].role);
        }
    }

    public static int find2Diamonds(Player[] players){ //returns index of player with 2D
        for (int i = 0; i<players.length;i++){
            for(Card c:players[i].hand) {
                if (c.value == 2 && c.sign == 0) {
                    return i;
                }
            }
        }
        return 0;

    }

    public static void displayHands(Player[] players){
        for(Player p:players) p.displayHand();
    }

    public void displayHand(){
        System.out.print(name + ":");
        for(int i=0;i<this.hand.size();i++) {
            System.out.print(i);
            this.hand.get(i).print();
        }
        System.out.println("");
    }

    //valid moves method
    public int[] validCards(LinkedList<Card> lastPlayed){
        return null;
    }

    public void removeCards(LinkedList<Card> selection) {
        for(Card c:selection){
            int index=this.hand.indexOf(c);
            this.hand.remove(index);
        }
    }

}
