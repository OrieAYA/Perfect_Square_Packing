package Piles;

import java.lang.reflect.Array;

public class TabDynamiquePile<E> extends TabStatiquePile<E> {

    public TabDynamiquePile(int taille) {
        super(taille);
    }

    @Override
    public boolean empiler(E element) {
        if(this.elements.length == this.sommet){
            E[] stock = this.elements;
            this.elements = (E[]) Array.newInstance(Object.class, this.elements.length*2);
            for(int i = 0; i < this.sommet; i++)elements[i] = stock[i];
        }
        return super.empiler(element);
    }
}
