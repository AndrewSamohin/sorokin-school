package org.schoolsorokin.spring.springcore_review.commands;

import org.schoolsorokin.spring.springcore_review.ConsoleOperationType;

public interface OperationCommand {
    void execute();
    ConsoleOperationType getOperationType();
}
