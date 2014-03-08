Running more than a handful of CI jobs at http://drone.io? Their usage of screen real estate can leave you scrolling all the time when you really need a quick glance to see if everything is up.

drone-aggregator is a [Spring Boot](http://projects.spring.io/spring-boot/) application that uses [Jsoup](http://jsoup.org) to elegantly grab every [Getting Started Guide](http://spring.io/guides) and construct a slim and trim table with links to each guide as well as its corresponding drone.io job.

## Getting Started

You first need to [install Spring Boot](https://github.com/spring-projects/spring-boot#installing-the-cli). I won't show that here because the instructions behind that link are pretty detailed. I'll just assume you got things set up right.

## Run the application
Now you can run the application.

    spring run app.groovy
        
You should see something like this:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::            (v1.0.0.RC1)

2014-03-07 21:42:30.452  INFO 35542 --- [       runner-0] o.s.boot.SpringApplication               : Starting application on retina with PID 35542 (/Users/gturnquist/.m2/repository/org/springframework/boot/spring-boot/1.0.0.RC1/spring-boot-1.0.0.RC1.jar started by gturnquist)
2014-03-07 21:42:30.489  INFO 35542 --- [       runner-0] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@73077dab: startup date [Fri Mar 07 21:42:30 CST 2014]; root of context hierarchy
2014-03-07 21:42:31.386  INFO 35542 --- [       runner-0] .t.TomcatEmbeddedServletContainerFactory : Server initialized with port: 8080
2014-03-07 21:42:31.596  INFO 35542 --- [       runner-0] o.apache.catalina.core.StandardService   : Starting service Tomcat
2014-03-07 21:42:31.597  INFO 35542 --- [       runner-0] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/7.0.47
2014-03-07 21:42:31.685  INFO 35542 --- [ost-startStop-1] org.apache.catalina.loader.WebappLoader  : Unknown loader org.springframework.boot.cli.compiler.ExtendedGroovyClassLoader$DefaultScopeParentClassLoader@62b51993 class org.springframework.boot.cli.compiler.ExtendedGroovyClassLoader$DefaultScopeParentClassLoader
2014-03-07 21:42:31.691  INFO 35542 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2014-03-07 21:42:31.692  INFO 35542 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1205 ms
2014-03-07 21:42:32.288  INFO 35542 --- [ost-startStop-1] o.apache.tomcat.util.digester.Digester   : TLD skipped. URI: http://www.springframework.org/tags is already defined
2014-03-07 21:42:32.293  INFO 35542 --- [ost-startStop-1] o.apache.tomcat.util.digester.Digester   : TLD skipped. URI: http://www.springframework.org/tags/form is already defined
2014-03-07 21:42:34.678  INFO 35542 --- [       runner-0] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2014-03-07 21:42:34.802  INFO 35542 --- [       runner-0] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/],methods=[],params=[],headers=[],consumes=[],produces=[],custom=[]}" onto public java.lang.String io.spring.guide.drone.DroneAggregator.index(java.util.Map<java.lang.String, java.lang.Object>)
2014-03-07 21:42:34.864  INFO 35542 --- [       runner-0] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2014-03-07 21:42:34.864  INFO 35542 --- [       runner-0] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2014-03-07 21:42:35.055  INFO 35542 --- [       runner-0] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2014-03-07 21:42:35.246  INFO 35542 --- [       runner-0] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port: 8080
2014-03-07 21:42:35.248  INFO 35542 --- [       runner-0] o.s.boot.SpringApplication               : Started application in 5.129 seconds (JVM running for 7.207)
```

You can now see the table of CI jobs at <http://localhost:8080>.

![](images/screenshot.png)

