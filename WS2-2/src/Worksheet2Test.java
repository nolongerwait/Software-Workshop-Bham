import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


/**
 * In Self-Testing, the code should be tested against three different types, normal cases, boundary cases, and illegal cases.
 * <p>
 * This class contains 8 tests to test the basic functions in class Worksheet2.
 * @author Zetian Qin zxq876
 * @version 2020-01-23 15:08:38
 */

public class Worksheet2Test {

    @BeforeEach
	public void setup(TestInfo testInfo) {
        System.out.println("Start......" + testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Tests for the Exercise 1")
    public void test1() {
        // Tests for the normal case
        Tree<Integer> testCase1 = 
        new Tree<Integer>(9, 
            new Tree<Integer>(7, 
                new Tree<Integer>(6, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(8, new Tree<Integer>(), new Tree<Integer>())), 
            new Tree<Integer>(11, 
                new Tree<Integer>(10, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(12, new Tree<Integer>(), new Tree<Integer>())));

        Tree<Integer> expectedCase1 = 
        new Tree<Integer>(-9, 
            new Tree<Integer>(-7, 
                new Tree<Integer>(-6, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(-8, new Tree<Integer>(), new Tree<Integer>())), 
            new Tree<Integer>(-11, 
                new Tree<Integer>(-10, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(-12, new Tree<Integer>(), new Tree<Integer>())));

        assertEquals(expectedCase1, Worksheet2.negateAll(testCase1));
    }

    @Test
    @DisplayName("Tests for the Exercise 2")
    public void test2() {
        // Tests for the normal case - True
        Tree<Integer> testCase1 = 
        new Tree<Integer>(2, 
            new Tree<Integer>(4, 
                new Tree<Integer>(6, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(8, new Tree<Integer>(), new Tree<Integer>())), 
            new Tree<Integer>(10, 
                new Tree<Integer>(12, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(14, new Tree<Integer>(), new Tree<Integer>())));

        assertTrue(Worksheet2.allEven(testCase1));

        // Tests for the normal case - False
        Tree<Integer> testCase2 = 
        new Tree<Integer>(1, 
            new Tree<Integer>(2, 
                new Tree<Integer>(3, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(4, new Tree<Integer>(), new Tree<Integer>())), 
            new Tree<Integer>(5, 
                new Tree<Integer>(6, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(7, new Tree<Integer>(), new Tree<Integer>())));

        assertFalse(Worksheet2.allEven(testCase2));

        // Test for the empty tree
        assertTrue(Worksheet2.allEven(new Tree<Integer>()));
    }

    @Test
    @DisplayName("Tests for the Exercise 3")
    public void test3() {
        // Tests for the normal case
        Tree<Integer> testCase1 = 
        new Tree<Integer>(9, 
            new Tree<Integer>(7, 
                new Tree<Integer>(6, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(8, new Tree<Integer>(), new Tree<Integer>())), 
            new Tree<Integer>(11, 
                new Tree<Integer>(10, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(12, new Tree<Integer>(), new Tree<Integer>())));

        assertEquals(0, Worksheet2.depth(testCase1, 9));
        assertEquals(1, Worksheet2.depth(testCase1, 7));
        assertEquals(2, Worksheet2.depth(testCase1, 8));
        assertEquals(2, Worksheet2.depth(testCase1, 6));
        assertEquals(1, Worksheet2.depth(testCase1, 11));
        assertEquals(2, Worksheet2.depth(testCase1, 10));
        assertEquals(2, Worksheet2.depth(testCase1, 12));
        assertEquals(-1, Worksheet2.depth(testCase1, 0));
        assertEquals(-1, Worksheet2.depth(new Tree<Integer>(), 12));
    }

    @Test
    @DisplayName("Tests for the Exercise 4")
    public void test4() {
        // Search Tree
        Tree<Integer> testCase1 = 
            new Tree<Integer>(84, 
                new Tree<Integer>(48, 
                    new Tree<Integer>(30), 
                    new Tree<Integer>(50)), 
                new Tree<Integer>(96, 
                    new Tree<Integer>(90),
                    new Tree<Integer>(100)));
        List<Integer> expectedCase1 = 
        new List<Integer>(84, 
            new List<Integer>(48, 
                new List<Integer>(30, 
                    new List<Integer>(50, 
                        new List<Integer>(96, 
                            new List<Integer>(90,
                                new List<Integer>(100, 
                                    new List<>())))))));
        assertEquals(expectedCase1, Worksheet2.preorder(testCase1));
        //Not a Search Tree
        Tree<Integer> testCase2 = 
            new Tree<Integer>(84, 
                new Tree<Integer>(30, 
                    new Tree<Integer>(48), 
                    new Tree<Integer>(10)), 
                new Tree<Integer>(68, 
                    new Tree<Integer>(98), 
                    new Tree<Integer>()));
        List<Integer> expectedCase2= 
            new List<Integer>(84, 
                new List<Integer>(30, 
                    new List<Integer>(48, 
                        new List<Integer>(10, 
                            new List<Integer>(68, 
                                new List<Integer>(98, 
                                    new List<>()))))));
        assertEquals(expectedCase2, Worksheet2.preorder(testCase2));
        // Empty Tree
        Tree<Integer> testCase3 = new Tree<Integer>();
        List<Integer> expectedCase3 = new List<Integer>();
        assertEquals(expectedCase3, Worksheet2.preorder(testCase3));
    }

    @Test
    @DisplayName("Tests for the Exercise 5")
    public void test5() {
        // Search Tree
        Tree<Integer> testCase1 = 
            new Tree<Integer>(84, 
                new Tree<Integer>(48, 
                    new Tree<Integer>(30), 
                    new Tree<Integer>(50)), 
                new Tree<Integer>(96, 
                    new Tree<Integer>(90),
                    new Tree<Integer>(100)));
        assertTrue(Worksheet2.isSearchTree(testCase1));

        //  Not Search Tree
        Tree<Integer> testCase2 = 
            new Tree<Integer>(84, 
                new Tree<Integer>(30, 
                    new Tree<Integer>(48), 
                    new Tree<Integer>(10)), 
                new Tree<Integer>(68, 
                    new Tree<Integer>(98), 
                    new Tree<Integer>()));
        assertFalse(Worksheet2.isSearchTree(testCase2));

        // Empty Tree
        Tree<Integer> testCase3 = new Tree<Integer>();
        assertTrue(Worksheet2.isSearchTree(testCase3));
    }

    @Test
    @DisplayName("Tests for the Exercise 7")
    public void test7() {
        // Empty Tree
        Tree<Integer> testCase1 = new Tree<Integer>();
        int expectedCase1 = 0;
        assertEquals(expectedCase1, testCase1);

        // Normal Search Tree
        Tree<Integer> testCase2 = 
            new Tree<Integer>(84, 
                new Tree<Integer>(48, 
                    new Tree<Integer>(30), 
                    new Tree<Integer>(50)), 
                new Tree<Integer>(96, 
                    new Tree<Integer>(90),
                    new Tree<Integer>(100)));
        int expectedCase2 = 100;
        assertEquals(expectedCase2, testCase2);
    }

    @Test
    @DisplayName("Tests for the Exercise 8")
    public void test8() {
        
        Tree<Integer> testCase = 
            new Tree<Integer>(84, 
                new Tree<Integer>(48, 
                    new Tree<Integer>(30), 
                    new Tree<Integer>(50)), 
                new Tree<Integer>(96, 
                    new Tree<Integer>(90),
                    new Tree<Integer>(100)));

        // Delete the leaves node
        Tree<Integer> expectedCase1 = 
            new Tree<Integer>(84, 
                new Tree<Integer>(48, 
                    new Tree<Integer>(30), 
                    new Tree<Integer>(50)), 
                new Tree<Integer>(96, 
                    new Tree<Integer>(90),
                    new Tree<Integer>()));
        assertEquals(expectedCase1, Worksheet2.delete(testCase, 100));

        // Delete the middle node
        Tree<Integer> expectedCase2 = 
            new Tree<Integer>(84, 
                new Tree<Integer>(48, 
                    new Tree<Integer>(30), 
                    new Tree<Integer>(50)), 
                new Tree<Integer>(100, 
                    new Tree<Integer>(90),
                    new Tree<Integer>()));
        assertEquals(expectedCase2, Worksheet2.delete(testCase, 96));

        // Delete the root node
        Tree<Integer> expectedCase3 = 
            new Tree<Integer>(90, 
                new Tree<Integer>(48, 
                    new Tree<Integer>(30), 
                    new Tree<Integer>(50)), 
                new Tree<Integer>(96, 
                    new Tree<Integer>(),
                    new Tree<Integer>(100)));
        assertEquals(expectedCase3, Worksheet2.delete(testCase, 84));
        
    }

    @Test
    @DisplayName("Tests for the Exercise 9")
    public void test9() {
        // Is Height-balanced tree
        Tree<Integer> testCase1 = 
            new Tree<Integer>(84, 
                new Tree<Integer>(48, 
                    new Tree<Integer>(30), 
                    new Tree<Integer>(50)), 
                new Tree<Integer>(96, 
                    new Tree<Integer>(90),
                    new Tree<Integer>(100)));
        assertTrue(Worksheet2.isHeightBalanced(testCase1));

        // Not Height-balanced tree
        Tree<Integer> testCase2 = 
            new Tree<Integer>(84, 
                new Tree<Integer>(48, 
                    new Tree<Integer>(30,
                        new Tree<Integer>(20,
                            new Tree<Integer>(10),
                            new Tree<Integer>()),
                        new Tree<Integer>()),
                    new Tree<Integer>(50)), 
                new Tree<Integer>(96, 
                    new Tree<Integer>(90),
                    new Tree<Integer>(100)));
        assertFalse(Worksheet2.isHeightBalanced(testCase2));

        // Empty Tree
        Tree<Integer> testCase3 = new Tree<Integer>();
        assertTrue(Worksheet2.isHeightBalanced(testCase3));
    }

    @Test
    @DisplayName("Tests for the Exercise 10")
    public void test10() {
        Tree<Integer> testCase = 
            new Tree<Integer>(84, 
                new Tree<Integer>(48, 
                    new Tree<Integer>(30), 
                    new Tree<Integer>(50)), 
                new Tree<Integer>(96, 
                    new Tree<Integer>(90),
                    new Tree<Integer>(100)));
        // Insert a leaves node to AVL tree
        Tree<Integer> expectedCase1 = 
            new Tree<Integer>(84, 
                new Tree<Integer>(48, 
                    new Tree<Integer>(30), 
                    new Tree<Integer>(50)), 
                new Tree<Integer>(96, 
                    new Tree<Integer>(90),
                    new Tree<Integer>(100,
                        new Tree<Integer>(),
                        new Tree<Integer>(140))));
        assertEquals(expectedCase1, Worksheet2.insertHB(testCase, 140));
        // Insert a non-leaves node to AVL tree
        Tree<Integer> expectedCase2 = 
            new Tree<Integer>(84, 
                new Tree<Integer>(48, 
                    new Tree<Integer>(30), 
                    new Tree<Integer>(50)), 
                new Tree<Integer>(96, 
                    new Tree<Integer>(90),
                    new Tree<Integer>(100,
                        new Tree<Integer>(98),
                        new Tree<Integer>())));
        assertEquals(expectedCase2, Worksheet2.insertHB(testCase, 98));
        // Delete a leaves node to AVL tree
        Tree<Integer> expectedCase3 = 
            new Tree<Integer>(84, 
                new Tree<Integer>(48, 
                    new Tree<Integer>(30), 
                    new Tree<Integer>(50)), 
                new Tree<Integer>(96, 
                    new Tree<Integer>(90),
                    new Tree<Integer>()));
        assertEquals(expectedCase3, Worksheet2.deleteHB(testCase, 100));
        // Delete a non-leaves node to AVL tree
        Tree<Integer> expectedCase4 = 
            new Tree<Integer>(84, 
                new Tree<Integer>(48, 
                    new Tree<Integer>(30), 
                    new Tree<Integer>(50)), 
                new Tree<Integer>(100, 
                    new Tree<Integer>(90),
                    new Tree<Integer>()));
        assertEquals(expectedCase4, Worksheet2.deleteHB(testCase, 96));
    }

    @AfterEach
	public void tearDown(TestInfo testInfo) {
		System.out.println("Finished..." + testInfo.getDisplayName());
	}
}