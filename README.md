# 娄底字牌游戏规则
1、玩家可以三人或四人
2、每抓一次牌，类似于麻将，有人胡牌为本次结束，根据胡牌者的牌的特色计算本次胡牌的胡子（积分），胡牌可以胡底下抓出来的牌，或其他玩家放炮，放炮者本次积分为负数
3、玩家每次胡牌的积分累计，有一人上100，就开始结账
4、玩家先行商定每一胡子多少钱
5、本计算器不考虑玩的过程，只计算结账过程
# 结算过程
为了叙述方便，四个玩家的为ABCD，现在结账，胡子和打鸟情况分别如下
![image](https://github.com/TIL-MICE/Calculator/blob/master/screenshot/5.png)
## 第一步：先计算拖鸟的输赢：
### 1、计算A的：
用A分别与其他BCD比较，如果A-B>0，则A从B这里赢钱，数量为tn1+tn2
A与C，A与D也是这么计算，然后三个值相加为A拖鸟的输赢
### 2、BCD的计算也是和上面一样
注意：算出来这四个数相加应该为零
## 第二步：计算活鸟的输赢：
### 1、为了最后算出来不要有小数，先将玩家的胡子四舍五入
注意负数的四舍五入：例如：C：四舍五入后为-30
上例四舍五入后ABCD的胡子分别为：120，40，-30，60
### 2、计算A与其他玩家在活鸟上的输赢
2.1、计算A与B在活鸟上的输赢
2.1.1、计算A与B的胡子差值（四舍五入后的）
例如上例，A与B，120-40=80
2.1.2、计算A与B在活鸟上的输赢
80*0.5*（hn1+1）*（hn2+1）
2.2、计算A与CD玩家在活鸟上的输赢，方法同上：
2.3、将A与BCD在活鸟上的输赢值相加，得到A在活鸟上的输赢
### 3、其他玩家的算法也和A类似
注意：这四个值相加也为零
## 第三步：计算最终输赢
每个玩家的活鸟输赢加上拖鸟输赢

## 一些截图
![image](https://github.com/TIL-MICE/Calculator/blob/master/screenshot/1.png)
![image](https://github.com/TIL-MICE/Calculator/blob/master/screenshot/2.png)
![image](https://github.com/TIL-MICE/Calculator/blob/master/screenshot/3.png)
![image](https://github.com/TIL-MICE/Calculator/blob/master/screenshot/4.png)



