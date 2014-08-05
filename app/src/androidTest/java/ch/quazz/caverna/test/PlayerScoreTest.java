package ch.quazz.caverna.test;

import android.test.AndroidTestCase;
import org.mockito.Mockito;

import ch.quazz.caverna.Cattle;
import ch.quazz.caverna.Family;
import ch.quazz.caverna.Homeboard;
import ch.quazz.caverna.Inventory;
import ch.quazz.caverna.PlayerScore;

public class PlayerScoreTest extends AndroidTestCase {

    private PlayerScore testee;

    private int initialScoreDwarfs = 2;
    private int initialScoreFarmAnimals = -8;
    private int initialScore = initialScoreDwarfs + initialScoreFarmAnimals;

    @Override
    protected void setUp() throws Exception {
        testee = new PlayerScore();
    }

    public void test_the_initial_score_counts_two_dwarfs() {
        assertEquals(2, testee.getCount(PlayerScore.Item.Dwarfs));
    }

    public void test_the_initial_count_of_anything_else_than_dwarfs_is_zero() {
        for (PlayerScore.Item item : PlayerScore.Item.values()) {
            if (item != PlayerScore.Item.Dwarfs) {
                assertEquals(0, testee.getCount(item));
            }
        }
    }

    public void test_initial_score_is_two_dwarfs_and_missing_farm_animal_cost() {
        assertEquals(initialScore, testee.score());
    }

    public void test_each_dwarf_scores_a_point() {
        final int initialScoreWithoutDwarfs = initialScore - initialScoreDwarfs;

        testee.setCount(PlayerScore.Item.Dwarfs, 3);
        assertEquals(initialScoreWithoutDwarfs + 3, testee.score());

        testee.setCount(PlayerScore.Item.Dwarfs, 5);
        assertEquals(initialScoreWithoutDwarfs + 5, testee.score());
    }

    public void test_each_missing_type_of_farm_animal_costs_two_points() {
        final int initialScoreWithoutFarmAnimals = initialScore - initialScoreFarmAnimals;

        final int farmAnimals = 4;
        assertEquals(initialScoreWithoutFarmAnimals - (farmAnimals * 2), testee.score());
    }

    public void test_each_animal_scores_a_point() {
        final int initialScoreWithoutFarmAnimals = initialScore - initialScoreFarmAnimals;

        testee.setCount(PlayerScore.Item.Dogs, 1);
        testee.setCount(PlayerScore.Item.Sheep, 1);
        testee.setCount(PlayerScore.Item.Donkeys, 1);
        testee.setCount(PlayerScore.Item.Boars, 1);
        testee.setCount(PlayerScore.Item.Cattle, 1);

        assertEquals(initialScoreWithoutFarmAnimals + 5, testee.score());
    }
}