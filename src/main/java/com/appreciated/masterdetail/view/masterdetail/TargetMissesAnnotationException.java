package com.appreciated.masterdetail.view.masterdetail;

public class TargetMissesAnnotationException extends RuntimeException {
    public TargetMissesAnnotationException(Class<?> navigationTarget, Class annotation) {
        super("The Class " + navigationTarget.getName() + " is missing the Annotation " + annotation.getName() + "!");
    }
}
