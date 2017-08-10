package by.itacademy.dto.validation;

import by.itacademy.entity.enums.Specialization;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yury V. on 10.08.17.
 */
public class ValidSpecializationConstraint implements ConstraintValidator<SpecializationValid, String> {

    private Set<String> getSetFormSpecializationEnum() {
        Set<String> enumerationSet = new HashSet<>();

        for (Specialization value : Specialization.values()) {
            enumerationSet.add(value.name());
        }

        return enumerationSet;
    }

    @Override
    public void initialize(SpecializationValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return getSetFormSpecializationEnum().contains(value);
    }
}
