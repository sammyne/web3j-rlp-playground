import scala.jdk.CollectionConverters._

import org.scalatest.funsuite.AnyFunSuite

import org.web3j.rlp.{RlpString, RlpList, RlpEncoder, RlpDecoder}
import org.web3j.rlp.RlpType

def assertEqual(a: RlpType, b: RlpType): Unit =
  val v = (a, b)
  v match {
    case (x: RlpString, y: RlpString) =>
      val xx = new String(x.getBytes)
      val yy = new String(y.getBytes)
      assert(x.equals(y), s"$x != $y")
    case (x: RlpList, y: RlpList) => {
      val xx = x.getValues.asScala
      val yy = y.getValues.asScala
      assert(
        xx.size == yy.size,
        s"list length mismatch: ${xx.size}!=${yy.size}",
      )

      xx.zip(yy).foreach(assertEqual(_, _))
    }
    case _ =>
      throw new IllegalStateException("types mismatch")
  }

def newRlpString(s: String): RlpString = RlpString.create(s.getBytes)

def prettyPrint(v: RlpType, indent: String): Unit =
  v match
    case x: RlpString =>
      val s = new String(x.getBytes)
      println(s"$indent$s")
    case x: RlpList =>
      println(indent + "[")
      val itemIndent = indent + "  "
      x.getValues.forEach(prettyPrint(_, itemIndent))
      println(indent + "]")

class HelloWorld extends AnyFunSuite:

  test("encode then decode two-level list") {
    val mList = new RlpList(
      newRlpString("hello"),
      new RlpList(
        newRlpString("how"),
        newRlpString("do"),
        newRlpString("you"),
        newRlpString("do"),
      ),
      newRlpString("world"),
    )

    val encoded = RlpEncoder.encode(mList)

    val decoded = {
      val v = RlpDecoder.decode(encoded).getValues
      v.get(0)
    }

    // println("\nmList")
    // prettyPrint(mList, "")

    // println("\ndecoded")
    // prettyPrint(decoded, "")

    assertEqual(mList, decoded)
  }
