package bench

import org.openjdk.jmh.annotations._
import impl.Main

@BenchmarkMode(Array(Mode.Throughput))
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 3, time = 1)
@Fork(1)
class Bench {
}
