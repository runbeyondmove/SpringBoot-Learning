package com.didispace;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置类
 * swagger api 的访问地址：http://localhost:8080/swagger-ui.html
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/4/18 下午12:02.
 * @blog http://blog.didispace.com
 */
@Configuration  //让Spring来加载该类配置
@EnableSwagger2  // 启用Swagger2
//@ConditionalOnProperty(value="dev")  //在哪个环境中使用
public class Swagger2 {

    /**
     * select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义，Swagger会扫描该包下所有Controller定义的API，
     * 并产生文档内容（除了被@ApiIgnore指定的请求）。
     *
     * @ApiOperation注解来给API增加说明、
     * @ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.didispace.web"))
                .paths(PathSelectors.any())
                .build();
    }

    // 该API文档的基本信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
                .termsOfServiceUrl("http://blog.didispace.com/")
                .contact("小润润") // 定义API文档编写作者
                .version("v1.0") // 定义API文档版本
                .build();
    }

    /**
     *
     1. 如何在生成环境中去除swagger2？除了使用@Profile还有其他方法吗
        可使用ConditionalOnProperty注解实现






     *
     *
     */

}
