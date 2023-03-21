package Liste;

public class Noeud<E>{
    E valeur;
    int index;
    Noeud next;
    Noeud before;

    Noeud(E valeur, Noeud next, Noeud before, int index){
        this.valeur = valeur;
        this.index = index;
        this.next = next;
        this.before = before;
    }
}