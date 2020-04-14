package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.coordinate.Direction;
import domain.coordinate.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static testAssistant.creationAssistant.*;

class KnightTest {
	Knight knight;

	@BeforeEach
	void setUp() {
		knight = createKnight("black", "a1");
	}

	@Test
	void move() {
		Coordinate coordinate = createPoint("b3");
		Knight expect = createKnight("black", "b3");

		assertThat(knight.move(coordinate)).isEqualTo(expect);
	}

	@Test
	void canMove() {
		Direction direction = createDirection("n");

		assertThatThrownBy(() -> knight.validateMoveDirection(direction))
				.isInstanceOf(CanNotMoveException.class);
	}
}