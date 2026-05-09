package duy.project.yoot_management.common.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotFutureDateValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
public @interface NotFutureDateConstraint {

    String message() default "Input date must not be in the future";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
