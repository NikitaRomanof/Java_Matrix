import org.junit.jupiter.api.Assertions;

class MatrixTest {

    @org.junit.jupiter.api.Test
    void setRow() {
        Matrix first = new Matrix(2, 2);
        first.setRow(3);
        Assertions.assertEquals(first.getRow(), 3);
    }

    @org.junit.jupiter.api.Test
    void setCol() {
        Matrix first = new Matrix(2, 2);
        first.setCol(3);
        Assertions.assertEquals(first.getCol(), 3);
    }

    @org.junit.jupiter.api.Test
    void setMatrix() {
        Matrix first = new Matrix(2, 2);
        first.setMatrix(4,4);
        Matrix second = new Matrix(4, 4);
        Assertions.assertArrayEquals(first.getMatrix(), second.getMatrix());
    }

    @org.junit.jupiter.api.Test
    void getRow() {
        Matrix first = new Matrix(2, 2);
        int check = first.getRow();
        Assertions.assertEquals(check, 2);
    }

    @org.junit.jupiter.api.Test
    void getCol() {
        Matrix first = new Matrix(2, 2);
        int check = first.getCol();
        Assertions.assertEquals(check, 2);
    }

    @org.junit.jupiter.api.Test
    void setOneCell_and_getOneCell() {
        Matrix first = new Matrix(3, 3);
        first.setOneCell(0,0,10.987);
        Assertions.assertEquals(first.getOneCell(0, 0), 10.987);
    }

    @org.junit.jupiter.api.Test
    void getMatrix() {
        Matrix first = new Matrix(3, 3);
        Matrix second = new Matrix(3, 3);
        Assertions.assertArrayEquals(first.getMatrix(), second.getMatrix());
    }

    @org.junit.jupiter.api.Test
    void sumMatrix() {
        Matrix a = new Matrix(1, 1);
        a.setOneCell(0, 0, 4.01);
        Matrix b = new Matrix(1, 1);
        b.setOneCell(0, 0, 5.01);
        a.sumMatrix(b);
        Matrix c = new Matrix(1, 1);
        c.setOneCell(0, 0, 9.02);
        Assertions.assertArrayEquals(a.getMatrix(), c.getMatrix());
    }

    @org.junit.jupiter.api.Test
    void subMatrix() {
        Matrix a = new Matrix(1, 1);
        a.setOneCell(0, 0, 4.0);
        Matrix b = new Matrix(1, 1);
        b.setOneCell(0, 0, 3.0);
        a.subMatrix(b);
        Matrix c = new Matrix(1, 1);
        c.setOneCell(0, 0, 1.0);
        Assertions.assertArrayEquals(a.getMatrix(), c.getMatrix());
    }

    @org.junit.jupiter.api.Test
    void equalMatrix_true() {
        Matrix matrix = new Matrix(5, 5);
        Matrix result= new Matrix(5, 5);
        Assertions.assertTrue(result.equalMatrix(matrix));
    }

    @org.junit.jupiter.api.Test
    void equalMatrix_true2() {
        Matrix matrix = new Matrix(5, 5);
        Matrix result= new Matrix(5, 5);
        matrix.setOneCell(0, 0, 1.12345678);
        result.setOneCell(0, 0, 1.12345678);
        Assertions.assertTrue(result.equalMatrix(matrix));
    }

    @org.junit.jupiter.api.Test
    void equalMatrix_false() {
        Matrix matrix = new Matrix(5, 5);
        Matrix result= new Matrix(5, 5);
        matrix.setOneCell(0, 0, 10.98);
        Assertions.assertFalse(result.equalMatrix(matrix));
    }

    @org.junit.jupiter.api.Test
    void mulMatrixOnNumber() {
        Matrix a = new Matrix(2, 2);
        a.setOneCell(0, 0, 2.0);
        a.mulMatrixOnNumber(5);
        Matrix b = new Matrix(2, 2);
        b.setOneCell(0, 0, 10.0);
        Assertions.assertTrue(b.equalMatrix(a));

    }

    @org.junit.jupiter.api.Test
    void mulMatrix() {

        Matrix a = new Matrix(1, 1);
        a.setOneCell(0, 0, 2.0);
        Matrix b = new Matrix(1, 1);
        b.setOneCell(0, 0, 2.0);
        a.mulMatrix(b);
        Matrix c = new Matrix(1, 1);
        c.setOneCell(0, 0, 4.0);
        Assertions.assertTrue(c.equalMatrix(a));
    }

    @org.junit.jupiter.api.Test
    void transpose() {
        Matrix a = new Matrix(3, 2);
        a.setOneCell(0, 0, 1.0);
        a.setOneCell(0, 1, 2.0);
        a.setOneCell(1, 0, 3.0);
        a.setOneCell(1, 1, 4.0);
        a.setOneCell(2, 0, 5.0);
        a.setOneCell(2, 1, 6.0);
        Matrix b = new Matrix(2, 3);
        b.setOneCell(0, 0, 1.0);
        b.setOneCell(0, 1, 3.0);
        b.setOneCell(0, 2, 5.0);
        b.setOneCell(1, 0, 2.0);
        b.setOneCell(1, 1, 4.0);
        b.setOneCell(1, 2, 6.0);
        a = a.transpose();
        Assertions.assertTrue(a.equalMatrix(b));

    }

    @org.junit.jupiter.api.Test
    void determinant() {
        Matrix a = new Matrix(3, 3);
        a.setOneCell(0, 0, 1.0);
        a.setOneCell(0, 1, 2.0);
        a.setOneCell(0, 2, 3.0);
        a.setOneCell(1, 0, 4.0);
        a.setOneCell(1, 1, 5.0);
        a.setOneCell(1, 2, 6.0);
        a.setOneCell(2, 0, 7.0);
        a.setOneCell(2, 1, 8.0);
        a.setOneCell(2, 2, 9.0);
        Assertions.assertEquals(0.0, a.determinant());
    }

    @org.junit.jupiter.api.Test
    void calcComplements() {
        Matrix a = new Matrix(3, 3);
        a.setOneCell(0, 0, 1.0);
        a.setOneCell(0, 1, 2.0);
        a.setOneCell(0, 2, 3.0);
        a.setOneCell(1, 0, 0.0);
        a.setOneCell(1, 1, 4.0);
        a.setOneCell(1, 2, 2.0);
        a.setOneCell(2, 0, 5.0);
        a.setOneCell(2, 1, 2.0);
        a.setOneCell(2, 2, 1.0);
        Matrix b = new Matrix(3, 3);
        b.setOneCell(0, 0, 0.0);
        b.setOneCell(0, 1, 10.0);
        b.setOneCell(0, 2, -20.0);
        b.setOneCell(1, 0, 4.0);
        b.setOneCell(1, 1, -14.0);
        b.setOneCell(1, 2, 8.0);
        b.setOneCell(2, 0, -8.0);
        b.setOneCell(2, 1, -2.0);
        b.setOneCell(2, 2, 4.0);
        a = a.calcComplements();
        Assertions.assertTrue(a.equalMatrix(b));
    }

    @org.junit.jupiter.api.Test
    void inverseMatrix() {
        Matrix a = new Matrix(3, 3);
        a.setOneCell(0, 0, 2.0);
        a.setOneCell(0, 1, 5.0);
        a.setOneCell(0, 2, 7.0);
        a.setOneCell(1, 0, 6.0);
        a.setOneCell(1, 1, 3.0);
        a.setOneCell(1, 2, 4.0);
        a.setOneCell(2, 0, 5.0);
        a.setOneCell(2, 1, -2.0);
        a.setOneCell(2, 2, -3.0);
        Matrix b = new Matrix(3, 3);
        b.setOneCell(0, 0, 1.0);
        b.setOneCell(0, 1, -1.0);
        b.setOneCell(0, 2, 1.0);
        b.setOneCell(1, 0, -38.0);
        b.setOneCell(1, 1, 41.0);
        b.setOneCell(1, 2, -34.0);
        b.setOneCell(2, 0, 27.0);
        b.setOneCell(2, 1, -29.0);
        b.setOneCell(2, 2, 24.0);
        a = a.inverseMatrix();
        Assertions.assertTrue(a.equalMatrix(b));
    }
}