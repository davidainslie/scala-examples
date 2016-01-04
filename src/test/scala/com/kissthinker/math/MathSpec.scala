package com.kissthinker.math

import org.specs2.mutable.Specification

class MathSpec extends Specification {
  "Fibonacci" should {
    "calculate 9" in {
      fibonacci(9) mustEqual 34
    }
  }
}