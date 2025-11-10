package testing.demo.computation_demo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 * CalculatorTest
 * 
 * Covers all operations in Calculator.java:
 * add, subtract, multiply, divide
 * Includes both normal and exceptional paths for 100% line coverage.
 * 
 * Author: Mark Moore
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {

    private Calculator calculator;

    @BeforeAll
    static void beforeAll() {
        System.out.println("[Before All] Calculator Test suite starting ... by Mark Moore");
    }

    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        calculator = new Calculator();
        System.out.println("[Before Each] Starting: " + testInfo.getDisplayName());
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        System.out.println("[After Each] Finished: " + testInfo.getDisplayName());
    }

    @AfterAll
    static void afterAll() {
        System.out.println("[After All] All Calculator tests completed by Mark Moore.");
    }

    // ---------- Addition ----------
    @Test @Order(1)
    @DisplayName("Add positive numbers")
    void testAddPositive() {
        assertEquals(102, calculator.add(100, 2));
    }

    @Test @Order(2)
    @DisplayName("Add negative and positive numbers")
    void testAddNegativePositive() {
        assertEquals(98, calculator.add(100, -2));
    }

    @Test @Order(3)
    @DisplayName("Add negative numbers")
    void testAddNegative() {
        assertEquals(-98, calculator.add(-100, 2));
    }

    // ---------- Subtraction ----------
    @Test @Order(4)
    @DisplayName("Subtract numbers")
    void testSubtract() {
        assertEquals(98, calculator.subtract(100, 2));
    }

    @Test @Order(5)
    @DisplayName("Subtract with negatives")
    void testSubtractNegative() {
        assertEquals(-98, calculator.subtract(-100, -2));
    }

    // ---------- Multiplication ----------
    @Test @Order(6)
    @DisplayName("Multiply numbers")
    void testMultiply() {
        assertEquals(200, calculator.multiply(100, 2));
    }

    @Test @Order(7)
    @DisplayName("Multiply negatives")
    void testMultiplyNegative() {
        assertEquals(-200, calculator.multiply(100, -2));
    }

    @Test @Order(8)
    @DisplayName("Multiply two negatives")
    void testMultiplyBothNegatives() {
        assertEquals(200, calculator.multiply(-100, -2));
    }

    // ---------- Division ----------
    @Test @Order(9)
    @DisplayName("Divide normal positive numbers")
    void testDivideNormalPositive() {
        assertEquals(2, calculator.divide(4, 2));
    }

    @Test @Order(10)
    @DisplayName("Divide with negative divisor")
    void testDivideNegative() {
        assertEquals(-2, calculator.divide(4, -2));
    }

    @Test @Order(11)
    @DisplayName("Divide with negative dividend")
    void testDivideNegativeDividend() {
        assertEquals(-2, calculator.divide(-4, 2));
    }

    @Test @Order(12)
    @DisplayName("Divide both negative")
    void testDivideBothNegative() {
        assertEquals(2, calculator.divide(-4, -2));
    }

    @Test @Order(13)
    @DisplayName("Divide by zero throws exception")
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(100, 0));
    }
}
