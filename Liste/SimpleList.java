package Liste;

import java.util.Iterator;

public class SimpleList<E> implements List<E>{

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E>{

        Noeud courant = debut;

        @Override
        public boolean hasNext() {
            return courant!=null;
        }

        @Override
        public E next() {
            E n = (E) courant.valeur;
            courant = courant.next;
            return n;
        }
    }

    public SimpleList(){
        debut = null;
        courant = debut;
        fin = debut;
    }

    private Noeud debut;
    private Noeud courant;
    private Noeud fin;

    @Override
    public boolean ajouter(E element){
        Noeud e = new Noeud(element, null, null, 0);
        if(estVide()){
            debut = e;
            courant = debut;
        }
        else{
            e.before = fin;
            e.index = e.before.index+1;
            if(e.index%2==0)courant = courant.next;
            fin.next = e;
        }
        fin = e;
        return true;
    }

    public E get(int index){
        return (E) find(index).valeur;
    }

    private Noeud find(int index){
        Noeud e;
        boolean up = false;
        if(index < courant.index && index < courant.index/2){
            e = debut;
            up = true;
        }
        else if(index < courant.index && index > courant.index/2){
            e = courant;
        }
        else if(index > courant.index && fin.index - index < courant.index + courant.index/2){
            e = courant;
            up = true;
        }
        else{
            e = fin;
        }

        if(up) while(e.index != index)e=e.next;
        else while(e.index != index)e=e.before;

        Noeud f = e.next;
        while (f != null){
            f.index--;
            f = f.next;
        }

        return e;
    }

    public Noeud find(E f){
        Noeud e = debut;
        while(e != null && e.valeur!=f){
            e = e.next;
        }
        return e;
    }

    @Override
    public E retirer(E element) {
        return retirer(find(element));
    }

    public E retirer(Noeud e) {
        if(e.next == null && e.before == null){
            E v = (E) e.valeur;
            debut = null;
            fin = debut;
            courant = debut;
            return v;
        }
        //Si le Noeud est entre 2 Noeud
        if(e.next != null && e.before != null) {
            e.next.before = e.before;
            e.before.next = e.next;
        }
        //Si le Noeud est à la fin
        else if(e.next == null){
            e.before.next = null;
        }
        //Si le Noeud est au début
        else{
            e.next.before = null;
        }

        if (e == debut) debut = e.next;
        else if (e == fin) fin = e.before;
        if(e==courant) {
            if (e.next != null && e.index%2 == 0)courant = e.next;
            else if (e.before != null)courant = e.before;
        }

        Noeud n = e;
        while ((n = n.next) != null) n.index--;

        return (E) e.valeur;
    }

    @Override
    public E dernier() {
        return (E) fin.valeur;
    }

    @Override
    public E recuperer(int index) {
        return (E) find(index).valeur;
    }

    @Override
    public boolean estVide() {
        return debut==null;
    }

    @Override
    public int nbElements() {
        return fin.index + 1;
    }

    @Override
    public void vider() {
        while(!estVide())retirer(fin);
    }

}
