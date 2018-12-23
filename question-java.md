
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















##### 100. question100
