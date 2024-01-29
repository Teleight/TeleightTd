package org.teleight.td.codegenerator.extractor;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.teleight.td.api.ApiMethod;
import org.teleight.td.codegenerator.rebuilder.Rebuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FunctionsExtractor extends VoidVisitorAdapter<Void> {

    private final File outputPath;

    public FunctionsExtractor() {
        try {
            outputPath = Files.createTempDirectory("teleight_functions").toFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration classDeclaration, Void extractor) {
        super.visit(classDeclaration, extractor);

        final String classNameAsString = classDeclaration.getNameAsString();

        //This removes the TdApi class
        if (classNameAsString.equalsIgnoreCase("tdapi")) {
            return;
        }



        for (ClassOrInterfaceType extendedType : classDeclaration.getExtendedTypes().stream().toList()) {
            final String extendedNameAsString = extendedType.getNameAsString();
            final boolean isFunction = extendedNameAsString.equalsIgnoreCase("function");
            if (!isFunction) {
                return;
            }
            classDeclaration.getExtendedTypes().remove(extendedType);
        }




        classDeclaration.removeModifier(Modifier.Keyword.STATIC, Modifier.Keyword.FINAL);


        classDeclaration.getFieldByName("CONSTRUCTOR").ifPresent(Node::remove);
        for (ConstructorDeclaration constructor : classDeclaration.getConstructors()) {
            for (Parameter parameter : constructor.getParameters()) {
                String typeAsString = parameter.getTypeAsString();
                if (typeAsString.equalsIgnoreCase("DataInput")) {
                    constructor.remove();
                }
            }
        }
        for (MethodDeclaration method : classDeclaration.getMethods()) {
            method.remove();
        }



        ClassOrInterfaceType interfaceType = new ClassOrInterfaceType(ApiMethod.class.getSimpleName());
        classDeclaration.getImplementedTypes().addLast(interfaceType);




        File outputClassFile = new File(outputPath, classNameAsString + ".java");
        try {
            Files.write(outputClassFile.toPath(), classDeclaration.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Rebuilder rebuilder = new Rebuilder();
            rebuilder.remap(outputClassFile, TdClassType.FUNCTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
