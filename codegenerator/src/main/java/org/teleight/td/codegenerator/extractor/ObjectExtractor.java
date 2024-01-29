package org.teleight.td.codegenerator.extractor;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.teleight.td.codegenerator.rebuilder.Rebuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ObjectExtractor extends VoidVisitorAdapter<Void> {

    private final File outputPath;

    public ObjectExtractor() {
        try {
            outputPath = Files.createTempDirectory("teleight_objects").toFile();
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

        for (ClassOrInterfaceType extendedType : classDeclaration.getExtendedTypes()) {
            final String extendedNameAsString = extendedType.getNameAsString();
            final boolean isFunction = extendedNameAsString.equalsIgnoreCase("function");
            if(isFunction){
                return;
            }
        }


        classDeclaration.removeModifier(Modifier.Keyword.STATIC, Modifier.Keyword.FINAL);


        for (ClassOrInterfaceType permittedType : classDeclaration.getPermittedTypes()) {
            //This removes the TdApi class from the permitted types
            permittedType.setScope(null);
        }


        classDeclaration.getFieldByName("CONSTRUCTOR").ifPresent(Node::remove);
        for (ConstructorDeclaration constructor : classDeclaration.getConstructors()) {
            for (Parameter parameter : constructor.getParameters()) {
                String typeAsString = parameter.getTypeAsString();
                if (typeAsString.equalsIgnoreCase("DataInput")) {
                    constructor.remove();
                }
            }
        }

        //This removes all methods
        for (MethodDeclaration method : classDeclaration.getMethods()) {
            method.remove();
        }



        File outputClassFile = new File(outputPath, classNameAsString + ".java");
        try {
            Files.write(outputClassFile.toPath(), classDeclaration.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }



        try {
            Rebuilder rebuilder = new Rebuilder();
            rebuilder.remap(outputClassFile, TdClassType.OBJECTS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
