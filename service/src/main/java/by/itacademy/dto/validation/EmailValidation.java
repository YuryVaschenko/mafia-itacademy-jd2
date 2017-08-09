package by.itacademy.dto.validation;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Yury V. on 10.08.17.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@NotEmpty
@Pattern(regexp = "([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}")
@Constraint(validatedBy = {})
@ReportAsSingleViolation
public @interface EmailValidation {

    String message() default "validation.error.email_incorrect";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
