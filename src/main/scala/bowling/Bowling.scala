package bowling

import scala.util.Random

case class Bowling(frames: List[Frame]) {
    
    def roll(pins:(Int,Int)): Bowling = {
        val newFrame = Frame(pins._1,pins._2)
        val newFramesList = newFrame :: frames
        return Bowling(newFramesList)
    }

    
    def score(gameFrames: List[Frame], spare: Boolean=false, strike: Boolean=false,wasStrike: Boolean=false, round: Int=0): Int = {
        val newScore = if(gameFrames.length==0){
            0
        }
        else {
            //spare
            if(spare){

                if(gameFrames.head.roll1==10){ // spare + strike : spare = 20
                    (gameFrames.head.roll1)*2+score(gameFrames.tail,false,true,false,round+1) // register the strike for next roll
                }
                else if(gameFrames.head.roll1+gameFrames.head.roll2==10){ // spare + spare : spare = 10 + next roll*2
                    (gameFrames.head.roll1)*2 + gameFrames.head.roll2+score(gameFrames.tail,true,false,false,round+1) // register the spare for next roll
                }
                else{ // spare = 10 + next roll *2
                    (gameFrames.head.roll1)*2 + gameFrames.head.roll2+score(gameFrames.tail,false,false,false,round+1)
                }

            }
            //strike
            else if(strike){

                if(gameFrames.head.roll1==10){
                    if(wasStrike && round!=10){ // 3rd strike in a row
                        (gameFrames.head.roll1)*3+score(gameFrames.tail,false,true,true,round+1) // register the 3 strikes in a row for next roll
                    } // 2nd strike in a row
                    else (gameFrames.head.roll1)*2+score(gameFrames.tail,false,true,true,round+1) // register the 2 strikes in a row for next roll
                }

                else if(gameFrames.head.roll1+gameFrames.head.roll2==10){ // strike + spare

                    if(wasStrike && round!=10){ // spare after 2 strikes
                        (gameFrames.head.roll1*2 + gameFrames.head.roll2)+score(gameFrames.tail,true,false,false,round+1)
                    }

                    else (gameFrames.head.roll1)*2 + gameFrames.head.roll2+score(gameFrames.tail,true,false,false,round+1) // spare after 1 strike
                }
                else{
                    (gameFrames.head.roll1 + gameFrames.head.roll2)*2+score(gameFrames.tail,false,false,false,round+1)
                }

            }

            else{

                if(gameFrames.head.roll1==10){
                    (gameFrames.head.roll1)*2+score(gameFrames.tail,false,true,false,round+1) // 1st strike
                }
                else if(gameFrames.head.roll1+gameFrames.head.roll2==10){ // 1st spare
                    (gameFrames.head.roll1)*2 + gameFrames.head.roll2+score(gameFrames.tail,true,false,false,round+1)
                }
                else{ // regular roll
                    (gameFrames.head.roll1) + gameFrames.head.roll2+score(gameFrames.tail,false,false,false,round+1)
                }

            }

        }
        return newScore
    }

    
}


object Bowling{
    def genRoll(limit: Int): Int = {
        Random.nextInt(limit)+1
    }        

    def genFrame(roll1: Int): Frame = { // generate a frame after the 1st roll has been done
        val roll2 = genRoll(9-roll1)
        new Frame(roll1,roll2)
    }
}