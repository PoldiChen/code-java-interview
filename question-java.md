
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

#### 21. 数据库查询left join、right join和inner join？
left：以左表为准，右表不存在的字段为null<br>
right：以右表为准，左表不存在的字段为null<br>
inner：取左右两张表都存在的字段

#### 22. 创建对象的几种方式？
(1) new<br>
(2) Class类的newInstance方法<br>
(3) Constructor类的newInstance方法<br>
(4) clone<br>
(5) 反序列化<br>
前三种调用了构造函数。两种newInstance就是反射，Class的newInstance方法内部调用了Constructor的newInstance方法，众多框架如Spring、Hibernate使用的是Constructor。

#### 23.Java的四种引用，强引用、软引用、弱引用、虚引用？？？？？？？？？？
强引用 |	软引用 | 弱引用 | 虚引用
-|-|-|-
引用存在则永远不会回收，即使内存不足，也是抛出OutOfMemory。<br>通过赋值为null可以中断强引用和对象的关联。 | 内存溢出之前回收 | 第二次垃圾回收时回收。<br>一旦发现就会回收？？？ | 每次垃圾回收都会被回收
/ | 实现图片缓存功能，内存不足时回收不再使用的Bitmap | / | 用来跟踪对象被垃圾回收的回收活动

因为Java的垃圾回收时机是不可控的，所以使用不同的引用类型来适当控制对象被回收的时机。<br>
通过软引用实现Java对象的高速缓存：软引用和HashMap的结合。？？？

#### 24. 进程间的通信方式？
(1) 管道（pipe）：管道是一种半双工的通信方式，数据只能单向流动，而且只能在具有亲缘关系的进程间使用。进程间的亲缘关系通常是指父子进程关系。<br>
(2) 有名管道（named pipe）：有名管道也是半双工的通信方式，但它允许无亲缘关系的进程间通信。<br>
(3) 信号量（semophore）：信号量是一个计数器，可以用来控制多个进程对共享资源的访问。常作为一种锁机制，防止某进程正在访问共享资源时，其他进程也访问该资源。因此，主要作为进程间以及同一进程的线程之间的同步手段。<br>
(4) 消息队列（message queue）：消息队列是由消息的链表，存放在内核中并由消息队列标识符标识。消息队列克服了信号量传递信息少、管道只能承载无格式字节流及缓冲区大小受限等缺点。<br>
(5) 信号（signal）：一种比较复杂的通信方式，用于通知接收进程某个事件已经发生。<br>
(6) 共享内存（shared memory）：映射一段能被其他进程访问的内存，这段内存由一个进程创建，但多个进程都可以访问。共享内存是最快的IPC方式，针对其他进程间通信方式运行效率低而设计的，往往与其他进程间通信方式，如信号量，配合使用。<br>
(7) 套接字（socket）：可用于不同机器间的进程通信。

#### 25. 连接MySQL数据库的语句？code

#### 26. 不使用框架的情况下的分页查询？
select * from table limit 300, 100;<br>
300表示起始位置，100表示查询的数量

#### 27. 数据库查询的左连接、右连接、内连接？
left join：A left join B on A.id = B.id;以左表A为准，右表没有的数据用null表示<br>
right join：A right join B on A.id = B.id;以右表B为准，左表没有的数据用null表示<br>
inner join：A inner join B on A.id = B.id;取出两张表都有的数据

#### 28. Oracle和MySQL的索引？
Oracle的索引有聚集索引、非聚集索引、唯一索引。<br>
查询时可以提高效率，但插入时需要重新排序，降低了效率。

#### 29. JDK 1.6和JDK 1.7的substring方法的区别？
1.7版本的substring方法会new String()创建一个新的字符串，1.6不会

#### 30. 可以随机访问的集合类？线程安全的集合类？
随机访问：ArrayList、HashMap、TreeMap、Hashtable<br>
线程安全：Vector、Hashtable、ConcurrentHashMap、Stack、Properties

#### 31. Collection和Collections的区别？
Collection | Collections
-|-
接口 | 工具类，提供了很多静态方法

#### 32. 创建只读集合、同步集合？
Collections.unmodifiableCollection(Collection c);<br>
Collections.synchronizedCollection(Collection c);

#### 33. 子类能否覆盖（override）父类的static方法？能否覆盖private方法？code
不能。覆盖是运行时动态绑定的，而static方法是在编译时静态绑定的。子类中可以有和父类中签名一样的方法，但不能用@override声明，一般称为隐藏。<br>
不能。也不能被继承。

#### 34. a = a + b;和a += b;的区别？
+=操作隐式的将结果强制转换为持有结果的类型。<br>
两个整型相加，如byte、short、int会提升到int类型，然后做加法操作

#### 35. volatile的作用？
(1) 提供顺序（happens-before）保证：阻止JVM对语句重排、确保一个线程对变量的修改对其他线程可见。<br>
(2) 提供可见性保证：CPU缓存（线程工作内存）和主存一致（解决缓存一致性问题）<br>
实例化一个对象的时候也可能发生语句重排，三个步骤：<br>
a. 分配内存空间<br>
b. 初始化对象<br>
c. 将内存空间的地址赋给对应的引用<br>
指令重排后可能先将地址赋给引用，再初始化对象，多线程环境下可能会将未初始化的对象暴露出来。<br>
(3) 将非原子操作变为原子操作（对单个变量的读写具有原子性）（不能保证++操作的原子性）：如double、long类型，64位，每次只能读取32位，不是原子操作，volatile能将其变成原子操作（内存屏障）。<br>
(4) 和CAS结合保证原子性（volatile本身不能保证原子性），如java.util.concurrent.atomic包下的类，AtomicInteger<br>
(5) 常用于标记状态量和double check。

#### 36. 能否创建一个包含不可变对象的可变对象？
可以，如包含日期对象的自定义对象。不能共享可变对象的引用

#### 37. 32位JVM和64位JVM的最大堆内存分别是多少？
32位堆内存2^32，4GB，寻址的基本单位是B，2^32=4G<br>
64位堆内存2^64。受限于物理内存和操作系统提供的虚拟内存。<br>
32位JVM的程序迁移到64位JVM上，性能会损失10%~20%。JVM虚拟机规范包括JIT编译器和垃圾回收，字节码更小更容易编译？？？指针越大GC管理越困难？？？

#### 38. JRE、JDK、JVM、JIT？
JRE：Java run-time environment，Java运行时环境<br>
JDK：Java development kit，开发工具，如编译器，也包括jre<br>
JVM：Java virutal machine，Java虚拟机，运行java程序<br>
JIT：Just In Time compilation，即时编译，将热点代码的字节码转化为本地代码，提高性能

#### 39. 怎样获取Java程序使用的内存？
java.lang.Runtime类，freeMemory()、totalMemory()、totalMemory()等方法

#### 40. 编译期常量？
public static final修饰的成员。<br>
在编译时会被替换掉，因为编译器直到这些常量的值，并且知道他们在运行时是不改变的。<br>
如果依赖的第三方库中有编译期常量，且引入后又发布人又修改过，则需要重新编译，否则使用的还是旧的值。

#### 41. 遍历ArrayList时删除一个元素，什么时候会抛出ConcurrentModificationException？

#### 42. 什么时候用享元模式？
享元模式通过共享对象来避免创建过多的对象。需确保对象是不可变的。<br>
JDK中的String池、Integer池都是享元模式的例子。

#### 43. 访问修饰符的作用范围由大到小：
public > protected > default > private

作用域 | 当前类 | 同一package | 子孙类 | 其他package
-|-|-|-|-
public | √ | √ | √ | √
protected | √ | √ | √ | ×
friendly | √ | √ | × | ×
private | √ | × | × | ×

#### 44. 哈希表中处理冲突的方法？
线性探测、二次哈希、链接<br>
线性探测：如果桶已经被占据了，则线性的查找下一个桶，直到找到一个空位<br>
链接：多个元素可以存储在同一个桶中

#### 45. sql查找第二大的值？

#### 46. 使用位运算判断一个数是不是2的幂？
x & (x - 1) == 0<br>
2的幂的二进制表示是1000...000，减去1表示为0111...111，进行与运算结果为0.

#### 47. IO操作的两种方式？
面向字节（Byte） | 面向字符（Character）
-|-
以8位为单位对二进制数据进行操作 | 以字符为单位对数据进行操作
InputStream和OutputStream的子类 | Reader和Writer的子类

#### 48. 常用的加密算法？
对称加密：DES、AES<br>
非对称加密：RSA、DSA<br>
单向散列函数加密：MD5、SHA<br>

#### 49. 如何跳出当前的多重嵌套循环？code
使用标号。

#### 50. Java中负数的二进制如何表示？byte类型、int类型运算的溢出？
负数使用正值的补码表示。（反码加1则为补码）<br>
int类型溢出项目实例：<br>
两个系统交互，传送文件，用一个int类型的值保存文件的大小，单位为B（字节）。int类型能表示的最大整数约为2,000,000,000，即int类型值表示的最大文件大约为2GB，当文件大于2GB时则发生了int类型的溢出。















##### 100. question100
