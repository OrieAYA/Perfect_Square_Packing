package Piles;

public interface Pile<E> {

    /**
     * @param element
     * Empile un élément à la pile
     * Renvoie true si OK, false sinon
     * @return boolean
     */
    boolean empiler(E element);

    /**
     * @param element
     * Depile un élément de la pile
     * Renvoie null si pile vide, E sinon
     * @return E
     */
    E depiler();

    /**
     * Retourne null si pile vide, E ( sommet ) sinon
     * @return E
     */
    E sommet();

    /**
     * Retourne true si la pile est vide, false sinon
     * @return boolean
     */
    boolean estVide();

    /**
     * Retourne le nombre d'élements de la pile
     * @return int
     */
    int nbElements();

    /**
     * Vide la pile
     */
    void vider();

}
