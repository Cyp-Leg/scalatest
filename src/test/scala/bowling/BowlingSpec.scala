package bowling

import org.scalatest.{FunSpec, Matchers}
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll

class BowlingSpec extends FunSpec with Matchers{
    describe("A roll") {
        describe("with 0 pin down") {
            it("gives a score of 0") {
                val bowling = new Bowling(Nil)
                val newBowling = bowling.roll((0,0))
                assert(newBowling.score(newBowling.frames) == 0)
            }
        }
        describe("with strikes") {
            it("gives a score of 300") {
                val bowling = new Bowling(Nil)
                val newBowling = bowling.roll((10,0))
                val newBowling2 = newBowling.roll((10,0))
                val newBowling3 = newBowling2.roll((10,0))
                val newBowling4 = newBowling3.roll((10,0))
                val newBowling5 = newBowling4.roll((10,0))
                val newBowling6 = newBowling5.roll((10,0))
                val newBowling7 = newBowling6.roll((10,0))
                val newBowling8 = newBowling7.roll((10,0))
                val newBowling9 = newBowling8.roll((10,0))
                val newBowling10 = newBowling9.roll((10,0))
                val newBowling11 = newBowling10.roll((10,0))
                assert(newBowling11.score(newBowling11.frames) == 300)
            }
        }
        describe("with 1 pin down") {
            it("gives a score of 20") {
                val bowling = new Bowling(Nil)
                val newBowling = bowling.roll((1,1))
                val newBowling2 = newBowling.roll((1,1))
                val newBowling3 = newBowling2.roll((1,1))
                val newBowling4 = newBowling3.roll((1,1))
                val newBowling5 = newBowling4.roll((1,1))
                val newBowling6 = newBowling5.roll((1,1))
                val newBowling7 = newBowling6.roll((1,1))
                val newBowling8 = newBowling7.roll((1,1))
                val newBowling9 = newBowling8.roll((1,1))
                val newBowling10 = newBowling9.roll((1,1))
                assert(newBowling10.score(newBowling10.frames) == 20)
            }
        }
    }
    describe("20 rolls with 1 pin down") {
        it("give a score of 20") {
            val bowling = new Bowling(Nil)
            val newBowling = bowling.roll((1,1))
            val newBowling2 = newBowling.roll((1,1))
            val newBowling3 = newBowling2.roll((1,1))
            val newBowling4 = newBowling3.roll((1,1))
            val newBowling5 = newBowling4.roll((1,1))
            val newBowling6 = newBowling5.roll((1,1))
            val newBowling7 = newBowling6.roll((1,1))
            val newBowling8 = newBowling7.roll((1,1))
            val newBowling9 = newBowling8.roll((1,1))
            val newBowling10 = newBowling9.roll((1,1))
            assert(newBowling10.score(newBowling10.frames) == 20)
        }
    }


    val rollGen = for (n <- Gen.choose(1, 10)) yield n
    
    /*forAll(rollGen){ (n:Int)=>
        Bowling.genFrame(n).score should be < 10
    }*/
}