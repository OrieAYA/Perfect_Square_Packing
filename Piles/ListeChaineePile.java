package Piles;

public class ListeChaineePile<E> implements Pile<E> {

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
        fin = null;
    }

    private Noeud debut;
    private Noeud fin;

    @Override
    public boolean empiler(E element) {
        if(debut==null){
            debut = new Noeud(element,null,null);
            fin = debut;
        }
        else{
            Noeud node = new Noeud(element,null,fin);
            fin.next = node;
            fin = node;
        }
        return true;
    }

    @Override
    public E depiler() {
        if(debut == fin) {
            debut = null;
            fin = null;
            return null;
        }
        fin.before.next = null;
        fin = fin.before;
        return fin.valeur;
    }

    @Override
    public E sommet() {
        if(fin.valeur!=null)return fin.valeur;
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
