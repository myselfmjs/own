在 SpringBootApplication 上使用@ServletComponentScan 注解后，
Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码

Servlet 8大监听对象
监听Context、Request、Session对象的创建和销毁
ServletContextListener
ServletRequest
ListenerHttpSessionListener​​
监听Context、Request、Session对象属性的变化
ServletContextAttributeListener
ServletRequestAttributeListener
HttpSessionAttributeListener​
监听Session内部的对象
HttpSessionActivationListener
HttpSessionBindingListener

Springboot 实现自定义拦截器只需要3步：
   1、创建我们自己的拦截器类并实现 HandlerInterceptor 接口
   2、创建一个Java类实现WebMvcConfigurer，并重写 addInterceptors 方法
   2、实例化我们自定义的拦截器，然后将对像手动添加到拦截器链中（在addInterceptors方法中添加）


启动加载数据 直接实现 CommandLineRunner 并重写run 方法即可： @Order 控制执行顺序