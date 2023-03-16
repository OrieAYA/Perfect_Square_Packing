package Piles;

import java.lang.reflect.Array;
import java.util.Iterator;

public class TabStatiquePile<E> implements Pile<E>{

    E[] elements;
    int sommet;

    public TabStatiquePile(int taille){
        elements = (E[]) Array.newInstance(Object.class, taille);
        sommet = 0;
    }

    @Override
    public boolean empiler(E element) {
        if(sommet<elements.length) {
            elements[sommet++] = element;
            return true;
        }
        return false;
    }

    @Override
    public E depiler() {
        if(estVide())return null;
        E e = elements[--sommet];
        elements[sommet] = null;
        return e;
    }

    @Override
    public E sommet() {
        return elements[sommet-1];
    }

    @Override
    public boolean estVide() {
        return (sommet == 0);
    }

    @Override
    public int nbElements() {
        return sommet;
    }

    @Override
    public void vider() {
        while(!this.estVide())this.depiler();
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E>{

        int courant = sommet;

        @Override
        public boolean hasNext() {
            return courant>0;
        }

        @Override
        public E next() {
            return elements[--courant];
        }
    }
}
