package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private final String bunName;
    private final String ingredientSauceName;
    private final String ingredientFillingName;
    private final float bunPrice;
    private final float ingredientSaucePrice;
    private final float ingredientFillingPrice;

    public BurgerParameterizedTest(String bunName, String ingredientSauceName, String ingredientFillingName, float bunPrice, float ingredient1Price, float ingredientFillingPrice) {
        this.bunName = bunName;
        this.ingredientSauceName = ingredientSauceName;
        this.ingredientFillingName = ingredientFillingName;
        this.bunPrice = bunPrice;
        this.ingredientSaucePrice = ingredient1Price;
        this.ingredientFillingPrice = ingredientFillingPrice;
    }


    @Parameterized.Parameters(name = "Рецепт {0} {1} {2}")
    public static Object[][] burgerRecipes(){
        return new Object[][] {
                {"black bun", "hot sauce", "cutlet", 100, 100, 100},
                {"white bun", "chili sauce", "dinosaur", 200, 300, 200},
                {"red bun", "sour cream", "sausage", 300, 200, 300},
        };
    }

    @Mock
    private  Bun mockBun;

    @Mock
    private Ingredient mockIngredientSauce;

    @Mock
    private Ingredient mockIngredientFilling;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPriceReturnsTotalPrice(){
        Burger burger = new Burger();
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientSauce);
        burger.addIngredient(mockIngredientFilling);
        when(mockBun.getPrice()).thenReturn(bunPrice);
        when(mockIngredientSauce.getPrice()).thenReturn(ingredientSaucePrice);
        when(mockIngredientFilling.getPrice()).thenReturn(ingredientFillingPrice);
        float expectedPrice = bunPrice * 2 + ingredientSaucePrice + ingredientFillingPrice;
        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0);
    }

    @Test
    public void testGetReceiptReturnsStringReceipt(){
        Burger burger = new Burger();
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientSauce);
        burger.addIngredient(mockIngredientFilling);
        when(mockBun.getName()).thenReturn(bunName);
        when(mockIngredientSauce.getName()).thenReturn(ingredientSauceName);
        when(mockIngredientFilling.getName()).thenReturn(ingredientFillingName);
        when(mockIngredientSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredientFilling.getType()).thenReturn(IngredientType.FILLING);
        when(mockBun.getPrice()).thenReturn(bunPrice);
        when(mockIngredientSauce.getPrice()).thenReturn(ingredientSaucePrice);
        when(mockIngredientFilling.getPrice()).thenReturn(ingredientFillingPrice);
        String receipt = burger.getReceipt();
        String expected = String.format("(==== %s ====)%n", mockBun.getName()) +
                String.format("= %s %s =%n", "sauce", mockIngredientSauce.getName()) +
                String.format("= %s %s =%n", "filling", mockIngredientFilling.getName()) +
                String.format("(==== %s ====)%n", mockBun.getName()) +
                String.format("%nPrice: %f%n", bunPrice*2+ingredientSaucePrice+ingredientFillingPrice);

        assertEquals(expected, receipt);
    }

}