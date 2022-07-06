# Hugin: Focus on data processing

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

-------

## Hugin
   &emsp;&emsp;福金（Huginn）和雾尼（Muninn），是北欧神话奥丁（Odin）养的两只乌鸦，福金的名字有“思想”（Thought）的意思，雾尼则是“记忆”（Memory）之意。它们两个每天早上一破晓就飞到人间，到了晚上再回去跟奥丁报告。它们总是栖息于奥丁的肩头同他窃窃耳语。也因此奥丁又被称为“乌鸦神”。（从百度百科抄的）

## 前言

* **起源**
   
   &emsp;&emsp;你一定做过数据脱敏这类的处理吧，就是把“李老六”转换为“李xx”这样的操作。开发人员通常会写一小段代码，把输入字符串的后面几位用星星或叉叉替换，在每个需要脱敏的地方把数据库里面查到的对应字段放进这个函数中跑一下，然后再set回去。如果你的行为被我说中了，不妨听我介绍一个新的方法
   
   &emsp;&emsp;在我介绍这个新方法之前，我们先想象一个场景，还是刚才的功能模块，在你处理完脱敏的功能之后，你的狗产品找到了你。他说要在手机号，身份证号，以及其他几个字段上也做一下脱敏处理，并且在其他几个也具有人名，手机号身份证号这类属性的实体中，做相同的事情。你有些烦了，但还是要一遍一遍的把这些属性get出来，处理一下之后再set回去，慢慢的，你的项目中充满了这种小代码碎片，而且很难一眼得知他们都做了什么，或者用在了什么地方。那么为什么我们不把这种处理的行为和需要处理的属性绑定在一起呢？下面允许我隆重介绍一个新的解决方案，Hugin

## 什么是Hugin

* **集中的处理逻辑管理**

   将所有hugin所能进行的处理操作集中管理在规则容器中，一目了然
   
* **紧密的绑定关系**

   像下面这样，在属性上增加一个注解，就可以将属性与一个处理逻辑绑定起来
   ```
   @HuginPoint(ruleCode = "人名脱敏（注：我更建议你将它命名成英文，但是选择权在你）")
   private String name;
   ```

* **内置预设处理工具**
   
   内置方便快捷的处理功能，包括但不限于脱敏，加密等场景
   ```
   /**
     * 姓名
     * 说明：示例脱敏规则：自第 1 位起至第 字符串最大长度 位止，进行脱敏处理
     * 例：齐老二 转换为 齐**
     */
    @HuginPoint(ruleCode = HuginPresetRule.DESENSITIZE, param = {"1", "${length}"})
    private String name;
    ```

* **开放的可插拔与无限的可能**

   完全自定义的处理逻辑，能玩出什么花样完全看你的想象力

* **未来可能加入的功能**

   专用于脱敏，加密，等各种场景的独立注解，使用内置处理工具协同完成快速转换方案绑定
   
   规则分区，一键启动目标分区字段的转换逻辑
   
   其他更具特色的规则类（当前仅支持自定义转换规则）
   
   全自动脱敏函数，不需要告诉Hugin你要脱敏的是个什么东西，他会猜到的
   
   （如果你的产品经理让你把110当作电话号脱敏的话，我建议你还是打他一顿）
   
   其他脑洞大开的鬼玩意

## 快速开始

* **maven坐标**
   
   &emsp;&emsp;groupId : com.github.qishenghe
   
   &emsp;&emsp;artifactId : hugin
   
   &emsp;&emsp;version : 1.0.1

* **开发文档**

   wiki主页：[Welcome to Hugin wiki home](https://github.com/qishenghe/hugin/wiki)

## 加入我们

* **钉钉**

<div  align="center">
<img src="doc/munin_dingding_code.png" width="30%" syt height="30%" style="zoom:30%"/>
</div>

<!-- * **微信** -->

<!-- <div  align="center"> -->
<!-- <img src="doc/munin_wechat_code.png" width="30%" syt height="30%" style="zoom:30%"/> -->
<!-- </div> -->
