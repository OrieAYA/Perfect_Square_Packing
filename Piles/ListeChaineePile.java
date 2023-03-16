package Piles;
import java.lang.reflect.Array;
import java.util.Iterator;

public class ListeChaineePile<E> implements Pile<E>{

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E>{

        Noeud courant = sommet;

        @Override
        public boolean hasNext() {
            return courant!=debut;
        }

        @Override
        public E next() {
            return (E) (courant = courant.before);
        }
    }

    private class Noeud{
        E valeur;
        Noeud next;
        Noeud before;

        Noeud(E valeur, Noeud next, Noeud before){
            this.valeur = valeur;
            this.next = next;
            this.before = before;
        }
    }

    public ListeChaineePile(){
        debut = null;
        sommet = debut;
    }

    private Noeud debut;

    private Noeud sommet;

    @Override
    public boolean empiler(E element) {
        Noeud node = new Noeud(element,null,sommet);
        if(sommet==null){
            debut = node;
        }
        else{
            sommet.next = node;
        }
        sommet = node;
        return true;
    }

    @Override
    public E depiler() {
        Noeud s = sommet;
        if(debut == sommet) {
            debut = null;
            sommet = null;
        }
        else {
            sommet.before.next = null;
            sommet = sommet.before;
        }
        return s.valeur;
    }

    @Override
    public E sommet() {
        if(!this.estVide())return sommet.valeur;
        return null;
    }

    @Override
    public boolean estVide() {
        return debut == null;
    }

    @Override
    public int nbElements() {
        int count = 0;
        Noeud node = debut;
        while (node != null){
            count++;
            node = node.next;
        }
        return count;
    }

    @Override
    public void vider() {
        while(!this.estVide())depiler();
    }
}
