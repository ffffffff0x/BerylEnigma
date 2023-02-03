package ReflectTest;

import java.lang.annotation.Annotation;
import java.lang.module.ModuleFinder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: BerylEnigma
 * @author: RyuZU
 * @create: 2023-02-02 17:23
 **/

public class GetClassesWithAnnotation {
//    private static List<Class<?>> getAnnotatedClasses(String packageName, Class<? extends Annotation> annotation) throws Exception {
//        List<Class<?>> annotatedClasses = new ArrayList<>();
//        Module[] modules = ModuleLayer.boot().modules().toArray(Module[]::new);
//        for (Module module : modules) {
//            Set<Class<?>> classes = module.getPackages().stream()
//                    .filter(p -> p.getName().startsWith(packageName))
//                    .flatMap(p -> p.getDeclaredClasses().stream())
//                    .collect(Collectors.toSet());
//
//            for (Class<?> cls : classes) {
//                if (cls.isAnnotationPresent(annotation)) {
//                    annotatedClasses.add(cls);
//                }
//            }
//        }
//        return annotatedClasses;
//    }

    public static void main(String[] args) throws Exception {
        String packageName = "com.example";
//        List<Class<?>> annotatedClasses = getAnnotatedClasses(packageName, Deprecated.class);
//        annotatedClasses.forEach(System.out::println);
    }
}
