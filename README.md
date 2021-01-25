## 一、安装 vjudge

手动执行以下shell命令：

~~~bash
#!/bin/bash
apt-get update
apt-get -y install tomcat8 mysql-server
wget https://github.com/zhblue/vjudge/raw/master/vjudge.war
cp vjudge.war /var/lib/tomcat8/webapps/
## 等待tomcat自动解压缩得到vjudge目录
echo "waiting for tomcat8 deploying vjudge.war ... 8s count down"
sleep 8
## 缓存各种数据
DBUSER=`cat /etc/mysql/debian.cnf | grep user|awk '{print $3}'|head -1`
DBPASS=`cat /etc/mysql/debian.cnf | grep password|awk '{print $3}'|head -1`
SQL=`find /var/lib/tomcat8/webapps/vjudge -name "*.sql"`
cat $SQL | mysql -u$DBUSER -p$DBPASS  #导入数据库
cd /var/lib/tomcat8/webapps/vjudge/WEB-INF/classes
## 将缓存的数据写入配置文件
sed -i "s/jdbc.username=root/jdbc.username=$DBUSER/g" config.properties
sed -i "s/jdbc.password=root/jdbc.password=$DBPASS/g" config.properties
sed -i "s:remote_accounts.json:/var/lib/tomcat8/webapps/vjudge/WEB-INF/classes/remote_accounts.json:g" config.properties
sed -i "s:http_client.json:/var/lib/tomcat8/webapps/vjudge/WEB-INF/classes/http_client.json:g" config.properties
sed -i "s:../logs/log4j.log:/var/lib/tomcat8/logs/log4j.log:g" log4j.properties  #通过log监控平台状态

service tomcat8 restart
~~~

## 二、配置 vjudge

安装后几个重要配置文件的位置如下：

~~~
/var/lib/tomcat8/webapps/vjudge/WEB-INF/classes/config.properties
/var/lib/tomcat8/webapps/vjudge/WEB-INF/classes/remote_accounts.json  #设置各OJ网站的账号和密码。
~~~

## 三、修改 tomcat 默认根目录

编辑Tomcat配置文件var/lib/tomcat8/conf/server.xml，在host标签之间加入如下标签：

~~~
<Host name="localhost"  appBase=""
            unpackWARs="true" autoDeploy="true">
 
	<Context path="" docBase="/var/lib/tomcat8/webapps/vjudge" debug="0" reloadable="true" />
	<Context path="/Gossip" docBase="/var/lib/tomcat8/webapps/Gossip" debug="0" reloadable="true" />
</Host>
~~~

注意：此处需要把appBase设置为空""，不然会引发spring定时任务执行两次的bug

<br>

**参考链接**

[1] [https://github.com/zhblue/vjudge](https://github.com/zhblue/vjudge)  
[2] [https://blog.csdn.net/lzyws739307453/article/details/103092747](https://blog.csdn.net/lzyws739307453/article/details/103092747)  
[3] [https://www.iteye.com/blog/nkliuliu-816335](https://www.iteye.com/blog/nkliuliu-816335)