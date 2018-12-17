//@BenchmarkMode(Array(Mode.Throughput))
//@Warmup(iterations = 40, time = 3)
//@Measurement(iterations = 40, time = 3)
//@Fork(1)
//class Bench {
//
//  @Benchmark
//  def benchNoBottleneck(): Unit = {
//    noBottleneck()
//  }
//
//  @Benchmark
//  def benchReflection(): Unit = {
//    reflection()
//  }
//
//  @Benchmark
//  def benchShapeless(): Unit = {
//    shapeless()
//  }
//
//  @Benchmark
//  def benchShapelessAny(): Unit = {
//    shapelessAny()
//  }
//
//  @Benchmark
//  def benchCirce(): Unit = {
//    circe()
//  }
//
//  @Benchmark
//  def benchJackson(): Unit = {
//    jackson()
//  }
//}
