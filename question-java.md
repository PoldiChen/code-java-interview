
[TOC]

#### 1. 接口多继承？code
Java类不能多继承，但接口之间可以多继承。

#### 2. Iterator和ListIterator？
Iterator可以遍历Set和List集合，ListIterator只能遍历List集合。<br>
Iterator只能向前遍历，ListIterator可以双向遍历。<br>
ListIterator实现了Iterator接口，并包含其他功能，比如增加元素、替换元素、获取前一个和后一个元素的索引。<br>

#### 3. 线程的监视器？如何做线程同步？
监视器和锁一起使用，监视器监视一块同步代码块，确保一次只有一个线程执行同步代码块。<br>
每个监视器都和一个对象引用相关联。<br>
线程在获得锁之前不允许执行同步代码块。<br>
Java提供了显式监视器（Lock）和隐式监视器（synchronized）两种锁方案。？？？<br>

#### 4. 为什么集合类没有实现Cloneable和Serializable接口？
克隆(cloning)或者是序列化(serialization)的语义和含义是跟具体的实现相关的。因此，应该由集合类的具体实现来决定如何被克隆或者是序列化。<br>
Collection接口指定一组对象，对象即是它的元素，如何维护这些元素由Collection的具体实现决定，比如List的元素允许重复而Set不允许。Collection只是一个抽象表现。<br>
涉及到具体实现时，clone和serialize的语义和含义才发挥作用。<br>

#### 5. HashMap、Hashtable和ConcurrentHashMap的区别？ConcurrentHashMap能否完全替代Hashtable？HashMap为什么是线程不安全的？
HashMap |	Hashtable |	ConcurrentHashMap
-| - | -
非线程安全 |	线程安全 | 线程安全
/ | 实现基于锁 |	锁分段
key和value允许为null | key和value不允许为null | /

HashMap和TreeMap的区别？

HashMap |	TreeMap
-|-
无序 | 有序
适用于插入、删除、定位 |	适用于有序的遍历
JDK 1.7：Entry<K, V>数据<br>JDK 1.8：数组+链表（链表的长度达到一个阀值8后转化为红黑树） |	红黑树

不能完全替代。Hashtable的迭代器是强一致性的，ConcurrentHashMap是弱一致性的。<br>
弱一致性是指在ConcurrentHashMap中put一个元素，对get不是立即可见的，为了提升效率，效率和一致性的权衡。<br>
多线程环境下扩容会导致Node链表形成环形（？？？）结构，next节点永远不为空，导致死循环。

#### 6. Comparable和Comparator接口
Comparable接口 | Comparator接口
-|-
compareTo(Object o)方法，和this比较，返回负数，0，正数来表示输入对象小于，等于，大于已存在的对象 | compare(Object o1, Object o2)方法和equals()方法，comparator对象作为参数，和当前comparator的排序相同的时候返回true
用于定义对象的自然顺序 | 用于定义用户定制的顺序

#### 7. HashSet和TreeSet？HashSet如何检查重复？
HashSet | TreeSet
-| -
由hash表实现（内部使用HashMap实现） | 由树形结构实现（红黑树？）
无序 | 有序，维持了插入顺序
添加，删除，查找的时间复杂度是O(1) | 添加，删除，查找的时间复杂度是O(log(n))

将元素加入HashSet时，先计算hashCode判断加入的位置，同时与集合中已存在的元素的hashCode做比较，没有相同则没有重复，如果有相同再调用equals()判断hashCode相等的两个元素是否真的相同，是则不加入。

#### 8. Java的两种异常
运行时异常 RuntimeException | 检查式异常 checkedException
-|-
异常出现时由虚拟机接管 | 必须try catch
ClassCastException<br>NullPointerException<br>IndexOutOfBoundsException<br>ArithmeticException<br>UnmodifiableSetException | /

异常的基类为Throwable，Error和Exception继承自Throwable；<br>
RuntimeException继承自Exception;<br>
Error和RuntimeException及其子类是unchecked exception，其他的Exception类都是checked exception

#### 9. 数据库语句Statement、PreparedStatement和CallableStatement
Statement | PreparedStatement | CallableStatement
-|-|-
/ | 预编译，性能更好<br>可以重用<br>安全，减少SQL注入攻击 | 执行存储过程。<br>存储过程可以接收参数，也可以返回结果。<br>提供了安全性和模块化。<br>CallableStatement.prepareCall();

#### 10. PreparedStatement如何减少SQL注入攻击？
PreparedStatement使用预编译机制，参数用占位符?代替，setXX传入参数的时候已经过了预编译，所以传入特殊值不会起作用。<br>
PreparedStatement不允许在插入参数时改变查询的逻辑结构，只是把输入参数作为数据处理，不需要再进行解析。

#### 11. 数据库连接池有哪些？各自的优缺点？
C3P0、DBCP、tomcat-jdbc-pool<br>
C3P0和DBCP是单线程的，tomcat-jdbc-pool支持高并发

#### 12. 事务的数据读问题，数据脏读、幻读和不可重复读？数据更新问题，第一类丢失更新，第二类丢失更新？

#### 13. 什么是URL编码和URL解码？
URL编码是把URL中的空格和其他特殊字符替换成对应的十六进制表示，反之则是解
码。

#### 14. 线程的sleep方法、yield方法和对象的wait方法？
wait | sleep | yield
-|-|-
Object类的实例方法 | Thread的静态方法 | Thread的静态方法
放弃对象的锁 | 保持对象的锁 | ？？
进入阻塞状态<br>notify或notifyAll后进入等锁池，重新获得锁后进入就绪态 | 进入阻塞状态<br> | sleep时间结束后自动回到就绪状态	进入就绪状态

#### 15. 类加载器的双亲委派模型？
除了顶层的启动类加载器，其他的类加载器都有自己的父类加载器。<br>
如果一个类加载器收到了加载类的请求，首先会将请求委派给父类加载器，所有的请求
最终都会传递到顶层的启动类加载器。父类加载器反馈无法加载时，子类加载器才自己去加载类。<br>
Java类随着它的类加载器具备了一个优先级的层次关系，比如Object类只能由顶层的
启动类加载器加载。<br>
类加载器的层级关系：启动类加载器（Bootstrap ClassLoader）->扩展类加载器（Extension ClassLoader）->应用程序类加载器（Application ClassLoader）->自定义类加载器（User ClassLoader）。

#### 16. 类实例化的顺序？code
(1)	初始化父类的静态代码块，包括静态变量<br>
(2)	初始化子类的静态代码块<br>
(3)	初始化父类的非静态代码，包括非静态变量<br>
(4)	执行父类的构造函数<br>
(5)	初始化子类的非静态代码<br>
(6)	执行子类的构造函数<br>

#### 17. Java的BIO、NIO和AIO？code
BIO | NIO | AIO
-|-|-
同步阻塞 | 同步非阻塞 | 异步非阻塞
多线程，一个连接一个线程 | 少量线程或单线程，多个连接共用一个线程，一个请求一个线程 | 一个有效请求一个线程
面向流 | 面向缓冲<br>多通道 | /
适用于连接数量比较小且固定的场景 | 适用于连接数量多，但连接比较短的场景 | 适用于连接数量多且连接长的场景

Java NIO中有一个选择器（selector），可以将多个通道（channel）注册到一个选择器上，然后使用一个线程来监视这些线程：如果这些通道中有某个可以进行读写操作，则进行相应的读写操作。在等待通道变为可读写的时候，请求的线程可以去做别的事情。

#### 18. Java静态代理和动态代理？动态代理的实现方式？实际的应用？？？code
应用：spring框架的AOP

#### 19. 深复制和浅复制？code
浅复制：被复制的对象的所有变量都与原对象有相同的值，而对其他对象的引用仍指向原对象。即只考虑要复制的对象，不考虑该对象引用的其他对象。<br>
深复制：被复制的对象的所有变量都与原对象有相同的值，对其他对象的引用指向新复制的对象。即把要复制的对象引用的其他对象也复制了一遍。<br><br>
深复制有两种方式：<br>
1) 重写需要复制的对象的clone方法，将引用的对象也复制一遍<br>
2) 使用序列化和反序列化的方法。

#### 20. 堆内存溢出和栈内存溢出？
堆溢出包括内存泄露和内存溢出，内存泄露是指无用的对象没有被回收，一直积累；内存溢出是指程序需要的内存大于虚拟机分配的内存。<br>
栈主要存放栈帧，局部变量表、操作数栈、动态链接、方法出口信息；栈相关的内存异
常包括StackOverFlowError(方法调用次数太多，栈内存不够新建栈帧，比如递归的层次太多)和OutOfMemoryError(线程太多，栈内存不够新建线程)。



















##### 100. question100
