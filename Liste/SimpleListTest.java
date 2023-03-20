package Liste;

import static org.junit.jupiter.api.Assertions.*;

class SimpleListTest {

    @org.junit.jupiter.api.Test
    void ajouter() {
        List<Integer> l = new SimpleList<Integer>();
        l.ajouter(15);
        assert l.iterator().next() == 15;
    }

    @org.junit.jupiter.api.Test
    void retirer() {
        List<Integer> l = new SimpleList<Integer>();
        l.ajouter(15);
        assert l.retirer(0) == 15 && l.estVide();
    }

    @org.junit.jupiter.api.Test
    void dernier() {
    }

    @org.junit.jupiter.api.Test
    void recuperer() {
    }

    @org.junit.jupiter.api.Test
    void estVide() {
        List<Integer> l = new SimpleList<Integer>();
        int[] i = new int[l.nbElements()];
    }

    @org.junit.jupiter.api.Test
    void nbElements() {
    }

    @org.junit.jupiter.api.Test
    void vider() {
    }
}