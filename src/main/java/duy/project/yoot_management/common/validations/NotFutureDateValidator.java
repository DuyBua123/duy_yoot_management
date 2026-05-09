package duy.project.yoot_management.common.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class NotFutureDateValidator implements ConstraintValidator<NotFutureDateConstraint, LocalDate> {

    @Override
    public void initialize(NotFutureDateConstraint constraint) {
    }

    @Override
    public boolean isValid(
        LocalDate inputField,
        ConstraintValidatorContext cxt
    ) {
        LocalDate currentDate = LocalDate.now();

        return inputField != null &&
                !inputField.isAfter(currentDate);
    }

}
