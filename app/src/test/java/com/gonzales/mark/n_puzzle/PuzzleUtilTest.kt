package com.gonzales.mark.n_puzzle

import com.gonzales.mark.n_puzzle.TestUtil.Companion.permute
import org.junit.Assert.*
import org.junit.Test

class PuzzleUtilTest {
    @Test
    fun `Check if a shuffled 8-puzzle with solution is detected as solvable`() {
        val puzzleState: ArrayList<Int> = arrayListOf(0, 1, 2, 3, 4, 8, 6, 7, 5)
        val errorMessage = "\nShould be solvable: $puzzleState\n"

        assertTrue(errorMessage, PuzzleUtil.isSolvable(puzzleState, TestUtil.BLANK_TILE_MARKER))
    }

    @Test
    fun `Check if an already-solved 8-puzzle is detected as solvable`() {
        val puzzleState: ArrayList<Int> = arrayListOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
        val errorMessage = "\nShould be solvable: $puzzleState\n"

        assertTrue(errorMessage, PuzzleUtil.isSolvable(puzzleState, TestUtil.BLANK_TILE_MARKER))
    }

    @Test
    fun `Check if an 8-puzzle with no solution is detected as unsolvable`() {
        val puzzleState: ArrayList<Int> = arrayListOf(0, 1, 2, 3, 4, 5, 7, 6, 8)
        val errorMessage = "\nShould not be solvable: $puzzleState\n"

        assertFalse(errorMessage, PuzzleUtil.isSolvable(puzzleState, TestUtil.BLANK_TILE_MARKER))
    }

    @Test
    fun `Check if the number of solvable 8-puzzles is equal to half of the total number of puzzle configurations`() {
        val puzzleState: ArrayList<Int> = arrayListOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
        val testCases: List<List<Int>> = puzzleState.permute()

        var numSolvable = 0
        for (testCase in testCases) {
            if (PuzzleUtil.isSolvable(testCase as ArrayList<Int>, TestUtil.BLANK_TILE_MARKER)) {
                numSolvable++
            }
        }

        assertEquals(TestUtil.NUM_PUZZLE_CONFIGS / 2, numSolvable)
    }

    @Test
    fun `Check if the number of unsolvable 8-puzzles is equal to half of the total number of puzzle configurations`() {
        val puzzleState: ArrayList<Int> = arrayListOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
        val testCases: List<List<Int>> = puzzleState.permute()

        var numUnsolvable = 0
        for (testCase in testCases) {
            if (!PuzzleUtil.isSolvable(testCase as ArrayList<Int>, TestUtil.BLANK_TILE_MARKER)) {
                numUnsolvable++
            }
        }

        assertEquals(TestUtil.NUM_PUZZLE_CONFIGS / 2, numUnsolvable)
    }

    @Test
    fun `Check swapping of first consecutive non-blank tiles if the first and second tiles in the grid are non-blank`() {
        val puzzleState: ArrayList<Int> = arrayListOf(0, 1, 3, 2, 4, 5, 6, 7, 8)
        val expectedSwapped: ArrayList<Int> = arrayListOf(1, 0, 3, 2, 4, 5, 6, 7, 8)
        PuzzleUtil.swapTiles(puzzleState, TestUtil.BLANK_TILE_MARKER)

        assertEquals(expectedSwapped, puzzleState)
    }

    @Test
    fun `Check swapping of first consecutive non-blank tiles if the first tile in the grid is blank but the second is not`() {
        val puzzleState: ArrayList<Int> = arrayListOf(8, 1, 3, 2, 4, 5, 6, 7, 0)
        val expectedSwapped: ArrayList<Int> = arrayListOf(8, 3, 1, 2, 4, 5, 6, 7, 0)
        PuzzleUtil.swapTiles(puzzleState, TestUtil.BLANK_TILE_MARKER)

        assertEquals(expectedSwapped, puzzleState)
    }

    @Test
    fun `Check swapping of first consecutive non-blank tiles if the second tile in the grid is blank but the first is not`() {
        val puzzleState: ArrayList<Int> = arrayListOf(3, 8, 4, 1, 2, 5, 6, 7, 0)
        val expectedSwapped: ArrayList<Int> = arrayListOf(3, 8, 1, 4, 2, 5, 6, 7, 0)
        PuzzleUtil.swapTiles(puzzleState, TestUtil.BLANK_TILE_MARKER)

        assertEquals(expectedSwapped, puzzleState)
    }
}