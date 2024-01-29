package org.teleight.td.codegenerator.rebuilder.types;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import org.teleight.td.codegenerator.utils.ClassUtils;

public class ConstructorRebuilder extends VoidVisitorAdapter<TypeSpec.Builder> {

    @Override
    public void visit(ConstructorDeclaration constructorDeclaration, TypeSpec.Builder builder) {
        super.visit(constructorDeclaration, builder);

        boolean shouldSave = true;


        MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder();

        NodeList<Modifier> modifiers = constructorDeclaration.getModifiers();
        for (com.github.javaparser.ast.Modifier modifier : modifiers) {
            final com.github.javaparser.ast.Modifier.Keyword keyword = modifier.getKeyword();
            final String keywordAsString = keyword.asString();
            constructorBuilder.addModifiers(javax.lang.model.element.Modifier.valueOf(keywordAsString.toUpperCase()));
        }


        for (Parameter parameter : constructorDeclaration.getParameters()) {
            Type parameterType = parameter.getType();
            String parameterName = parameter.getNameAsString();
            if (parameterType.asString().equalsIgnoreCase("datainput")) {
                shouldSave = false;
                continue;
            }

            TypeName wrappedClassType = ClassUtils.wrapType(parameterType);
            ParameterSpec.Builder parameterSpec = ParameterSpec.builder(wrappedClassType, parameterName);
            constructorBuilder.addParameter(parameterSpec.build());

            constructorBuilder.addStatement("this." + parameterName + " = " + parameterName);
        }


        if (shouldSave) {
            builder.addMethod(constructorBuilder.build());
        }
    }

}
