import org.scalatest.funsuite.AnyFunSuite

class WordCounterSpec extends AnyFunSuite {

  val stopWords = Set("the", "and")

  // checks that punctuation is removed and text becomes lowercase
  test("normalize removes punctuation and converts to lowercase") {
    val input = "Hello, HELLO!!"
    val result = WordCounter.normalize(input)
    assert(result.sameElements(Array("hello", "hello")))
  }

  // checks that numbers are removed during normalization
  test("normalize removes numbers") {
    val input = "hello 123 world 456"
    val result = WordCounter.normalize(input)
    assert(result.sameElements(Array("hello", "world")))
  }

  // checks that special characters are removed
  test("normalize removes special characters") {
    val input = "hello@#$ world!!!"
    val result = WordCounter.normalize(input)
    assert(result.sameElements(Array("hello", "world")))
  }

  // checks that stop words are removed correctly
  test("removeStopWords removes common words") {
    val words = Array("the", "car", "and", "road")
    val result = WordCounter.removeStopWords(words, stopWords)
    assert(result.sameElements(Array("car", "road")))
  }

  // checks behavior when all words are stop words
  test("removeStopWords returns empty when all words are stop words") {
    val words = Array("the", "and")
    val result = WordCounter.removeStopWords(words, stopWords)
    assert(result.isEmpty)
  }

  // checks that counting logic works correctly
  test("count calculates correct frequency") {
    val words = Array("a", "b", "a")
    val result = WordCounter.count(words)
    assert(result("a") == 2)
    assert(result("b") == 1)
  }

  // checks counting with large repetition
  test("count handles repeated words correctly") {
    val words = Array.fill(1000)("scala")
    val result = WordCounter.count(words)
    assert(result("scala") == 1000)
  }

  // checks that topN returns highest counts first
  test("topN returns highest frequency first") {
    val counts = Map("a" -> 5, "b" -> 2, "c" -> 10)
    val result = WordCounter.topN(counts, 2)
    assert(result.head._1 == "c")
    assert(result.length == 2)
  }

  // checks that topN handles n larger than map size
  test("topN handles n larger than available entries") {
    val counts = Map("a" -> 2, "b" -> 1)
    val result = WordCounter.topN(counts, 10)
    assert(result.length == 2)
  }

  // checks normalization of empty input
  test("normalize handles empty string") {
    val result = WordCounter.normalize("")
    assert(result.isEmpty)
  }
}