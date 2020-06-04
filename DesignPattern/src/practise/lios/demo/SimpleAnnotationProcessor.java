package practise.lios.demo;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @author liaiguang
 * @created 2020/6/3
 */
@SupportedAnnotationTypes(
        "practise.lios.demo.SimpleAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class SimpleAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for(TypeElement t : annotations) {
            System.out.println(t);
        }
        for(Element el :
                roundEnv.getElementsAnnotatedWith(SimpleAnnotation.class)) {
            display(el);
        }
        return false;
    }

    void display(Element element) {
        System.out.println("==== " + element + " ====");
        System.out.println(element.getKind() +
                " : " + element.getModifiers() +
                " : " + element.getSimpleName() +
                " : " + element.asType());
        if(element.getKind().equals(ElementKind.CLASS)) {
            TypeElement te = (TypeElement)element;
            System.out.println(te.getQualifiedName());
            System.out.println(te.getSuperclass());
            System.out.println(te.getEnclosedElements());
        }
        if(element.getKind().equals(ElementKind.METHOD)) {
            ExecutableElement ex = (ExecutableElement)element;
            System.out.print(ex.getReturnType() + " ");
            System.out.print(ex.getSimpleName() + "(");
            System.out.println(ex.getParameters() + ")");
        }
    }
}