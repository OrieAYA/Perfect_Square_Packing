import Piles.ListeChaineePile;
import Piles.Pile;

public class Packing {


    private class bloc{

        private static int[] taille = new int[2];
        private ListeChaineePile blocs;

    }
    private ListeChaineePile blocs;

    private Packing(int taille, ListeChaineePile blocs){
        this.taille = taille;
        model = new int[taille][taille];
        this.blocs = blocs;
    }


    private void packEverything(Pile e){

    }

    public static void main(String[] args){

        for(SquarePackingInstance e : SquarePackingInstance.values()){
            Packing carre = new Packing(e.taille,e.elements);
        }

    }

}
