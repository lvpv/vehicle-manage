## 生成秘钥的命令
```shell script
    keytool -genkeypair -alias vehicle-manage -keyalg RSA -keypass Csj5840504 -keystore vehicle-manage.jks -storepass Csj5840504
```
## 生成秘钥的相关信息
```text
-alias 别名：vehicle-manage
-keyalg 使用的加密算法：RSA
-keypass 秘钥的访问密码： Csj5840504
-keystore 生成的秘钥文件名：vehicle-manage.jks
-storepass 秘钥库的访问密码：Csj5840504 
```

## 相关命令
> 查询证书信息
```shell script
    keytool -list -keystore vehicle-manage.jks
```
> 删除别名
```shell script
    keytool -delete -alias vehicle-manage -keystore vehicle-manage.jks
```
## 导出公钥
> 安装OpenSSL软件
>将软件安装目录下的bin目录添加到环境变量path
>执行命令
 ```shell script
    keytool -list -rfc --keystore vehicle-manage.jks | openssl x509 -inform pem -pubkey
```
> 输入密码：Csj5840504

## 项目公钥
```text
-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlMFmbAcnf+TtG+aNWgAfqdPwCVvYOthd60DqahsHbQuILlMAqQ6o0TJGBbNW8sVLuTgDGtkiqZ2FtGxu0dJKUQ/IAyQTsXduZw8UXX0hDLfH8DIFrK9G0zJTmlUaTY2/T5mEemvyDqGxdhgqImfgPpxWtLpCSQuX9GCN4N1urtBYRy11z0L99tlEirNzDJDuzX2mFtL2y3WyicobprYyDV/EI8Ca9+Hnq3nF76JdkkE3/I+/afQX59gvZqB4uZ+CCJNEJM3TN3j5uRTBmDEV9OH7dtmyeyXt8pmfBikOg+I+T7zmhH+/E/tqH5+FUDNLXvGzTSM5J1hbgKCFVlG4RwIDAQAB-----END PUBLIC KEY-----
```