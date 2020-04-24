

###### 1.Servlet的相关概念

```Java
1.在总结Servlet时，先对Http和Tomcat、Xml,B/S架构有一个简单的认识。

2.一个神奇的文件：
web.xml：总的来说该文件起到路径的映射的作用

3.一个特殊的Java类Servlet
可以让浏览器通过一个路径去访问（一般的Java类是做不到这一点的）。

4.怎样写一个类，能成为Servlet
方式a:实现javax.servlet.Servlet接口
方式b:继承javax.servlet.GenericServlet抽象类
方式c:继承javax.servlet.http.HttpServlet抽象类

注意:
Servlet接口中,有五个抽象方法,其中最重要的一个方法是:
service(ServletRequestreq,ServletResponse res)

抽象类GenericServlet实现了接口Servlet,但是留了一个方法没有实现,就是上面所提到的service方法,除此之外,GenericServlet里面还增加了一些新的方法。

抽象类HttpServlet,继承了GenericServlet,但是HttpServlet中没有任何抽象方法,除了从父类继承过来的方法之外,里面还有很多新的方法:doXxx方法,另外需要注意的是,HttpServlet里面有俩个service方法,但是俩个方法的参数类型不同。

i.Servlet接口中的service方法.
    因为无论我们使用哪一种方式写出来的一个servlet中,一定会有一个方法叫做service,这个方法是Servlet接口中的定义的,tomcat服务器接收到客户端请求后,帮我们调用servlet中的方法的时候,它只会调用这一个service方法.
注意这个service方法中参数的类型:
    service(ServletRequestreq,ServletResponse res)

ii.GenericServlet中的俩个init方法
	带参数的init(ServletConfig config)方法是从Servlet接口中实现的,还有一个是这个类直接定义的,无参的init()方法。
	tomcat服务器创建servlet对象的时候,会帮我们去调用init(ServletConfig config)进行servlet类中的初始化工作,所以如果我们想在servlet类中初始化自己的一些相关数据的话,需要去重写这个init方法。有参数的init方法中调用了无参的init方法,所以将来我们只需要重写这个无参的init方法就可以了。这样我们就不需要再专门对这个init(ServletConfig config)方法中的参数进行处理了。

iii.HttpServlet中的俩个service方法

	这个是从Servlet接口中实现的service方法.
	service(ServletRequest req, ServletResponse res){
	    //强转参数类型
	    HttpServletRequest request = (HttpServletRequest)req;
	    HttpServletResponse response = (HttpServletResponse)res;
	    
	    //调用新定义的service方法
	    service(request,response);
		
	}
	
	
	这个是HttpServlet中新定义的service方法
	service(HttpServletRequest req, HttpServletResponse resp){
	   //拿到当前请求的类型 get/post
	   String method = req.getMethod();
	   
	   //判断请求的方法 然后去调用doXxx方法
           if(method.equals("GET")){
		doGet(req,resp);
	   }
	   if(method.equals("POST")){
		doPost(req,resp);
	   }
	   ...
	   ..
	}

http://ip:port/项目名字/资源路径
```

###### 2.Servlet的生命周期

```xml
1.servlet的对象是单例:在web项目整个运行期间,每一个servlet类只会有一个对象存在.默认情况下,这一个对象是在servlet第一次被访问的时候才会创建的,而且之后不管再访问多少次这个servlet,也不会再去创建它的的对象的.(我们可以设置某一个servlet的对象在服务器启动的时候就被创建出来)  


注意：这些操作(方法的调用)都是由服务器来做的:
I.默认情况下,第一次被访问的时候tomcat创建servlet对象(调用无参构造器)
II.tomcat调用init(ServletConfig config)方法，在servlet对象实例化之后,tomcat服务器会立马调用这个方法给servlet的实例进行初始化工作。
III.客户端访问的时候,tomcat会调用service(ServletRequest req,ServletResponse res)方法
VI.在销毁servlet对象的时候会tomcat调用destroy()方法.


通过在web.xml文件中设置,可以让servlet的对象是在服务器启动的时候被创建出来.
web.xml文件:
<servlet>
	<servlet-name>LifeServletTest</servlet-name>
	<servlet-class>servlet.LifeServletTest</servlet-class>
	<load-on-startup>1</load-on-startup>
</servlet>

	 加入<load-on-startup>标签,表示这个servlet需要在启动服务器的时候就被创建对象.其中,标签可以要放一个数字,这个数字的大小就决定了多个servlet对象被创建的顺序,数字越小越先创建(如果我们配置了多个servlet在启动服务器的时候被创建对象的话)
```

###### 3.Servlet的线程安全问题

```
问题产生的原因:
	  1.servlet是单例,一个servlet类只有一个对象在项目运行期间。
	  2.web项目项目本身就有多线程的特点,虽然我们在代码中没有写和线程相关的东西,但是这个多线程的特点是由服务器给我们提供出来的,一个客户端发送了一个请求,服务器接收到请求后就会建一个线程去处理这个请求。
	所以就有可能出现这样的情况:俩个线程同时去访问同一个servlet对象.

如何防止/解决
1.加锁synchronized
2.尽量少的定义成员变量，因为只有成员变量才会产生线程安全的问题(在多个线程访问同一个对象的时候),方法中的局部变量是没有这样的问题的.
3.其他(实现一些安全性的接口)

```

###### 4.客户端向服务器发送请求并传参

```java
客户端向服务器发送请求可以是get方式也可以是post方式.所以传参也分为get方式下传参和post方式下传参.
	
1.哪些方式的请求属于get/post方式
	get方式:
		a.浏览器中输入地址(URL)然后回车
		b.超链接
        c.页面中引入的css样式文件
        d.页面中引入的js的文件(javascript)
        e.<img src="image/a.jpg" />
        f.form表单中method="get"
        g.ajax中可以设置异步提交请求的方式为get
        f.其他

    post方式:
        a.form表单中method="post"
        b.ajax中可以设置异步提交请求的方式为post
        c.其他
2.get和post的特点及其区别
	它们各自的特点及其区别主要是体现在所传递的参数上面。
	    a.get方式参数
		参数是直接放在要请求url后面的
		例如:
             url?参数名=参数值&参数名=参数值
             要请求的url为:
             http://ip:port/WebTest/firstServlet
             传参:
             .../firstServlet?name=tom
             .../firstServlet?name=tom&age=20

      	这种方式传参的特点:
		   1.参数直接放在url后面
		   2.从浏览器的地址栏里面可以直接看到所传的参数
		   3.参数的长度有限制,不能把一个很长的数据通过get方式传参.(与浏览器种类有关)

	     b.post方式传参
			参数是放在请求的体部的。
			(浏览器发送的请求可以大致的分为请求头+请求体)

		这种方式传参的特点:
               1.参数放在请求的体部而不是url后面.
               2.浏览器的地址栏中看不到所传的参数.
               3.因为参数不用出现在地址栏里面,所有参数长度是没有限制的.                         
```

###### 5.servlet中接收客户端传过来的参数

```Java
客户端都是以这样的形式传参的:
	参数名字=参数值
	所有在servlet中我们需要用参数名字来拿到参数值:
	String value = request.getParameter("key");
	其中key就是参数名字,value是参数值,不管传的值本身是什么型,servlet中接收到以后只能是字符串类型或者字符串类型数组.如果客户端用的多选框,这个时候传过来的参数就要用一个字符串类型数组来接收:
String[] like = req.getParameterValues("like");
	在表单中,参数值就是用户所填写的内容或者是所选的选项的value属性值,参数名字就是每一个input或者其他输入元素中的name属性的值.
	例如:
	<input type="text" name="username">
	参数名字就是name属性的值:username
	参数的值就是将来用户所填内容.

	在servlet中,不管是get方式提交的时候还是post方式提交的时候,来到servlet里面以后,都是用相同的方式来取得数据。
	request.getParameter("key");
	request.getParameterValues("key");
```

###### 6.中文乱码

```Java
a.get方式提交数据,servlet拿到后乱码
	   需要修改tomcat中的配置文件,然后重新启动tomcat服务器.
	   server.xml:在这个文件中找到修改端口号的那个标签,然后加入一个新的属性URIEncoding="UTF-8",或者是写GBK、GB2312
	   例如:
	   <Connector  connectionTimeout="20000" port="8002" protocol="HTTP/1.1" redirectPort="8443"
	   URIEncoding="GBK"/>


b.post方式提交数据,servlet拿到后乱码
	   在代码中,我们去接收数据之前,也就是调用getParameter方法之前,需要先转换一下接收数据的编码:
	   req.setCharacterEncoding("GBK");
	   里面的值可以是GBK、UTF-8、GB2312

	
	注意:其实不管是get方式或者是post方式提交数据,我们最后是在Eclipse/MyEclipse中打印了出来,所以我们最后还要看看工具中是用什么编码显示的我们的数据的.点击一个文件或者项目名字后右键,properties选项中可以看到这个编码.
	
c.servlet向浏览器返回数据,浏览器显示乱码.
	    在代码中,获得输出流之前,我们要先设置输出流是用什么编码把数据写回去的:
	    resp.setCharacterEncoding("GBK");
	    同时我们还可以通知浏览器我们向它传输的数据是用的什么样的编码:
	    resp.setContentType("text/html;charset=GBK");

	 注意:在我们使用的浏览器中,也是可以调整编码的设置的,就是我们可以自己选择一种编码来显示当前页面的内容,同时浏览器也会有自己一个默认的显示编码.当浏览器不知道服务器传过来的的数据是用什么编码的时候,浏览器会用默认的编码来显示.
```

###### 7.servlet中的跳转

```
a.服务器内部跳转
	   1.跳转到一个页面
	   2.跳转到另外一个servlet

	   本质是：浏览器发出请求调用服务器servlet的service方法，该方法需要调用其他servlet的service方法或需要复制页面内容给浏览器
	
	page="页面/servlet"
	//获得一个跳转对象(服务器内部跳转)
	RequestDispatcher rd = req.getRequestDispatcher(page);
	//跳转
	rd.forward(req,resp);

	特点:
	1.不管servlet服务器内部跳转多少次,浏览器地址栏中显示的地址都是第一次访问到的servlet的地址
	2.服务器内部跳转其实就是把request和response对象进行转发,转发到另外一个服务器内部的一个资源中,如果转发的对象是一个页面,那么就把这个页面返回给浏览器,如果转发的对象是另外一个servlet,那么会调用到这个servlet里面的service方法,然后把之前那个request和response当做传送传进来.
	3.在服务器内部跳转的过程中,每一个环节中所用到的request和response对象都是同一个request和response对象.

	注意：forward跳转如果跳转多个servlet，前面的servlet中向response中写的内容会被后面的servlet中的内容覆盖掉

b.客户端重定向
	   1.跳转到一个页面
	   2.跳转到另外一个servlet

	   重定向本质是：请求服务器资源，servlet告知浏览器请求新的资源。
		
	   String page = "页面/servlet";
	   resp.sendRedirect(page);
	
	特点:
	   1.跳转之后,浏览器中显示的是跳转到的那个资源(页面/servlet)的地址,而不是第一次访问的servlet地址.即地址栏的地址会发生改变，重定向至少两次请求，用到的request和response都是新的。
	   
	   2.客服端重定向,其实就是让客户端浏览器重新再发一次新的请求到要跳转的资源。所以在客户端重定向中我们使用的对象是response,因为response这个对象的作用就是向客户端传递数据传递消息用的。
	   
	   3.在客户端重定向的过程中,每个环节中所用到的request,response对象都是浏览器新发出的。
```

###### 8.web项目中的路径问题

```Java
问题1：
路径WebRoot/path/  下面俩个页面:
	   pathA.html
	      A页面中有超链接,链接到B页面
	      <a href="pathB.html">pathB.html</a>

	   pathB.html

	一个servlet的映射路径:
	/PathServletTest
	这个servlet里面服务器内部直接跳转到A页面
	
    注意: 相对路径,指的是相对于当前路径,当前路径指的是当前地址栏里面的路径.
	
	正常情况:
	直接访问A页面:
	...web_test/path/pathA.html
	点击超链接:href="pathB.html"
	...web_test/path/pathB.html

	404的情况:
	先访问servlet,再跳转到A页面:
	...web_test/PathServletTest
	这时候页面显示的是A页面,因为servlet跳转了.
	点击超链接:href="pathB.html"
	...web_test/pathB.html
	
	因为要访问到pathB.html需要这样的路径:...web_test_servlet/path/pathB.html
	
	所以上面的这种情况就报错了 404

问题2：
路径中最左边的/
       例如 :一个servlet的映射路径
       /servlet/test/myfristservet
      这里的第一个/就是我们要讨论的.
      路径中其他/都是表示路径的分割。

       注意:如果路径最左没有/,那么路径就是要按照当前路径算,如果最左边有/,那么这个路就要按照这个/所代表的含义来算。
      
       这个/一般会有俩种含义:
	http://ip:port/
     1.代表地址栏中端口号后的那个/
	   例如:在这种情况下 
	   /test.html 
	   相当于:
	   http://ip:port/test.html


	http://ip:port/项目名字/
	2.代表地址栏中项目名字后的那个/
	    例如:在这种情况下 
	    /test.html 
	    相当于:
	    http://ip:port/项目名字/test.html



        注意:这里指的都是路径最左面的/
	特点:和客户端相关的路径中的/
	      http://ip:port/
	a. html页面中的/代表地址栏中端口号后的那个/
	b. 客户端重定向中的/代表地址栏中端口号后的那个/

	特点:和服务器端相关的路径中的/
	      http://ip:port/项目名字/
	c. web.xml文件中的/代表地址栏中项目名字后的那个/
	d. 服务器内部跳转中的/代表地址栏中项目名字后的那个/
```

###### 9.Servlet中request、session,application三大容器对象

```
注意：三个容器内部都维护了Map集合
       EI表达式是专门从容器中取内容
       
       语法：${容器，key}
       request             requestScope
       HttpSession         sessionScope
       ServletContext      applciationScope

Cookie保存服务器端的信息
       浏览器cookie是以文件形式呈现在浏览器中，同时在Tomcat中以对象形式呈现;

       注意：cookie是response带回给浏览器的
       浏览器接收的站点是有限的，每一个站点可以写入的cookie也是有限的（火狐每个站点最多维护180个cookie）
       每一个cookie的值最多是4096b（4kb）

       服务器写回cookie本地存在的key，会覆盖

       如果浏览器cookie维护到最大值，服务器再写回cookie对象，浏览器会移除最早写入的cookie

       如果浏览器禁用cookie，那么浏览器就没办法保存jsessionid，这时候就出现了重写URL技术；

       重写URL技术（每次发送请求都把cookie带上）本质是在请求资源后面追加jsessionid

       过滤器（filter）：作用是拦截资源并对资源进行校验，符合规范就放行（请求什么资源就继续请求资源）不符合规范的特殊处理
        

这三个对象可以在服务器内部帮我们保存数据以及传递数据.
            类型            对象名
       HttpServletRequest   request
       
       设置值：
       request.setAttribute(key,value);
       取值：
       request.getAttribute(key);

       生命周期：request始于浏览器终止于浏览器（浏览器每发出一次http请求，都会构建新的request对象）request给浏览器传数据是一次性的，只能通过服务器内部跳转给页面传数据


       HttpSession	    session
       
       获取对象：
       HttpSession session = request.getSession();
       设置值：
       session.setAttribute(key,value);
       取值：
       session.getAttribute(key)；

       范围：
       session存储的都是用户的私人信息

       生命周期：默认的是浏览器和服务器三十分钟之内没有交互，服务器自动销毁session对象及里面信息

       session创建：第一次访问非静态资源时创建，浏览器jessionod存在，服务器sessionid销毁，服务器重新创建
        浏览器jessionod不存在，服务器sessionid存在，也是重新创建（服务器端session没有销毁）
	
	服务器正常关闭session的所有对象序列化保存到work目录，启动反序列化得到对象

	session对象对同一个浏览器访问的同一个项目中的任意资源都共享

       特点：http协议是无状态协议（不记录是哪个浏览器访问的）需要服务器给出一种机制（标记），来标记是哪个浏览器访问的，浏览器访问非静态资源（servlet，jsp），服务器会给该浏览器创建一个session对象，当前请求回写资源给浏览器时，会把session的id带给浏览器，浏览器把session的id保存到浏览器中的cookie里面，同时会标记该网站及失效时间，下一次只要访问该项目都会把session的id发给服务器（session的id在本地以jsessionid出现）


       ServletContext	    application
       
       获取对象：
       第一种：
       ServletContext application = request.getServletContext();


       特点：存储的是公共数据（大家共享的数据）
             每一个web项目只有一个application对象
	     整个项目中任何页面或servlet共享该对象

	生命周期：application对象由Tomcat创建，并且Tomcat关闭时销毁



       
       request:
         保存数据:key必须是String,value是Object类型
	 request.setAttribute("key", value);
	 取数据:
	 通过key拿到value值,需要的话可以强转
	 Object o = request.getAttribute("key");

	 作用范围:request的存/取数据只能在一次请求之内起作用.比如服务器内部跳转的时候(因为内部跳转客户端只发一次请求).
	 注意:客户端重定向是不行的,因为客户端重定向会发出多个请求.

	
       session:
	
	  保存数据和取数据:
	     和上面request一模一样的操作,只是对象的名字变化了,上面的加request,而这个叫session.

	  如何获得:
	  HttpSession session = req.getSession();

	  作用范围:在一个会话之中起作用.一个会话可以包括很多个request请求,所以放在session中的数据,可以被多个请求所共享.
	 
       
       application:
          保存数据和取数据:
	     和上面request也是一模一样的操作

          如何获得:
	     ServletContext application = 
			req.getSession().getServletContext();

	  作用范围:整个项目在运行期间,只有一个application对象,所以这个对象是所有人所有会员共享的,大家都可以向这个对象里面存值,也可以再拿出来.因为 这个对象一个项目中只有一个.

  作用范围: request<session<application
  (page<request<session<application  这page是之后我们在jsp中要学习的另一个范围,页面范围,只在一个页面中起作用。)


  注意:getParameter("key")方法和getAttribute("key")方法,getParameter是接收客户端传过来的值,getAttribute方法是取到之前调用setAttribute方法保存到这个对象里面的值.

```

###### 其它

```
服务器内部跳转
		跳转到页面
		跳转到其他的Servlet
	本质：浏览器发出请求调用服务器中Servlet
	的Service方法，该方法需要调用其他的
	Servelt的service方法或需要复制页面内容给
	浏览器
	
	特点：地址栏出现的资源是第一次请求的资源
		，服务器内部跳转是服务器内部完成的，
		浏览器只请求了一次，服务器内部不管
		跳转Servlet还是页面，req,res使用的都
		是同一个
	注意：forward跳转如果跳转多个servlet
	前面的Servlet中向response中写了内容
	或被后面的覆盖掉
	include负责跳转，跳转过程
	不管那个Servlet向response
	对象中写内容是追加效果（合并输出到浏览器）

	

	重定向
		跳转到页面
		跳转到其他的Servlet
	本质：请求服务器资源，服务器资源告知
	浏览器请求新的资源（html,Servlet,jsp）

	特点：重定向之后浏览器中的地址栏的地址
	    会发生改变，
	    重定向至少两次请求，
	    重定向用到的req和res都是全新的

	注意：浏览器每发出一次请求tomcat都会
	构建新req和res

	http://127.0.0.1:8888/jd1812_web/Rt

	http://127.0.0.1:8888/jd1812_web/login.html
	

	http://127.0.0.1:8888/jd1812_web/user/Rt1
	http://127.0.0.1:8888/jd1812_web/user/login.html

	http://127.0.0.1:8888/jd1812_web/login.html

	response.sendRedirect(“user/login.html");

	http://127.0.0.1:8888/jd1812_web/path/pathA.html
	http://127.0.0.1:8888/jd1812_web/Ps

	http://127.0.0.1:8888/jd1812_web/pathB.html

http://127.0.0.1:8888/jd1812_web/path/Ps

	pageContext(作用范围：jsp页面内)
	
	request		HttpServletRequest
	设置值<String,Object>
	request.setAttribute(key,value)
	取值
	request.getAttribute(key);

	生命周期：request始于浏览器终止于浏览器
	（浏览器每发出一次http请求都会构建新的
	request对象），
	request给浏览器传数据一次性的,只能借助
	服务器内部跳转给页面传数据
	


	
	Session(会话)	HttpSession
	获取session对象
	httpSession session=
		request.getSession();
	设置值<String,Object>
	session.setAttribute(key,value)
	取值
	session.getAttribute(key);

	范围：session存储的都是用户的私人信息
	
	生命周期：默认的是浏览器和服务器30分钟
	之内没有交互，服务器自动销毁该session
	对象

	session的创建
	第一访问非静态资源创建
	浏览器中jessionid存在 服务器session对象
	销毁，服务器端重新创建
	浏览器中jessionid不存在 服务器session对象
	存在，重新创建（原来的服务器端的session没有销毁）
	
	服务器正常关闭session所有的对象序列化保存到
	work目录 启动反序列化读到内存

	特点：	
	   http协议无状态协议（不记录是哪个浏览器
	访问的），需要服务器给出一种机制（标记）
	用于标记是那个浏览器范围的
	浏览器访问非静态资源（Servlet,jsp）,服务器
	会给该浏览器创建一个session对象，单前请求回写
	资源给浏览器的时候会把session的id带给浏览器
	，浏览器把session的id保存到浏览器的cookie中
	，同时会标记该项目及失效时间，下一次只要访问
	该项目都会把session的Id发给服务器
	(session的id在本地以jsessionid出现)

	application	ServletContext
	获取application对象
	第一种：
	ServletContext application=
	request.getServletContext();
	第二种:
	ServletContext application=
	session.getServletContext();
	第三种:
	ServletContext application=
	getServletContext();
	或
	ServletContext application=
	this.getServletContext();
	设置值<String,Object>
	application.setAttribute(key,value)
	取值
	application.getAttribute(key);

	注意：三个容器内部都维护了Map集合

	El表达式专门从容器中取内容的
	语法：
	${容器.key}
	e.g.：${requestScope.name}
	后台			前台
	request			requestScope
	HttpSession		sessionScope
	ServletContext		applciationScope
	
	
	FF8327C62BBD8A5569E7655474C7482F

	http://127.0.0.1:8888/jd1812_web/sess;jsessionID=FF8327C62BBD8A5569E7655474C7482F

```

