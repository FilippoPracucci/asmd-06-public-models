package u06.modelling

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*

class PNSpec extends AnyFunSuite:

  import u06.examples.PNMutualExclusion.*

  test("PN for mutual exclusion should properly generate 7-length paths"):

    val expected1 = List(MSet(N,N), MSet(T,N), MSet(T,T), MSet(C,T), MSet(T), MSet(C), MSet())
    val expected2 = List(MSet(N,N), MSet(T,N), MSet(C,N), MSet(C,T), MSet(T), MSet(C), MSet())
    val expected3 = List(MSet(N,N), MSet(T,N), MSet(C,N), MSet(N), MSet(T), MSet(C), MSet())

    pnME.paths(MSet(N,N),7).toSet should be:
      Set(expected1, expected2, expected3)

  import scala.u06.examples.PNReadersWriters.*

  test("PN for readers and writers should properly generate 12-length paths"):
    pnRW.paths(MSet(P1,P1), 12).toSet.foreach(_.foreach: m =>
      m.disjoined(MSet(P6,P7)) should be (true)
      m.disjoined(MSet(P7,P7)) should be (true)
    )
