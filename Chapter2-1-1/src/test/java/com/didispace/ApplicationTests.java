package com.didispace;

import com.didispace.service.BlogProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(Application.class)已经过时
@SpringBootTest
public class ApplicationTests {

	private static final Log log = LogFactory.getLog(ApplicationTests.class);

	@Autowired
	private BlogProperties blogProperties;


	@Test
	public void test1() throws Exception {
		Assert.assertEquals("程序猿DD", blogProperties.getName());
		Assert.assertEquals("Spring Boot教程", blogProperties.getTitle());
		Assert.assertEquals("程序猿DD正在努力写《Spring Boot教程》", blogProperties.getDesc());

		log.info("随机数测试输出：");
		log.info("随机字符串 : " + blogProperties.getValue());
		log.info("随机int : " + blogProperties.getNumber());
		log.info("随机long : " + blogProperties.getBignumber());
		log.info("随机10以下 : " + blogProperties.getTest1());
		log.info("随机10-20 : " + blogProperties.getTest2());

	}

}
/**
 *
 * Spring是不推荐静态变量或者静态方法注入的，因为Spring的哲学是由Spring去生成bean实例，
 * 并帮你维护bean的生命周期以及相对应的依赖式注入的属性。
 * 而静态方法或者变量实际上是某个类所共享的属性，而不属于某个bean实例的属性。
 * 一旦依赖式注入了某个静态变量，会造成测试、维护等方面的各种问题，所以最好还是要避免静态变量的注入。
 * 当然，在某些情况下我们依然需要自己去实例化一些类并自己维护其生命周期，这时候我们确实又有需求
 * 使用到Spring context中的bean，这种情况下我们可以通过下面的方法将bean注入进来：
 * @Component public class Sample {
 * 		public static String name;
 * 		@Value(${my.name})
 * 		public void setName(String name){
 * 			Sample.name = name;
 * 		}
 * 	}
 * 	对于普通方法，@Value是可以生效的，这样我们就能够将Sample.name的值注入进来。 试试这样
 *
 * 测试如果出现乱码问题：
 * 		properties文件中文注入会乱码 org.junit.ComparisonFailure: Expected :程序猿DD Actual :?¨???????DD
 * 解决方案：
 * 		改完配置。删除文件，重新建一个就可以了。
 *
 */
