SQL语言共分为四大类：数据查询语言DQL，数据操纵语言DML，数据定义语言DDL，数据控制语言DCL。

- 数据查询语言DQL

  数据查询语言DQL基本结构是由SELECT子句，FROM子句，WHERE
  子句组成的查询块：
  SELECT <字段名表>
  FROM <表或视图名>
  WHERE <查询条件>

- 数据操纵语言DML

  数据操纵语言DML主要有三种形式：
  1) 插入：INSERT
  2) 更新：UPDATE
  3) 删除：DELETE

- 数据定义语言DDL

  数据定义语言DDL用来创建数据库中的各种对象-----表、视图、索引、同义词、聚簇等如：
  CREATE TABLE/VIEW/INDEX/SYN/CLUSTER等
  表 视图 索引 同义词 簇

  DDL操作是隐性提交的！不能rollback 

- 数据控制语言DCL

  数据控制语言DCL用来授予或回收访问数据库的某种特权，并控制
  数据库操纵事务发生的时间及效果，对数据库实行监视等。如：
  1) GRANT：授权。

  2) ROLLBACK [WORK] TO [SAVEPOINT]：回退到某一点。
  回滚---ROLLBACK
  回滚命令使数据库状态回到上次最后提交的状态。其格式为：
  SQL>ROLLBACK;

  3) COMMIT [WORK]：提交。

  在数据库的插入、删除和修改操作时，只有当事务在提交到数据
  库时才算完成。在事务提交前，只有操作数据库的这个人才能有权看
  到所做的事情，别人只有在最后提交完成后才可以看到。
  提交数据有三种类型：显式提交、隐式提交及自动提交。下面分
  别说明这三种类型。

  + 显示提交

    用COMMIT命令直接完成的提交为显式提交。其格式为：
    SQL>COMMIT；

  + 隐式提交

    用SQL命令间接完成的提交为隐式提交。这些命令是：
    ALTER，AUDIT，COMMENT，CONNECT，CREATE，DISCONNECT，DROP，
    EXIT，GRANT，NOAUDIT，QUIT，REVOKE，RENAME

  + 自动提交

    若把AUTOCOMMIT设置为ON，则在插入、修改、删除语句执行后，
    系统将自动进行提交，这就是自动提交。其格式为：
    SQL>SET AUTOCOMMIT ON；

预编译对象PreparedStatement方法摘要：

            1. executeQuery():在此PreparedStatement对象中执行SQL语句，并返回该查询生成的ResultSet对象。
            2. executeUpdate():在此PreparedStatement对象中执行SQL语句，该语句必须是一个SQL数据操作语言(Date Manipulation Language，DML)语句，比如insert、update、delete语句；或者是无返内容的SQL语句，比如DDL（数据定义语言）语句。
            3. execute():在此PreparedStatement对象中执行SQL语句，该语句可以是任何种类的SQL语句。
            4. getMetaData():获取包含有关ResultSet对象列信息的ResultSetMetaData对象，ResultSet对象将在此执行PreparedStatement对象时返回。
            5. getParameterMetaData():获取此PreparedStatement对象的参数的编号、类型和属性。
            6. setAsciiStream(int parameterIndex, InputStream x, int longth):将指定参数设置为给定输入流，该输入流将具有给定字节数。