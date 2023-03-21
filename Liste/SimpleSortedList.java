package Liste;

public class SimpleSortedList<E> extends SimpleList<E>{

    private Noeud debut;
    private Noeud courant;
    private Noeud fin;

    public SimpleSortedList(){
        super();
    }

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

    public E decrement(E element){
        return (E) find(element).before.valeur;
    }

}
