### 常规调试

#### show Execution point

功能：显示执行点：点击该按钮,光标将定位到当前正在调试的位置
快捷键：Alt + F10

![image-20251015143141046](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20251015143141046.png)

#### Step Over

 功能：单步跳过，点击该按钮表示程序将执行下一行，如果该行是一句代码直接执行到下一行，如果该行是
 一个方法， 不会进入该方法，直接执行到下一行。
快捷键：F8

![image-20251015143237453](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20251015143237453.png)

#### Step Into

功能：单步跳入，点击该按钮表示程序将执行下一行，如果该行是一个方法， 会进入该方法继续执行。
快捷键：F7

![image-20251015145439276](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20251015145439276.png)

#### Step Out

功能：跳出前面step into 进入的方法，如果需要跳出的该方法有断点就不会跳出该方法，而会进入该方法下个断点
快捷键：shift + F8

![image-20251015145648564](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20251015145648564.png)



#### run to Cursor

功能：跳到下一个断点
快捷键：alt + F9

![image-20251015165748663](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20251015165748663.png)

#### 查看断点

如果我们想查看那些地方打了断点，点击该按钮即可.也可以取消勾选断点

快捷键 ： ctrl+shift + F8

![image-20251015171047670](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20251015171047670.png)

#### 修改变量值

可以在变量区，右键set value修改变量运行期间的值

![image-20251015171559845](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20251015171559845.png)

### 高级调试



#### 条件断点

应用场景：循环时，查看特定值时

如图：只有当tempToken == 123 时，这个断点才会停下来

![image-20251020152113846](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20251020152113846.png)





#### 依赖断点

个断点的执行，依赖于另一个断点。

应用场景：

有个功能线。A 功能点--通过 B 功能点--到达 C 功能点--到达 D 功能点--到达 E 功能点

A B C D E 都打上断点，并且 让E依赖D，D依赖C...B依赖A。我们跑一遍就知道，在那个功能点上出问题了



设置依赖断点

73行的执行，依赖69行的执行

![image-20251020154345645](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20251020154345645.png)





#### 接口断点

场景：项目太大，而且都是通常接口隔离的，想看接口的调用，比较麻烦。如果想看接口实现类的调用，直接打一个接口断点，那么实现类执行的时候，会在实现类的地方停下来





![image-20251020160241262](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20251020160241262.png)



#### 变量断点

就是在跟其他断点一样，在变量上打断点，变量是对象的话，记得断点右键，把Field Access勾选上

这个变量的值在那里改变的时候，断点就会停留在哪里。

![image-20251021143005892](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20251021143005892.png)



#### 断点日志

执行到这个断点的时候，就会打印日志

![image-20251021144153149](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20251021144153149.png)





#### 添加/修改数据或查看变量的值

evaluate Expression来查看变量的属性，或者是给变量改变属性。这个东西绝对是，调试的利器

1.查询变量值

![image-20251021145252661](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20251021145252661.png)

2.对值进行操作

result 真正的值是 0 ，我对它进行了  -1，点击Evaluate 改值成功

![image-20251022113915363](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20251022113915363.png)

#### Add to Watches

该区域将显示你所感兴趣的变量的值。

在调试模式下，双击变量名称，点击右键，选择 Add to Watches

![image-20251022145349424](C:\Users\lzy\AppData\Roaming\Typora\typora-user-images\image-20251022145349424.png)