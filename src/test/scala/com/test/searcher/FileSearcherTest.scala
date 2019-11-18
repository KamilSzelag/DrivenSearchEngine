package com.test.searcher

import com.test.model.{FileNameWithPercentMatch, FileNameWithWords}
import org.scalatest.funsuite.AnyFunSuite

class FileSearcherTest extends AnyFunSuite {

  test("Empty directory and empty search text") {
    val result = FileSearcher.findTopNFiles(
      fileNameWithWords = Array.empty,
      searchText = "")

    assert(result.isEmpty)
  }

  test("One file and one word") {
    val fileName = "f1"
    val result = FileSearcher.findTopNFiles(
      fileNameWithWords = Array(FileNameWithWords(
        fileName = fileName,
        words = Array("ala", "ma", "kota"))),
      searchText = "ala"
    )

    val expectedResult = Array(FileNameWithPercentMatch(fileName, 100))

    assert(result sameElements expectedResult)
  }

  test("Two files and single word") {
    val fileName1 = "f1"
    val fileName2 = "f2"
    val result = FileSearcher.findTopNFiles(
      fileNameWithWords = Array(
        FileNameWithWords(
          fileName = fileName1,
          words = Array("ala", "ma", "kota")),
        FileNameWithWords(
          fileName = fileName2,
          words = Array("biega", "krzyczy", "Pan", "Hilary"))
      ),
      searchText = "biega"
    )

    val expectedResult = Array(FileNameWithPercentMatch(fileName2, 100))

    assert(result sameElements expectedResult)
  }

  test("Two files and multiple words") {
    val fileName1 = "f1"
    val fileName2 = "f2"
    val result = FileSearcher.findTopNFiles(
      fileNameWithWords = Array(
        FileNameWithWords(
          fileName = fileName1,
          words = Array("ala", "ma", "kota", "to")),
        FileNameWithWords(
          fileName = fileName2,
          words = Array("biega", "krzyczy", "Pan", "Hilary"))
      ),
      searchText = "biega kota i ala"
    )

    val expectedResult = Array(FileNameWithPercentMatch(fileName1, 50), FileNameWithPercentMatch(fileName2, 25))

    assert(result sameElements expectedResult)
  }
}
