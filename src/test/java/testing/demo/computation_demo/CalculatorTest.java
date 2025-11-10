package testing.demo.computation_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class CalculatorTest {

	private Calculator calculator;
	private static int count = 0;

	@BeforeAll
	static void beforeAll() {
		System.out.println("[Before All] Calculator Test suite starting ... by Mark Moore\n");
	}

	@BeforeEach
	void beforeEachTest(TestInfo testInfo) {
		calculator = new Calculator();
		System.out.printf("[Before Each] Starting Test #%d: %s%n", ++count, testInfo.getDisplayName());
	}

	@AfterEach
	void afterEachTest(TestInfo testInfo) {
		System.out.printf("[After Each] Finished Test #%d: %s%n%n", count, testInfo.getDisplayName());
	}

	@AfterAll
	static void afterAll() {
		System.out.printf("[After All] completed %d test invocations by Mark Moore.%n", count);
	}

	// --- add(int,int) with @MethodSource ---
	@ParameterizedTest(name = "{0} + {1} = {2}")
	@DisplayName("Add two numbers")
	@MethodSource("provideAddData")
	void add_twoNumbers(int input1, int input2, int expected) {
		assertEquals(expected, calculator.add(input1, input2));
	}

	static Stream<Arguments> provideAddData() {
		return Stream.of(Arguments.of(100, 2, 102), Arguments.of(100, -2, 98), Arguments.of(-100, 2, -98),
				Arguments.of(-100, -2, -102));
	}

	// --- substract(int,int) with @CsvSource ---
	@ParameterizedTest(name = "{0} - {1} = {2}")
	@DisplayName("Subtract two numbers")
	@CsvSource({ "100, 2, 98", "100, -2, 102", "-100, 2, -102", "-100, -2, -98" })
	void substract_twoNumbers(int input1, int input2, int expected) {
		assertEquals(expected, calculator.substract(input1, input2));
	}

	// --- multiple(int,int) with @CsvFileSource ---
	@ParameterizedTest(name = "{0} * {1} = {2}")
	@DisplayName("Multiply two numbers")
	@CsvFileSource(resources = "/data/calculator-multiply.csv", numLinesToSkip = 1)
	void multiple_twoNumbers(int input1, int input2, int expected) {
		assertEquals(expected, calculator.multiple(input1, input2));
	}

	// --- divide(int,int) ONE negative test ---
	@Test
	@DisplayName("Divide by zero throws IllegalArgumentException")
	void divide_byZero() {
		assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0));
	}
	
	class CalculatorCoverageTest {
	    private final Calculator calc = new Calculator();

	    @Test
	    void divide_normal_positive() {
	        assertEquals(2, calc.divide(4, 2));
	    }

	    @Test
	    void divide_normal_signs() {
	        assertEquals(-2, calc.divide(4, -2));
	    }
	}
}
