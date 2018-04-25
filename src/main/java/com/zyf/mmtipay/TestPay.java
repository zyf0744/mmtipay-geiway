package com.zyf.mmtipay;

import java.util.UUID;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class TestPay {
	public static void main(String[] args) {
		String url = "http://gateway.rong-he.net/online/gateway";
		String id = "17098";
		String key = "d1722c32fc25dbe1b5d43a1df815f372";

		String version = "3.0";
		// 版本号 version N Y 固定值3.0
		String method = "Rh.online.interface";
		String partner = "17098";// 商户ID partner N Y商户id,由易付分配
		String banktype = "ALIPAY"; // 银行类型 banktype N Y
										// 银行类型，具体参考附录1,default为跳转到易付接口进行选择支付
		String paymoney = "1"; // 金额 paymoney N Y 单位元（人民币）
		String ordernumber =UUID.randomUUID().toString().replace("-", "");// 商户订单号 ordernumber N Y
		// 商户系统订单号，该订单号将作为易付接口的返回数据。该值需在商户系统内唯一，易付系统暂时不检查该值是否唯一
		String callbackurl = "http://mmtipay.com";// 下行异步通知地址 callbackurl N Y
								// 下行异步通知的地址，需要以http://开头且没有任何参数
		String hrefbackurl = "http://mmtipay.com";// 下行同步通知地址 hrefbackurl Y N
								// 下行同步通知过程的返回地址(在支付完成后易付接口将会跳转到的商户系统连接地址)。
		// 注：若提交值无该参数，或者该参数值为空，则在支付完成后，易付接口将不会跳转到商户系统，用户将停留在易付接口系统提示支付成功的页面。
		String attach = "";// 备注信息 attach Y N 备注信息，下行中会原样返回。若该值包含中文，请注意编码
		String isshow = "0";// 是否显示收银台 isshow Y N
							// 该参数为支付宝扫码、微信、QQ钱包专用，默认为1，跳转到网关页面进行扫码，如设为0，则网关只返回二维码图片地址供用户自行调用
		String sign = "";
		String sign2 = "version=" + version + "&method=" + method + "&partner=" + partner + "&banktype=" + banktype
				+ "&paymoney=" + paymoney + "&ordernumber=" + ordernumber + "&callbackurl=" + callbackurl + key;
		// MD5签名 sign N N 32位小写MD5签名值，GB2312编码

		// DigestUtils.md5Hex(sign2);
		Digester md5 = new Digester(DigestAlgorithm.MD5);
		String digestHex = md5.digestHex(sign2);

		//System.out.println(digestHex);
		
		
		System.out.println(url+"?version=" + version + "&method=" + method + "&partner=" + partner + "&banktype=" + banktype
				+ "&paymoney=" + paymoney + "&ordernumber=" + ordernumber + "&callbackurl=" + callbackurl+"&hrefbackurl="+hrefbackurl+"&attach="+attach+"&isshow="+isshow+"&sign="+digestHex);
		 

	}

}
