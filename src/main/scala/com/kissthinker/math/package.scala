package com.kissthinker

import scala.annotation.tailrec

package object math {
  def fibonacci(x: Int): BigInt = {
    @tailrec
    def fibonacci(x: Int, prev: BigInt = 0, next: BigInt = 1): BigInt = x match {
      case 0 => prev
      case 1 => next
      case _ => fibonacci(x - 1, next, next + prev)
    }

    fibonacci(x)
  }
}