import java.util.LinkedList;

public class StartingRound extends Round{

    public StartingRound(Player[] players,Board b) {
        super(players,b);
    }

    public void execute(){
        int playedstatus,playercount, woncount;
        int index=Player.find2Diamonds(players);
        int lastplayedindex=index;
        LinkedList<Card> sel;
        int turncount=0;
        boolean aceplayed;
        boolean lastturnpass=false;
        boolean playerwon=false;
        int arrayLength=players.length;

        woncount=0;

        playercount=board.activePlayerCount;
        while(playercount>1){
        	aceplayed=false;
            if(players[index].active){
                Player.displayHands(players);
                board.displaytable();
                sel=Main.getSelection(players[index]);
                playedstatus=board.validateandPlay(sel,players[index]);
                if(board.checkwin(players[index],players)){
                    woncount++;
                }

                System.out.println("Turncount:"+turncount);
                System.out.println("Playercount:"+playercount);
                System.out.println();
                switch(playedstatus) {
                    case 0:
                        turncount++;
                        //if player 'Passes' when being last of turn, next player is previous player
                        if((turncount>=playercount)){
                            lastturnpass=true;
                        }
                        break;
                    case 2:
                        //ace is played
                        turncount=0;
                        aceplayed=true;
                        lastplayedindex=index;
                        break;
                    default:
                        lastplayedindex=index;
                        turncount++;
                        break;
                }

            }

            if(turncount>=playercount){

                if(lastturnpass) {//in case last turn player passes
                    index=lastplayedindex;//return to player who last played
                    while (!players[index].active){
                        //index = (index - 1 + arrayLength) % arrayLength;
                        index = (index + 1) % arrayLength;
                    }
                }

                turncount=0;
                board.lastPlayed.clear();
                board.table.clear();
                playercount=playercount-woncount;
                woncount=0;
            }else{
                if (!aceplayed && lastturnpass==false) {
                    index = (index + 1) % arrayLength;
                }
            }
            lastturnpass = false;
        }
        while (!players[index].active){ //return to last remaining player
            index = (index + 1) % arrayLength;
        }
        players[index].role=Board.nextRole(); // assign role to last remaining player
        board.reset();

    }
}
