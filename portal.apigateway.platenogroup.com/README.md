### ApiGatewayPortal API网关管理入口 

> [Markdown语法](https://www.jianshu.com/p/191d1e21f7ed)

> [参考Kong管理服务方式](https://my.oschina.net/u/4050414/blog/3022358/print)



１.　添加API

２.　给API设置路由

#### 例子

比特币： https://api.coindesk.com/v1/bpi/currentprice/CNY.json

API访问路径

http://ourhost/api/getcurrentpriceforcnybitcoin need return 

```
{ 
	"code":"SA0000",
	"body":{ 
		"time":{ 
				"updated":"Jan 19, 2020 08:13:00 UTC",
				"updatedISO":"2020-01-19T08:13:00+00:00",
				"updateduk":"Jan 19, 2020 at 08:13 GMT"
				},
		"disclaimer":"This data was produced from the CoinDesk Bitcoin Price Index (USD & CNY respectively).",
		"bpi":{ 
				"USD":{ 
				"code":"USD",
				"rate":"9,088.0250",
				"description":"United States Dollar",
				"rate_float":9088.025
				},
		"CNY":{ 
				"code":"CNY",
				"rate":"40,202.5000",
				"description":"Chinese Yuan",
				"rate_float":40202.5
			}
		}
	}
}
```
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