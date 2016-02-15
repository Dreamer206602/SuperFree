package com.zchx.lb.superfree.app;

/**
 * Created on 2016/1/11 15:15
 * Created by Author boobooL
 * 邮箱：boobooMX@163.com
 */

/**
 * 必须的URL地址
 */
public class AppConstants {

    public final class RequestPath {
        public static final String BASE_URL = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?";

        //1.忘记密码
        //url: http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=forgetPassword
        public static final String FORGETPASSWORD = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=forgetPassword";

        // 2.发送验证码
        //url: http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=getRegValiCode
        public static final String GETREGVALICODE = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=getRegValiCode";

        //3.验证验证码
        // url: http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=confirmValicode
        public static final String CONFIRMVALICODE = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=confirmValicode";


        //4.验证绑卡信息
        //url:http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=checkBank
        public static final String CHECKBANK = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=checkBank";


        //5.并且发送验证码
        //url:http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=getPayValiCode
        public static final String GETPAYVALICODE = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=getPayValiCode";

        //6.确认支付
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=subPay
        public static final String SUBPAY = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=subPay";


        //9．修改登录密码
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=updatePassword
        public static final String UPDATELOGINPASSWORD = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=updatePassword";

        //10．修改支付密码
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=updatePayPassword
        public static final String UPDATEPAYPASSWORD = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=updatePayPassword";

        //12．显示银行
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=selectAllBank
        public static final String ALLBANKS = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=selectAllBank";

        //13.查看基本信息
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=selectInfo
        public static final String SELECTINFO = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=selectInfo";

        //14.计算器
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do? method=calculator
        //truepay=3000&productId=16  暂时不用

        //15.登录
        // http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=login
        public static final String LOGIN = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=login";

        //16.登录注销
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=loginout
        public static final String LOGOUT = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=loginout";

        // 17.忘记支付密码
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=forgetPayPassword
        public static final String FORGETPAYPASSWORD = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=forgetPayPassword";

        //18.注册第一步  mobile=15298371359&password=1234567&rePassword=1234567&recPrsonMobile=15951762936
        public static final String REGISTER = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=register";


        //19.注册完成
        // http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=finishReg
        public static final String FINISHREGISTER = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=finishReg";

        //20. 查询当前用户的投资记录
        // http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=selectInvestmentdetails
        public static final String SELECTINVESTMENTDETAILS = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=selectInvestmentdetails";

        //21. 返回余额
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=returnBalance
        public static final String RETURNBALANCE = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=returnBalance";
        //22. 返回总资产
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=returnAssets
        public static final String RETURNASSETS = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=returnAssets";
        //23. 返回首页轮播图
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=pictureCpgoods
        public static final String PICTURECPGOODS = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=pictureCpgoods";

        //24. 返回积分图
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=pictureInter
        public static final String PICTUREINTER = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=pictureInter";

        //25. 返回积分产品图片
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=interProduct
        public static final String INTERPRODUCT = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=interProduct";
        //26. 显示所有的项目
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=cpgoodsAll
        public static final String CPGOODSALL = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=cpgoodsAll";


        //27. 得到一个商品的详情
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=cpgoodsdetail
        public static final String CPGOODSDETAIL = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=cpgoodsdetail";


        // 28. 投资
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=investProduct
        //method=investProduct&mobile=15298371359&pro_id=28&truepay=11&payword=123456

        public static final String INVESTPRODUCT = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=investProduct";


        // 29. 立即兑换
        // http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=insertAddress
        public static final String INSERTADDRESS = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=insertAddress";


        //30. 提现记录
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=doactionAll
        public static final String DOACTIONALL = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=doactionAll";

        //31. 提现钱
        // http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=doactionMoney
        public static final String DOACTIONMONEY = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=doactionMoney";


        //32.交易详细         充值记录
        // http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=doactionSuccess
        public static final String DOACTIONSUCCESS = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=doactionSuccess";


        //33. 获取记录   mobile=15298371359
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method= recordAdd
        public static final String RECORDALL = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=recordAdd";

        //34. 使用记录
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=recordLess
        public static final String RECORDLESS = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=recordLess";

        //35. 返回积分轮播图
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=pictureInterAll
        public static final String PICTUREINTERALL = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=pictureInterAll";

        //36. 得到积分
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=getinter
        public static final String GETINTER = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=getinter";

        //37. 首页最新项目
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=selectNewProduct
        public static final String SELECTNEWPRODUCT = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=selectNewProduct";


        //38. 确认支付（兑换产品时用的确认支付）
        //http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=pay

        public static final String CONFIRMPAY = "http://test.chaoyouli.com:8080/IOSAgent/IOSAgent.do?method=pay";


    }

    public final class ParamsMethod {
        public static final String PICTURECPGOODS = "pictureCpgoods";
    }

    public final class ParamDefaultValue {
        public static final String METHOD = "method=";

        public static final String MOBILE = "mobile";
        public static final String NEWPASS = "newPass";//新密码
        public static final String RENEWPASS = "reNewPass";//确认新密码


        public static final String PASSWORD = "password";
        public static final String REPASSWORD = "rePassword";
        public static final String RECPERSONMOBILE = "recPrsonMobile";

        public static final String OLDPASSWORD = "oldPassord";//旧密码
        public static final String NEWPASSWORD = "newPassword";//新密码
        public static final String newRePassword = "newRePassword";//确认新密码

        public static final String OLDPAYPASSWORD = "oldPayPassord";//旧支付密码
        public static final String NEWPAYPASSWORD = "newPayPassword";//新的支付密码
        public static final String NEWREPAYPASSWORD = "newRePayPassword";//确认新的支付密码


        public static final String NEWPAYPASS = "newPayPass";//新的支付密码
        public static final String RENEWPAYPASS = "reNewPayPass";//确认新的支付密码


        //username=&idcard=&cardno=&bindMobile=&mobile=&payPass=&bankname=
        public static final String USERNAME = "username";//用户名
        public static final String IDCARD = "idcard";//身份证号
        public static final String CARDNO = "cardno";//银行卡号
        public static final String BINDMOBILE = "bindMobile";//绑定的手机号
        // public static final String MOBILE="mobile";
        public static final String PAYPASS = "payPass";//支付密码
        public static final String BANKNAME = "bankname";//银行名

        public static final String PRICE = "price";

        //orderId=LC1EQhBg0Oqtd0DH&valiCode=663972&price=0.01
        public static final String ORDERID = "orderId";
        public static final String VALICODE = "valicode";
        public static final String VALICODE2 = "valiCode";

        //cnumber=6222620140007614405&bankname=交通银行&balance=2000&mcount=1000&payword=123456

        public static final String CNUMBER = "cnumber";//绑定的银行卡号
        public static final String BALANCE = "balance";//账户余额
        public static final String MCOUNT = "mcount";//用户体现的金额
        public static final String PAYWORD = "payword";//用户的支付密码

        //mobile=15298371359&proName=Apple iPhone 6s 64G 玫瑰金色手机&intergration=750&price=750&balance=9000&userIntergration=9000&address=1234567&name=问问&userphone=123456&payword=123456&pro_id=17
        public static final String PRONAME = "proName";
        public static final String INTERGRATION = "intergration";//积分
        //public static final String PRICE="price";
        //public static final String BALANCE="balance";//账户的余额
        // name=问问&userphone=123456&payword=123456&pro_id=17
        public static final String USERINTERGRATION = "userIntergration";
        public static final String ADDRESS = "address";
        public static final String NAME = "name";
        public static final String USERPHONE = "userphone";
        //public static final String PAYWORD="payword";
        public static final String PRO_ID = "pro_id";//产品的编号
        public static final String PRO_INTEGRAL = "pro_integral";//产品的积分
        public static final String PRO_STORE = "pro_store";//产品的库存
        public static final String PRO_PRICE = "pro_price";//产品的价格
        public static final String PRO_CONTENT = "pro_content";//产品的介绍


        // truepay=11&payword=123456
        public static final String TRUEPAY = "truepay";
        public static final String ISLOGIN = "isLogin";
        public static final String ISBINDSUCCESS="isBindSuccess";
        public static final String ID = "id";
        public static final String PERCENTAGE = "percentage";
        public static final String BALANCECOUNT = "balanceCount";//产品的剩余量
        public static final String GOOD_PRICE = "goods_price";//产品的起投价格

        public static final String AVAILABLEBALANCE = "availableBalance";//总资产
        public static final String MONEY = "money";//项目剩余的金额


        public static final String ENTERPRISE_INFO = "enterprise_info";
        public static final String GOODS_DESCRIBE = "goods_describe";
        public static final String GOODS_TITLE="goods_title";//产品名字


        public static final String PRO_RATE = "good_rate";//产品的汇率
        public static final String PRO_TERM = "good_term";//产品的期限

        public static final String ATY_FRG="aty_frg";//Activity和Fragment的传值

    }


}
