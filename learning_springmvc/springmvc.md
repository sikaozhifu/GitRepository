#### springmvc配置：

需要在web.xml中配置前端控制器DispatcherServlet

```xml
<!-- 前端控制器 -->
  <servlet>
  	<servlet-name>springmvc</servlet-name>
  	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  	<init-param>
  		<param-name>contextConfigLocation</param-name>
  		<param-value>classpath:springmvc.xml</param-value>
  	</init-param>
  </servlet>
  <servlet-mapping>
  	<servlet-name>springmvc</servlet-name>
      <!-- 
		/*表示过滤所有包括jsp、css等
		/表示除了jsp，剩下的过滤
		*.action表示过滤后缀名
-->
  	<url-pattern>*.action</url-pattern>
  </servlet-mapping>
```

#### 框架结构：

![1526479096835](assets/1526479096835.png)

* 用户发送请求至前端控制器DispatcherServlet

* DispatcherServlet收到请求调用HandlerMapping处理器映射器。

* 处理器映射器根据请求url找到具体的处理器，生成处理器对象及处理器拦截器(如果有则生成)一并返回给DispatcherServlet。

* DispatcherServlet通过HandlerAdapter处理器适配器调用处理器

* 执行处理器(Controller，也叫后端控制器)。

* Controller执行完成返回ModelAndView

* HandlerAdapter将controller执行结果ModelAndView返回给DispatcherServlet

* DispatcherServlet将ModelAndView传给ViewReslover视图解析器

* ViewReslover解析后返回具体View

* DispatcherServlet对View进行渲染视图（即将模型数据填充至视图中）。

* DispatcherServlet响应用户

  #### 说明：

  在springmvc的各个组件中，处理器映射器（HandlerMapping）、处理器适配器（HandlAdapter）、视图解析器（ViewResolver）称为springmvc的三大组件。需要我们开发的组件有handler、view

  #### 组件扫描器：

  ```xml
  <!-- 配置扫描包 -->
  	<context:component-scan base-package="com.springmvc"></context:component-scan>
  ```

  #### 配置处理器映射器：

  从spring3.1版本开始，废除了DefaultAnnotationHandlerMapping的使用，推荐使用RequestMappingHandlerMapping完成注解式处理器映射。

  ```xml
  <!-- 处理器映射器 -->
  	<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping"></bean>
  ```

  #### 配置处理器适配器：

  从spring3.1版本开始，废除了AnnotationMethodHandlerAdapter的使用，推荐使用RequestMappingHandlerAdapter完成注解式处理器适配。

  ```xml
  <!-- 处理器适配器 -->
  	<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter"></bean>
  ```

  #### 配置注解驱动：

  直接配置处理器映射器和处理器适配器比较麻烦，SpringMVC在springmvc.xml中使用注解驱动来加载RequestMappingHandlerMapping和RequestMappingHandlerAdapter

  ```xml
  <mvc:annotation-driven />
  ```

  #### 配置视图解析器：

  ```xml
  <!-- 配置视图解析器 -->
  <!-- 
  	例如：/WEB-INF/jsp/test.jsp
  	之后只需要在程序中书写test即可
  -->
  	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
  		<property name="prefix" value="/WEB-INF/jsp/"></property>
  		<property name="suffix" value=".jsp"></property>
  	</bean>
  ```

  #### 配置监听器加载配置文件：

  ```xml
  <context-param>
    	<param-name>contextConfigLocation</param-name>
    	<param-value>classpath:applicationContext.xml</param-value>
    </context-param>
    
    <!-- 配置监听器 -->
    <listener>
    	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
  ```

  #### 解决post提交乱码问题：

  ```xml
  <!-- 过滤器 -->
    <filter>
    	<filter-name>encoding</filter-name>
    	<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    	<init-param>
    		<param-name>encoding</param-name>
    		<param-value>UTF-8</param-value>
    	</init-param>
    </filter>
    <filter-mapping>
    	<filter-name>encoding</filter-name>
    	<url-pattern>*.action</url-pattern>
    </filter-mapping>
  ```

  #### 配置Converter：

  ```xml
  <!-- 配置注解驱动 -->
  	<mvc:annotation-driven conversion-service="formattingConversionServiceFactoryBean"/>

  	<!--转换 -->
  	<bean id="formattingConversionServiceFactoryBean" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
  		<property name="converters">
  			<list>
  				<bean class="com.springmvc.convertion.DateConveter"></bean>
  			</list>
  		</property>
  	</bean>
  ```

  #### springmvc于Struts2的区别：

  * springmvc的入口是一个servlet即前端控制器，而struts2入口是一个filter过滤器。
  * springmvc是基于方法开发(一个url对应一个方法)，请求参数传递到方法的形参，可以设计为单例或多例(建议单例)，struts2是基于类开发，传递参数是通过类的属性，只能设计为多例。
  * Struts采用值栈存储请求和响应的数据，通过OGNL存取数据， springmvc通过参数解析器是将request请求内容解析，并给方法形参赋值，将数据和视图封装成ModelAndView对象，最后又将ModelAndView中的模型数据通过request域传输到页面。Jsp视图解析器默认使用jstl。

  #### Controller返回值

   * ModelAndView

   * void

   * String

     数据于视图分离，即解耦思想。

     ```java
     return "redirect:/item/itemList.action";
     //return "forward:/item/itemList.action";
     ```

  #### 上传文件：

   * 导入jar包；
   * 配置上传解析器

  ```xml
  <!-- 配置上传文件实现类 -->
  	<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
  		<property name="maxUploadSize" value="50000" />
  	</bean>
  ```

  * 方法：

  ```java
  @RequestMapping(value ="updateitem.action")
  	public String updateItem(Items items,MultipartFile pictureFile) throws Exception {
  		//文件的名字
  		String name = UUID.randomUUID().toString().replaceAll("-", "");
  		//扩展名
  		String extension = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
  		pictureFile.transferTo(new File("C:\\Users\\guanripeng\\Desktop\\upload"+name+"."+extension));
  		
  		items.setPic(name+"."+extension);
  		
  		itemService.updateItemById(items);
  		//重定向
  		
  //		return "redirect:/item/itemList.action";
  		return "redirect:/itemEdit.action"+items.getId();
  	}
  ```

  #### json数据交互：

  @RequestBody注解用于读取http请求的内容(字符串)，通过springmvc提供的HttpMessageConverter接口将读到的内容（json数据）转换为java对象并绑定到Controller方法的参数上。

  ```java
  /**
   * 测试json的交互
   * @param item
   * @return
   */
  @RequestMapping("testJson")
  // @ResponseBody
  public @ResponseBody Item testJson(@RequestBody Item item) {
  	return item;
  }
  ```

  #### 从URL上获取参数：

   * 使用注解@RequestMapping("item/{id}")声明请求的url{xxx}叫做占位符，请求的URL可以是“item /1”或“item/2”
  * 使用(@PathVariable() Integer id)获取url上的数据

  ```java
  /**
   * 使用RESTful风格开发接口，实现根据id查询商品
   * 
   * @param id
   * @return
   */
  @RequestMapping("item/{id}")
  @ResponseBody
  public Item queryItemById(@PathVariable() Integer id) {
  	Item item = this.itemService.queryItemById(id);
  	return item;
  }

  ```

  #### 拦截器：

  自定义拦截器，实现HandlerInterceptor接口

  在springmvc.xml中配置拦截器

  ```xml
  <!-- 配置拦截器 -->
  <mvc:interceptors>
  	<mvc:interceptor>
  		<!-- 所有的请求都进入拦截器 -->
  		<mvc:mapping path="/**" />
  		<!-- 配置具体的拦截器 -->
  		<bean class="cn.itcast.ssm.interceptor.HandlerInterceptor1" />
  	</mvc:interceptor>
  	<mvc:interceptor>
  		<!-- 所有的请求都进入拦截器 -->
  		<mvc:mapping path="/**" />
  		<!-- 配置具体的拦截器 -->
  		<bean class="cn.itcast.ssm.interceptor.HandlerInterceptor2" />
  	</mvc:interceptor>
  </mvc:interceptors>

  ```

  #### 拦截器处理流程：

   * preHandle按拦截器定义顺序调用
  * postHandler按拦截器定义逆序调用
  * afterCompletion按拦截器定义逆序调用
  * postHandler在拦截器链内所有拦截器返成功调用
  * afterCompletion只有preHandle返回true才调用

  ​