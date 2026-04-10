package scala.u06.examples

export u06.modelling.PetriNet
import u06.modelling
import u06.modelling.PetriNet
import u06.modelling.System
import u06.utils.MSet

object PNReadersWriters:

  enum Place:
    case P1, P2, P3, P4, P5, P6, P7
    
  export Place.*
  export u06.modelling.PetriNet.*
  export u06.modelling.SystemAnalysis.*
  export u06.utils.MSet

  def pnRW: System[Marking[Place]] = PetriNet[Place](
    MSet(P1) ~~> MSet(P2),
    MSet(P2) ~~> MSet(P3),
    MSet(P2) ~~> MSet(P4),
    MSet(P3,P5) ~~> MSet(P5,P6),
    MSet(P4,P5) ~~> MSet(P7) ^^^ MSet(P6),
    MSet(P6) ~~> MSet(P1),
    MSet(P7) ~~> MSet(P1,P5)
  ).toSystem

@main def mainPNReadersWriters =
  import PNReadersWriters.*
  // example usage
  println(pnRW.paths(MSet(P1, P1),7).toList.mkString("\n"))
