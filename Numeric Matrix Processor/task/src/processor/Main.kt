package processor

import processor.MatrixOperation.determinantMatrix
import processor.MatrixOperation.inverseMatrix
import processor.MatrixOperation.transposeHorizontalLine
import processor.MatrixOperation.transposeMainDiagonal
import processor.MatrixOperation.transposeSideDiagonal
import processor.MatrixOperation.transposeVerticalLine

fun main() {
    while (true) {
        printMenu()
        print("Your choice: ")
        try {
            when (readLine()!!.toInt()) {
                0 -> break
                1 -> processAddMatrices()
                2 -> processMultiplyMatrix()
                3 -> processMultiplyMatrices()
                4 -> processTransposeMatrix()
                5 -> processDeterminantMatrix()
                6 -> processInverseMatrix()
            }
        } catch (e: Exception) {
            println(e.message)
            println()
        }
    }
}

fun printMenu() {
    println("1. Add matrices")
    println("2. Multiply matrix by a constant")
    println("3. Multiply matrices")
    println("4. Transpose matrix")
    println("5. Calculate a determinant")
    println("6. Inverse matrix")
    println("0. Exit")
}

fun printTransposeMenu() {
    println("1. Main Diagonal")
    println("2. Side Diagonal")
    println("3. Vertical line")
    println("4. Horizontal line")
}

fun processAddMatrices() {
    print("Enter size of first matrix: ")
    val sizeFirstMatrix = readLine()!!.split(" ")
    val rowNumberFirstMatrix = sizeFirstMatrix[0].toInt()
    val columnNumberFirstMatrix = sizeFirstMatrix[1].toInt()
    val firstMatrix = Array(rowNumberFirstMatrix) { DoubleArray(columnNumberFirstMatrix) }
    println("Enter first matrix:")
    repeat(rowNumberFirstMatrix) {
        firstMatrix[it] = readLine()!!.split(" ").map { s -> s.toDouble() }.toDoubleArray()
    }
    print("Enter size of second matrix: ")
    val sizeSecondMatrix = readLine()!!.split(" ")
    val rowNumberSecondMatrix = sizeSecondMatrix[0].toInt()
    val columnNumberSecondMatrix = sizeSecondMatrix[1].toInt()
    val secondMatrix = Array(rowNumberSecondMatrix) { DoubleArray(columnNumberSecondMatrix) }
    println("Enter second matrix: ")
    repeat(rowNumberSecondMatrix) {
        secondMatrix[it] = readLine()!!.split(" ").map { s -> s.toDouble() }.toDoubleArray()
    }
    if (rowNumberFirstMatrix != rowNumberSecondMatrix && columnNumberFirstMatrix != columnNumberSecondMatrix) {
        throw Exception("The operation cannot be performed.")
    }
    val resultMatrix = MatrixOperation.addMatrices(firstMatrix, secondMatrix)
    println("The result is:")
    for (row in resultMatrix) {
        println(row.joinToString(" "))
    }
    println()
}

fun processMultiplyMatrix() {
    print("Enter size of matrix: ")
    val sizeMatrix = readLine()!!.split(" ")
    val rowNumberMatrix = sizeMatrix[0].toInt()
    val columnNumberMatrix = sizeMatrix[1].toInt()
    val matrix = Array(rowNumberMatrix) { DoubleArray(columnNumberMatrix) }
    println("Enter matrix: ")
    repeat(rowNumberMatrix) {
        matrix[it] = readLine()!!.split(" ").map { s -> s.toDouble() }.toDoubleArray()
    }
    print("Enter constant: ")
    val scalar = readLine()!!.toDouble()
    val resultMatrix = MatrixOperation.multiplyMatrix(matrix, scalar)
    println("The result is:")
    for (row in resultMatrix) {
        println(row.joinToString(" "))
    }
    println()
}

fun processMultiplyMatrices() {
    print("Enter size of first matrix: ")
    val sizeFirstMatrix = readLine()!!.split(" ")
    val rowNumberFirstMatrix = sizeFirstMatrix[0].toInt()
    val columnNumberFirstMatrix = sizeFirstMatrix[1].toInt()
    val firstMatrix = Array(rowNumberFirstMatrix) { DoubleArray(columnNumberFirstMatrix) }
    println("Enter first matrix: ")
    repeat(rowNumberFirstMatrix) {
        firstMatrix[it] = readLine()!!.split(" ").map { s -> s.toDouble() }.toDoubleArray()
    }
    print("Enter size of second matrix: ")
    val sizeSecondMatrix = readLine()!!.split(" ")
    val rowNumberSecondMatrix = sizeSecondMatrix[0].toInt()
    val columnNumberSecondMatrix = sizeSecondMatrix[1].toInt()
    val secondMatrix = Array(rowNumberSecondMatrix) { DoubleArray(columnNumberSecondMatrix) }
    println("Enter second matrix: ")
    repeat(rowNumberSecondMatrix) {
        secondMatrix[it] = readLine()!!.split(" ").map { s -> s.toDouble() }.toDoubleArray()
    }
    if (columnNumberFirstMatrix != rowNumberSecondMatrix) {
        throw Exception("The operation cannot be performed.")
    }
    val resultMatrix = MatrixOperation.multiplyMatrices(firstMatrix, secondMatrix)
    println("The result is:")
    for (row in resultMatrix) {
        println(row.joinToString(" "))
    }
    println()
}

fun processTransposeMatrix(){
    println()
    printTransposeMenu()
    print("Your choice: ")
    val option = readLine()!!.toInt()
    try {
        print("Enter size of matrix: ")
        val sizeMatrix = readLine()!!.split(" ")
        val rowNumberMatrix = sizeMatrix[0].toInt()
        val columnNumberMatrix = sizeMatrix[1].toInt()
        val matrix = Array(rowNumberMatrix) { DoubleArray(columnNumberMatrix) }
        println("Enter matrix: ")
        repeat(rowNumberMatrix) {
            matrix[it] = readLine()!!.split(" ").map { s -> s.toDouble() }.toDoubleArray()
        }
        var resultMatrix : Array<DoubleArray> = emptyArray()
        when (option) {
            1 -> resultMatrix = transposeMainDiagonal(matrix)
            2 -> resultMatrix = transposeSideDiagonal(matrix)
            3 -> resultMatrix = transposeVerticalLine(matrix)
            4 -> resultMatrix = transposeHorizontalLine(matrix)
        }
        println("The result is:")
        for (row in resultMatrix) {
            println(row.joinToString(" "))
        }
        println()
    } catch (e: Exception) {
        println(e.message)
    }
}

fun processDeterminantMatrix() {
    print("Enter size of matrix: ")
    val sizeMatrix = readLine()!!.split(" ")
    val rowNumberMatrix = sizeMatrix[0].toInt()
    val columnNumberMatrix = sizeMatrix[1].toInt()
    val matrix = Array(rowNumberMatrix) { DoubleArray(columnNumberMatrix) }
    println("Enter matrix: ")
    repeat(rowNumberMatrix) {
        matrix[it] = readLine()!!.split(" ").map { s -> s.toDouble() }.toDoubleArray()
    }
    println("The result is:")
    println(determinantMatrix(matrix))
    println()
}

fun processInverseMatrix() {
    print("Enter size of matrix: ")
    val sizeMatrix = readLine()!!.split(" ")
    val rowNumberMatrix = sizeMatrix[0].toInt()
    val columnNumberMatrix = sizeMatrix[1].toInt()
    val matrix = Array(rowNumberMatrix) { DoubleArray(columnNumberMatrix) }
    println("Enter matrix: ")
    repeat(rowNumberMatrix) {
        matrix[it] = readLine()!!.split(" ").map { s -> s.toDouble() }.toDoubleArray()
    }
    val resultMatrix = inverseMatrix(matrix)
    println("The result is:")
    for (row in resultMatrix) {
        println(row.joinToString(" "))
    }
    println()
}
