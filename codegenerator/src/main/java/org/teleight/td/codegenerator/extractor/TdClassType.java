package org.teleight.td.codegenerator.extractor;

import org.teleight.td.codegenerator.CodeGenerator;

public enum TdClassType {

    FUNCTIONS(CodeGenerator.DEFAULT_PACKAGE + ".functions"),
    OBJECTS(CodeGenerator.DEFAULT_PACKAGE + ".objects");

    private final String packageName;

    TdClassType(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

}
