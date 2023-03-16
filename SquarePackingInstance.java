import Piles.ListeChaineePile;

public enum SquarePackingInstance {

    PS_DD_1(7, /**/ 1, 1, 1, 2, 2, 2, 3, 3, 4),
    PS_DD_2(16, /**/ 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6),
    PS_0(20, /**/ 2, 3, 4, 5, 6, 7, 8),
    PS_1(112, /**/ 2, 4, 6, 7, 8, 9, 11, 15, 16, 17, 18, 19, 24, 25, 27, 29, 33, 35, 37, 42, 50),
    PS_2(110, /**/ 2, 3, 4, 6, 7, 8, 12, 13, 14, 15, 16, 17, 18, 21, 22, 23, 24, 26, 27, 28, 50, 60),
    PS_3(110, /**/ 1, 2, 3, 4, 6, 8, 9, 12, 14, 16, 17, 18, 19, 21, 22, 23, 24, 26, 27, 28, 50, 60);

    public final int taille;
    public final ListeChaineePile elements;

    SquarePackingInstance(int taille, int... elements) {
        this.taille = taille;
        this.elements = new ListeChaineePile<>();
        for(int i : elements){
            this.elements.empiler(i);
        }
    }
}