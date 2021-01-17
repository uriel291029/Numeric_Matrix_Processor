package processor

import processor.MatrixOperation.determinantMatrix
import kotlin.math.pow

object MatrixUtil {

    private fun getMinor(matrix: Array<DoubleArray>, rowPos: Int, columnPos: Int): Array<DoubleArray> {
        val minor = mutableListOf<DoubleArray>()
        var row: MutableList<Double>
        for (i in matrix.indices) {
            if (i == rowPos) {
                continue
            }
            row = mutableListOf()
            for (j in matrix.first().indices) {
                if (j == columnPos) {
                    continue
                }
                row.add(matrix[i][j])
            }
            minor += row.toDoubleArray()
        }
        return minor.toTypedArray()
    }

    private fun sign(rowPos: Int, columnPos: Int): Double = (-1.0).pow((rowPos + 1 + columnPos + 1))

    fun cofactor(rowPos: Int, columnPos: Int, matrix: Array<DoubleArray>): Double =
        sign(rowPos, columnPos) * determinantMatrix(
            getMinor(matrix, rowPos, columnPos)
        )
}