package org.teleight.td.codegenerator.rebuilder.types;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import org.teleight.td.codegenerator.utils.ClassUtils;

import javax.lang.model.element.Modifier;

public class FieldRebuilder extends VoidVisitorAdapter<TypeSpec.Builder> {

    @Override
    public void visit(FieldDeclaration fieldDeclaration, TypeSpec.Builder builder) {
        super.visit(fieldDeclaration, builder);

        String fieldName = "";
        for (VariableDeclarator variableDeclarator : fieldDeclaration.getVariables()) {
            fieldName = variableDeclarator.getNameAsString();
        }
        final Type elementType = fieldDeclaration.getCommonType();
        final TypeName wrappedClassType = ClassUtils.wrapType(elementType);
        final FieldSpec.Builder fieldBuilder = FieldSpec.builder(wrappedClassType, fieldName);

        final Modifier[] wrappedModifiers = fieldDeclaration.getModifiers()
                .stream()
                .map(modifier -> Modifier.valueOf(modifier.getKeyword().asString().toUpperCase()))
                .toArray(Modifier[]::new);
        fieldBuilder.addModifiers(wrappedModifiers);

        builder.addField(fieldBuilder.build());
    }

}