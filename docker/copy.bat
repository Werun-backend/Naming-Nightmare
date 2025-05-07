@echo off

REM copy jar
echo begin copy gateway
xcopy ..\gateway\target\gateway.jar .\modules\gateway\jar /Y

echo begin copy comments
xcopy ..\comments\target\comments.jar .\modules\comments\jar /Y

echo begin copy posts
xcopy ..\posts\target\posts.jar .\modules\posts\jar /Y

echo begin copy user
xcopy ..\user\target\user.jar .\modules\user\jar /Y

