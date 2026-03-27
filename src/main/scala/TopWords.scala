import scala.io.Source

object TopWords {

  def main(args: Array[String]): Unit = {

    val url = "https://www.gutenberg.org/files/2701/2701-0.txt"

    val source = Source.fromURL(url)("UTF-8")
    val text =
      try source.mkString
      finally source.close()

    val stopWords = Set(
      "the","of","to","and","a","in","is","it","you","that","he","was","for","on",
      "are","with","as","i","his","they","be","at","one","have","this","from","or",
      "had","by","not","word","but","what","some","we","can","out","other","were",
      "all","there","when","up","use","your","how","said","an","each","she"
    )

    val words = WordCounter.normalize(text)
    val filtered = WordCounter.removeStopWords(words, stopWords)
    val counts = WordCounter.count(filtered)
    val top50 = WordCounter.topN(counts, 50)

    top50.foreach { case (word, count) =>
      println(s"$word -> $count")
    }
  }
}