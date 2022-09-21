import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class Matrix {
    private int row;
    private int col;
    private double[][] matrix;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        matrix = new double[this.row][this.col];
    }

    public Matrix() {
        row = 1;
        col = 1;
        matrix = new double[row][col];
    }

    public void setRow(int row) {
        if (row <= 0) {
            throw new ArithmeticException("setRow: value must be greater than zero");
        }
        this.row = row;
        matrix = new double[this.row][col];
    }

    public void setCol(int col) {
        if (col <= 0) {
            throw new ArrayIndexOutOfBoundsException("setCol: value must be greater than zero");
        }
        this.col = col;
        matrix = new double[row][this.col];
    }

    public void setMatrix(int row, int col) {
        if (row <= 0 || col <= 0) {
            throw new ArrayIndexOutOfBoundsException("setMatrix: value must be greater than zero");
        }
        this.row = row;
        this.col = col;
        matrix = new double[this.row][this.col];
    }

    public void setOneCell(int posRow, int posCol, double value) {
        if (row < 0 || col < 0 || posRow > row || posCol > col) {
            throw new ArrayIndexOutOfBoundsException("setOneCell: value must be greater -1 and less row/col");
        }
        matrix[posRow][posCol] = value;
    }

    public double getOneCell(int posRow, int posCol) {
        if (row < 0 || col < 0 || posRow > row || posCol > col) {
            throw new ArrayIndexOutOfBoundsException("getOneCell: value must be greater -1 and less row/col");
        }
        return matrix[posRow][posCol];
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    private void sumOrSubMatrix(@NotNull Matrix other, int sign) {
        if (row != other.row || col != other.col) {
            throw new ArrayIndexOutOfBoundsException("sumOrSubMatrix: Rows/columns of matrices are not equal");
        }
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                matrix[i][j] += (sign * other.matrix[i][j]);
            }
        }
    }

    public void sumMatrix(@NotNull Matrix other) {
        sumOrSubMatrix(other, 1);
    }

    public void subMatrix(@NotNull Matrix other) {
        sumOrSubMatrix(other, -1);
    }

    public boolean equalMatrix(@NotNull Matrix other) {
        boolean result = row == other.row && col == other.col;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (Math.abs(matrix[i][j] - other.matrix[i][j]) >= 1e-15) {
                    return false;
                }
            }
        }
        return result;
    }

    public void mulMatrixOnNumber(double number) {
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                matrix[i][j] *= number;
            }
        }
    }

    public void mulMatrix(@NotNull Matrix other) {
        if (col != other.row) {
            throw new ArithmeticException("mulMatrix: Columns are not equal to rows");
        }
        Matrix tmp = new Matrix(row, other.col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < other.col; j++) {
                for (int n = 0; n < other.row; n++) {
                    tmp.matrix[i][j] += matrix[i][n] * other.matrix[n][j];
                }
            }
        }
        col = other.col;
        matrix = tmp.matrix;
    }

    public Matrix transpose() {
       Matrix tmp = new Matrix(col, row);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                tmp.matrix[j][i] = matrix[i][j];
            }
        }
        return tmp;
    }

    private @NotNull Matrix getReducedMatrix(int rowsReduced, int colsReduced) {
        Matrix tmp = new Matrix(row - 1, col - 1);
        int newRows = 0, newCols = 0;
        for (int i = 0; i < row; i++) {
            if (i != rowsReduced) {
                for (int j = 0; j < row; j++) {
                    if (j != colsReduced) {
                        tmp.matrix[newRows][newCols] = matrix[i][j];
                        newCols++;
                    }
                }
                newRows++;
                newCols = 0;
            }
        }
        return tmp;
    }

    public double determinant() {
        double det = 0.0;
        if (row != col) {
            throw new ArrayIndexOutOfBoundsException("determinant: Error, matrix is not square");
        }
        if (row == 1) {
            det = matrix[0][0];
        } else if (row == 2) {
            det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else {
            for (int i = 0; i < row; i++) {
                Matrix tmp = getReducedMatrix(i, 0);
                det += matrix[i][0] * Math.pow(-1, i) * tmp.determinant();
            }
        }
        return det;
    }

    public @NotNull Matrix calcComplements() {
        if (row != col || row == 1) {
            throw new ArrayIndexOutOfBoundsException("calcComplements: Error, matrix is not square");
        }
        Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Matrix tmp = getReducedMatrix(i, j);
                result.matrix[i][j] = Math.pow(-1, i + j) * tmp.determinant();
            }
        }
        return result;
    }

    public @NotNull Matrix inverseMatrix() {
        double det = determinant();
        if (row != col || det == 0.0 || row == 1) {
            throw new ArrayIndexOutOfBoundsException("inverseMatrix: Determinant is zero, or matrix is not square");
        }
        Matrix result = calcComplements().transpose();
        result.mulMatrixOnNumber(1.0 / det);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix1 = (Matrix) o;
        return row == matrix1.row && col == matrix1.col && Arrays.deepEquals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(row, col);
        result = 31 * result + Arrays.deepHashCode(matrix);
        return result;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "row=" + row +
                ", col=" + col +
                ", matrix=" + Arrays.deepToString(matrix) +
                '}';
    }
}
