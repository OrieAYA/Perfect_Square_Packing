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
    private int aire;

    private Packing(int taille, SimpleList<Integer> blocs){
        this.taille = taille;
        for(int i : blocs) {
            this.remainingBlocs.ajouter(new bloc(new pos(i,i), new pos(-1, -1)));
        }
        this.aire = taille * taille;
    }


    private void packEverything(){
        if(remainingBlocs.estVide())this.printModel();
        List<bloc> aireBlocs = this.findAllAnchorPoints();
        for(pos p : anchorPoints){
            if(aireBlocs.dernier().aire > aire/2)break;
        }
        if(aireBlocs.estVide())return;
        else{
            this.placeBloc(aireBlocs.dernier());
            this.packEverything();
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
        int z;
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

    private SimpleList<bloc> findAllAnchorPoints() {

        SimpleList<bloc> anchors = new SimpleList<>();

        for(bloc b : placedBlocs){
            pos x = new pos(b.pos.posx + b.lengths.posx, b.pos.posy);
            pos d = findLengths(x);
            if(d.posx != 0 && d.posy != 0){
                int y = 0;
                for(bloc p : placedBlocs){
                    if(p.pos.posy + p.lengths.posy <= d.posy && p.pos.posx + p.lengths.posx > d.posy && p.pos.posy + p.lengths.posy > y) y = p.pos.posy + p.lengths.posy;
                }
                d.posy = y;
                anchors.ajouter(new bloc(x, d));
            }
            pos y = new pos(b.pos.posx + b.lengths.posx, b.pos.posy);
            d = findLengths(y);
            if(d.posx != 0 && d.posy != 0){
                int z = 0;
                for(bloc p : placedBlocs){
                    if(p.pos.posx + p.lengths.posx <= d.posx && p.pos.posy + p.lengths.posy > d.posx && p.pos.posx + p.lengths.posx > z) z = p.pos.posx + p.lengths.posx;
                }
                d.posy = z;
                anchors.ajouter(new bloc(y, d));
            }
        }

        return anchors;

    }

    private bloc findAnchor(){

        return new bloc(null, null);

    }

    private void printModel() {

        int[][] model = new int[taille][taille];

        for(bloc i : placedBlocs){
            for(int x = i.pos.posx; x < i.pos.posx + i.lengths.posx; x++){
                for(int y = i.pos.posy; y < i.pos.posy + i.lengths.posy; y++){
                    model[x][y] = taille;
                }
            }
        }

    }

    public static void main(String[] args){

        for(SquarePackingInstance e : SquarePackingInstance.values()){
            Packing carre = new Packing(e.taille,e.elements);
            carre.packEverything();
        }

    }

}
