package Liste;

public interface List<E> extends Iterable<E> {

    /**
     * @param element
     * Empile un élément à la pile
     * Renvoie true si OK, false sinon
     * @return boolean
     */
    boolean ajouter(E element);

    /**
     * Depile un élément de la pile
     * Renvoie null si pile vide, E sinon
     * @return E
     */
    E retirer(int index);

    /**
     * Retourne null si pile vide, E ( sommet ) sinon
     * @return E
     */
    E dernier();

    /**
     * @param index
     * Retourne l'élément à l'index donné
     * @return E
     */
    E recuperer(int index);

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
