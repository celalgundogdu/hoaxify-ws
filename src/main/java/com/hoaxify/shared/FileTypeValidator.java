package com.hoaxify.shared;

import com.hoaxify.file.FileService;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileTypeValidator implements ConstraintValidator<FileType, String> {

    private final FileService fileService;

    private String[] supportedTypes;

    public FileTypeValidator(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void initialize(FileType constraintAnnotation) {
        this.supportedTypes = constraintAnnotation.types();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        String fileType = fileService.detectType(value);
        for (String supportedType : this.supportedTypes) {
            if (fileType.contains(supportedType)) {
                return true;
            }
        }
        createCustomMessage(context);
        return false;
    }

    private void createCustomMessage(ConstraintValidatorContext context) {
        String types = String.join(", ", this.supportedTypes);
        context.disableDefaultConstraintViolation();
        HibernateConstraintValidatorContext hibernateConstraintValidatorContext = context.unwrap(HibernateConstraintValidatorContext.class);
        hibernateConstraintValidatorContext.addMessageParameter("types", types);
        hibernateConstraintValidatorContext.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addConstraintViolation();
    }
}
