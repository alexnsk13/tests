package org.anas.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class QuadraticEquationTest {

    private QuadraticEquation equation;

    @BeforeEach
    public void setUp() {
        equation = new QuadraticEquation();
    }

    @Test
    public void solve_WhenDeterminantIsLessThanMinusEpsilon_ReturnEmptyArray() {
        assertThat(equation.solve(1, 0, 1)).isEmpty();
    }

    @Test
    public void solve_WhenDeterminantIsGreaterThanEpsilon_ReturnTwoRoots() {
        assertThat(equation.solve(1, 0, -1))
                .hasSize(2)
                .containsExactly(1, -1);
    }

    @Test
    public void solve_WhenDeterminantEqualToZero_ReturnEqualRoots() {
        assertThat(equation.solve(1, 2, 1))
                .hasSize(2)
                .containsExactly(-1, -1);
    }

    @Test
    public void solve_WhenDeterminantAbsIsLessThanOrEqualToEpsilon_ReturnEqualRoots() {
        double a = 1.0;
        double b = 2.0000000001;
        double c = 1.0;

        var roots = equation.solve(a, b, c);
        assertThat(roots[0]).isCloseTo(-1.0, within(1e-6));
        assertThat(roots[1]).isCloseTo(-1.0, within(1e-6));
        assertThat(roots[0]).isEqualTo(roots[1]);
    }

    @Test
    public void solve_WhenCoefficientAIsEqualToZero_ThrowException() {
        assertThatThrownBy(() -> equation.solve(0, 1, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Coefficient 'a' must not be zero");
    }

    @ParameterizedTest
    @MethodSource("provideAnglesForExecute")
    public void solve_WhenUnacceptableValues_ThrowException(double a) {
        assertThatThrownBy(() -> equation.solve(a, 1, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid coefficient");
    }

    private static Stream<Arguments> provideAnglesForExecute() {
        return Stream.of(
                Arguments.of(Double.NaN),
                Arguments.of(Double.POSITIVE_INFINITY),
                Arguments.of(Double.NEGATIVE_INFINITY)
        );
    }
}