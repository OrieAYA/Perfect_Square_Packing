package Liste;

public class SimpleSortedList<E> extends SimpleList<E>{

    private class Noeud{
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

    private Noeud debut;
    private Noeud courant;
    private Noeud fin;

    public boolean ajouter(E element){
        Noeud e = new Noeud(element, null, null, 0);
        if(estVide()){
            debut = e;
            courant = debut;
        }
        else{
            Noeud cr = debut;
            while (e.valeur.hashCode() < cr.valeur.hashCode() || cr != null)cr = cr.next;
            e.before = cr;
            e.next = cr.next;
            cr.next = e;
            e.index = e.before.index+1;
            if(e.index%2==0)courant = courant.next;
            if(cr == debut)debut = e;
            if(cr == fin)fin = e;
        }
        return true;
    }

}
