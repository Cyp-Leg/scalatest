package bowling

case class Frame(roll1: Int, roll2: Int){
    def score = roll1+roll2
       
}