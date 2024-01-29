package org.teleight.td.codegenerator.rebuilder.types;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import org.teleight.td.codegenerator.utils.ClassUtils;

import java.lang.reflect.Field;

public class ClassRebuilder extends VoidVisitorAdapter<TypeSpec.Builder> {

    public ClassRebuilder() {
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration classDeclaration, TypeSpec.Builder builder) {
        super.visit(classDeclaration, builder);

        for (Modifier modifier : classDeclaration.getModifiers()) {
            if(modifier.getKeyword().asString().toLowerCase().contains("sealed")){
                continue;
            }
            builder.addModifiers(javax.lang.model.element.Modifier.valueOf(modifier.getKeyword().asString().toUpperCase()));
        }


        for (ClassOrInterfaceType extendedType : classDeclaration.getImplementedTypes()) {
            String extendedTypeName = extendedType.getNameAsString().toLowerCase();
            if(extendedTypeName.contains("function")){
                continue;
            }
            if(extendedTypeName.contains("object")){
                continue;
            }
            builder.addSuperinterface(ClassUtils.wrapApiMethod(classDeclaration, extendedType));
        }


        for (ClassOrInterfaceType extendedType : classDeclaration.getExtendedTypes()) {
            String extendedTypeName = extendedType.getNameAsString().toLowerCase();
            if(extendedTypeName.contains("function")){
                continue;
            }
            if(extendedTypeName.contains("object")){
                continue;
            }
            builder.superclass(ClassUtils.wrapType(extendedType));
        }




        ClassName genericParameter = ClassName.get("it.tdlight.jni.TdApi", classDeclaration.getNameAsString());
        String classNormalizedName = classDeclaration.getNameAsString();


        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("test")
                .addParameter(ParameterSpec.builder(genericParameter,"input").build())
                .addModifiers(javax.lang.model.element.Modifier.PUBLIC)
                ;
        for (FieldDeclaration field : classDeclaration.getFields()) {
            for (VariableDeclarator variable : field.getVariables()) {
                String typeName = variable.getTypeAsString();
                Type fieldType = variable.getType();
                String variableName = variable.getName().asString();





                if (fieldType instanceof ClassOrInterfaceType classOrInterfaceType) {
                    if(classOrInterfaceType.getName().getIdentifier().equalsIgnoreCase("String")){
                        appendString(methodBuilder, variable);
                        continue;
                    }
                    appendTdLibType(methodBuilder, classOrInterfaceType, variable);
                }
            }
        }

        builder.addMethod(methodBuilder.build());
    }

    private void appendString(MethodSpec.Builder methodBuilder, VariableDeclarator variable) {
        final String variableName = variable.getName().asString();
        final String statement = String.format("input.%s = this.%s", variableName, variableName);
        methodBuilder.addStatement(statement);
    }


    private void appendTdLibType(MethodSpec.Builder methodBuilder, ClassOrInterfaceType classOrInterfaceType, VariableDeclarator variable) {
        String typeName = variable.getTypeAsString();
        String variableName = variable.getName().asString();


        String arguments = "";
        String normalizedType = "";

        try {
            var clazz = Class.forName("it.tdlight.jni.TdApi$" + classOrInterfaceType.getName());
            for (Field declaredField : clazz.getDeclaredFields()) {
                String declaredFieldName = declaredField.getName();
                if (declaredFieldName.equalsIgnoreCase("CONSTRUCTOR")) {
                    continue;
                }
                arguments += variableName + "." + declaredFieldName + ", ";
            }

            normalizedType = "it.tdlight.jni.TdApi." + typeName;
        } catch (ClassNotFoundException e) {
            arguments += variableName + ", ";
            normalizedType = "" + typeName;
        }

        if (arguments.isEmpty()) {
            return;
        }
        arguments = arguments.substring(0, arguments.length() - 2);


        final String statement = String.format("input.%s = new %s(%s)", variableName, normalizedType, arguments);
        methodBuilder.addStatement(statement);
    }

}
