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

    private SimpleList<bloc> remainingBlocs = new SimpleList<bloc>();
    private SimpleList<bloc> placedBlocs = new SimpleList<bloc>();
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
        if(this.remainingBlocs.estVide()){
            this.printModel();
            return;
        }
        List<bloc> aireBlocs = this.findAllAnchorPoints();
        if(aireBlocs.estVide())return;
        bloc max = aireBlocs.dernier();
        for(bloc p : aireBlocs){
            if(p.aire >= this.aire/2){
                max = p;
                break;
            }
            else if(p.aire > max.aire)max = p;
        }
        this.placeBloc(max);
        this.packEverything();
    }

    private void placeBloc(bloc dernier) {
        bloc b = this.remainingBlocs.dernier();
        for(bloc n : this.remainingBlocs){
            if(fitIn(n, dernier) && b != null && fitIn(b, n)) b = n;
        }
        this.remainingBlocs.retirer(b);
        b.pos.posx = dernier.pos.posx;
        b.pos.posy = dernier.pos.posy;
        this.aire -= dernier.aire;
        this.placedBlocs.ajouter(b);
    }

    private boolean fitIn(bloc n, bloc d){
        return n.lengths.posx <= d.lengths.posx && n.lengths.posy <= d.lengths.posy;
    }

    private pos findLengths(pos p) {
        int x = this.taille - p.posy;
        int y = this.taille - p.posx;
        for(bloc b : this.placedBlocs){
            if(b.pos.posy >= p.posy && b.pos.posx + b.lengths.posx > p.posx && b.pos.posy - p.posy < x){
                x = b.pos.posy - p.posy;
                if(b.pos.posx >= p.posx && b.pos.posy + b.lengths.posy > p.posy && b.pos.posx - p.posx < y){
                    y = b.pos.posx - p.posx;
                }
            }
        }
        return new pos(y, x);
    }

    private SimpleList<bloc> findAllAnchorPoints() {

        SimpleList<bloc> anchors = new SimpleList<>();

        if(this.placedBlocs.estVide()){
            anchors.ajouter(new bloc(new pos(this.taille, this.taille), new pos(0,0)));
        }
        else {
            for (bloc b : this.placedBlocs) {
                //Angle Haut Droit du bloc
                pos x = new pos(b.pos.posx + b.lengths.posx, b.pos.posy);
                pos bLen = findLengths(x);
                if (bLen.posx != 0 && bLen.posy != 0) {
                    for (bloc p : this.placedBlocs) {
                        if (goThroughtTop(p, x)){
                            int xY = x.posy;
                            x.posy = p.pos.posy + p.lengths.posy;
                            bLen.posy += (xY - x.posy);
                        }
                    }
                    anchors.ajouter(new bloc(bLen, x));
                }
                //Angle Bas Gauche du bloc
                pos y = new pos(b.pos.posx, b.pos.posy + b.lengths.posy);
                bLen = findLengths(y);
                if (bLen.posx != 0 && bLen.posy != 0) {
                    for (bloc p : this.placedBlocs) {
                        if (goThroughtLeft(p, y)){
                            int yX = y.posx;
                            y.posx = p.pos.posx + p.lengths.posx;
                            bLen.posx += (yX - y.posx);
                        }
                    }
                    anchors.ajouter(new bloc(bLen, y));
                }
            }
        }

        return anchors;

    }

    private boolean goThroughtLeft(bloc p, pos x){
        return (x.posy >= p.pos.posy && x.posy < p.pos.posy + p.lengths.posy && x.posx >= p.pos.posx + p.lengths.posx);
    }

    private boolean goThroughtTop(bloc p, pos x){
        return (x.posx >= p.pos.posx && x.posx < p.pos.posx + p.lengths.posx && x.posy >= p.pos.posy + p.lengths.posy);
    }

    private boolean goThrought(bloc p, pos x, pos bLen){
        //Point Ancrage dans le carré supérieure gauche ancré du projeté de p et point projeté dans le carré bas droit d'ancrage du point d'ancrage de p
        return ((x.posx < p.pos.posx + p.lengths.posx && x.posy < p.pos.posy + p.lengths.posy) && (x.posx + bLen.posx > p.pos.posx && x.posy + bLen.posy > p.pos.posy));
    }

    private void printModel() {

        for(bloc i : this.placedBlocs){
            System.out.println("Lx : " + i.lengths.posx + ", Ly : " + i.lengths.posy + ", Px : " + i.pos.posx + ", Py : " + i.pos.posy + ", aire : " + i.aire);
        }

        /*
        int[][] model = new int[this.taille][this.taille];

        for(bloc i : this.placedBlocs){
            for(int x = i.pos.posx; x < i.pos.posx + i.lengths.posx; x++){
                for(int y = i.pos.posy; y < i.pos.posy + i.lengths.posy; y++){
                    model[x][y] = this.taille;
                }
            }
        }
         */

    }

    public static void main(String[] args){

        for(SquarePackingInstance e : SquarePackingInstance.values()){
            Packing carre = new Packing(e.taille,e.elements);
            carre.packEverything();
            break;
        }

    }

}
