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
            E n = courant.valeur;
            courant = courant.next;
            return n;
        }
    }

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

    @Override
    public E retirer(int index) {
        Noeud e = find(index);
        if(e.next != null && e.before != null) {
            e.next.before = e.before;
            e.before.next = e.next;
        }
        else if(e.next == null){
            e.before.next = null;
        }
        else{
            e.next.before = null;
        }

        if(e.next != null) {
            if (e == debut) debut = e.next;
            else if(e==courant)courant = e.next;
        }
        else if(e.before != null) {
            if (e == fin) fin = e.before;
            else if(e==courant && e.before != null)courant = e.before;
        }

        return e.valeur;
    }

    @Override
    public E dernier() {
        return fin.valeur;
    }

    @Override
    public E recuperer(int index) {
        return find(index).valeur;
    }

    @Override
    public boolean estVide() {
        return debut==fin;
    }

    @Override
    public int nbElements() {
        return fin.index + 1;
    }

    @Override
    public void vider() {
        while(!estVide())retirer(fin.index);
    }

}
