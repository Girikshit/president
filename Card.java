public class Card implements  Comparable<Card>{
    int value;
    int sign;

    public Card(int val,int sgn){
        value=val;
        sign=sgn;
    }

    public void print(){
        System.out.print("("+value +" " +sign+"),");
    }

    @Override
    public int compareTo(Card c) {
        return Integer.compare(this.value, c.value);
    }
}
