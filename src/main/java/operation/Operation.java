package operation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Operation {

    private OperationType type;
    private LocalDate date;
    private BigDecimal amount;

    public Operation(OperationType type, LocalDate date, BigDecimal amount) {
        if (type == null) {
            throw new IllegalArgumentException();
        }
        if (date == null) {
            throw new IllegalArgumentException();
        }
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Operation at " + date.toString() + " is of type " + type + " and of amount " + amount.toString();
    }

    public OperationType getType() {
        return this.type;
    }

    LocalDate getDate() {
        return this.date;
    }

    BigDecimal getAmount() {
        return this.amount;
    }
}
