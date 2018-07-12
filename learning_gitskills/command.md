## Git命令：
初始化仓库：git init

添加文件到仓库：git add <file>	git commit -m ""
查看日志： git log	| git reflog

回退版本：git reset --hard HEAD commit_id 上一个版本^
查看状态：git status

查看修改：git diff

丢弃修改：git checkout --<file>

删除文件：git rm -r --cached <file>

（ached不会把本地的文件夹删除）

关联远程仓库：git remote add origin git@github.com:用户名/仓库名.git

第一次推送远程仓库：git push -u origin master

推送远程仓库：git push origin master

克隆：git clone git@github.com:用户名/仓库名.git

查看分支：git branch

切换分支：git checkout <name>

创建+切换：git checkout -b <name>

合并某分支到当前分支：git merge <name>

删除分支：git branch -d <name>

储藏工作现场：git stash

查看工作现场：git stash list

工作现场恢复并删除：git stash pop

创建标签：git tag <name>

查看远程仓库的信息：git remote -v

远程推送：git push

若出现如下错误：

```ba
$ git push -u origin master
To github.com:sikaozhifu/Program.git
 ! [rejected]        master -> master (fetch first)
error: failed to push some refs to 'git@github.com:sikaozhifu/Program.git'
hint: Updates were rejected because the remote contains work that you do
hint: not have locally. This is usually caused by another repository pushing
hint: to the same ref. You may want to first integrate the remote changes
hint: (e.g., 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
```

**则主要原因是github中的README.md文件不在本地代码目录中**

可以通过如下命令进行代码合并【注：pull=fetch+merge]

git pull --rebase origin master

之后就可以看到本地代码仓库中多了README.md文件

此时再执行语句：

git push -u origin master

即可完成代码上传到github

查看用户名和邮箱地址：

$ git config user.name

$ git config user.email

修改用户名和邮箱地址：

$ git config --global user.name "username"

$ git config --global user.email "email"

重新设置本机git配置：`git config --global credential.helper store` 