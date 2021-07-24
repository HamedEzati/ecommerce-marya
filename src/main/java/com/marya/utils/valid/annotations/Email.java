package com.marya.utils.valid.annotations;

import com.marya.utils.valid.EmailClientValidationConstraint;
import com.marya.utils.valid.EmailConstraintValidator;
import org.primefaces.validate.bean.ClientConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author muludag on 4.05.2020
 */
@Target({METHOD,FIELD,ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy= EmailConstraintValidator.class)
@ClientConstraint(resolvedBy= EmailClientValidationConstraint.class)
@Documented
public @interface Email {

	String message() default "{org.primefaces.examples.primefaces}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
