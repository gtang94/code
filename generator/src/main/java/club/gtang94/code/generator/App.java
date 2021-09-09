package club.gtang94.code.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.base.Strings;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    // 数据库URL
    private static final String URL="jdbc:mysql://127.0.0.1:3306/bb2?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    // 数据库驱动
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    // 数据库用户名
    private static final String USERNAME = "root";
    // 数据库密码
    private static final String PASSWORD = "123456";
    // @author 值
    private static final String AUTHOR = "tanggq";
    // 包的基础路径
    private static final String BASE_PACKAGE_URL = "com.bobang.common";
    // xml文件路径
    private static final String XML_PACKAGE_URL = "/src/main/resources/mapper/";
    // xml 文件模板
    private static final String XML_MAPPER_TEMPLATE_PATH = "templates/mapper.xml";
    // mapper 文件模板
    private static final String MAPPER_TEMPLATE_PATH = "templates/mapper.java";
    // entity 文件模板
    private static final String ENTITY_TEMPLATE_PATH = "templates/entity.java";
    // service 文件模板
    private static final String SERVICE_TEMPLATE_PATH = "templates/service.java";
    // serviceImpl 文件模板
    private static final String SERVICE_IMPL_TEMPLATE_PATH = "templates/serviceImpl.java";
    // controller 文件模板
    private static final String CONTROLLER_TEMPLATE_PATH = "templates/controller.java";
    // 表前缀
    private static final String TABLE_PREFIX = "qf";


    public static void main( String[] args ) {

        AutoGenerator generator = new AutoGenerator();

        generator.setGlobalConfig(globalConfig());
        generator.setDataSource(dataSourceConfig());
        generator.setStrategy(strategyConfig());
        generator.setTemplate(templateConfig());
        generator.setPackageInfo(packageConfig());

        generator.execute();;
    }

    /**
     * 全局配置
     */
    private static GlobalConfig globalConfig() {
        return new GlobalConfig()
                .setOpen(false) // 打开文件
                .setFileOverride(true) // 文件覆盖
                .setActiveRecord(false) //开启activeRecord模式
                .setBaseResultMap(true) // mapper.xml生成查询映射结果
                .setBaseColumnList(true) // 生成查询结果列
                .setSwagger2(true) // 开启swagger，需要swagger依赖
                .setAuthor(AUTHOR)
                .setServiceName("%sService")
                .setMapperName("%sDao")
                ;
    }

    /**
     * 数据源配置
     */
    private static DataSourceConfig dataSourceConfig() {
        return new DataSourceConfig()
                .setDbType(DbType.MYSQL) // 数据库类型
                .setDriverName(DRIVER_NAME) // 连接驱动
                .setUrl(URL) // DB地址
                .setUsername(USERNAME) // DB用户名
                .setPassword(PASSWORD) // DB密码
                ;
    }

    /**
     * 代码生成策略配置
     */
    private static StrategyConfig strategyConfig() {
        return new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel) // 表名生成策略： 下划线转驼峰
                .setColumnNaming(NamingStrategy.underline_to_camel) // 字段生成策略： 下划线转驼峰
                .setRestControllerStyle(true) // 生成controller
                .setTablePrefix(TABLE_PREFIX) // 去除表前缀
                .setControllerMappingHyphenStyle(false) // controller驼峰转连字符
                .setEntityLombokModel(true) // lombok依赖
                .setEntityTableFieldAnnotationEnable(true) // 实体字段注解
                .setInclude(scanner("表名："))
                .setTableFillList(
                        Arrays.asList(
                                new TableFill("update_time", FieldFill.UPDATE),
                                new TableFill("create_time", FieldFill.INSERT)
                ));
    }

    /**
     * 自定义模板配置
     */
    private static TemplateConfig templateConfig() {
        return new TemplateConfig()
                .setXml(XML_MAPPER_TEMPLATE_PATH)
                .setMapper(MAPPER_TEMPLATE_PATH)
                .setEntity(ENTITY_TEMPLATE_PATH)
                .setService(SERVICE_TEMPLATE_PATH)
                .setServiceImpl(SERVICE_IMPL_TEMPLATE_PATH)
                .setController(CONTROLLER_TEMPLATE_PATH)
                ;
    }

    /**
     * 包配置
     */
    private static PackageConfig packageConfig() {
        return new PackageConfig()
                .setMapper("dao")
                .setParent(BASE_PACKAGE_URL) // 生成包配置
                ;
    }

    private static String scanner(String tip) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + ":");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (!Strings.isNullOrEmpty(ipt)) {
                return ipt;
            }
        }

        throw new MybatisPlusException("请输入正确的" + tip);
    }
}
