package bench

import org.openjdk.jmh.annotations._
import impl.Impl._

@BenchmarkMode(Array(Mode.Throughput))
@Warmup(iterations = 20, time = 1)
@Measurement(iterations = 20, time = 1)
@Fork(1)
class Bench {

  @Benchmark
  def benchNoBottleneck(): Unit = {
    noBottleneck()
  }

  @Benchmark
  def benchReflection(): Unit = {
    refrection()
  }

  @Benchmark
  def benchShapeless(): Unit = {
    shapeless()
  }

  @Benchmark
  def benchCirce(): Unit = {
    circe()
  }
}
