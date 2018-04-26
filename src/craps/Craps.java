/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craps;
/******************************************************************************
 *  Compilation:  java Craps.java
 *  Execution:    java Craps
 *  
 *  Play 1 million games of craps and print probability of winning.
 *
 *  Craps simulation of "pass bet"           
 *  True odds of winning = 244/495 = 0.4929292929...
 * 
 *  % java Craps
 *  Winning percentage = 0.493396
 *
 *  % java Craps
 *  Winning percentage = 0.492537
 *
 *  % java Craps
 *  Winning percentage = 0.492998
 * 
 ******************************************************************************/

public class Craps {
    int bank = 100;
    int bet = 5;
    int oddsBet = 5;
    int wins = 0;
    int trials = 10;
    // uniform random integer in [0, n) 
    public static int uniform(int n) {
        return (int) (Math.random() * n);
    } 

    // return sum of two dice
    public static int sumOfTwoDice() {
        int x = 1 + uniform(6);
        int y = 1 + uniform(6);
        return x + y;
    }
    
    public int winPassBet() {
        wins++;
        bank = bank + bet;
        System.out.println("Won pass bet of " + bet + " Bank is now: " + bank);
        return bank; 
    }
    
//    public int winOddsBet() {
//        wins++;
//        bank = bank + bet + oddsBet;
//        System.out.println("Won Odds bet of " + oddsBet + " Bank is now: " + bank);
//        return bank;
//    }
    
    public int loseBet() {
        bank = bank - bet;
        return bank;
    }
    
     public int loseOddsBet() {
        bank = bank - (bet + oddsBet);
        return bank;
    }
    
  public int pointOnFour(int x) {
        int y = sumOfTwoDice();
        if (y == 7) return loseOddsBet();
        if (y == x) return twoOneOdds();
        else return pointOnFour(x);
       
    }
    
    public int twoOneOdds() {
        wins++;
        bank = bank + bet + (oddsBet * 2);
        System.out.println("Won Odds bet of " + (oddsBet * 2) + " Bank is now: " + bank);
        return bank;
    }
    
    public int pointOnFive(int x) {
        int y = sumOfTwoDice();
        if (y == 7) return loseOddsBet();
        if (y == x) return threeTwoOdds();
        else return pointOnFour(x);
    }
    
    public int threeTwoOdds() {
        wins++;
        bank = (int) (bank + bet + (oddsBet * 1.5));
        System.out.println("Won Odds bet of " + (oddsBet * 1.5) + " Bank is now: " + bank);
        return bank;
    }
    
    public int pointOnSix(int x) {
        int y = sumOfTwoDice();
        if (y == 7) return loseOddsBet();
        if (y == x) return threeTwoOdds();
        else return pointOnFour(x);
    }
    
    public int fiveSix() {
        wins++;
        bank = (int) (bank + bet + (oddsBet * (5/6)));
        System.out.println("Won Odds bet of " + (oddsBet * (5/6)) + " Bank is now: " + bank);
        return bank;
    }
   /***************************************************************************
    * Pass bet:
    *
    * Player rolls two dice. Let x be sum.
    *   - if x is 7 or 11 instant win
    *   - if x is 2, 3, or 12 instant loss
    *   - otherwise player repeatedly rolls two dice until sum is x or 7
    *        if sum is x then win
    *        if sum is 7 then lose
     * @return 
    ***************************************************************************/
    public int winsPassBet() {
        int x = sumOfTwoDice();
        if (x == 7 || x == 11)           return winPassBet();
        if (x == 2 || x == 3 || x == 12) return loseBet();
        if (x == 4 || x == 10)           return pointOnFour(x);
        if (x == 5 || x == 9)           return pointOnFive(x);
        else          return pointOnSix(x);
    }
    
    
    
    
//    public int gameTwo(int x){
//        int y = sumOfTwoDice();
//        if (y == 7) return loseBet();
//        if (y == x) return winOddsBet();
//        else return gameTwo(x);
//    }

    public int getWins(){
        return wins;
    }
    
    public int getBank(){
        return bank;
    }
    
    
//        while (true) {
//            int y = sumOfTwoDice();
//            if (y == 7) return false;
//            if (y == x) return true;
//        } 
   



   /***************************************************************************
    *  Run simulation of pass bet n times  
    *  Output winning percentage.          
    ***************************************************************************/
    public static void main(String[] args) { 
        Craps c = new Craps();
        for (int t = 0; t < c.trials; t++)
            c.winsPassBet();
            
        System.out.println("Wins = " + c.getWins() + "  Losses = " + (c.trials - c.getWins()) + "  Bank = " + c.getBank());
        
//        int trials = 1000000;     // number of pass bets to simulate
//        int wins = 0;             // number of pass bets won
//
//        for (int t = 0; t < trials; t++)
//            if (winsPassBet()) wins++;
//
//        System.out.println("Winning percentage = " + 1.0 * wins / trials);
//    }
}}

