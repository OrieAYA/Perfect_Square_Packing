import Liste.List;
import Liste.SimpleList;
import Liste.SimpleSortedList;

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

    private SimpleList remainingBlocs;
    private SimpleList placedBlocs;
    private SimpleList<pos> anchorPoints;
    private int taille;
    private int[][] model;
    private int aire;

    private Packing(int taille, SimpleList blocs){
        this.taille = taille;
        this.model = new int[taille][taille];
        this.remainingBlocs = blocs;
        this.aire = taille * taille;
    }


    private void packEverything(){
        if(remainingBlocs.estVide())this.generateModel();
        anchorPoints = this.findAllAnchorPoints();
        List<bloc> airesOfBlocs = new SimpleSortedList<>();
        for(pos p : anchorPoints){
            airesOfBlocs.ajouter(new bloc(findLengths(), p));
            if(airesOfBlocs.dernier().aire > aire/2)break;
        }
        if(airesOfBlocs.estVide())return;
        else{
            this.placeBloc(airesOfBlocs.dernier());
        }
        aire = calculeAire(findPointsExtremite());
    }

    private void placeBloc(bloc dernier) {

        

    }

    private SimpleList findPointsExtremite() {

        return new SimpleList<pos>();

    }

    private pos findLengths() {

        return new pos(0,0);

    }

    private SimpleList<pos> findAllAnchorPoints() {

    }

    private void generateModel() {

    }

    private void printModel() {

    }

    private static int calculeAire(List<pos> pointExtremite){
        int aire = 0;
        return aire;
    }

    public static void main(String[] args){

        for(SquarePackingInstance e : SquarePackingInstance.values()){
            Packing carre = new Packing(e.taille,e.elements);
            carre.packEverything();
            carre.printModel();
        }

    }

}
