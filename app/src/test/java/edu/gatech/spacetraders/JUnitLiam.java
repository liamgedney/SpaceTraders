package edu.gatech.spacetraders;

import org.junit.Test;

import java.util.EnumMap;
import java.util.Map;

import edu.gatech.spacetraders.entity.Difficulty;
import edu.gatech.spacetraders.entity.Good;
import edu.gatech.spacetraders.entity.Market;
import edu.gatech.spacetraders.entity.Player;
import edu.gatech.spacetraders.entity.Ship;

import static junit.framework.TestCase.assertEquals;

/**
 *
 */
public class JUnitLiam {
    public static final Good[] goods = Good.values();
    /**
     *
     */
    @Test
    public void Market_CanBuyTest() {
        //This first test tests whether canBuy returns true when
        //the market's tech level is less than the good's mtlu
        //and false otherwise. In this case, the market's tech level
        //is 1.
        Player player1 = new Player("name", Difficulty.Normal, 4, 4, 4, 4);
        player1.setCredits(9999); //so that credits doesn't affect the test.
        Ship ship1 = new Ship();
        Market market1 = new Market(1, player1, ship1);
        for (Good good: Good.values()) { //so market inventory doesn't affect the test.
            market1.upInventory(good.ordinal(), 9999);
        }
        for (int i = 0; i < 10; i++) {
            boolean canBuy;
            canBuy = !(1 < goods[i].mtlu());
            assertEquals(canBuy, market1.canBuy(i));
        }
        //increases market's tech level as that isn't being tested in the later tests.
        market1 = new Market(4, player1, ship1);
        for (Good good: Good.values()) { //so market inventory doesn't affect the test.
            market1.upInventory(good.ordinal(), 9999);
        }

        //The second test: Tests whether the player can buy a good based
        //on the number of credits they have and the good's price.
        for (int i = 0; i < 10; i++) {
            Good currentGood = goods[i];
            boolean canBuy;
            int price = currentGood.base() + currentGood.var() + (currentGood.ipl() * (4 - currentGood.mtlp()));
            canBuy = !(player1.getCredits() < price);
            assertEquals(canBuy, market1.canBuy(i));
        }
        player1.setCredits(0);
        for (int i = 0; i < 10; i++) {
            Good currentGood = goods[i];
            boolean canBuy;
            int price = currentGood.base() + currentGood.var() + (currentGood.ipl() * (4 - currentGood.mtlp()));
            canBuy = !(player1.getCredits() < price);
            assertEquals(canBuy, market1.canBuy(i));
        }
        player1.setCredits(9999);

        //The third test: based on the player's cargo space
        for (int i = 0; i < 10; i++) {
            boolean canBuy;
            canBuy = !(ship1.getCargoSpace() <= 0);
            assertEquals(canBuy, market1.canBuy(i));
        }
        ship1.setCurCargo(10);
        for (int i = 0; i < 10; i++) {
            boolean canBuy;
            canBuy = !(ship1.getCargoSpace() <= 0);
            assertEquals(canBuy, market1.canBuy(i));
        }
        ship1.setCurCargo(0);

        market1 = new Market(2, player1, ship1); //resets the shop and it's inventory
        //The fourth test: based on the market's inventory for each good
        for (int i = 0; i < 10; i++) {
            Good currentGood = goods[i];
            int amountOfGood = 10 * ((2 - currentGood.mtlp()) + 1); //the way inventory is calculated
            amountOfGood = amountOfGood < 0 ? 0 : amountOfGood;
            boolean canBuy = !(amountOfGood <= 0);
            assertEquals(canBuy, market1.canBuy(i));
        }

        System.out.println("All tests passed!");
    }
}