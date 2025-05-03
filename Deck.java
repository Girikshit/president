import java.util.LinkedList;
import java.util.Random;

public class Deck {
    LinkedList<Card> cards;

    public Deck(){
        cards=new LinkedList<>();
        int i=0;
        int v=2;
        int s=0;
        while (i<52){
            if(v==15) {
                v = 2;
                s++;
            }
            cards.add(new Card(v,s));
            v++;
            i++;
        }
    }

    public void shuffle(){
        LinkedList<Card> dck=new LinkedList<>();
        int max= cards.size();
        while(max!=0) {
            Random random = new Random();
            int randomNumber = random.nextInt(max);
            dck.add( cards.remove(randomNumber));
            max--;
        }
        cards=dck;
    }

    public  void distribute(Player[] arr){
        int size= cards.size();
        while(size!=0){
            for(Player p:arr){
                p.hand.add(cards.pop());
                size--;
            }
        }
    }

    public void print(){
        for(Card c:cards) c.print();
        System.out.println();
    }

}
