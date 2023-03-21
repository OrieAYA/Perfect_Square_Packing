import Liste.List;
import Liste.SimpleList;
import Liste.SimpleSortedList;
import java.util.ArrayList;

public class Packing {

    private class pos{

        int posx;
        int posy;

        private pos(int posx, int posy){

            this.posx = posx;
            this.posy = posy;

        }

    }

    private class bloc{

        pos lengths;
        pos pos;
        int aire;

        private bloc(pos lengths, pos pos){

            this.lengths = lengths;
            this.pos = pos;
            this.aire = lengths.posx * lengths.posy;

        }

    }

    private SimpleSortedList<bloc> remainingBlocs;
    private SimpleList<bloc> placedBlocs;
    private SimpleList<pos> anchorPoints;
    private int taille;
    private int[][] model;
    private int aire;

    private Packing(int taille, SimpleList<Integer> blocs){
        this.taille = taille;
        this.model = new int[taille][taille];
        for(int i : blocs) {
            this.remainingBlocs.ajouter(new bloc(new pos(i,i), new pos(-1, -1)));
        }
        this.aire = taille * taille;
    }


    private void packEverything(){
        if(remainingBlocs.estVide())this.generateModel();
        anchorPoints = this.findAllAnchorPoints();
        List<bloc> aireBlocs = new SimpleSortedList<>();
        for(pos p : anchorPoints){
            aireBlocs.ajouter(new bloc(findLengths(p), p));
            if(aireBlocs.dernier().aire > aire/2)break;
        }
        if(aireBlocs.estVide())return;
        else{
            this.placeBloc(aireBlocs.dernier());
        }
    }

    private void placeBloc(bloc dernier) {
        bloc b = remainingBlocs.dernier();
        while(b.lengths.posx > dernier.lengths.posx && b.lengths.posy > b.lengths.posy || b != null) b = remainingBlocs.decrement(b);
        if(b == null)return;
        remainingBlocs.retirer(b);
        b.pos.posx = dernier.pos.posx;
        b.pos.posy = dernier.pos.posy;
        aire -= dernier.aire;
        placedBlocs.ajouter(b);
    }

    private pos findLengths(pos p) {
        int x = taille - p.posy;
        int y = taille - p.posx;
        for(bloc b : placedBlocs){
            if(b.pos.posy >= p.posy && b.pos.posx + b.lengths.posx > p.posx && b.pos.posy - p.posy < x){
                x = b.pos.posy - p.posy;
                if(b.pos.posx >= p.posx && b.pos.posy + b.lengths.posy > p.posy && b.pos.posx - p.posx < y){
                    y = b.pos.posx - p.posx;
                }
            }
        }
        return new pos(x, y);
    }

    private SimpleList<pos> findAllAnchorPoints() {

        return new SimpleList<pos>();

    }

    private void generateModel() {

    }

    private void printModel() {

    }

    public static void main(String[] args){

        for(SquarePackingInstance e : SquarePackingInstance.values()){
            Packing carre = new Packing(e.taille,e.elements);
            carre.packEverything();
            carre.printModel();
        }

    }

}
