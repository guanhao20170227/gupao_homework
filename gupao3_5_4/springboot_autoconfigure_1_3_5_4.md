### 1 基于你对springboot的理解描述一下什么是springboot？

我认为 springboot是一个框架, 是一个服务于 spring 框架的框架, 它是为了更好的来简化创建项目的流程, 减少配置。

### 2.约定优于配置指的是什么？

​    约定优于配置就是默认的你的项目结构是什么样子的, 这样在多个项目间就可以实现 bean 跨 项目的 spring 的管理。

### 3.@SpringBootApplication由哪几个注解组成，这几个注解分别表示什么作用;

主要是由 @SpringBootConfiguration, @ComponentScan, @EnableAutoConfiguration 这三个注解组成;

1 @SpringBootConfiguration 也是一个复合注解,类似 @Configuration 注解， 类似 xml 配置的<beans />  常常与注解 @Bean 一起使用, 用来注入 bean 对象;

2 @ComponeScan 注解是用来指定扫描路径的, 等价于 xml 配置中的 <context: component-scan />;

3 @EnableAutoConfiguration 注解用来实现动态注入, 条件注入的;

​    动态注入主要通过实现 ImportSelector, ImportBeanDefinitionRegistrar 两个接口来实现;

​    条件注入主要是通过项目的目录结构来实现的;



### 4.springboot自动装配的实现原理；

​    自动装配的原理就是通过注解实现了类与类之间的依赖管理, 基于 “约定优于配置的理念” 明确项目的目录结构， 加上 SPI 的扩展机制， 使得项目之间交流的 bean 可以被 spring 统一管理.

参考博客: https://www.jianshu.com/p/88eafeb3f351



### 5.spring中的spi机制的原理是什么？

spring spi 机制的原理是通过反射获取具体实现类的实例；

参考博客: https://blog.csdn.net/liyuling52011/article/details/81058362





