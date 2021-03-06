### 正则表达式

推荐：[正则表达式30分钟入门教程](https://www.cnblogs.com/deerchao/archive/2006/08/24/zhengzhe30fengzhongjiaocheng.html)

正则表达式（Regular Expression，在代码中常简写为regex、regexp或RE）

使用单个字符来描述、匹配一系列符合某个句法规则的字符串。用常用来检索、替换哪些符合某个模式的文本。

##### 元字符

* \b

  代表单词的开头或者结尾，也就是单词的分界处。匹配一个位置。

* .

  匹配除了换行符以外的任意字符。

* \*

  代表数量，它指定*前面的内容可以连续重复使用任意次以使整个表达式得以匹配。

* \d

  表示匹配一位数字（0，或者1，或者2······）

  例如匹配中国的电话号码，且区号为三位数的。

  0\d\d-\d\d\d\d\d\d\d\d	也可以这样写0\d{2}-\d{8}

* \s

  表示匹配任意空白符，包括空格，制表符（Tab），换行符，中文全角空格等。

* \w

  表示匹配字母或者数字或者下划线或汉字等。

  例如匹配以字母a开头的单词

  \ba\w*\b

* \+

  匹配重复1次或者多次

  例如匹配一个或者更多连续的数字

  \d+

* ^和$

  都表示匹配一个位置，^匹配查找字符串的开头，$匹配查找字符串的结尾。在验证输入内容的时候非常有用

  例如要求填写的数字为5到12位

  ^\d{5,12}$

  {5,12}表示重复的次数不能少于5次，不能多余12次。

  | 字符 | 说明                         |
  | :--: | :--------------------------- |
  |  .   | 匹配除换行符以外的任意字符   |
  |  \w  | 匹配字母或数字或下划线或汉字 |
  |  \s  | 匹配任意的空白符             |
  |  \d  | 匹配数字                     |
  |  \b  | 匹配单词的开始或结束         |
  |  ^   | 匹配字符串的开始             |
  |  $   | 匹配字符串的结束             |

##### 字符转义

如果需要查找元素本身，比如查找.或者^等等，可以使用\来转义，即\.和\^。如果查找\本身，则也使用\\。

##### 重复次数

* ?

  表示重复一次或者多次

  | 字符  | 说明             |
  | :---: | ---------------- |
  |   *   | 重复零次或更多次 |
  |   +   | 重复一次或更多次 |
  |   ?   | 重复零次或一次   |
  |  {n}  | 重复n次          |
  | {n,}  | 重复n次或更多次  |
  | {n,m} | 重复n到m次       |

##### 字符类

如果想匹配没有预定义元字符的字符集，例如匹配元音字母a，e，i，o，u，则只需要在方括号里面列出来即可[aeiou]，[?.!]匹配标点符号（.或?或!）

[0-9]表示的含义与\d完全一致，即匹配一位数字。

##### 分支条件

表示的是有几种规则，如果满足其中任意一种规则都应当匹配成。即用|把不同的规则分隔开。

\\(?0\d{2}\\)?[- ]?\d{8}|0\d{2}[- ]?\d{8}表示匹配3位区号的电话号码，其中区号可以用小括号括起来，也可以不用，区号与本地号间可以用连字号或空格间隔，也可以没有间隔。

\\(?0\d{3}\\)?[- ]?\d{7}|0\d{3}[- ]?\d{7}表示支持4位区号。

**但是在使用分枝条件的时候，要注意各个条件的顺序。因为将会从左到右地测试每个条件，如果满足了某个分枝，就不在管其他分枝了。**

##### 分组

表示的是可以重复多个字符，使用小括号来指定子表达式（也叫做分组）

例如匹配IP地址。

(?=(\b|\D))((2[0-4]\d|25[0-5]|[01]?\d\d?)\\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)(?=(\b|\D))

##### 反义

表示查找不属于某个能简单定义的字符类的字符，比如查找除了数字以外的任意字符。

|  字符   | 说明                                       |
| :-----: | ------------------------------------------ |
|   \W    | 匹配任意不是字母，数字，下划线，汉字的字符 |
|   \S    | 匹配任意不是空白符的字符                   |
|   \D    | 匹配任意非数字的字符                       |
|   \B    | 匹配不是单词开头或结束的位置               |
|  [^x]   | 匹配除了x以外的任意字符                    |
| [^abcd] | 匹配除了abcd这几个字母以外的任意字符       |

例子：

\S+匹配不包含空白符的字符串

<a\[^>]+>匹配用尖括号括起来的以a开头的字符串

##### 后向引用

使用小括号指定一个子表达式后，**匹配这个子表达式的文本**(也就是此分组捕获的内容)可以在表达式或其它程序中作进一步的处理。默认情况下，每个分组会自动拥有一个组号，规则是：从左向右，以分组的左括号为标志，第一个出现的分组的组号为1，第二个为2，以此类推。 

后向引用用于重复搜索前面某个分组匹配的文本。例如\1代表分组1匹配的文本。

\b(\w+)\b\s+\1\b可以用来匹配重复的单词，例如go go

\1表示分组1中捕获的内容（也就是前面匹配的那个单词）。

| 分类     | 代码/语法    | 说明                                                         |
| -------- | ------------ | ------------------------------------------------------------ |
| 捕获     | (exp)        | 匹配exp,并捕获文本到自动命名的组里                           |
| 捕获     | (?<name>exp) | 匹配exp,并捕获文本到名称为name的组里，也可以写成(?'name'exp) |
| 捕获     | (?:exp)      | 匹配exp,不捕获匹配的文本，也不给此分组分配组号               |
| 零宽断言 | (?=exp)      | 匹配exp前面的位置                                            |
| 零宽断言 | (?<=exp)     | 匹配exp后面的位置                                            |
| 零宽断言 | (?!exp)      | 匹配后面跟的不是exp的位置                                    |
| 零宽断言 | (?<!exp)     | 匹配前面不是exp的位置                                        |
| 注释     | (?#comment)  | 这种类型的分组不对正则表达式的处理产生任何影响，用于提供注释让人阅读 |

##### 零宽断言

在使用正则表达式时，有时我们需要捕获的内容前后必须是特定内容，但又不捕获这些特定内容的时候，零宽断言就起到作用了 。

概念：零宽断言正如它的名字一样，是一种零宽度的匹配，它匹配到的内容不会保存到匹配结果中去，最终匹配结果只是一个位置而已。**用于查找在某些内容(但并不包括这些内容)之前或之后的东西，也就是说它们像\b,^,$那样用于指定一个位置，这个位置应该满足一定的条件(即断言)，因此它们也被称为零宽断言**

作用：是给指定位置添加一个限定条件，用来规定此位置之前或者之后的字符必须满足限定条件才能使正则中的字表达式匹配成功 。

以下内容复制于[有shi以来最详细的正则表达式入门教程](https://www.cnblogs.com/yunfeifei/p/4071467.html)

**正则表达式的先行断言和后行断言一共有4种形式：** 

* (?=pattern) 零宽正向先行断言(zero-width positive lookahead assertion) 
* (?!pattern) 零宽负向先行断言(zero-width negative lookahead assertion) 
* (?<=pattern) 零宽正向后行断言(zero-width positive lookbehind assertion) 
* (?<!pattern) 零宽负向后行断言(zero-width negative lookbehind assertion) 

这里面的pattern是一个正则表达式。

如同^代表开头，$代表结尾，\b代表单词边界一样，先行断言和后行断言也有类似的作用，它们只匹配某些**位置**，在匹配过程中，不占用字符，所以被称为“零宽”。所谓**位置**，是指字符串中(每行)第一个字符的左边、最后一个字符的右边以及相邻字符的中间（假设文字方向是头左尾右）。 
下面分别举例来说明这4种断言的含义。

* **(?=pattern) 正向先行断言** 

代表字符串中的一个位置，**紧接该位置之后**的字符序列**能够匹配**pattern。 
例如对”a regular expression”这个字符串，要想匹配**re**gular中的re，但不能匹配expression中的re，可以用”re(?=gular)”，该表达式限定了re右边的位置，这个位置之后是gular，但并不消耗gular这些字符，将表达式改为”re(?=gular).”，将会匹配reg，元字符.匹配了g，括号这一砣匹配了e和g之间的位置。

* **(?!pattern) 负向先行断言** 

代表字符串中的一个位置，**紧接该位置之后**的字符序列**不能匹配**pattern。 
例如对”regex represents regular expression”这个字符串，要想匹配除**re**gex和**re**gular之外的re，可以用”re(?!g)”，该表达式限定了re右边的位置，这个位置后面不是字符g。负向和正向的区别，就在于该位置之后的字符能否匹配括号中的表达式。

* **(?<=pattern) 正向后行断言** 

代表字符串中的一个位置，**紧接该位置之前**的字符序列**能够匹配**pattern。 
例如对”regex represents regular expression”这个字符串，有4个单词，要想匹配单词内部的re，但不匹配单词开头的re，可以用”(?<=\w)re”，单词内部的re，在re前面应该是一个单词字符。之所以叫后行断言，是因为正则表达式引擎在匹配字符串和表达式时，是从前向后逐个扫描字符串中的字符，并判断是否与表达式符合，当在表达式中遇到该断言时，正则表达式引擎需要往字符串前端检测已扫描过的字符，相对于扫描方向是向后的。

* **(?<!pattern) 负向后行断言** 

代表字符串中的一个位置，**紧接该位置之前**的字符序列**不能匹配**pattern。 
例如对”regex represents regular expression”这个字符串，要想匹配单词开头的re，可以用”(?<!\w)re”。单词开头的re，在本例中，也就是指不在单词内部的re，即re前面不是单词字符。当然也可以用”\bre”来匹配。

##### 贪婪匹配

表示在使整个表达式能得到匹配的前提下，匹配尽可能多的字符。

例如a.*b它将会匹配最长的以a开始，以b结束的字符串。

##### 懒惰匹配

表示在使整个表达式能得到匹配的前提下，尽可能匹配少的字符。即在后面加？。

| 代码/语法 | 说明                            |
| --------- | ------------------------------- |
| *?        | 重复任意次，但尽可能少重复      |
| +?        | 重复1次或更多次，但尽可能少重复 |
| ??        | 重复0次或1次，但尽可能少重复    |
| {n,m}?    | 重复n到m次，但尽可能少重复      |
| {n,}?     | 重复n次以上，但尽可能少重复     |

##### 注释

通过语法(?#comment)来包含注释

##### 平衡组/递归匹配

有时候需要匹配可嵌套的层次结构，例如嵌套的div标签

需要用到以下构造语法：

- (?'group') 把捕获的内容命名为group,并压入堆栈(Stack)

- (?'-group') 从堆栈上弹出最后压入堆栈的名为group的捕获内容，如果堆栈本来为空，则本分组的匹配失败

- (?(group)yes|no) 如果堆栈上存在以名为group的捕获内容的话，继续匹配yes部分的表达式，否则继续匹配no部分

- (?!) 零宽负向先行断言，由于没有后缀表达式，试图匹配总是失败

  **解释为：第一个就是在黑板上写一个"group"，第二个就是从黑板上擦掉一个"group"，第三个就是看黑板上写的还有没有"group"，如果有就继续匹配yes部分，否则就匹配no部分。**

  平衡组的一个最常见的应用就是匹配HTML,下面这个例子可以匹配嵌套的<div>标签：

  <div|\[^>]*>\[^<>]*(((?'Open'<div\[^>]*>)\[^<>]*)+((?'-Open'\</div>)[\^<>]*)+)*(?(Open)(?!))\</div> 