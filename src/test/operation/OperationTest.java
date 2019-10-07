package operation;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OperationTest {

    @Test(expected = IllegalArgumentException.class)
    public void operationWithNullType() {
        new Operation(null, LocalDate.now(), 720.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void operationWithNullDate() {
        new Operation(OperationType.WITHDRAWAL, null, 55.23);
    }

    @Test
    public void operationWithCorrectParameters() {
        Operation operationTest = new Operation(OperationType.WITHDRAWAL, LocalDate.now(), 658.3);
        assertEquals(operationTest.getType(), OperationType.WITHDRAWAL);
        assertEquals(operationTest.getDate(), LocalDate.now());
        assertEquals(operationTest.getAmount(), 658.3, 0.0);
    }

    @Test
    public void readOperation() {
        Operation operationTest = new Operation(OperationType.WITHDRAWAL, LocalDate.now(), 658.3);
        assertTrue(operationTest.toString().contains("Operation at " + LocalDate.now().toString()));
        assertTrue(operationTest.toString().contains("is of type " + OperationType.WITHDRAWAL));
        assertTrue(operationTest.toString().contains("and of amount " + 658.3));
    }

}