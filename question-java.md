
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

#### 51. Math类的三个取整的方法？
ceil，floor和round<br>
ceil：向上取整<br>
floor：向下取整<br>
round：加0.5后再向下取整<br>

#### 52. synchronized和java.util.concurrent.locks.Lock的区别？
Lock能完成synchronized的所有功能。<br>
Lock有更精确的线程语义和更好的性能。<br>
synchronized自动释放锁，Lock需要程序手动释放，且必须在finally中释放。<br>
Lock为读和写分别提供了锁。<br>
Lock接口可以尝试非阻塞的获取锁，在指定的时间内无法获取，则返回。

#### 53. ArrayList和Vector的区别？
ArrayList | Vector
-|-
相同点 | 都是基于索引，内部有一个数组支持<br>有序的集合，允许重复<br>迭代器都是fail-fast的
非线程安全 | 线程安全
每次扩容为1.5倍 | 每次扩容为2倍

Vector可以看做是线程安全版的ArrayList。<br><br>
Array和ArrayList的区别？什么时候用Array而不是ArrayList？

Array | ArrayList
-|-
可以包含基本类型和对象类型 | 只能包含对象类型
大小固定 | 大小动态变化

处理基本数据类型的时候应该使用Array。<br><br>

ArrayList和LinkedList的区别？源码？？？

ArrayList | LinkedList
-|-
数组，允许元素为null | 双向循环链表，允许元素为null
随机访问快，增删慢 | 随机访问慢，增删快
/ | 根据下标访问的时候会判断在前半段还是后半段，决定顺序遍历还是逆序

#### 54. 如何去除Vector、ArrayList、LinkedList中重复的元素？
使用HashSet，将数组的实例作为参数传入HashSet的构造函数中。

#### 55. 快排算法实现？其他排序算法？？？code
冒泡排序<br>
插入排序<br>
（多路）归并排序<br>

#### 56. Spring的IoC容器如何为普通的类（非单例模式）创建单例？是线程安全的吗？
Bean的配置有个scope属性，有5种值：singleton、prototype、request、session、global-session，默认使用singleton，比如：<br>
```
<bean id="p1" class="com.test.Person" />
<bean id="p2" class="com.test.Person" scope="prototype" />
```
不是线程安全的。大部分的bean没有可变的状态，但View Model有多种状态，需要自行保证线程安全，一个简单的方法就是把作用域从默认的singleton改成prototype，每次请求都创建一个新的bean。

#### 57. Javascript是否支持面向对象？
是。

#### 58. MySQL的union和union all的区别？
将两个查询子集进行合并，union和自动去除重复的记录，union不去除

#### 59. 注册jdbc驱动程序的三种方式？

#### 60. 什么情况下不建议使用hibernate？
数据量大、表关系复杂

#### 61. 数据库的三大范式？
第一范式：每列都是不可分割的原子值，所有关系型数据库都满足<br>
第二范式：每列都和主键相关<br>
第三范式：一个表中不能包含已经在其他表中存在的非主键字段，即不能冗余<br>
          非主键字段不能相互依赖？

#### 62. 如何创建一个不可变（immutable class）对象？JDK有哪些不可变对象？？？
(1) 类用final修饰<br>
(2) 类的所有成员都用final修饰<br>
(3) 如果包含可变的成员，则在获取该成员的时候返回一个拷贝<br>
另一种回答：<br>
(1) 将所有成员声明为私有的<br>
(2) 通过构造函数初始化所有成员<br>
(3) 对变量不要提供setter方法<br>
(4) 在getter方法中，不要返回对象，而是克隆对象，返回对象的拷贝<br><br>
怎么证明不可变？？？？？？？？？？？<br><br>
JDK的不可变类有String、Integer和其他包装类型。

#### 63. Java中的构造器链？
在一个构造器中调用了另一个构造器，只在重载了类的构造器的时候有可能出现。

#### 64. 使用递归的方法将一个字符串反转？code

#### 65. 不使用临时变量的情况下交换两个整数的值？code

#### 66. synchronized和ReentrantLock的区别？为什么要设计可重入锁ReentrantLock？？？
重入性：同一个线程多次试图获取它占有的锁，都会成功。相应的，释放锁时，重入计数为零才释放成功。

synchronized | ReentrantLock
-|-
阻塞式 | 阻塞式
Java的关键字，原生语法层面的互斥，需要jvm实现 | JDK 1.5之后提供的API层面的互斥锁
执行完同步块的代码后自动释放锁 | 需要手动lock和unlock，配合try/finally
释放锁的顺序必须和获得锁的顺序相反 | 释放锁的顺序自由
不能中断 | 可中断

#### 67.main方法不用static修饰会怎样？

#### 68.Java socket通信？
question 94

#### 69. JSP九大内置对象？
out对象：是JspWriter类的实例，向客户端输出内容<br>
request对象：表示客户端的一次请求，封装了请求的信息，是HttpServletRequest类的实例，在请求完成之前一直是有效的。<br>
response对象：包含了响应客户端的信息，是HttpServletResponse类的实例，具有页面的作用域<br>
session对象：会话，一个客户端打开浏览器连接到服务器，到客户端关闭浏览器。HttpSession类的实例。<br>
application对象：实现了用户间数据的共享，可以存放全局变量，生命周期从服务器启动到关闭<br>
page对象<br>
pageContext对象<br>
exception对象<br>
config对象

#### 70. 请求转发和请求重定向？
转发 | 重定向
-|-
服务端行为 | 客户端行为
request.getRequestDispatcher().forward() | response.sendRedirect()
一次请求 | 两次请求
客户端url地址不变 | 客户端url地址改变

#### 71. 部署在tomcat的web项目启动的过程？
tomcat有三种加载程序的方式：<br>
1) 配置在conf/server.xml中<br>
2) 配置在conf/Catalina/localhost下<br>
3) 配置在webapps下
<br><br>
web.xml加载过程：<br>
context-param->listener->filter->servlet<br>
1) 读取web.xml文件的两个节点&lt;listener&gt;和&lt;context-param&gt;<br>
2) 创建一个ServletContext上下文，为web项目共享<br>
3) 将<context-param>转化为键值对，赋给ServletContext<br>
4) 创建<listener>中的类实例，即创建监听<br>
5) 创建filter<br>
6) 创建servlet<br>

#### 72. 什么是session钝化？
正常情况下，session是存放在服务器的内存当中的，用户较多时，服务器的开销会很大，需要把未使用到的session对象序列化到文件系统中，需要用到的时候再反序列化到内存中。

#### 73. 反射的作用是什么？什么是动态语言？Java是不是动态语言？
动态语言是指在运行时可以改变其结构：引进或删除函数。如javascript、Python、Ruby<br>
动态语言在运行时才检查类型，静态语言在运行前检查，如编译阶段。<br>
动态语言不需要写过多关于类型的代码，方便阅读，但不易调试；静态语言结构规范，易于调试，但需要写更多的类型代码。<br>
Java是静态语言，或“准动态语言”，主要体现在反射和动态编译、动态类型转换、动态字节码操作。<br>

反射机制是java被视为准动态语言的关键特质。允许程序在运行时通过反射得到一个已知名称的类的内部信息。<br>

反射的作用：<br>
(1) 在运行时判断任意一个对象所属的类<br>
(2) 在运行时判断任意一个类具有的成员变量和方法<br>
(3) 在运行时调用任意一个对象的方法<br>
(4) 在运行时构造任意一个类的对象<br>
(5) 生成动态代理操作<br>

反射的缺点：<br>
(1) 性能相对较低<br>
(2) 不安全，破坏了类的封装性（获取类的私有方法和属性）

#### 74. Java创建实例化对象的几种方式？
(1) new语句<br>
(2) 工厂方法，如String s = String.valueOf();<br>
(3) 反射机制，Class类的三种方法，或者通过类类型的newInstance()方法<br>
(4) clone<br>
(5) 反序列化

#### 75. SpringMVC工作流程？
(1) 请求request到达DispatcherServlet（DispatcherServlet能够拦截所有请求）<br>
(2) DispatcherServlet查找HandleMapping，将功能代理给HandleMapping<br>
(3) HandleMapping根据配置，找到Controller和HandleInterceptor<br>
(4) 把Controller和HandleInterception制作成一个可执行的链条，也就是HandleAdapter<br>
(5) HandleAdapter将信息返回给DispatcherServlet，DispatcherServlet开始调用这个一般化的处理器<br>
(6) Controller生成ModelAndView，返回给DispatcherServlet<br>
(7) DispatcherServlet调用ViewResolver视图解析器，返回到View对象<br>
(8) ModelAndView将数据传递到View

#### 76. 注解的作用？自定义注解？（项目中的使用，参数检查）

#### 77. 深复制和浅复制？
question 19

#### 78. Spring有哪些类型的依赖注入方式？
(1) 构造器依赖注入：通过容器触发类的一个构造器，参数可以表示对其他类的依赖<br>
(2) setter方法依赖注入：通过容器调用无参构造器或无参static工厂方法实例化bean，调用bean的set方法<br>
(3) 接口注入

#### 79. Spring框架中bean的生命周期？
一个bean实例初始化时，需要执行一系列的初始化操作以达到可用状态；不再被调用时需要执行相关的析构操作，从bean容器移除。<br>
BeanFactory负责管理bean的生命周期，bean的生命周期由两组回调函数组成：初始化之后调用的回调方法和销毁之前调用的回调方法。<br>
Spring框架提供了4种方式来管理bean的生命周期：

#### 80. Spring的bean装配？自动装配？
bean装配是指在spring容器中把bean组装在一起，前提是容器需要知道bean的依赖关系，通过依赖注入装配到一起。<br>
spring容器能够自动装配相互合作的bean。<br>
自动装配的5种模式：<br>
(1) no：默认的方式，不自动装配，通过手动设置ref属性来装配<br>
(2) byName：通过参数名自动装配，查找和bean属性具有相同名字的其他bean<br>
(3) byType：通过参数的类型自动装配，查找和bean属性类型相同的其他bean<br>
(4) constructor：和byType类似<br>
(5) autodetect：如果有默认的构造函数，则通过constructor的方式，否则通过byType的方式<br>

#### 81. Spring支持的事务管理类型？
(1) 编程式事务管理：通过编程的方式管理事务，灵活，但难维护<br>
(2) 声明式事务管理：将业务代码和事务管理分离，只需用注解和XML配置来管理

#### 82. Spring的BeanFactory和ApplicationContext的区别？
BeanFactory是spring IoC的具体实现，提供了一种先进的配置机制，能配置任何类型的对象。<br>
ApplicationContext对BeanFactory进行扩展，添加了其他功能，如国际化、统一的资源文件读取方式。<br>
三种常见的ApplicationContext实现方式：<br>
(1) ClassPathXmlApplicationContext<br>
(2) FileSystemXmlApplicationContext<br>
(3) XmlWebApplicationContext

#### 83. Spring中使用的设计模式？
(1) 代理模式：AOP<br>
(2) 单例模式：实例化的bean默认是singleton的<br>
(3) 工厂模式：BeanFactory用来创建对象的实例<br>
(4) 模板方法：用来解决代码重复的问题，如RestTemplate

#### 84. Spring的依赖注入（DI，Dependency Injection）和控制反转（IoC，Inversion of Control Container）？
依赖注入：在运行时将类的依赖注入到代码中，将依赖定义为接口，将实现了这个接口的实体类注入到主类的构造器中。<br>
依赖注入可以通过单一责任原则来提高代码的内聚，因为依赖的对象通常都是能独立完成一个功能的对象。<br>
控制反转容器：一个支持依赖注入的中心容器，如spring框架，定义哪个依赖应该使用哪个实体类。<br>
不实际生成对象，而是定义如何生成对象。<br>
依赖注入和控制反转能够在运行时绑定类之间的关系，而不是编译时。<br>
松耦合也更易于单元测试。

#### 85. Hibernate的SessionFactory和Session是线程安全的吗？SessionFactory如何保证线程安全？？？
SessionFactory是线程安全的，Session不是。<br>
Session表示与数据库交互的一个单元，由SessionFactory创建。<br>
为避免创建太多session，可用ThreadLocal将session将当前线程绑定在一起，同一个线程获得的都是同一个session（Hibernate 3中SessionFactory的getCurrentSession()方法）

#### 86. Hibernate实体对象的三种状态（四种）？
瞬时态、持久态、游离态、移除态<br>
(1) 瞬时态（new/transient）：new一个实体对象后，处于瞬时态，只是保存在内存中，如果没有变量引用则会被垃圾回收机制回收，可以通过Session的save()、saveOrUpdate()、persist()、merge()插入到数据库中，变成持久态<br>
(2) 持久态（managed/persistent）：在数据库中有对应的记录，并拥有一个持久化标识（ID）。对持久态的对象执行delete后，会将数据库对应的记录删除，持久态变成移除态（或瞬时态）。持久态对象修改后，事务提交才会更新到数据库。<br>
(3) 游离态（detached）：session进行close、clear、evict、flush后，实体对象从持久态变成游离态

#### 87. 如何理解Hibernate的延迟加载机制？如何处理延迟加载和session关闭的矛盾？
不是在读取的时候就把数据加载进来，而是在实际使用的时候再加载。<br>
Hibernate使用虚拟代理的机制实现延迟加载，使用session的load方法，或者一对多的映射关系一的一方加载多的一方，得到的都是虚拟代理，返回的不是实体本身，而是实体对象的代理，代理对象在被调用getter方法的时候才会从数据库加载数据。<br>

加载数据需要连接数据库，而session关闭后相当于断开了数据库连接，二者存在矛盾。<br>
延迟加载和session关闭的矛盾处理方式：<br>
(1) 关闭延迟加载<br>
       较简单。但出现这种矛盾说明存在外键关联，关闭延迟加载的话查询的开销会很大。<br>
(2) 在session关闭之前获取需要查询的数据<br>
       使用hibernate的isInitialized方法判断对象是否已经加载，如果未加载则使用<br>
       initialize方法、加载对象。<br>
(3) 使用拦截器或过滤器延长session的生命周期直到视图获得数据

#### 88. Hibernate的一级缓存、二级缓存和查询缓存？
(1) 一级缓存<br>
默认开启。修改持久化实体时不会立即提交到数据库，而是缓存在当前session中, 除非显式调用session的flush方法，通过这种方式可以减少与数据库的交互，提高程序性能。<br>
(2) 二级缓存<br>
默认关闭。开启并设置需要使用二级缓存的实体类，SessionFactory就会缓存访问过得实体类的每个对象。<br>
(3) 查询缓存<br>
默认关闭。一级缓存和二级缓存都是对整个实体进行缓存，如果需要缓存普通属性,可以使用查询缓存。查询缓存将HQL和SQL语句及查询结果作为键值对进行缓存，对于同样的查询可以从缓存中获取。

#### 89. Hibernate的SessionFactory的openSession和getCurrentSession的区别？
openSession | getCurrentSession
-|-
得到一个新的session对象 | 得到一个和当前线程绑定的session对象
需要手动关闭，手动提交 | 事务回滚或提交时自动关闭
不需要配置 | 需要配置<br>&lt;property name="current_session_context_class"&gt;thread&lt;/property&gt;

#### 90. Spring如何使用ThreadLocal解决线程安全问题？
ThreadLocal是线程的一个本地化对象。多线程环境的对象使用ThreadLocal维护变量时，为每个线程分配一个变量副本，每个线程可以独立的改变自己的副本，相当于线程的本地变量。<br>
ThreadLocal类中有一个内部类ThreadLocalMap，key为线程对象，value为线程的变量副本。<br>
数据连接和会话一般是非线程安全的，

#### 91. 什么是XSS攻击和CSRF攻击？
XSS（Cross Site Script）跨站脚本，向网页中注入恶意脚本，用户浏览网页时在用户浏览器中执行。反射型：诱使用户点击含有恶意脚本的链接；持久型：将脚本提交到网站的数据库中，用户访问时从数据库加载到页面中执行。<br>
防范：<br>
(1) 对危险字符进行http转义<br>
(2) 使用HttpOnly，cookie对浏览器不可见。<br>

CSRF（Cross Site Request Forgery）跨站请求伪造，通过跨站的请求，以合法的身份进行非法的操作。原理是利用浏览器的cookie或服务器的session窃取用户身份。<br>
防范：<br>
(1) 在表单中添加token令牌<br>
(2) 验证码<br>
(3) 检查请求头中的Referer，Referer是http请求头的一个属性，表明请求来自哪里。

#### 92. 时间处理类SimpleDateFormat非线程安全，用ThreadLocal封装成线程安全。
SimpleDateFormat内部引用了一个Calendar对象来处理时间，parse方法有一个clear的操作和一个getTime的操作，多线程环境下不同线程如果共享一个SimpleDateFormat对象，调用parsr方法时在clear和getTime之间就会存在冲突。<br>
项目实例：<br>
一个并发较大的接口服务，使用账号+秘钥+时间戳做身份和权限认证，认证的中间件接口定义了一个SimpleDateFormat单例，用于检查时间戳和系统当前时间的差，并发情况下得到的时间差不正确。

#### 93. 垃圾回收Serial GC和Parallel GC的区别？Minor GC、Major GC、Full GC的区别？并行收集器和并发收集器？
都会引起stop-the-world。<br>
Serial是默认的收集器，执行GC的时候只有一个线程；<br>
Parallel收集器使用多个线程。<br>
可以通过JVM参数设置：-XX:+UseSerialGC，或者：-XX:+UseParNewGC<br>

Minor GC：从年轻代空间（包括Eden和Survivor）回收内存，不会影响到永久代。<br>
Major GC：清理老年代，由Full GC触发，也会引起stop-the-world。<br>
Full GC：清理整个堆空间，包括年轻代和老年代。<br>

Minor GC触发条件：Eden区满。<br>
Full GC触发条件：<br>
(1) 调用System.gc()，系统建议执行Full GC，但不一定会执行。<br>
(2) 老年代空间不足<br>
(3) 方法区空间不足<br>
(4) 通过Minor GC后进入老年代的平均大小>老年代的可用空间<br>
(5) 由Eden区、From Space区向To Space区复制时，对象大小>To Space区可用空间，则把该对象转到老年代，且老年代可用的空间小于该对象大小。<br>

并行收集器（Parallel Collector, Throughput Collector）：使用多线程的方式，利用多CPU提高GC的效率，以达到一定吞吐量为目标。<br>
并发收集器（Concurrent Low Pause Colllector）：？？？<br>
CMS收集器（Concurrent Mark Sweep，老年代收集器）：以获取最短回收停顿时间为目的，基于标记-清除算法。

#### 94. Socket服务端、客户端实例。code

#### 95. 多线程编程的最佳实践？
(1) 给线程命名<br>
(2) 最小化同步的范围，而不是对整个方法同步<br>
(3) 优先使用volatile，而不是synchronized<br>
(4) 使用更高层次的并发工具进行线程间通信，而不是wait()和notify()，比如BlockingQueue、CountDownLatch、Semeaphore<br>
(5) 优先使用并发集合，而不是对集合进行同步。并发集合提供更好的扩展性。<br>
(6) 使用线程池

#### 96. 使用Collections的最佳实践？
(1) 使用正确的集合类，比如不需要同步列表时，使用ArrayList而不是Vector。<br>
(2) 优先使用并发集合，而不是对集合进行同步。<br>
(3) 使用接口代表和访问集合，比如用List存储ArrayList，用Map存储HashMap。<br>
(4) 使用迭代器循环集合。<br>
(5) 使用泛型，类型安全、可读性、健壮性，避免运行时的ClassCastException。<br>
(6) 元素个数固定且事先知道，应该用Array而不是ArrayList。<br>
(7) 指定容器初始容量，避免重新计算hash或者扩容。<br>
(8) 使用JKD的不变类（immutable class）作为Map的key值，避免自己定义的类去实现hashCode()和equals()方法。<br>
(9) 容器为空的时候返回长度是0的集合或者数组，不要返回null。

#### 97. volatile和synchronized的比较
线程安全包括两个方面：原子性和可见性<br>
锁提供了两种特性：互斥和可见性<br>
多线程环境下内存可分成主内存和线程的本地内存，线程执行时，将变量从主内存读到本地内存，操作后，在某一时间将变量写回主内存。为了加快速度，写回主内存之前可能先在寄存器或CPU缓存上进行，这时变量的新值对其他线程是不可见的。<br>
Volatile修饰的变量修改时，会将缓存中的修改前的值清除。告知JVM寄存器中的值是不确定的，需要从主内存中读取

volatile | synchronized
-|-
轻量级，只能修饰变量 | 重量级，可修饰变量、方法
只能保证可见性 | 能保证可见性和原子性
不会造成线程阻塞 | 可能造成线程阻塞

#### 98. CAS同步非阻塞的无锁算法？
CAS是乐观锁技术，当多个线程尝试使用CAS同时更新一个变量时，只有一个能成功更新，其他都失败，失败的线程不会挂起，可以再次尝试。<br>
CAS有三个操作数，内存值V，预期的旧值A，新值B，仅当内存值等于预期的旧值时，才会将值更新为新值B，否则什么都不做。<br>
当同步出现冲突的机会很小时，这种机制相较于synchronize能提高性能。<br>
CAS是CPU指令级的操作，只有一步原子操作。

应用：java.util.concurrent.atomic中的类，如AtomicInteger的自增操作，可调用incrementAndGet方法。<br>
CAS实现原子操作的三个问题：<br>
(1) ABA问题。CAS算法在操作值的时候检查有没有变化，没有变化则更新，但一个值从A变成B，再变回A，则检查不出有变化。解决办法是使用版本号：1A->2B->3A<br>
(2) 循环时间长开销大。自旋CAS如果长时间不成功，会给CPU带来非常大的执行开销。<br>
(3) 只能保证一个共享变量的原子操作。Java 1.5开始JDK提供了AtomicReference类来保证对象引用间的原子性，可以把多个变量放进一个对象中进行CAS操作。

#### 99. 线程的start方法和run方法的区别？
start方法用来启动新创建的线程，内部调用了run方法。<br>
调用run方法的时候，只是在原来的线程中调用，没有启动新的线程。

#### 100. 什么是竞态条件（Race Condition）？
计算的正确性取决于多个线程的交替执行时序时，就会发生竞态条件。<br>
导致竞争条件的区域称为临界区（Critical Section）。<br>
最常见的竞态条件是先检测后执行，比如单例模式的懒汉式实现，先检测实例是否存在，再实例化。

#### 101. 单例模式和静态变量的区别？
单例模式能保证一个类只有一个实例，而静态变量只是保证该类在当前的类中只有一个实例，其他类可以重新创建该类的实例。<br>
单例模式的类完成自身的初始化。

#### 102. 如何停止一个线程？
(1) 使用退出标志，设置一个bool变量（用volatile修饰），在run方法中判断bool变量决定是否执行，可以在线程外改变bool的值来退出线程。<br>
(2) stop方法，不推荐<br>
(3) interruput方法

#### 103. 线程间通信方式？
(1) 同步，通过synchronized关键字<br>
(2) while轮询，浪费CPU资源<br>
(3) wait/notify<br>
(4) 管道

#### 104. 编写两个线程，一个线程打印1~52，另一个线程打印字母A~Z，打印顺序为12A34B56C……5152Z，要求使用线程间的通信。code

#### 105. 为什么wait、notify、notifyAll这些方法放在Object中而不是Thread中？
Java提供的锁是对象级的而不是线程级的，每个对象都有锁，通过线程可以获得。<br>
线程需要等待某些锁调用对象的wait方法就有意义了，如果wait方法定义在Thread中，线程在等待的就不知道是哪个对象的锁。

#### 106. 线程的interrupted方法和isInterrupted方法的区别？
interrupt方法用于中断线程，调用线程的该方法将线程置为“中断状态”，但不会停止线程，需要用户去监听线程的状态并做处理，是否会抛出中断异常。<br>
两个方法最终都会调用一个本地方法，传递一个参数，是否将中断状态清除<br>

interrupted | isInterrupted
-|-
Thread的静态方法 | Thread的实例方法
作用于当前线程 | 作用于调用该方法的线程对象对应的线程
清除中断状态 | 不清除中断状态

#### 107. wait和notify为什么需要在同步代码块中调用？
(1) API强制要求，否则会抛出IlleagelMonitorStateException<br>
(2) 避免竞态条件，比如生产-消费模型，生产和消费都是“先检查后执行”，如果没有在synchronized代码块中执行wait/notify，可能出现生产者还没wait，消费者就先notify，那之后生产者就永远不会被通知到。

#### 108. 对于线程来说，堆内存和栈内存有什么区别？
每个线程都有自己的栈内存，用于存储本地变量、方法参数、栈调用，一个线程中存储的变量对其他线程是不可见的。<br>
堆内存是所有线程共享的，对象都在堆中创建。<br>
为了提高效率，线程会从堆中弄一块内存缓存在自己的栈中，如果多个线程使用同一个变量就会引发问题，这是volatile变量就会发挥作用了，它要求线程从主内存中读取该变量的值。

#### 109. Java同步集合和并发集合的区别？
都能实现线程安全，区别在于性能和可扩展性，还有实现线程安全的方法。<br>
同步集合有：HashMap、Hashtable、HashSet、Vector、ArrayList<br>
并发集合有：ConcurrentHashMap、CopyOnWriteArrayList、CopyOnWriteHashSet<br>
同步集合会把整个List或Map锁起来，而并发集合使用了较先进的技术，如锁分离，比如ConcurrentHashMap把Map分成几个片段，只对几个加锁，允许其他线层访问未加锁的片段。<br>
CopyOnWriteArrayList允许多个线程以非同步的方式读，有线程需要写的时候复制一个副本。<br>
在读多写少的情况下使用并发集合比同步集合能获得更好的性能。

#### 110. 多线程生产-消费模型实例？在环境下如何实现？
wait-notify<br>
Blockingqueue<br>
Lock、Conditiion<br>
模型的本质是传递生产者和消费者之间的消息，分布式环境下可用第三方传递消息的媒介，如Zookeeper、Redis、Kafka。

#### 111. 多线程信号灯（信号量）模型？code
Semaphore，信号量，限制某段代码块的并发数，由构造函数的参数传入。超出数量的线程需要等待，直到某个线程执行完了，下一个线程才能进入。

#### 112. 多线程哲学家就餐模型？code

#### 113. 多线程CountDownLatch使用？
适用于几个子线程运算后由一个主线程汇总计算结果的场景。

#### 114. 多线程Lock和Condition类的使用？code
生产-消费模型

#### 115. 死锁的条件？实例？如何避免死锁？code
(1) 互斥条件：一个资源每次只能被一个进程使用<br>
(2) 请求与保持条件：一个进程因请求资源而阻塞时，已获得的资源不释放<br>
(3) 不剥夺条件：进程已获得的资源，在使用完之前，不能强行剥夺<br>
(4) 循环等待条件：若干进程之间形成一种首尾相接的循环等待资源关系<br>

避免死锁最简单的方法就是阻止循环等待条件，将系统中所有资源设置标志位、排序，规定所有进程申请资源必须以一定的顺序。<br>
避免嵌套加锁：已经取得了一个资源，避免在释放前去获取另一个资源<br>
避免无期限的等待：设置最长等待时间（如何设置？？？）

#### 116. 如何保证多个线程按顺序执行？code
使用线程的join方法，先启动最后一个线程，依次调用前一个线程的join方法。

#### 117. 什么是Java中的ReadWriteLock？实例？code
用来提升并发程序性能的锁分离技术。<br>
一个ReadWriteLock维护一对关联的锁，一个用于只读一个用于写。<br>
在没有写线程的情况下一个读锁可能会被多个线程持有，而写锁是独占的。<br>
适用于缓存的实现。

#### 118. volatile变量和atomic变量的区别？Atomic源码？？？
volatile变量能保证先行关系，即写操作会发生在后续的读操作之前，但不能保证原子性，如++操作。<br>
AtomicInteger类提供的具有原子性的getAndIncrement方法。

#### 119. Java中进程和线程的区别？
一个进程对应一个执行的程序，一个线程则是进程执行过程中一个单独的执行序列。<br>
一个进程可以包含多个线程。<br>
线程也被称为轻量级的进程。<br>
一个Java虚拟机的实例运行在一个单独的进程中，不同的线程共享Java虚拟机进程的堆内存，同时保有自己的栈内存。

#### 120. 线程“等待”状态和“阻塞”状态的区别？
阻塞：试图获得一个锁，而这个锁正被其他线程持有<br>
等待：等待另一个线程通知调度器来唤醒？<br>
两者都会暂停线程的执行，进入waiting状态是线程主动的，进入blocked状态是被动的。<br>
进入waiting状态是在同步代码块内，进入blocked状态是在同步代码块外。

#### 121. 线程“同步”和“阻塞”的区别？“同步阻塞”和“同步非阻塞”？

#### 122. 如何自定义注解？

#### 123. Spring源码-IoC？
BeanFactory：bean的管理工厂，所有bean都在其中创建、存储、销毁<br>
DefaultListableBeanFactory：BeanFactory的实现类<br>
Resource：spring的配置信息，可以来自xml文件、网络、数据流<br>
BeanDefinition：封装bean的所有信息，包括参数值、方法名、是否懒加载、是否单例<br>
BeanDefinitionReader：构建BeanDefinition，从Resource中读取信息封装成BeanDefinition<br>
ApplicationContext：上下文，实现了各种接口，封装了各种bean对象<br>
Environment：运行环境配置信息<br>
Document：从xml文件中抽取出来的文本对象<br>
Element：从Document中抽取出来的node节点<br>
spring-test

#### 124. Spring源码-AOP？在项目中的使用？和拦截器、过滤器的区别？？？
两种代理的方式：默认使用JDK动态代理，目标类无接口的时候用cglib（code generation library），代码生成类库，可以在运行时时期扩展Java类实现Java接口，动态生成新的class<br>
权限验证、异常处理。

#### 125. 什么是线程调度器（Thread Scheduler）和时间分片（Time Slicing）？
线程调度器是一个操作系统服务，为runnable状态的线程分配CPU时间。<br>
一个线程创建并启动后，它的执行便依赖于线程调度器的实现。<br>
时间分片是指将可用的CPU时间分配给可用的runnable线程的过程。<br>

#### 126. 为什么wait()、notify()、notifyAll()必须在同步方法或同步代码块中调用？
当一个线程需要调用一个对象的wait方法的时候，必须拥有该对象的锁，调用后会释放锁并进入等待状态，知道其他线程调用这个对象的notify方法。<br>
同样的，线程调用对象的notify方法时，会释放对象的锁，其他等待的线程可以得到这个对象的锁。<br>
所有的这些方法都需要线程持有对象的锁，只能通过同步来实现。

#### 127. 为什么Thread类的sleep()和yield()方法是静态的？
sleep()和yield()方法将在当前正在运行的线程上执行，在其他等待状态的线程上调用是没有意义的。

#### 128.如何创建守护线程？
在调用start()方法之前调用setDaemon(true)方法。

#### 129. 什么是线程的Callable和Future？code
Callable类似于Runnable，Callable执行后可以返回值，这个值可以被Future拿到。<br>
适用于复杂的计算，主线程开启子线程去执行计算，用future获取计算结果。

#### 130. servlet的生命周期？tomcat servlet生命周期？tomcat如何创建servlet类实例？
加载--实例化--服务--销毁<br>
init()：在servlet的生命周期中仅执行一次init()。在服务器装入servlet的时候执行，负责初始化servlet对象。<br>
service()：servlet的核心，负责响应客户端的请求。每当一个客户端请求一个HttpServlet对象，该对象的service()方法就被调用，并且传递给这个方法一个请求对象（ServletRequest）和一个响应对象（ServletResponse）作为参数。<br>
destroy()：仅执行一次，在服务端停止且卸载servlet时执行。

(1) 加载servlet。Tomcat负责创建servlet的实例。<br>
(2) 初始化。Servlet被实例化后，tomcat调用其init()方法初始化这个对象。<br>
(3) 处理服务。浏览器访问的时候，servlet会调用service()方法处理请求。<br>
(4) 销毁。Tomcat关闭时或者检测到servlet要从tomcat删除的时候自动调用destroy()方法，让该实例释放占用的资源。一个servlet如果长时间不被使用的话，也会被tomcat自动销毁。<br>
(5) 卸载。Servlet调用完destroy()方法后，等待垃圾回收，如果需要再次使用，会重新调用init()方法进行初始化。

容器启动时，读取webapps目录下所有应用的web.xml文件，进行解析，得到servlet注册信息。然后将每个应用中注册的servlet类都进行加载，并通过反射的方式实例化。<br>
在servlet注册时，如果<load-on-startup>1</load-on-startup>为正数，则一开始就实例化，不写或为负数则第一次请求才实例化。

#### 131. servlet和CGI的区别？
servlet处于服务器进程中，通过多线程的方式运行其service方法，一个实例可以服务于多个请求，并且实例一般不会销毁。<br>
而CGI对每一个请求产生一个新的进程，服务完成就销毁，效率较低。

#### 132. 在静态方法上使用同步会怎样？
同步静态方法时会获取该类的Class对象，当一个线程进入同步的静态方法时，线程监视器获取类本身的对象锁，其他线程不能进入这个类的其他静态同步方法。<br>
在实例方法上使用同步，多个线程可以同时方法不同实例的同步方法。

#### 133. 两个线程可以同时调用同一个对象的两个不同的同步方法吗？
不能。当一个线程进入该对象的一个同步方法时，线程获取了该对象的对象锁，只有执行完了同步方法释放了对象锁，才能执行其他同步方法。

#### 134.什么是线程饿死？什么是活锁？
线程饥饿指的是无法访问到需要的资源而无法继续执行，引发饥饿问题的常见资源是CPU时钟周期。<br>
活锁指的是线程不断重复同样的操作，但每次操作的结果都是失败的。不会引发阻塞，但程序也无法继续执行。解决办法是每次操作引入随机机制。

#### 135. 为什么String是不可变的？
(1) 字符串常量池的需要。<br>
(2) 允许String对象缓存HashCode，保证了hash码的唯一性。<br>
(3) 安全性，调用系统级的操作之前可能有一些校验，如果是可变类，校验之后内部值被改变，可能引起严重的问题。<br>
(4) 线程安全

#### 136. Java中用到的线程调度算法是什么？
抢占式。<br>
一个线程用完CPU后，操作系统会根据线程优先级、线程饥饿情况等数据算出一个总的优先级并分配下一个时间片给下一个线程执行。<br>
时间片轮转。

#### 137. Thread.sleep(0);有什么用？
由于Java采用抢占式的线程调度算法，所以可能出现某个线程经常获得CPU控制权的情况，为了让一些优先级较低的线程也能获取控制权，可以用这个语句手动触发一次操作系统分配时间片的操作，这也是平衡CPU控制权的一种操作。

#### 138. 什么是Java内存模型？code
a) 程序计数器
一个数据结构，用于保存当前正在执行的程序的内存地址。
Java虚拟机的多线程就是通过线程轮流切换并分配处理器时间来实现的，为了线程切换后能恢复到原来的位置，每个线程需要一个独立的程序计数器，该区域为线程私有。
在Java虚拟机规范中唯一没有规定OutOfMemoryError的区域。

b) 虚拟机栈
线程私有，与线程生命周期相同，用于存储局部变量表，操作栈，方法返回值。
局部变量表存放基本数据类型和对象的引用。
当栈的调用深度大于JVM虚拟机允许的范围，会抛出StackOverflowError错误，这个深度不是一个固定的值。
动态扩展时无法申请足够的内存，会抛出OutOfMemoryeError。

c) 本地方法栈
和虚拟机栈很像，为虚拟机使用的Native方法服务。
也会抛出StackOverflowError和OutOfMemoryError。

d) Java堆
虚拟机中最大的一块内存，所有线程共享的内存区域，存放对象实例。
垃圾收集器管理的主要区域。
申请不到空间时会抛出OutOfMemoryError。

e) 方法区
各个线程共享的区域。
存储虚拟机加载的类信息、常量、静态变量，编译后的代码。

f) 运行时常量池
运行时每个class文件中的常量表，包括编译时的数字常量、方法、	域的引用。

Java内存模型定义了一种多线程访问Java内存的规范。主要有几个部分：<br>
1) 将内存分为主内存和工作内存。<br>
类的状态存储在主内存中，即类之间共享的变量。<br>
线程需要使用这些变量的时候，从主存中读取，并在自己的工作内存拷贝一份。<br>
操作这些变量的时候操作的是自己工作内存的那一份。<br>
执行完后将最新的值更新到主内存中。<br>
2) 定义了几个原子操作，用于操作主内存和工作内存中的变量。<br>
3) 定义了volatile变量的使用规则<br>
4) Happens-before，即先行发生原则。

#### 139. Hashtable的size()方法只有一条语句”return count”，为什么还需要同步？
1) 同一时间只能有一个线程执行类的同步方法，但可以有多个线程执行类的非同步方法。Hashtable的put和size方法可能冲突。对size()方法同步确保在put()方法执行完成后调用，得到正确的个数<br>
2) Java代码只有一行，编译后的字节码也只有一行，但CPU执行的机器码可能有多行，可能执行完一行CPU就切换了。

#### 140. 如何获取线程dump文件？
先获取到线程的pid，可以通过jps命令，在linux下可以用命令ps -ef | grep java<br>
再打印线程堆栈，通过jstack pid命令，在linux下可以用命令kill -3 pid<br>
Windows下可以使用ctrl+break。

#### 141. 怎样检测一个线程是否持有对象监视器？
Thread类提供了holdsLock(Object obj)的方法，当对象obj的监视器被某个线程持有时才会返回true。<br>
这是一个static方法，“某个线程”指的是当前线程。

#### 142. 父类与子类之间的调用顺序。code
父类静态代码块<br>
子类静态代码块<br>
父类构造函数<br>
子类构造函数<br>
子类普通方法

#### 143. Java垃圾回收GC的时机，具体做了什么事情？
应用程序空闲（没有应用线程在运行）的时候；堆内存不足的时候。<br>
(1) 虚拟机内存分为新生代、老年代、永久代三部分。<br>
(2) 新生代有一个Eden区和两个Survivor区（Eden和Survivor比例8:1），首先将对象放入Eden区，如果空间不足就往其中一个Survivor区放，如果仍然不足就会引发一次新生代的minor GC，将存活的对象放入另一个Survivor区，然后清空Eden区和原来那个Survivor区的内存。在某次GC的过程中，发现仍然后放不下的对象，就将这些对象放入到老年代中。<br>
(3) 大对象及长期存活的对象直接放入老年区中。<br>
(4) 每次执行Minor GC的时候对晋升到老年代的对象进行分析，如果这些对象大小超过了老年区的剩余大小，那么执行一次Full GC以尽可能的获得老年区的空间。<br>
(5) 回收的对象：从GC Roots搜索不到，而且经过一次标记清理之后仍然没有复活的对象。<br>
(6) 操作<br>
   新生代：复制清理<br>
   老年代：标记-清除和标记-压缩算法<br>
   永久代：存放java中的类和加载类的类加载器本身<br>
(7) GC Roots有哪些：<br>
   虚拟机栈中的引用的对象<br>
   方法区中静态属性引用的对象，常量引用的对象<br>
   本地方法栈中JNI（Native方法）引用的对象

#### 144.Java 8和Java 9的内存模型，有什么新的地方？？？
Java 8取消了永久代，新增了元空间（MetaSpace），使用的是物理内存。

#### 145. Java的fail-fast机制？fail-safe机制？
&emsp;&emsp;Java集合（Collection）中的一种错误机制。当多个线程对同一个集合中的内容进行操作时，就可能引发fail-fast事件。<br>
&emsp;&emsp;比如线程A通过iterator去遍历集合时，集合的内容被其他线程改变了，会抛出ConcurrentModificationException，产生fail-fast事件。<br>
&emsp;&emsp;集合中保存了一个modCount变量，即修改次数，每次对集合的修改会增加这个计数。迭代器初始化的时候将这个值赋给迭代器的expectedModCount，迭代的时候判断modCount和expectedModCount是否相等，不等则表明有其他线程修改了集合。<br>
&emsp;&emsp;fail-safe，安全失败，迭代的时候会在底层对集合做一个拷贝，修改上层集合不会有影响，不会抛出ConcurrentModficationException。java.util.concurrent包下的集合都是安全失败的。

#### 146. 线程池的作用？
(a) 降低资源消耗。通过重复利用已创建的线程减少线程创建和销毁的消耗。<br>
(b) 提高响应速度。任务到达时，不需要等待线程创建就能执行。<br>
(c) 提高线程的可管理性。<br>
(d) 控制并发数。<br>

#### 147. 类加载器的工作机制？
(a) 装载：通过类的全限定名获取二进制字节流，将二进制字节流转换成方法区中的运行时数据结构，在内存中生成java.lang.Class对象。<br>
(b) 链接：<br>
   &emsp;&emsp;(1) 校验：检查载入class文件数据的正确性<br>
   &emsp;&emsp;(2) 准备：给类的静态变量分配存储空间<br>
   &emsp;&emsp;(3) 解析：将常量池中的符号引用转成直接引用<br>
(c) 初始化：对类的静态变量、静态方法和静态代码块执行初始化

#### 148. 有序的Map实现类？怎样保证有序？
TreeMap和LinkedHashMap是有序的，TreeMap默认升序，LinkedHashMap记录了插入的顺序。<br>
TreeMap基于红黑树实现，有序。<br>
LinkedHashMap，HashMap和双向链表LinkedList的结合，将所有Entry节点链入一个双向链表，用于保证插入的顺序。<br>

#### 149. 什么情况下会发生栈内存溢出？
如果线程请求的栈深度大于虚拟机所允许的最大深度，会抛出StackOverflowError异常。如果虚拟机在动态扩展栈时无法申请到足够的内存空间，则抛出OutOfMemoryError异常。

#### 150. 线程生命周期状态图？
新建状态（New）：新创建的线程对象，还没有调用start()方法。<br>
就绪状态（Runnable）：线程对象的start()方法被调用，位于可运行线程池中，等待获取CPU使用权。<br>
运行状态（Running）：就绪状态的线程获得了CPU使用权，执行代码。<br>
阻塞状态（Blocked）：线程因为某种原因放弃CPU使用权，暂停运行，有三种情况：<br>
                  &emsp;&emsp;等待阻塞：执行wait()方法，被放入等待池中。<br>
                  &emsp;&emsp;同步阻塞：获取对象的同步锁时，锁被其他线程占用，被放入锁池。<br>
                  &emsp;&emsp;其他阻塞：执行sleep()或join()方法，或者发出了I/O请求。<br>
死亡状态（Dead）：执行完成或因异常退出了run()方法。<br>

枚举值：<br>
NEW：初始状态，线程被构建，但还没有调用start()方法。<br>
RUNNABLE：运行状态，Java线程将操作系统中的“就绪”和“运行”都称为“运行中”。<br>
BLOCKED：阻塞状态，表示线程阻塞于锁。<br>
WAITING：等待状态，进入该状态表示线程需要其他线程做出一些指定动作（通知或中断）<br>
TIME_WAITING：超时等待状态，不同于WAITING，可以在指定的时间内自行返回。<br>
TERMINATED：终止状态，表示线程已执行完毕。<br>

#### 151. Https加密方式？Tomcat如何实现https？？
对称加密和非对称加密。<br>
公钥、私钥。<br>

#### 152. Float类型的变量定义float f = 2.3;是否正确？
不正确。精度不准确，应该加强制类型转换，float f = (float)2.3;或者float f = 2.3f;<br>
没加小数点默认是int，有加小数点默认是double。

#### 153. 类装载的显式方式和隐式方式？
隐式装载，程序运行时遇到new方式创建对象时，隐式调用类装载器加载对应的类到JVM中。<br>
显式装载，class.forName();显式加载需要的类。

#### 154. String编码UTF-8和GBK的区别？？？

#### 155. Java垃圾回收的算法？<br>
(1) 标记回收法。遍历对象图并记录可到达的对象，以便删除不可到达的对象，一般使用单线程工作，可能产生内存碎片。<br>
(2) 标记-压缩回收法。前期和第一种算法一样，多了一步，将所有存活的对象压缩到内存的一端，这样内存碎片就可以合成一大片可再利用的内存区域，提高了内存利用率。<br>
(3) 复制回收法。将现有内存分成两部分，GC的时候将可到达的对象复制到另一半空间，清空正在使用的一半空间的全部对象。可用的内存只有一半。适合短生存期的对象，持续复制长生存期的对象会导致效率降低。<br>
(4) 分代回收法。将内存空间分成两个或多个区域，如年轻代和老年代。年轻代的特点是对象很快会被回收，因此使用效率较高的算法。当一个对象经过几次回收后仍然存活，就会被放入老年代，老年代使用标记-压缩算法。<br>
(5) 引用计数法。最简单古老的的方法，将资源（对象、内存或磁盘空间）的被引用次数保存起来，引用次数为0的时候就释放。<br>
(6) 对象引用遍历法。现在大多数jvm使用的算法，从一组根对象（GC Roots）开始，沿着整个对象图的每条链接，递归确定可到达（reachable）的对象，如果某个对象不能从这些根对象到达，则标记为回收。

#### 156. String编码UTF-8和GBK的区别？code
UTF-8包含了很多国家的字符，涵盖了整个unicode码表，存储一个字符的编码使用长度不确定，1~4个字节；GBK只包含中文的编码，只需要固定的2个字节，能表示6万多个字符。<br>
Java中的string使用的是unicode编码，而UTF-8和GBK是编码格式，string只存在内存中，没有存在文件中或在网络中传输（序列化）时，只有编码，没有编码格式。

#### 157. 面向对象的四大基本特性？
封装：可以使类具有独立性和隔离性；保证类的高内聚。只暴露给外部和子类必须的属性和操作。类封装的实现依赖类的修饰符public、protected、private等。<br>
继承：对现有类的一种复用机制。一个类如果继承现有的类，则将拥有被继承类的所有非私有属性和操作。继承包括类的继承和接口的实现。<br>
多态：多态实在继承的基础上实现的。多态的三个要素：继承、重写、父类引用指向子类对象。父类引用指向不同的子类对象时，调用相同的方法，呈现不同的行为，就是多态的特性。多态可以分为编译时多态（方法重载）和运行时多态（方法重写）。<br>
抽象：提取事物的关键特性，为事物建立模型的过程。得到的抽象模型中包含属性（数据）和操作（行为）。这个抽象模型称为类。

#### 158. 面向对象的七大设计原则？
SOLID原则（<br>
&emsp;&emsp;单一职责原则：单一职责<br>
&emsp;&emsp;开放关闭原则：对扩展开放，对修改封闭，实现热插拔，提高扩展性<br>
&emsp;&emsp;里氏替换原则：实现抽象的规范，实现子类父类的相互替换<br>
&emsp;&emsp;接口隔离原则：接口单独设计，降低耦合度<br>
&emsp;&emsp;依赖倒置原则：针对接口编程<br>
）<br>
迪米特原则：不知道原则，功能模块尽量独立<br>
组合优于继承原则（合成复用原则）：尽量使用聚合、组合，而不是继承

#### 159. 计算-2>>1，-2>>>1
\>>带符号右移，负数符号位补1，正数符号位补0；>>>无符号右移，正负数符号位都补0。<br>
负数的二进制表示为正数取反加一，-2表示为1111 1111 1111 1111 1111 1111 1111 1110，带符号右移一位后为1111 1111 1111 1111 1111 1111 1111 1111，十进制为-1。<br>
-2无符号右移一位后为0111 1111 1111 1111 1111 1111 1111 1111，即2^31-1

#### 160. 内存中的栈（stack）、堆（heap）、方法区（method area）？
栈保存基本数据类型变量、对象的引用、函数调用现场；<br>
堆报错new关键字和构造器创建的对象，堆是垃圾收集的主要区域，可以分为新生代和老年代；<br>
方法区和堆都是各个线程共享的内存区域，用于存储已经被JVM加载的类信息、常量、静态变量、JIT编辑器编译后的代码。<br>
常量池是方法区的一部分。<br>
栈空间操作起来最快但是空间很小，通常大量的对象都放在堆空间，栈和堆的大小可以通过JVM的启动参数来调整。<br>
栈空间用完了会引发StackOverflowError，堆和常量池空间不足会引发OutOfMemoryError。

#### 161. HashMap实现原理？
&emsp;&emsp;Java中最常用的两种数据结构是数组和模拟引用（指针），几乎所有的数据结构都可以用这两种组合来实现。<br>
&emsp;&emsp;HashMap是一种散列链表，有一个数组，数组中的每一个元素都是一个链表，链表中的每一个元素都是entry。<br>
&emsp;&emsp;调用put(key, value)存储元素时，对key调用hashCode()方法，返回的hashCode用于找到bucket位置，存储entry对象。<br>
&emsp;&emsp;两个key的hashCode相同时，bucket位置相同，会发生碰撞，这个包含有键值对的Map.Entry对象会存储在链表中。调用get()方法的时候，如果hashCode相同，找到bucket位置后会调用key.equals()方法在链表中找到正确的节点。<br>
&emsp;&emsp;有几个重要特性，容量（capacity，默认值为16）、负载因子（load factor，默认值为0.75）、扩容极限（threshold resizing），元素个数大于capacity*load factor的时候会扩容为2n。

#### 162. Java内存模型的PermGen（永久代）和Metaspace（元空间）？code
HotSpot虚拟机使用PermGen来实现方法区，方法区是虚拟机的一个规范。<br>
永久代主要存放类定义、字节码和常量等很少会变更的信息。<br>
永久代也是会被回收，如果满了或者超过临界值，会触发Full GC。<br>
从JDK 1.8开始，HotSpot虚拟机取消PermGen，取而代之的是Metaspace（元空间）。字符串常量从永久代转移到堆内存中。<br>
元空间和永久代都是对方法区的实现，不同的是元空间不在虚拟机中，而是使用本地内存，默认情况下，元空间只受本地内存大小限制。

#### 163. AQS队列同步器？
全称是AbstractQueuedSychronizer，抽象队列同步器。<br>
如果说CAS是java.util.concurrent的基础，那么AQS则是整个并法包的核心。<br>
ReentrantLock、CountDownLatch、Semaphore等都用到了AQS。<br>
以双向队列的形式连接所有的Entry，比如ReentrantLock，所有等待线程都放在一个Entry中并连成双向队列，前一个线程使用完ReentrantLock，则双向队列实际上的第一个Entry开始执行。<br>
AQS定义了对双向队列的所有操作，只开放了tryLock和tryRelease给开发者重写。

#### 164. 什么是ABA问题？JDK是如何解决的？
question 98

#### 165. 静态类和静态方法？静态内部类和非静态内部类？
静态类都是静态内部类，没有静态的顶级类，顶级类的修饰只能是public、abstract、final。<br>
静态方法只能访问静态成员，而实例方法可以访问静态成员和实例成员。<br>
静态内部类不会持有外围类的引用，非静态内部类会隐式持有外围类的引用。<br>
非静态内部类可以访问外围类的静态和非静态成员，不能脱离外围类被实体被创建。

#### 166. 枚举类型？
enum类型的构造方法只能是private或friendly，不能是public或protected，所以枚举对象不能再程序中通过调用其构造方法来初始化。<br>
enum类型的值是通过运行时构造出对象来表示的，所以在cluster环境下，每个虚拟机会构造出一个同义的enum对象，可能出现同一个对象值不等的情况。

#### 167. Set集合通过什么方法来判断元素相同？equals还是==？源码？

#### 168. 反射中，Class.forName和classLoader的区别？加载数据库驱动为什么用Class.forName()？？？
&emsp;&emsp;（question 147 类加载过程）<br>
&emsp;&emsp;Class.forName(className);内部调用的是Class.forName(className, true, classLoader);第二个参数表示是否需要初始化（执行static代码块，设置static变量值），默认是true。<br>
&emsp;&emsp;ClassLoader.loadClass(className);内部去调用的是ClassLoader.loadClass(className, false);第二个参数表示是否需要进行链接，默认为false。<br>
&emsp;&emsp;动态加载一个类时，如果有静态代码块或静态变量，而且想在加载的时候初始化，应该使用Class.forName()。<br>

&emsp;&emsp;DriverManager中有一段需要执行的static代码块，用Class.forName(“com.mysql.DriverManager”);调用才能令其执行。

#### 169. ThreadLocal的设计理念和作用？使用场景？
&emsp;&emsp;提供线程内的局部变量，在多线程环境下访问时能保证每个线程内的局部变量独立。<br>
&emsp;&emsp;常用于以下场景：多线程环境下存在对非线程安全的对象的并发访问，而且该对象不需在线程间共享，也不想加锁，则可以用ThreadLocal来使得每个线程拥有这个对象的一个副本。<br>
&emsp;&emsp;消除了竞态条件，不需要同步就保证了线程安全。<br>
&emsp;&emsp;每个线程内部有一个ThreadLocal.ThreadLocalMap（ThreadLocal的静态内部类）类型的成员变量threadLocals，用来存储实际的变量副本，key是当前ThreadLocal变量，value是变量副本。<br>
&emsp;&emsp;在web服务器中，工作线程的生命周期比应用变量的生命周期长，线程局部变量没有释放的话应用有内存泄露的风险。<br>
&emsp;&emsp;ThreadLocalMap没有使用链表或红黑树解决hash冲突的问题，只用数组来维护哈希表，通过线性探测的开放地址法解决冲突。<br>

&emsp;&emsp;使用场景：<br>
&emsp;&emsp;web系统的session存储。Web容器采用线程隔离的多线程模型，一个请求一个线程，没有共享数据。请求到达时将session存储在ThreadLocal中，在请求过程中可以随意使用session，每个请求之间的session不相互影响。

#### 170. 3个线程A、B、C，循环打印ABCABCABC......，使用进程间通信？？？code

#### 171. JDK如何保证try-catch-finally中的finally块一定会被执行？？？

#### 172. 线程池java.util.concurrent.ThreadPoolExecutor的实现原理？源码？和ExecutorService的关系？
ThreadPoolExecutor继承自抽象类AbstractExecutorService，AbstractExecutorService实现了ExecutorService接口，ExecutorService接口继承自Executor接口。<br>
不提倡直接使用ThreadPoolExecutor，而是使用Executors的newPool的方法创建一个ExecutorService。<br>

构造函数的参数：<br>
corePoolSize：线程池应该维护的最小线程数量。<br>
maximumPoolSize：线程池中最大线程数。<br>
keepAliveTime：线程没有执行任务时保持多长时间终止。<br>
BlockingQueue<Runnable> workQueue：阻塞的任务队列，用来存储等待执行的任务。<br>
threadFactory：线程工厂，用来创建线程<br>
handler：当线程池和阻塞队列都满了，拒绝任务的策略。<br>
&emsp;&emsp;AbortPolicy：丢弃任务并抛出RejectedExecutionException异常<br>
&emsp;&emsp;DiscardPolicy：丢弃任务，但不抛出异常<br>
&emsp;&emsp;DiscardOldestPolicy：丢弃队列最前面的任务，重新尝试执行<br>
&emsp;&emsp;CallerRunsPolicy：由调用线程处理该任务<br>

几个方法：<br>
execute()：Executor中声明的方法，在ThreadPoolExecutor中实现，向线程池提交一个任务，由线程池去执行。<br>
submit()：ExecutorServivce声明的方法，在AbstractThreadPoolExecutor中实现。和execute()方法类似，调用了execute()方法，可以使用Future来返回线程执行结果。<br>
shutdown()：关闭线程池。<br>
shutdownNow()：关闭线程池。

定义了一个volatile变量runState，表示线程池的状态，volatile保证这个变量对所有线程可见。取值有0（RUNNING）、1（SHUTDOWN）、2（STOP）、3（TERMINATED）

#### 173. 数组的length属性和字符串的length()方法？
数组是一个容器对象，包含固定数量的同一类型的值，创建后长度固定，final的长度可以作为一个属性。<br>
String的数据结构是一个char数组，长度已经在char数组的length属性中提供。

#### 174. 调用线程start()方法和run()方法的区别？
start()方法启动新创建的线程，使创建的线程的状态变成可运行状态。<br>
调用run()方法的时候，只会在原来的线程中调用，没有创建新的线程，行为和普通的方法一样。

#### 175. 多线程读写同一资源不同步的实例。code

#### 176. Java内置锁synchronized可重入性？code
可重入。某个线程试图获取一个由它自己持有的锁时，请求会成功。

#### 177. 线程运行时发生异常会怎样？
&emsp;&emsp;如果异常没有捕获线程将会停止执行。<br>
&emsp;&emsp;Thread.UncaughtExceptionHandler是用于处理未捕获异常造成线程意外中断的一个内嵌接口，一个未捕获异常造成线程意外中断的时候，JVM会使用线程的Thread.getUncaughtExceptionHandler()来获取UncaughtExceptionHandler，并将线程和异常作为参数传递给handler的uncaughtException()方法处理。

#### 178. 如何在线程间共享数据？？？
通过共享对象，或者是阻塞队列这样的数据结构。

#### 179. 什么是FutureTask？
&emsp;&emsp;一个可以取消的异步运算。<br>
&emsp;&emsp;运算结束的时候结果才能取回，如果未结束的时候调用get会阻塞。<br>
&emsp;&emsp;实现了Runnable接口，可以通过线程池来执行，或传递给Thread对象执行。如果主线程遇到比较耗时的操作，又不想阻塞时，可以交给FutureTask执行，将结果返回。

#### 180. ConcurrentHashMap的并发度是什么？实现原理？
&emsp;&emsp;ConcurrentHashMap把map分成若干部分来实现可扩展性和线程安全，这种划分是使用并发度获得的，是构造函数的一个可选参数，默认值是16。<br>
&emsp;&emsp;将一个map分为多个Hashtable，根据key.hashCode()决定将key放到哪个Hashtable中。<br>
&emsp;&emsp;JDK 1.8中不再使用segment锁分离，而是使用乐观锁CAS算法来实现同步。底层还是数据+链表->红黑树。不需要对segment或者全局加锁，只需对单行加锁（hashCode相同）。对单个值的修改使用CAS。

#### 181. 线程池ThreadPoolExecutor已满的时候，提交一个任务，会发生什么？question 172
&emsp;&emsp;ThreadPoolExecutor的构造函数可以传入一个BlockingQueue workQueue。<br>
&emsp;&emsp;如果使用的是无界队列LinkedBlockingQueue，近乎认为是无限大的队列，任务可以添加到阻塞队列中。<br>
&emsp;&emsp;如果使用的是有界队列ArrayBlockingQueue，会使用拒绝策略RejectedExceptionHandler处理，默认是AbortPolicy，submit()方法将会抛出一个RejectedExecutionException。

#### 182. Java线程池execute()方法和submit()方法的区别？？？
execute() | submit()
-|-
/ | 都可以向线程池提交任务
返回类型是void | 可以返回持有计算结果的FutureTask对象
定义在Executor接口中 | 定义在ExecutorService接口中，扩展了Executor接口

#### 183. 单例模式的各种实现方法？code
(1) 懒汉式，线程不安全。多个线程可以实例化多个实例。<br>
(2) 懒汉式，线程安全。对getInstance方法同步，效率低<br>
(3) 饿汉式，基于classloader机制避免了多线程的同步问题，但没有达到lazy loading。<br>
(4) 饿汉式，static代码块，同上。<br>
(5) 静态内部类，同样利用了classloader机制保证实例化的时候只有一个线程，饿汉式中只要类被加载了，instance就会被实例化。这种方式类被加载了不会实例化instance，调用getInstance方法的时候才会状态内部类Holder。<br>
(6) 枚举，JVM保证enum不能被反射且构造方法只执行一次<br>
(7) DCL双检锁，（volatile避免初始化的时候指令重排？？？）

#### 184. 阻塞队列BlockingQueue的作用？
java.util.concurrent.BlockingQueue的特性是：队列是空的时，获取或删除元素的操作会被阻塞，队列是满的时，添加元素的操作会被阻塞。<br>
不接受空值，添加空值时会抛出NullPointerException。<br>
线程安全，所有的查询方法都是原子的。<br>
主要用于实现生产-消费模型。

#### 185. Spring有哪些优点？
轻量级。<br>
控制反转。注入依赖的对象，实现了松耦合。<br>
面向切面编程。将业务逻辑和系统服务分离。<br>
容器。管理对象的配置和生命周期。<br>
MVC框架。<br>
事务管理。统一的事务管理接口。<br>
异常处理。将特定技术（如JDBC、Hibernate）的异常转化为一致的unchecked异常。

#### 186. 如何在Spring中注入Java集合类？
使用集合配置元素：<br>
list元素：注入一系列的值，允许重复<br>
set元素：注入一系列的值，不允许重复<br>
map元素：注入一组键值对，键和值可以是任意类型<br>
props元素：注入一组键值对，键和值都是字符串

#### 187. Java代码优化实践？code

#### 188. Tomcat如何实现并发？Connector运行模式？多线程？NIO模式的项目经验？？？
自带的线程池，使用APR（Apache Portable Runtime）的Pool或Java的ThreadPool实现。<br>

Connector有BIO、NIO、APR三种模式。<br>
BIO：一个线程处理一个请求。传统的Java IO，同步且阻塞。<br>
NIO：可以用少量的线程处理大量的请求。同步阻塞或同步非阻塞IO。<br>
APR：tomcat以JNI的形式调用Apache HTTP服务器的核心动态链接库来处理文件读取或网络传输的操作，大大提高tomcat对静态文件的处理性能，从操作系统级别解决异步IO的问题。

#### 189. String对象为什么不适合做锁对象？？？

#### 190. equals()方法和==的区别？
==比较两个对象的地址是否相同。<br>
equals()可以重写，String类中比较的是字符串内容是否相同，如果没有重写，Object类中的实现是和==相同。

#### 191. boolean类型的大小是多少？
boolean类型的数据编译之后都使用int类型代替，boolean数据会被编译成byte数组，所以单独使用时占4个字节，在数组中时占1个字节。<br>
使用int的原因是32位的CPU一次进行32位的数据交换更高效。

#### 192.Object类定义了哪些方法？
clone()<br>
equals()<br>
hashcode()<br>
toString()<br>
wait()<br>
notify()<br>
notifyAll()<br>
finalize()<br>
getClass()

#### 193. Java动态绑定的内部实现机制？？？
动态绑定是实现“多态”的关键。

#### 194. ArrayBlockingQueue的原理和应用？
实现了BlockingQueue接口。<br>
一个基于数组实现的阻塞队列，构造需要指定容量。当试图向满队列添加元素或从空队列删除元素时，当前线程会被阻塞。

#### 195. ExecutorService类的execute()方法和submit()方法的区别？？？

### 196. Java能否创建volatile的数组？
能，但只是修饰指向数组的引用，多个线程同时改变数组的元素，并不能起到保护作用。

#### 197. 什么是多线程环境下的伪共享（false sharing）？
&emsp;&emsp;缓存系统中是以缓存行（cache line）为单位存储区的，当多线程修改相互独立的变量时，如果这些变量共享同一个缓存行，会影响彼此的性能，这就是伪共享。<br>
&emsp;&emsp;访问同一块内存区域时是共享，但这里应用程序要访问的独立的变量，是因为缓存行的存在才造成了共享，所以叫伪共享。<br>
&emsp;&emsp;缓存行是2的整数幂个连续字节，一般为32~256字节，最常见的是64字节。<br>
&emsp;&emsp;JDK 1.6中解决办法是使用缓存行填充，使一个对象占用的内存大小为64字节或整数倍，保证一个缓存行里不会有多个对象。<br>
&emsp;&emsp;JDK 1.7会优化掉无用的字段。<br>
&emsp;&emsp;JDK 1.8 缓存行填充被Java原生支持，添加@Contented注解会自动进行缓存行填充。

#### 198. WeakHashMap的工作原理？
和HashMap类似，但是使用弱引用作为key，当key对象没有任何引用时，key/value会被回收。

#### 199. 32位/64位的操作系统？CPU？JVM？？？
32位操作系统只能安装32wei的JVM，64位的操作系统两种JVM都可以安装。<br>
只有Server VM支持64位，Client VM不支持。？？？

#### 200.Java中的内存映射缓存区是什么？？？














##### 300. question300
