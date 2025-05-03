import java.util.Collections;
import java.util.LinkedList;

public class Main {

    public static LinkedList<Card> getSelection(Player player){ //gets input from user to select cards from hand
        boolean end=false;
        int choice;
        LinkedList<Card> list=new LinkedList<>();
        do{
            choice= Integer.parseInt(System.console().readLine("Enter"
            		+ " choice"+player.name+":"));
            if(choice>=0 &&choice<=player.hand.size()-1){
                list.add(player.hand.get(choice));
            }else{
                end=true;
            }

        }while(end!=true && (choice>=0 &&choice<=player.hand.size()-1));
        return list;
    }


    public static void main(String[] args) {
        int startingPlayer;

        Player[] players=Player.createPlayers();
        Board board=new Board();
        Deck deck=new Deck();

        deck.shuffle();
        deck.distribute(players);
        Board.sortPlayerHands(players);

        StartingRound startingRound=new StartingRound(players,board);
        startingRound.execute();
        Player.printRoles(players);
    }
}