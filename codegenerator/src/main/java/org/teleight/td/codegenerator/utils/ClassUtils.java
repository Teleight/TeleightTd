package org.teleight.td.codegenerator.utils;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import org.teleight.td.api.ApiMethod;
import org.teleight.td.codegenerator.CodeGenerator;

public interface ClassUtils {

    static TypeName wrapType(Type elementType) {
        TypeName wrappedClassType = null;
        if (elementType.isPrimitiveType()) {
            final String primitiveType = elementType.asString();
            if (primitiveType.equals("long")) {
                wrappedClassType = ClassName.LONG;
            }
            if (primitiveType.equals("int")) {
                wrappedClassType = ClassName.INT;
            }
            if (primitiveType.equals("boolean")) {
                wrappedClassType = ClassName.BOOLEAN;
            }
            if (primitiveType.equals("double")) {
                wrappedClassType = ClassName.DOUBLE;
            }
            if (primitiveType.equals("float")) {
                wrappedClassType = ClassName.FLOAT;
            }
            if (primitiveType.equals("byte")) {
                wrappedClassType = ClassName.BYTE;
            }
            if (primitiveType.equals("short")) {
                wrappedClassType = ClassName.SHORT;
            }
            if (primitiveType.equals("char")) {
                wrappedClassType = ClassName.CHAR;
            }
        }
        if (elementType.isArrayType()) {
            final Type componentType = elementType.asArrayType().getComponentType();
            final TypeName wrappedComponentType = wrapType(componentType);
            return ArrayTypeName.of(wrappedComponentType);
        }


        try {
            CombinedTypeSolver typeSolver = new CombinedTypeSolver(new ReflectionTypeSolver());
            ResolvedType resolvedType = JavaParserFacade.get(typeSolver).convertToUsage(elementType);
            if (resolvedType.isReferenceType()) {
                String fullyQualifiedName = resolvedType.asReferenceType().getQualifiedName();

                int lastDotIndex = fullyQualifiedName.lastIndexOf('.');
                String packageName = lastDotIndex == -1 ? "" : fullyQualifiedName.substring(0, lastDotIndex);
                String simpleName = fullyQualifiedName.substring(lastDotIndex + 1);

                wrappedClassType = ClassName.get(packageName, simpleName);
            }
        }catch (Exception e){
            wrappedClassType = ClassName.get(CodeGenerator.DEFAULT_PACKAGE + ".objects", elementType.asString());
        }
        return wrappedClassType;
    }





    static TypeName wrapApiMethod(ClassOrInterfaceDeclaration classDeclaration, Type elementType) {
        if(elementType.asString().contains("ApiMethod")){
            final String packageName = ApiMethod.class.getPackageName();
            final String className = ApiMethod.class.getSimpleName();

            return ParameterizedTypeName.get(
                    ClassName.get(packageName, className),
                    ClassName.get("it.tdlight.jni.TdApi", classDeclaration.getNameAsString())
            );
        }
        return null;
    }

}
