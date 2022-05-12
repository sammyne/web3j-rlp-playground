import org.web3j.rlp.{RlpString, RlpList, RlpEncoder, RlpDecoder}

@main def hello: Unit =
  HelloWorld

def HelloWorld: Unit =
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

  val decoded = RlpDecoder.decode(encoded)

def newRlpString(s: String): RlpString = RlpString.create(s.getBytes)
