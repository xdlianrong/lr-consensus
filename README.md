# Consensus

公司信息化系统，目前可以提交周报。

# 问题记录

1. 教学平台，MySQL5InnoDBDialect

2. 注意 JPA 数据库查询效率

3. session过期，前端发起异步请求，未能跳转到login页面

4. 查看数据库引擎 show create table table_name

5. 为报表 entity 添加拦截器，对文字中包含的“\r\n”进行处理

6. @Fetch(FetchMode.SUBSELECT)


# 运行

## 构建

```
./gradlew build
```

## 开发环境运行

将配置文件放入Project根目录下，代码运行时默认查找Project根目录下的配置文件。

```
./gradlew bootRun
```

## jar包运行

jar将运行Java命令的路径作为baseDir，代码运行时默认查找baseDir目录下的配置文件。

```
项目运行时需要指定profiles参数，例如：dev,prod-lianrong
$ nohup java -jar --spring.profiles.active=prod-lianrong *.jar
$ nohup /usr/java/openjdk-17/bin/java -jar *.jar --spring.profiles.active=prod-lianrong *.jar
```

# 技术

## 技术路线

- Java 17
- Spring Boot 2.6.3
- Spring Boot Security
- Spring Data JPA
- H2
- Mysql
- Lombok
- bootstrap+popper+jquery

## 文档

### Spring Properties

https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html

### Spring Boot Security

**spring-boot-security-autoconfiguration**

https://www.baeldung.com/spring-boot-security-autoconfiguration

```
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Application {}

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {}
```

### Validate Password

https://mkyong.com/regular-expressions/how-to-validate-password-with-regular-expression/

### Session

https://docs.spring.io/spring-security/reference/servlet/authentication/session-management.html

### Spring MVC

https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-controller

### Mysql

https://spring.io/guides/gs/accessing-data-mysql/

### Spring AOP

https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop

### Thymeleaf

https://attacomsian.com/blog/thymeleaf-get-session-attributes

### Form

https://spring.io/guides/gs/validating-form-input/

https://spring.io/guides/gs/handling-form-submission/

### Validation

https://www.baeldung.com/javax-validation-method-constraints

### CORS

https://docs.spring.io/spring-security/site/docs/current/reference/html5/#cors

https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-cors

### Spring JPA

https://docs.spring.io/spring-data/jpa/docs/2.5.6/reference/html/#preface

### Java JPA

#### 使用清单

1. All Entity
2. Entity Inheritance
   1. Abstract Entities
   2. Mapped Superclasses
   3. Non-Entity Superclasses
3. Entity Inheritance Mapping Strategies
   1. The Single Table per Class Hierarchy Strategy(recommended)
   2. The Table per Concrete Class Strategy
   3. The Joined Subclass Strategy
4. Embeddable Classes in Entities
5. Direction in Entity Relationships
   1. Bidirectional
   2. Unidirectional
6. Multiplicity in Entity Relationships(OneToOne, OneToMany, ManyToOne, ManyToMany)
7. owning  side or inverse side
8. mappedBy，双向关系必须加上mappedBy
9. Cascade
10. @JsonIgnoreProperties

#### Entity

`Transient` 

`LAZY` or `EAGER`

```
@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
        +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
        +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
             message="{invalid.email}")
    protected String email;
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
             message="{invalid.phonenumber}")
    protected String mobilePhone;
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
             message="{invalid.phonenumber}")
    protected String homePhone;
```

#### Direction in Entity Relationships

- Bidirectional
  - owning side(Determines how the Persistence runtime makes updates to the relationship in the database)
    - @OneToOne(contains foreign key)
    - @ManyToOne
    - @ManyToMany
  - inverse side
    - @OneToOne (mappedBy)
    - @OneToMany (mappedBy)
    - @ManyToMany (mappedBy)

- Unidirectional
  - owning side

#### mappedBy and @JoinColumn

- mappedBy 用于 Bidirectional Relationships，强制区分主、次（owning or inverse side），owning side 维护关系。
- @JoinColumn 指定外键名称和来源。

#### Cascade Operations and Relationships

Entities that use relationships often have dependencies on the existence of the other entity in the relationship. For example, a line item is part of an order; if the order is deleted, the line item also should be deleted. This is called a cascade delete relationship.

#### Orphan Removal

- @OneToMany
- @OneToOne

#### Cascade delete and Orphan Removal

- Cascade delete，平衡操作
- Orphan Removal，单方操作

#### Embeddable Classes in Entities

#### Entity Inheritance

**Abstract Entities**

An abstract class may be declared an entity by decorating the class with `@Entity`. Abstract entities are like concrete entities but cannot be instantiated.

Abstract entities can be queried just like concrete entities. If an abstract entity is the target of a query, the query operates on all the concrete subclasses of the abstract entity:

```
@Entity
public abstract class Employee {
    @Id
    protected Integer employeeId;
    ...
}
@Entity
public class FullTimeEmployee extends Employee {
    protected Integer salary;
    ...
}
@Entity
public class PartTimeEmployee extends Employee {
    protected Float hourlyWage;
}
```

**Mapped Superclasses**

Entities may inherit from superclasses that contain persistent state and mapping information but are not entities. That is, the superclass is not decorated with the `@Entity` annotation and is not mapped as an entity by the Java Persistence provider. These superclasses are most often used when you have state and mapping information common to multiple entity classes.

Mapped superclasses are specified by decorating the class with the annotation `javax.persistence.MappedSuperclass`:

```
@MappedSuperclass
public class Employee {
    @Id
    protected Integer employeeId;
    ...
}
@Entity
public class FullTimeEmployee extends Employee {
    protected Integer salary;
    ...
}
@Entity
public class PartTimeEmployee extends Employee {
    protected Float hourlyWage;
    ...
}
```

**Non-Entity Superclasses**

**Entity Inheritance Mapping Strategies**

@javax.persistence.Inheritance

- The Single Table per Class Hierarchy Strategy
- The Table per Concrete Class Strategy
- The Joined Subclass Strategy

#### Primary Keys

Overlapping primary key/foreign key

```
@Column(insertable=false, updatable=false)
```

- Single-Valued Primary Keys
- Generated Primary Keys
  - @TableGenerator  @GeneratedValue(strategy=GenerationType.TABLE)
  - @GeneratedValue(strategy=GenerationType.IDENTITY)
- Compound Primary Keys
  - @IdClass(wrapperred class.class)

#### Entity Mapped to More Than One Database Table

```
@Entity
@Table(name="PERSISTENCE_ORDER_PART")
@SecondaryTable(name="PERSISTENCE_ORDER_PART_DETAIL", pkJoinColumns={
    @PrimaryKeyJoinColumn(name="PARTNUMBER",
        referencedColumnName="PARTNUMBER"),
    @PrimaryKeyJoinColumn(name="REVISION",
        referencedColumnName="REVISION")
})
public class Part {
...
}
```

#### Reference

https://docs.oracle.com/javaee/6/tutorial/doc/bnbpz.html

https://docs.oracle.com/javaee/6/tutorial/doc/bnbqa.html

https://docs.oracle.com/javaee/6/tutorial/doc/bnbqn.html

https://docs.oracle.com/javaee/6/tutorial/doc/giqst.html

https://stackabuse.com/spring-data-jpa-guide-to-the-query-annotation/

https://docs.jboss.org/hibernate/orm/5.3/userguide/html_single/Hibernate_User_Guide.html

https://www.baeldung.com/java-hibernate-multiplebagfetchexception

**FETCH LAZY Problems**

https://www.baeldung.com/hibernate-lazy-loading-workaround

https://www.baeldung.com/spring-open-session-in-view

### H2

https://www.h2database.com/html/tutorial.html

http://www.h2database.com/html/features.html

### Task Execution and Scheduling

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.task-execution-and-scheduling

https://spring.io/guides/gs/scheduling-tasks/

### Lombok

https://projectlombok.org/

### Log

slf4j-api

### JSON

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.json

## 工具

### Spring Profiles

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.profiles

### Spring Initializr

https://start.spring.io/

### Spring DevTools 

**Automatic Restart**

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using.devtools.restart

**LiveReload**

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using.devtools.livereload

http://livereload.com/extensions/

### STS

https://spring.io/guides/gs/sts/

### Springfox

http://springfox.github.io/springfox/docs/current/#introduction

https://github.com/springfox/springfox-demos

https://github.com/springfox/springfox-demos/tree/master/boot-webmvc

#### Swagger

http://localhost/documentation/swagger-ui/

http://localhost/documentation/swagger-resources

http://localhost/v3/api-docs

### Springdoc

```
/swagger-ui.html
/v3/api-docs
```

https://springdoc.org/

### Actuator

```
/actuator
```

https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using.packaging-for-production

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator

### Spring Boot Admin

https://codecentric.github.io/spring-boot-admin/current/#_what_is_spring_boot_admin

https://github.com/codecentric/spring-boot-admin/tree/master/spring-boot-admin-samples

https://github.com/codecentric/spring-boot-admin/

### War

https://spring.io/guides/gs/convert-jar-to-war/

# HTML + CSS + JS

## 前端框架

### jquery

https://jquery.com/

https://www.jsdelivr.com/package/npm/jquery

**jQuery API**

https://api.jquery.com/

### Bootstrap

****

https://getbootstrap.com/docs/5.0/getting-started/introduction/

### Masonry 

https://masonry.desandro.com/

### Izitoast

https://izitoast.marcelodolza.com/

https://cdnjs.com/libraries/izitoast

### Font

https://icons.getbootstrap.com/

https://icomoon.io/

http://fontawesome.io/

### Bootstrap class含义解释

1. toggler：切换器，小尺寸设备或下拉菜单使用
2. navbar-brand：导航栏上的品牌
3. dropdown：下拉
4. text-end：子元素在父元素中的位置
5. me-auto：占满剩余空间
6. stretched-link：所在区域可点击

## HTML标签和属性

1. <pre：Preformatted 
2. aria-current：增强accessibility ，https://www.aditus.io/aria/aria-current/

## CSS常用命名

1. 头：header
2. 内容：content/container
3. 尾：footer
4. 导航：nav
5. 侧栏：sidebar
6. 栏目：column
7. 页面外围控制整体布局宽度：wrapper
8. 左右中：left right center
9. 登录条：loginbar
10. 标志：logo
11. 广告：banner
12. 页面主体：main
13. 热点：hot
14. 新闻：news
15. 下载：download
16. 子导航：subnav
17. 菜单：menu
18. 子菜单：submenu
19. 搜索：search
20. 友情链接：friendlink
21. 页脚：footer
22. 版权：copyright
23. 滚动：scroll
24. 内容：content
25. 标签页：tab
26. 文章列表：list
27. 提示信息：msg
28. 小技巧：tips
29. 栏目标题：title
30. 加入：joinus
31. 指南：guide
32. 服务：service
33. 注册：register
34. 状态：status
35. 透明：transparent TRN

## Icon Font制作

- 获取合格的svg文件，比如获取Apereo Logo Vector https://logowik.com/apereo-vector-logo-4771/download-options.html
- 访问 https://icomoon.io/app/#/select
- Import Icons
- Generate Font
- Download 压缩包
- 解压，参考demo.html使用

## Reference

**Auto-Growing Inputs & Textareas**

https://css-tricks.com/auto-growing-inputs-textareas/

**How to convert form data to JSON with jQuery**

https://technotrampoline.com/articles/how-to-convert-form-data-to-json-with-jquery/

**How to validity form with js**

https://html.spec.whatwg.org/multipage/forms.html#dom-form-checkvalidity