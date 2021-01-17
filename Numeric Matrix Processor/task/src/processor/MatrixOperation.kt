package processor

import processor.MatrixUtil.cofactor

object MatrixOperation {

    fun addMatrices(firstMatrix: Array<DoubleArray>, secondMatrix: Array<DoubleArray>): Array<DoubleArray> {
        for (i in firstMatrix.indices) {
            for (j in firstMatrix[i].indices) {
                firstMatrix[i][j] += secondMatrix[i][j]
            }
        }
        return firstMatrix
    }

    fun multiplyMatrix(firstMatrix: Array<DoubleArray>, scalar: Double): Array<DoubleArray> {
        for (i in firstMatrix.indices) {
            for (j in firstMatrix[i].indices) {
                firstMatrix[i][j] *= scalar
            }
        }
        return firstMatrix
    }

    fun multiplyMatrices(firstMatrix: Array<DoubleArray>, secondMatrix: Array<DoubleArray>): Array<DoubleArray> {
        val resultMatrix = Array(firstMatrix.size) { DoubleArray(secondMatrix.first().size) }
        val rangeJ = secondMatrix.first().indices
        val rangeK = firstMatrix.first().indices
        for (i in firstMatrix.indices) {
            for (j in rangeJ) {
                for (k in rangeK) {
                    resultMatrix[i][j] += firstMatrix[i][k] * secondMatrix[k][j]
                }
            }
        }
        return resultMatrix
    }

    fun transposeMainDiagonal(matrix: Array<DoubleArray>): Array<DoubleArray> {
        val transposedMatrix = Array(matrix.size) { DoubleArray(matrix.first().size) }
        val rangeJ = matrix.first().indices
        for (i in matrix.indices) {
            for (j in rangeJ) {
                transposedMatrix[j][i] = matrix[i][j]
            }
        }
        return transposedMatrix
    }

    fun transposeSideDiagonal(matrix: Array<DoubleArray>): Array<DoubleArray> {
        val transposedMatrix = Array(matrix.size) { DoubleArray(matrix.first().size) }
        var rowT: Int
        var columnM: Int
        var columnT = matrix.size - 1
        var rowM = 0
        while (rowM <= matrix.size - 1 && columnT >= 0) {
            rowT = matrix.size - 1
            columnM = 0
            while (rowT >= 0 && columnM <= matrix.size - 1) {
                transposedMatrix[rowT][columnT] = matrix[rowM][columnM]
                rowT--
                columnM++
            }
            rowM++
            columnT--
        }
        return transposedMatrix
    }

    fun transposeVerticalLine(matrix: Array<DoubleArray>): Array<DoubleArray> {
        val transposedMatrix = Array(matrix.size) { DoubleArray(matrix.first().size) }
        val rangeJ = matrix.first().indices
        for (i in matrix.indices) {
            for (j in rangeJ) {
                transposedMatrix[i][matrix.size - 1 - j] = matrix[i][j]
            }
        }
        return transposedMatrix
    }

    fun transposeHorizontalLine(matrix: Array<DoubleArray>): Array<DoubleArray> {
        val transposedMatrix = Array(matrix.size) { DoubleArray(matrix.first().size) }
        val columnSize = matrix.first().size
        val rangeJ = matrix.first().indices
        for (i in matrix.indices) {
            for (j in rangeJ) {
                transposedMatrix[columnSize - 1 - i][j] = matrix[i][j]
            }
        }
        return transposedMatrix
    }

    fun determinantMatrix(matrix: Array<DoubleArray>): Double {
        if (matrix.size == 1) {
            return matrix[0][0]
        }
        if (matrix.size == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1]
        }
        var determinant = 0.0
        for (j in matrix.first().indices) {
            determinant += matrix[0][j] * cofactor(0, j, matrix)
        }
        return determinant
    }

    fun inverseMatrix(matrix: Array<DoubleArray>): Array<DoubleArray> {
        val cofactorMatrix = getCofactorMatrix(matrix)
        val transposedCofactorMatrix = transposeMainDiagonal(cofactorMatrix)
        val determinant = determinantMatrix(matrix)
        if (determinant == 0.0) {
            throw Exception("This matrix doesn't have an inverse.")
        }
        return multiplyMatrix(transposedCofactorMatrix, 1 / determinant)
    }

    fun getCofactorMatrix(matrix: Array<DoubleArray>): Array<DoubleArray> {
        val cofactorMatrix = Array(matrix.size) { DoubleArray(matrix.first().size) }
        val rangeJ = matrix.first().indices
        for (i in matrix.indices) {
            for (j in rangeJ) {
                cofactorMatrix[i][j] = cofactor(i, j, matrix)
            }
        }
        return cofactorMatrix
    }
}