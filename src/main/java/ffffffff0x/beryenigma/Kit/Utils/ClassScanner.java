package ffffffff0x.beryenigma.Kit.Utils;

import io.github.classgraph.*;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @program: java_learn
 * @author: RyuZU
 * @create: 2023-02-01 11:18
 **/

public class ClassScanner {
    public List<Class<?>> getAnnotationClasses(String pkgName,Class<? extends Annotation> annotation) {
        try (ScanResult scanResult =
                     new ClassGraph()
//                             .verbose()               // Log to stderr
                             .enableAllInfo()         // Scan classes, methods, fields, annotations
                             .acceptPackages(pkgName)     // Scan pkgName and subpackages (omit to scan all packages)
                             .scan()) {               // Start the scan
//            for (ClassInfo routeClassInfo : scanResult.getClassesWithAnnotation(annotation)) {
//                System.out.println(routeClassInfo.getName());
//            }
            return scanResult.getClassesWithAnnotation(annotation).loadClasses();
        }
    }
}
