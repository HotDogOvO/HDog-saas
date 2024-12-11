import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.nio.file.Paths;

public class MybatisPlusGenerator {

    private final static String URL = "jdbc:mysql://124.221.85.208:3306/hotdog-saas?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "cyfe67373";
    private final static String PROJECT_PATH = Paths.get(System.getProperty("user.dir")) + "/hotdog-saas-infra/src/main/java";

    public static void main(String[] args) {
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> builder
                        .author("hotdog")
                        .outputDir(PROJECT_PATH)
                        .commentDate("yyyy-MM-dd HH:mm:ss")
                        .disableOpenDir()
                )
                .packageConfig(builder -> builder
                        .parent("com.hotdog.saas.infra")
                        .entity("entity")
                        .mapper("dao")
                        .xml("mapper.xml")
                )
                .strategyConfig(builder -> builder
                        .addInclude("system_properties")
                        .entityBuilder().enableLombok().naming(NamingStrategy.underline_to_camel).columnNaming(NamingStrategy.underline_to_camel).formatFileName("%sPO")
                        .serviceBuilder().disable()
                        .controllerBuilder().disable()
                )
                .execute();

    }
}
