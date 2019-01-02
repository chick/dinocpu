// Lists of different instruction test cases for use with different CPU models

package CODCPU

/**
 * This object contains a set of lists of tests. Each list is a different set of
 * instruction types and corresponds to a RISC-V program in resources/risc-v
 *
 * Each test case looks like:
 *  - binary to run in src/test/resources/risc-v
 *  - number of cycles to run for each CPU type
 *  - initial values for registers
 *  - final values to check for registers
 *  - initial values for memory
 *  - final values to check for memory
 *  - extra name information
 */
object InstTests {

  val maxInt = BigInt("FFFFFFFF", 16)

  def twoscomp(v: BigInt) : BigInt = {
    if (v < 0) {
      return maxInt + v + 1
    } else {
      return v
    }
  }

	val rtype = List[CPUTestCase](
		CPUTestCase("add1",
                Map("single-cycle" -> 1, "five-cycle" -> 5, "pipelined" -> 5),
                Map(5 -> 1234),
								Map(0 -> 0, 5 -> 1234, 6 -> 1234),
								Map(), Map()),
		CPUTestCase("add2",
                Map("single-cycle" -> 1, "five-cycle" -> 5, "pipelined" -> 5),
                Map(5 -> 1234, 20 -> 5678),
								Map(0 -> 0, 10 -> 6912),
								Map(), Map()),
		CPUTestCase("add0",
                Map("single-cycle" -> 1, "five-cycle" -> 5, "pipelined" -> 5),
                Map(5 -> 1234, 6 -> 3456),
								Map(0 -> 0, 5 -> 1234, 6 -> 3456),
								Map(), Map()),
		CPUTestCase("addfwd",
                Map("single-cycle" -> 10, "five-cycle" -> 0, "pipelined" -> 14),
                Map(5 -> 1, 10 -> 0),
								Map(5 -> 1, 10 -> 10),
								Map(), Map()),
		CPUTestCase("or",
                Map("single-cycle" -> 1, "five-cycle" -> 5, "pipelined" -> 5),
                Map(5 -> 1234, 6 -> 5678),
								Map(7 -> 5886),
								Map(), Map()),
		CPUTestCase("sub",
                Map("single-cycle" -> 1, "five-cycle" -> 5, "pipelined" -> 5),
                Map(5 -> 1234, 6 -> 5678),
								Map(7 -> BigInt("FFFFEEA4", 16)),
								Map(), Map()),
		CPUTestCase("and",
                Map("single-cycle" -> 1, "five-cycle" -> 5, "pipelined" -> 5),
                Map(5 -> 1234, 6 -> 5678),
								Map(7 -> 1026),
								Map(), Map())
	)

	val branch = List[CPUTestCase](
		CPUTestCase("beq",
                Map("single-cycle" -> 3, "five-cycle" -> 7, "pipelined" -> 7),
                Map(5 -> 1234, 6 -> 1, 7 -> 5678, 8 -> 9012),
								Map(5 -> 0, 6 -> 1, 7 -> 5678, 8 -> 9012),
								Map(), Map(), "-False"),
		CPUTestCase("beq",
                Map("single-cycle" -> 3, "five-cycle" -> 9, "pipelined" -> 9),
                Map(5 -> 1234, 6 -> 1, 7 -> 5678, 28 -> 5678),
								Map(5 -> 1235, 6 -> 1, 7 -> 5678, 28 -> 5678),
								Map(), Map(), "-True")
	)

	val memory = List[CPUTestCase](
		CPUTestCase("lw1",
                Map("single-cycle" -> 1, "five-cycle" -> 5, "pipelined" -> 5),
                Map(),
								Map(5 -> BigInt("ffffffff", 16)),
								Map(), Map()),
		CPUTestCase("lwfwd",
                Map("single-cycle" -> 2, "five-cycle" -> 0, "pipelined" -> 7),
                Map(5 -> BigInt("ffffffff", 16), 10 -> 5),
								Map(5 -> 1, 10 -> 6),
								Map(), Map()),
		CPUTestCase("sw",
                Map("single-cycle" -> 6, "five-cycle" -> 10, "pipelined" -> 10),
                Map(5 -> 1234),
								Map(6 -> 1234),
								Map(), Map(0x100 -> 1234))
	)

  val immediate = List[CPUTestCase](
		CPUTestCase("auipc0",
                Map("single-cycle" -> 1, "five-cycle" -> 5, "pipelined" -> 5),
                Map(10 -> 1234),
								Map(10 -> 0),
								Map(), Map()),
		CPUTestCase("auipc1",
                Map("single-cycle" -> 2, "five-cycle" -> 6, "pipelined" -> 6),
                Map(10 -> 1234),
								Map(10 -> 4),
								Map(), Map()),
		CPUTestCase("auipc2",
                Map("single-cycle" -> 2, "five-cycle" -> 6, "pipelined" -> 6),
                Map(10 -> 1234),
								Map(10 -> (17 << 12)),
								Map(), Map()),
		CPUTestCase("auipc3",
                Map("single-cycle" -> 2, "five-cycle" -> 6, "pipelined" -> 6),
                Map(10 -> 1234),
								Map(10 -> ((17 << 12) + 4)),
								Map(), Map()),
		CPUTestCase("lui0",
                Map("single-cycle" -> 1, "five-cycle" -> 5, "pipelined" -> 5),
                Map(10 -> 1234),
								Map(10 -> 0),
								Map(), Map()),
		CPUTestCase("lui1",
                Map("single-cycle" -> 1, "five-cycle" -> 5, "pipelined" -> 5),
                Map(10 -> 1234),
								Map(10 -> 4096),
								Map(), Map()),
		CPUTestCase("addi1",
                Map("single-cycle" -> 1, "five-cycle" -> 5, "pipelined" -> 5),
                Map(),
								Map(0 -> 0, 10 -> 17),
								Map(), Map()),
		CPUTestCase("addi2",
                Map("single-cycle" -> 2, "five-cycle" -> 6, "pipelined" -> 6),
                Map(),
								Map(0 -> 0, 10 -> 17, 11 -> 93),
								Map(), Map()),
		CPUTestCase("slli",
                Map("single-cycle" -> 1, "five-cycle" -> 5, "pipelined" -> 5),
                Map(5 -> 1),
								Map(0 -> 0, 5 -> 1, 6 -> 128),
								Map(), Map()),
		CPUTestCase("srai",
                Map("single-cycle" -> 1, "five-cycle" -> 5, "pipelined" -> 5),
                Map(5 -> 1024),
								Map(0 -> 0, 5 -> 1024, 6 -> 8),
								Map(), Map()),
		CPUTestCase("srai",
                Map("single-cycle" -> 1, "five-cycle" -> 5, "pipelined" -> 5),
                Map(5 -> twoscomp(-1024)),
								Map(0 -> 0, 5 -> twoscomp(-1024), 6 -> twoscomp(-8)),
								Map(), Map(), "-negative")
  )

  val jump = List[CPUTestCase](
    CPUTestCase("jal",
                Map("single-cycle" -> 2, "five-cycle" -> 6, "pipelined" -> 6),
                Map(5 -> 1234),
								Map(0 -> 0, 5 -> 1234, 6 -> 1234, 1 -> 4),
								Map(), Map()),
    CPUTestCase("jalr0",
                Map("single-cycle" -> 2, "five-cycle" -> 6, "pipelined" -> 6),
                Map(5 -> 1234, 10 -> 28),
								Map(0 -> 0, 5 -> 1234, 6 -> 1234, 1 -> 4),
								Map(), Map()),
    CPUTestCase("jalr1",
                Map("single-cycle" -> 2, "five-cycle" -> 6, "pipelined" -> 6),
                Map(5 -> 1234, 10 -> 20),
								Map(0 -> 0, 5 -> 1234, 6 -> 1234, 1 -> 4),
								Map(), Map())
  )

  val tests = Map(
    "rtype" -> rtype,
    "branch" -> branch,
    "memory" -> memory,
    "immediate" -> immediate,
    "jump" -> jump
  )
}
