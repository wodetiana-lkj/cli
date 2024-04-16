package top.tsview.cli.generate;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.Data;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

public class SQLGenerate {
    private static final Datasource DATASOURCE;

    static {
        DATASOURCE = getDatasource();
    }

    public static void main(String[] args) {
        generate();
    }


    private static void generate() {
        FastAutoGenerator.create(DATASOURCE.getUrl(), DATASOURCE.getUsername(), DATASOURCE.getPassword())
                .globalConfig(builder -> {
                    builder
                            .disableOpenDir()   // 生成完后是否打开目录
                            //.enableSwagger() // 开启 swagger 模式
                            .author("ts-view") // 设置作者
                            .outputDir("src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder
                            .parent("top.tsview") // 设置父包名
                            .moduleName("cli") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                //.strategyConfig(builder -> {
                //    builder.addInclude("user"); // 设置需要生成的表名
                //    //.addTablePrefix("t_", "c_"); // 设置过滤表前缀
                //})
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    /**
     * 解析数据源
     */
    @SuppressWarnings("unchecked")
    private static Datasource getDatasource() {
        Yaml yml = new Yaml();
        InputStream inputStream = SQLGenerate.class.getClassLoader().getResourceAsStream("application.yml");
        Map<String, Object> map = yml.load(inputStream);
        Map<String, Object> spring = (Map<String, Object>) map.get("spring");
        Map<String, String> datasource = (Map<String, String>) spring.get("datasource");

        Datasource data = new Datasource();
        data.setDriverClassName(datasource.get("driver-class-name"));
        data.setUrl(datasource.get("url"));
        data.setUsername(datasource.get("username"));
        data.setPassword(String.valueOf(datasource.get("password")));
        return data;
    }


    @Data
    static class Datasource {
        String driverClassName;
        String url;
        String username;
        String password;
    }
}


