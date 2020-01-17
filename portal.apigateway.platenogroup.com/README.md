### ApiGatewayPortal API网关管理入口 
> [Markdown语法](https://www.jianshu.com/p/191d1e21f7ed)
#### 网关添加

```
curl -X POST -d '{"name":"3-get_employee_salary_report","description":"fetch employee detail salary","supportMethods":"GET","destHost":"http://shr.platenogroup/xxxxx","destPath":"/hr/salary/report","proxyMethod":"POST"}'  -H 'Content-Type: application/json'  "http://localhost:7090/api/"

```

#### 网关封存

```
curl -X DELETE "http://localhost:7090/api/deactive/3-get_employee_salary_report"

```


```
{
	"name":"get_employee_salary_report",
	"description": "获取员工工资条",
	"supportMethods": "GET",
	"destHost":"http://shr.platenogroup/xxxxx",
	"destPath":"/hr/salary/report",
	"proxyMethod":"POST"
}
```


### SPEL 使用

### DDD架构参考项目 Reference
####Project: community-ddd-demo 
1. 这个项目的包目录清晰，但是有个明显的问题。 Application Service层以来了interface/ui的东西，违反了下层不能依赖上层的原则

####Project：https://github.com/ChristophKnabe/spring-ddd-bank
非常清晰和标准的关于interface/service/domain三层的案例，偏向于不使用domain service,完全通过domain来做业务逻辑全实现。 甚至宁愿在domian
做了对repository的依赖。

####DDD设计分享1：
https://www.cnblogs.com/daoqidelv/p/7589092.html

####比较好的一个分享
https://www.cnblogs.com/daoqidelv/p/7492322.html