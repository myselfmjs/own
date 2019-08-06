简介：
Apache Shiro 是java的一个安全框架， Shiro可以帮我们完成：认证、授权、加密、会话管理、Web集成、缓存
Shiro 不会去维护用户、维护权限；这些需要我们自己去设计/提供；然后通过相应的接口注入给Shiro

认证：
Subject：首先调用Subject.login(token) 进行登录；其会自动委托给Security Manager ，调用之前需要通过 SecurityUtils.SetSecurityManager() 设置​​​
SecurityManager：负责真正的身份验证逻辑；它会委托给Authenticator进身份验证；设置 SecurityMananger 可通过：defaultSecurityManager 设置​​
Authenticator：是真正的身份验证者，Shiro API 中核心的身份认证入口点，可以自定义自己的实现
ModularRealmAuthenticator 调用 AuthenticationStategy 设置多Realm 认证modular.setAuthenticationStrategy(new FirstSuccessfulStrategy());​
AuthenticationStategy
  FirstSuccessfulStrategy：只要有一个Realm验证成功即可，只返回第一个Realm身份验证成功的认证信息，其他的忽略；
  AtLeastOneSuccessfulStrategy：只要有一个Realm验证成功即可，和FirstSuccessfulStrategy不同，返回所有Realm身份验证成功的认证信息；
  AllsuccessfulStrategy：所有Realm验证成功才算成功，且返回所有Realm身份验证成功的认证信息，如果有一个失败就失败了。
Realm：身份认证规则，可自定自己的认证规则
Realm继承关系
一般继承 AuthorizingRealm (授权) 即可；其继承了AuthenticatingRealm（即身份验证），而且也间接继承了CachingRealm（带有缓存实现）。其中主要默认实现如下：org.apache.shiro.realm.text.IniRealm：[users]部分指定用户名/密码及其角色；[roles]部分指定角色即权限信息；org.apache.shiro.realm.text.PropertiesRealm： user.username=password,role1,role2指定用户名/密码及其角色；role.role1=permission1,permission2指定角色及权限信息；org.apache.shiro.realm.jdbc.JdbcRealm：通过sql查询相应的信息，​
Realm
  CachingRealm
    AuthenticatingRealm
      AuthorizingRealm
        SimpleAccountRealm
          TextConfigurationRealm
            ProoertiesRealm ：org.apache.shiro.realm.text.PropertiesRealm： user.username=password,role1,role2指定用户名/密码及其角色；role.role1=permission1,permission2指定角色及权限信息；
            IniRealm：org.apache.shiro.realm.text.IniRealm：[users]部分指定用户名/密码及其角色；[roles]部分指定角色即权限信息；
        JdbcRealm
        AbstractLdpRealm
         ActiveDirectoryRealm

授权流程：
Authorizer：是真正的授权者，
ModularRealmAuthorizer：多个Realm 循环匹配；有一个Realm匹配成功，返回true1、首先检查相应的Realm 是否实现了Authorizer2、接着调用其相应的isPermitted* / hasRole 接口进行匹配3、如果有一个Realm 匹配那么将返回true如果Realm进行授权的话，应该继承AuthorizingRealm，其流程是：1.1、如果调用hasRole*，则直接获取.getRoles()与传入的角色比较即可；1.2、首先如果调用如isPermitted(“user:view”)，首先通过PermissionResolver将权限字符串转换成相应的Permission实例，默认使用WildcardPermissionResolver，即转换为通配符的WildcardPermission；2、通过AuthorizationInfo.getObjectPermissions()得到Permission实例集合；通过AuthorizationInfo. getStringPermissions()得到字符串集合并通过PermissionResolver解析为Permission实例；然后获取用户的角色，并通过RolePermissionResolver解析角色对应的权限集合（默认没有实现，可以自己提供）；3、接着调用Permission. implies(Permission p)逐个与传入的权限比较，如果有匹配的则返回true，否则false。 ​​
PermissionResolver：
用于解析权限字符串到Permission实例@Overridepublic Collection<Permission> resolvePermissionsInRole(String roleString) { if ("role001".equals(roleString)){ return Arrays.asList((Permission)new WildcardPermission("menu:*:*")); } return null;}
RolePermissionResolver：
用于根据角色解析相应的权限集合@Overridepublic Permission resolvePermission(String s) { WildcardPermission wildcardPermission = new WildcardPermission(s); return wildcardPermission;}
WildcardPermission：
实际上 WildcardPermission 用户 Permission 和 RolePermissionResolver 的返回