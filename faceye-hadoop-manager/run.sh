###############################################
#名称:项目打包运行脚本
#作者:@haipenge
#日期:2015.3.1
#使用:
#1. ./run.sh
################################################
mvn clean compile package -D maven.test.skip=true
cp -r target/faceye*.jar /tools/hadoop/hadoop-2.6.0/share/faceye
