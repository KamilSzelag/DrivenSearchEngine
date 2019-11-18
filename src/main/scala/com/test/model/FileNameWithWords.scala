package com.test.model

import scala.collection.mutable

case class FileNameWithWords(fileName: String,
                             words: Array[String]) {

  private val uniqueWords = words.distinct

  def searchWords(searchWords: Array[String]): Double = {
    val lastWords = mutable.Set(searchWords.distinct: _*)

    uniqueWords.takeWhile { word =>
      lastWords.remove(word)
      lastWords.nonEmpty
    }

    calculateMatchPercentRank(searchWords, lastWords)
  }

  private def calculateMatchPercentRank(searchWords: Array[String], lastWords: mutable.Set[String]): Double = {
    (1 - (lastWords.size / searchWords.length.toDouble)) * 100
  }
}
