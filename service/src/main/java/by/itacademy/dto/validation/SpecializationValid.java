package by.itacademy.dto.validation;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Yury V. on 10.08.17.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {ValidSpecializationConstraint.class})
@NotEmpty
@ReportAsSingleViolation
public @interface SpecializationValid {

    String message() default "validation.error.specialization_incorrect";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
