# 网关设计

1. 产品设计参考: 聚合数据[https://www.juhe.cn/]参照聚合数据的标准设计
2. 设计方法: DDD项目架构和包名，代码放置规则都参考 https://github.com/citerus/dddsample-core
3. 领域模型参考 Kong的领域模型设计，只是Kong的Service名字修改Api


## 一期网关功能列表

1. API标准制定（https协议， 精确到HTTP Method）
2. 统一错误码标准制定(网关错误码，不含具体业务错误码)
3. API服务上线（服务名称，服务name xxx.xxx.xxx.xxx, 服务转发等)
4. 应用管理（应用注册，绑定API服务，颁发app key和app secret
5. 应用调用服务基本报表（按时段/应用输出API调用次数)
6. 调用记录查询， 提供直接的 API调用输入输出查询


### 应用管理

1. 新建应用
2. 应用绑定API
