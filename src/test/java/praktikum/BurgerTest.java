package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    @Mock
    private Ingredient mockIngredient2;

    @Mock
    private Ingredient mockIngredient3;

    @Test
    public void testSetBunSetsBunSetsBun(){
        Burger burger = new Burger();
        burger.setBuns(mockBun);
        assertNotNull(burger.bun);
    }

    @Test
    public void testAddIngredientAddsIngredientToIngredientList(){
        Burger burger = new Burger();
        burger.addIngredient(mockIngredient);
        assertEquals(mockIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testAddIngredientAddsOnlyOneIngredientToIngredientList(){
        Burger burger = new Burger();
        burger.addIngredient(mockIngredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientRemovesIngredientFromIngredientList(){
        Burger burger = new Burger();
        burger.ingredients.add(mockIngredient);
        burger.ingredients.add(mockIngredient2);
        burger.ingredients.add(mockIngredient3);
        burger.removeIngredient(1);
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientRemovesChosenIngredientFromIngredientList(){
        Burger burger = new Burger();
        burger.ingredients.add(mockIngredient);
        burger.ingredients.add(mockIngredient2);
        burger.ingredients.add(mockIngredient3);
        burger.removeIngredient(1);
        assertFalse(burger.ingredients.contains(mockIngredient2));
    }

    @Test
    public void testMoveIngredientMovesIngredientInsideIngredientList(){
        Burger burger = new Burger();
        burger.ingredients.add(mockIngredient);
        burger.ingredients.add(mockIngredient2);
        burger.ingredients.add(mockIngredient3);
        burger.moveIngredient(0, 2);
        assertTrue(burger.ingredients.get(0).equals(mockIngredient3) || burger.ingredients.get(2).equals(mockIngredient));
    }

}
