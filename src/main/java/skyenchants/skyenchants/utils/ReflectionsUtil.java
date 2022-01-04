package skyenchants.skyenchants.utils;

import org.reflections.Reflections;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class ReflectionsUtil<T> {

    private final Reflections reflections;

    public ReflectionsUtil(String pathToScanner) {
        this.reflections = new Reflections(pathToScanner);
    }

    public Set<T> scanPathForSubTypes(Class<T> clazz) {
        Set<T> containT = new HashSet<>();
        this.reflections.getSubTypesOf(clazz)
                .forEach(o -> {
                    T classObj = null;
                    try {
                        classObj = (T) o.newInstance();
                        containT.add(classObj);
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        return containT;
    }
}
