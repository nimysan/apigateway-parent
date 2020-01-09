### 添加RabbitMQ用户

rabbitmqctl.bat add_user admin 123456 

rabbitmqctl set_permissions -p / admin ".*" ".*" ".*"  //添加权限

rabbitmqctl set_user_tags admin administrator