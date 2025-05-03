import java.util.Collections;
import java.util.LinkedList;

public class Board {
    LinkedList<Card> lastPlayed;
    LinkedList<Card> table;
    Player nextPlayer;
    int activePlayerCount;
    static Role[] roles;
    static boolean[] rolesTable;
    int roleindex;
    boolean loserHasBeenSet;

    public Board(){
        table= new LinkedList<>();
        lastPlayed= new LinkedList<>();
        roles=Role.values();
        roleindex=0;
        loserHasBeenSet=false;
        activePlayerCount=4;

        rolesTable=new boolean[4];
        for(int i=0;i<4;i++){
            rolesTable[i]=false;
        }
    }

    public void reset(){
        lastPlayed=new LinkedList<>();
        table=new LinkedList<>();
        activePlayerCount=4;
        roles=Role.values();
        for(int i=0;i<4;i++){
            rolesTable[i]=false;
        }
        roleindex=0;
        loserHasBeenSet=false;
    }


    private static Role getRoleFromIndex(int i){
        if (i < 0 || i > 4) {
            System.out.println("Error: Invalid roles index(out of bounds)");
            return null;
        }else{
            return roles[i];
        }
    }

    public boolean isAce(LinkedList<Card> selection){
        if(selection.getFirst().value==14&& selection.size()==1){
            return true;
        }else{
            return false;
        }
    }

    public int validateandPlay(LinkedList<Card> selection, Player player){
        //returns 0 for pass
        //returns 1 for normal play on new table
        //returns 2 for played ace

        if(selection.isEmpty()){
            //pass
            return 0;
        }

        if(table.isEmpty()){
            //playing on new table
            if(isSame(selection)&& selection.getFirst().value!=14){
                playNormal(selection,player);
                return 1;
            }else{
                return 0;
            }
        }else{
            //ace detection
            if(isAce(selection)){
                playAce(selection,player);
                return 2;
            }
            //normal play
            if(isSame(selection) && selection.getFirst().value!=14 && (selection.size()==lastPlayed.size())){
                if(selection.getFirst().value>lastPlayed.getFirst().value){
                    playNormal(selection, player);
                    return 3;
                }else{
                    return  0;
                }
            }else{
                return 0;
            }
        }
    }

    private void playAce(LinkedList<Card> selection, Player player) {
        //place ace
        player.removeCards(selection);
        table=new LinkedList<>();
        nextPlayer=player;
    }

    public void playNormal(LinkedList<Card> selection, Player player) {
        lastPlayed=selection;
        table.addAll(selection);
        player.removeCards(selection);
    }

    private boolean isSame(LinkedList<Card>cards){
        for(int i=1;i< cards.size();i++){
            if(cards.get(i-1).value!=cards.get(i).value){
                return false;
            }
        }
        return true;
    }

    public void displaytable(){
        System.out.print("Table:");
        for(Card c:table){
            c.print();
        }
        System.out.println();
    }

    public static Role nextRole(){
        int i=0;
        while(i<4){
            if(rolesTable[i]==false){
                rolesTable[i]=true;
                return getRoleFromIndex(i);
            }
            i++;
        }
        return null;
    }

    public boolean checkwin(Player p,Player[] players){
        if(p.hand.isEmpty()){
            //set next available role
            p.role=nextRole();
            p.active=false;
            return true;
        }else{
            return checkLastAces(p,players);
        }
    }

    //checks if remaining hand is full of aces and if P/VP has too much aces
    //true-->player won
    private boolean checkLastAces(Player p, Player[] players){
        if(isSame(p.hand) && p.hand.getFirst().value==14){
            if(p.role==Role.P){
                if(p.hand.size()>=3) {
                    //set minimum aval. role
                    if(!loserHasBeenSet){
                        p.role = Role.L;
                        loserHasBeenSet=true;
                    }else{
                        p.role=Role.VL;
                    }
                    p.active = false;
                }else{
                    distributeAces(p, players);
                }
                return true;
            }
            if(p.role==Role.VP){
                if(p.hand.size()>=2) {
                    if(!loserHasBeenSet){
                        p.role = Role.L;
                        loserHasBeenSet=true;
                    }else{
                        p.role=Role.VL;
                    }
                    p.active = false;
                }else{
                    distributeAces(p,players);
                }
                return true;
            }
        }else {
        	return false;
        }
		return loserHasBeenSet;
    }

    private void distributeAces(Player p, Player[] players) {
        int choice;
        boolean weiter;
        for(Card c:p.hand){
            weiter=true;
            while(weiter){
                choice= Integer.parseInt(System.console().readLine("Enter player:"));
                if(choice>=0 && choice<4){
                    if(players[choice].role==Role.VL||players[choice].role==Role.L){
                        players[choice].hand.add(c);
                        weiter=false;
                    }
                }
            }
        }
    }

    public static void sortPlayerHands(Player[] players){
        for(Player p:players){
            Collections.sort(p.hand);
        }
    }
}
