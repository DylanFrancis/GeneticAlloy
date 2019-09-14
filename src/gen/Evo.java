package gen;

import resources.Shop;
import utils.Codes;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class Evo extends Thread implements Codes {
    private LinkedList<Solution> currentGeneration;
    private LinkedList<Solution> nextGeneration;
    private ThreadLocalRandom random = ThreadLocalRandom.current();
    private Shop shop;

    public void run(){
        shop = new Shop();
        currentGeneration = new LinkedList<>();
        nextGeneration = new LinkedList<>();

    }

    private void initialisePopulation(int amt){
        currentGeneration = new LinkedList<>();
        while (amt >= 0){
            currentGeneration.addLast(new Solution(randomVectors()));

            amt--;
        }
    }

    private int[] randomVectors(){
        return new int[]{random.nextInt(0,30), random.nextInt(0,30), random.nextInt(0,30), random.nextInt(0,30)};
    }

    private void evaluateFitness(){
        currentGeneration.forEach(solution -> solution.setResult(shop.buy(solution.getVectors())));
        currentGeneration.sort(Solution::compareTo);

    }


    private void evolve(){

    }

}
