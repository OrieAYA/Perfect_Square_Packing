import Piles.ListeChaineePile;
import Piles.Pile;

public class Packing {

    private static int taille;
    private static int[][] carre;

    private Packing(int taille){
        this.taille = taille;
        carre = new int[taille][taille];
    }


    private void packEverything(Pile e){

    }

    public static void main(String[] args){

        for(SquarePackingInstance e : SquarePackingInstance.values()){
            Packing carre = new Packing(e.taille);
            carre.packEverything(e.elements);
        }

    }

}
