###### 1.会话追踪技术

```
http协议的访问是无状态的访问,就是说当前这次访问是不会知道之前访问的状态的.(http协议的访问是不会帮我们保存访问的记录/痕迹的).
	有些我们的访问是不需要知道之前访问的状态的.比如我们访问一些静态的页面,在访问一个校园网站的时候,第一次访问点击了页面中的校园风采,第二次访问点击了学生作品,这俩次访问完全可以没有任何关系,也不需要知道各自访问的状态.
	但是有些访问我们是一定要知道之前几次访问的状态的.比如在购物网站的时候,第一次访问点击购买了一个手机,第二次访问点击购买了一个电脑,第三次访问点击购物车结算,这个时候我们就必须知道前俩次访问的时候购买了什么,要不然就没有方法去结算。
	所以我们就有了会话追踪技术来解决这个访问无状态的问题
	
	session和cookie
	session是保存在服务器端的一个对象.客户端是没有session的.

	cookie在客户端和服务器端都会有。但是存在的形式不一样.在客户端cookie是以本地文件(浏览器管理的文件)的形式存在的,在服务器端是以java对象的形式存在.

	cookie的作用:和session一样,是用来保存用户访问的信息数据的。但是session是把数据保存在服务器端的内存里面,cookie是把数据保存在客户端的文件里面.

	
	客户端访问服务器的时候,服务器会给这个客户端创建一个会话,也就是一个session对象,服务器端的内存里面同时可能有好多个session对象,分别对应的不同的客户端,每一个session对象都有一个唯一的id值,叫做JSESSIONID。
	
	服务器端给客户端产生一个session对象后,会通过这次访问的response对象把这个session的JSESSIONID的值返回给浏览器,浏览器接收到后会把这个值以一个cookie文件的形式保存起来.

	之后,这个浏览器再发生请求到服务器的时候,就会把之前保存在cookie文件中的JSESSIONID的值传给服务器,服务器通过这个JESSIONID的值就能够知道服务器内存中是哪一个session对象和当前这个客户端对应.

	这样以来,最后就能到达一个效果,我们客户端的每一次访问,在服务器端都能够拿到相同的一个session对象,从而实现不同请求之间通过相同的session对象进行数据的共享.

	
	如何从服务器端向浏览器写回cookie
		//创建cookie对象
		Cookie c1 = new Cookie("name","tom");
		Cookie c2 = new Cookie("msg","hello");
		//设置cookie的有效时间
		c1.setMaxAge(60*60*24*365);
		c2.setMaxAge(60*60*24*365*10);
		//把cookie放到response里面
		resp.addCookie(c1);
		resp.addCookie(c2);
		
	 最后response对象会把cookie带回到浏览器,并又浏览器把cookie对象中的内容保存到对应的一个cookie的文件中。

	 注意:如果没有设置cookie生效的时间,那么这个cookie就是会话cookie,当会话结束(关闭浏览器)的时候cookie就是失效了。


         如何在servlet中拿到从浏览器发送过来的cookie文件中的数据
	   //从request中拿到一个cookie数组
	   //如果没任何cookie数据则返回null
           Cookie[] cookies = req.getCookies();
	   //遍历数组 拿出key和value
	   for(Cookie c:cookies){
		String key = c.getName();
		String value = c.getValue();
	   }

       注意:session对象的创建时间。
```

###### 2.URL重写

```
也属于会话追踪技术的一种.

	URL重写解决了这样一个问题:
	当前浏览器把cookie禁用之后,浏览器在发请求的时候,就不会把cookie带到服务器端了(其中最重要的也包括JSESSIONID),因为禁用cookie之后浏览器拒绝一切站点向浏览器写入cookie的(注意再禁用之前是否已经有一些已经存在的cookie了),这样的话,多个请求就不能在服务器端拿到同一个session对象了(因为发送请求的时候没有把JSESSIONID的值传给服务器)。

	把JSESSIONID从客户端传给服务器,有俩种方式:
	1.JSESSIONID保存在cookie文件中,浏览器发送请求的时候把这个cookie文件中的数据带给服务器(cookie).

        2.通过传参的方式,把JSESSIONID的值通过要访问的URL传给服务器.(URL重写)

	
	如何实现URL重写:
	String url = resp.encodeURL("..");
	这个方法参数就是我们要访问的URL,这个方法会把重写后的URL以字符串的形式返回.

	例如:在一个超链接中,本来要访问的URL是:<a href="GetDataFromSession">
	
	重写后:
	<a href="GetDataFromSession;jsessionid=5480EF9016295A73DC56731A2F123246">
	
```

###### 3.过滤器（Filter）

```
作用:在一个请求去访问某个资源的时候,filter可以在这个请求访问到这个资源之前,把请求拦下,然后做出一系列的处理或者判断(比如编码的转换,信息的过滤、权限的判断、是否已经登录的验证等等),最后filter再决定是否要让这个请求去访问那个资源.

	如何写一个filter:
	写一个java类,然后实现javax.Servlet.Filter接口

	这个接口中有三个方法:
	init  destroy  doFilter
	init:这个过滤器类被服务器创建对象的时候会调用到这个方法。
	destroy:过滤器对象被销毁的时候会调用这个方法。
	doFilter:当过滤器拦截到请求的时候,会调用这个doFilter.
	
	注意:
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain)

	这个方法有三个参数,第三个参数表示的一个过滤器链对象,因为同一个请求有可能要依次的通过俩个或者多个过滤器,在web中把这样多个过滤器看做一个过滤器链条对象,就是用这个FilterChain类型的对象来表示。
	chain.doFilter(req,res)表示把当前的req和res传给这个过滤器链条中的下一个过滤器进行过滤,如果说链条中已经没有下一个过滤器,那么就把这次访问放行,让它去访问它真正要访问的资源.

	注意:如果这次访问没有符合过滤器中的条件,那么我们就不用调用chain.doFilter(req,res)这个方法把这次访问放行了,而是可以直接进行跳转(服务器内部跳转或者客户端重定向),跳转到一个页面,页面中提示用户一下,为什么这次不让他去访问,比如说 还没有登录、权限不够等等原因。


        最后还需要在web.xml文件中进行配置:
	例如:
  <filter>
  	<filter-name>encodingFilter</filter-name>
  	<filter-class>com.briup.filter.EncodingFilter</filter-class>
  </filter>
  
  <filter-mapping>
  	<filter-name>encodingFilter</filter-name>
  	<url-pattern>/*</url-pattern>
  </filter-mapping>

	这个配置和servlet的配置很相似。

	<url-pattern>/*</url-pattern>
	表示当前这个过滤器,要拦截的路径是/*,表示项目中所有的资源。
	
	<url-pattern>/servlet/*</url-pattern>
	表示当前这个过滤器,要拦截的路径是/servlet/*,也就是项目下面的servlet路径下面的所有资源.

	<url-pattern>/firstServlet</url-pattern>
	表示当前这个过滤器,要拦截的路径是/firstServlet,也就是说这个过滤器只会拦截这一个路径.

	如果要拦截的路径有俩个,我们可以配置俩个<filter-mapping>标签分别都去和同一个<filter>标签对应。

     注意:1.这里的/代表地址栏中项目名字后的/
	  2.某一个资源是不是会被拦截,要看地址栏中会不会出现我们在web.xml文件所配置的要拦截的路径.
```

###### 3.监听器（Listener)

```
作用:监听web中的一些事件的发生,如果某些事件一旦发生了,那么这个监听器就会调用某些方法进行处理.
	比如:在web中可以监听request对象的创建和销毁.

	如何去写一个监听器:
	1.写一个类,实现一个特点的接口。
	2.在web.xml文件中进行配置。
	web.xml:
	 <listener>
  		<listener-class>com.briup.listener.RequestListener</listener-class>
	 </listener>
	
	比如:
	监听request对象的创建和销毁要实现接口ServletRequestListener
	监听session对象的创建和销毁要实现接口HttpSessionListener
	监听application对象的创建和销毁要实现接口    

```

