package bench

import org.openjdk.jmh.annotations._
import impl.Main

class Bench {
  @Benchmark
  def measureMapMeth(): Seq[Int] = {
    Main.mapMeth(100)
  }
}
