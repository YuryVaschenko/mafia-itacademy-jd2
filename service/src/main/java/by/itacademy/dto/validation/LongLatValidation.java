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
 * Created by Yury V. on 05.08.17.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@NotEmpty
@Pattern(regexp = "\\d{2}[.]\\d{1,6}")
@Constraint(validatedBy = {})
@ReportAsSingleViolation
public @interface LongLatValidation {

    String message() default "validation.error.longlat_incorrect";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
