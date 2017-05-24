
#配置密钥：
确认本机sshd的配置文件(需要root权限)
$ vi /etc/ssh/sshd_config
找到以下内容，并去掉注释符”#“

RSAAuthentication yes
PubkeyAuthentication yes
AuthorizedKeysFile      .ssh/authorized_keys
如果修改了配置文件需要重启sshd服务 (需要root权限)
$ vi /sbin/service sshd restart

ssh-keygen -t dsa -P '' -f ~/.ssh/id_dsa
cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys
chmod 600 ~/.ssh/authorized_keys

bin/hdfs dfs -mkdir -p lib
bin/hdfs dfs -put ~/.m2/repository/org/mongodb/mongo-java-driver/2.13.0/mongo-java-driver-2.13.0.jar lib/
bin/hdfs dfs -put ~/.m2/repository/org/mongodb/mongo-hadoop/mongo-hadoop-core/1.3.2/mongo-hadoop-core-1.3.2.jar  lib/


#使配置文件生效
hadoop dfsadmin -refreshNodes
