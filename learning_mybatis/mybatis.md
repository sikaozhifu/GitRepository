#### Mybatis架构：

![img](assets/clip_image002.gif)

##### Mybatis配置：

* SqlMapConfig.xml，此文件作为mybatis的全局配置文件，配置了mybatis的运行环境等信息。

* mapper.xml文件即sql映射文件，文件中配置了操作数据库的sql语句。此文件需要在SqlMapConfig.xml中加载。

* 通过mybatis环境等配置信息构造SqlSessionFactory即会话工厂

* 由会话工厂创建sqlSession即会话，操作数据库需要通过sqlSession进行。

* mybatis底层自定义了Executor执行器接口操作数据库，Executor接口有两个实现，一个是基本执行器、一个是缓存执行器。

* Mapped Statement也是mybatis一个底层封装对象，它包装了mybatis配置信息及sql映射信息等。mapper.xml文件中一个sql对应一个Mapped Statement对象，sql的id即是Mapped statement的id。

* Mapped Statement对sql执行输入参数进行定义，包括HashMap、基本类型、pojo，Executor通过Mapped Statement在执行sql前将输入的java对象映射至sql中，输入参数映射就是jdbc编程中对preparedStatement设置参数。

* Mapped Statement对sql执行输出结果进行定义，包括HashMap、基本类型、pojo，Executor通过Mapped Statement在执行sql后将输出结果映射至java对象中，输出结果映射过程相当于jdbc编程中对结果的解析处理过程。

  ##### Mybatis和Hibernate的区别：

   * Mybatis和hibernate不同，它不完全是一个ORM框架，因为MyBatis需要程序员自己编写Sql语句。mybatis可以通过XML或注解方式灵活配置要运行的sql语句；
   * mybatis无法做到数据库无关性；
   * Hibernate对象/关系映射能力强，数据库无关性好，对于关系模型要求高的软件（例如需求固定的定制化软件）如果用hibernate开发可以节省很多代码，提高效率。

  ##### Mapper动态代理方式：

  ​	遵循一下规范：

   * Mapper.xml文件中的namespace与mapper接口的类路径相同。

   * Mapper接口方法名和Mapper.xml中定义的每个statement的id相同

   * Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql 的parameterType的类型相同

   * Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同

     注册指定包下的所有mapper接口：

     例如：

     ```xml
     <package name="com.mybatis.mapper"/>
     ```

     注意：此种方法要求mapper接口名称和mapper映射文件名称相同，且放在同一个目录中。