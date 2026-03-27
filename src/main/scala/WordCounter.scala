object WordCounter {

  def normalize(text: String): Array[String] =
    text
      .toLowerCase
      .replaceAll("[^a-z\\s]", " ")
      .split("\\s+")
      .filter(_.nonEmpty)

  def removeStopWords(words: Array[String], stopWords: Set[String]): Array[String] =
    words.filterNot(stopWords.contains)

  def count(words: Array[String]): Map[String, Int] =
    words.foldLeft(Map.empty[String, Int]) { (acc, word) =>
      acc.updated(word, acc.getOrElse(word, 0) + 1)
    }

  def topN(counts: Map[String, Int], n: Int): Seq[(String, Int)] =
    counts.toSeq.sortBy(-_._2).take(n)
}