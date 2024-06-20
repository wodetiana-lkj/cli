package top.tsview.cli.utils;

import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.util.Collection;
import java.util.HashSet;

public class ReflectUtil {
    private final static String RESOURCE_PATTERN = "/**/*.class";

    @SneakyThrows
    public static Collection<Class<?>> getClass(String packagePath) {
        Collection<Class<?>> classes = new HashSet<>();
        //spring工具类，可以获取指定路径下的全部类
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    ClassUtils.convertClassNameToResourcePath(packagePath) + RESOURCE_PATTERN;
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            //MetadataReader 的工厂类
            MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                //用于读取类信息
                MetadataReader reader = metadataReaderFactory.getMetadataReader(resource);
                //扫描到的class
                String classFullName = reader.getClassMetadata().getClassName();
                Class<?> clazz = Class.forName(classFullName);
                classes.add(clazz);
            }
        return classes;
    }
}
