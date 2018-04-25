package com.zyf.mmtipay;

import java.util.UUID;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class TestPay2 {

	void data() {
		String callbackUrl = "http://www.baidu.com";
		String notifyUrl = "http://test.rm-tech.com.cn/platform/receiveNotify.html";
		String cardType = "01";
		String bankPayType = "01";
		String tradeType = "cs.pay.submit";
		String version = "2.0";
		String channel = "gateway";
		String mchNo = "100060000000001";
		String body = "商户支付订单";
		String mchOrderNo = "PU171808635";
		String amount = "0.01";
		String key ="91be991a7491481ab43a89657a780b69";
		
		String sign = "";
		
		String all = "version="+version
					+"&amount="+amount 
					+"&mchOrderNo="+mchOrderNo
					+"&body="+body
					+"&channel="+channel
					+"&mchNo="+mchNo
					+"&tradeType="+tradeType
					+"&bankPayType="+bankPayType
					+"&cardType="+cardType
					+"&notifyUrl="+notifyUrl
					+"&callbackUrl="+callbackUrl
					+"&extra={openId=12345678&notifyUrl=http://xxxxx/xx}"
					+"&details=[{}&{}&{}]";
		String all2 = all+"&key="+key;
	    System.out.println(all2);
		Digester md5 = new Digester(DigestAlgorithm.MD5);
		sign = md5.digestHex(all2);
		
		String allll =  all+"&sign="+sign;
		System.out.println("http://test.rm-tech.com.cn/platform/cloudplatform/api/trade.html?"+allll);
		// 抵扣金额 decAmount 否 Decimal(18,2) 抵扣金额，单位为元，小数两位
		// 附加数据 description 否 String(127) 描述
		// 货币类型 currency 否 String(16) 默认人民币：CNY 详见 7.5 币种列表
		// 订单支付时间 timePaid 否 String(14) 订单支付时间，格式为 yyyyMMddHHmmss，如 2009 年
		// 月 25 日 9 点 10 分 10 秒表示为 20091225091010
		// 订单失效时间 timeExpire 否 String(14) 订单失效时间，格式同上
		// 订单清算时间 timeSettle 否 String(14) 订单清算时间，格式同上
		// 商品的标题 subject 否 String(16) 商品的标题，该参数最长为 32 个 Unicode 字符
		// 扩展字段 extra 是 Array 特定渠道发起交易时需要的额外参数以及部分渠道支
		// 付成功返回的额外参数。见附件<扩展字段>
		// 内含订单明细 details 否 Array
	}

	public static void main(String[] args) {
		TestPay2 t =  new TestPay2();
		t.data();
		
	}
	public void a(){

		String amount = "0.04";
		String body = "";
		String channel;
		String amountSettle;
		String imeExpire;
		String timeSettle;
		String subject = "";
		String currency = "CNY";
		String description = "扫描订单";
		String mchNo = "000030001000001";
		String mchOrderNo = "201609090001";
		String timePaid = "";
		String tradeType = "cs.pay.submit";
		String version = "2.0";
		String details = "";
		String extra = "";

		String url = "https://www.rm-tech.com.cn/agent-platform/cloudplatform/api/trade.html";
		String id = "17098";
		String key = "d1722c32fc25dbe1b5d43a1df815f372";

		// 版本号 version N Y 固定值3.0
		String method = "Rh.online.interface";
		String partner = "17098";// 商户ID partner N Y商户id,由易付分配
		String banktype = "ALIPAY"; // 银行类型 banktype N Y
									// 银行类型，具体参考附录1,default为跳转到易付接口进行选择支付
		String paymoney = "1"; // 金额 paymoney N Y 单位元（人民币）
		String ordernumber = UUID.randomUUID().toString().replace("-", "");// 商户订单号
																			// ordernumber
																			// N
																			// Y
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

		// System.out.println(digestHex);

		System.out.println(url + "?version=" + version + "&method=" + method + "&partner=" + partner + "&banktype="
				+ banktype + "&paymoney=" + paymoney + "&ordernumber=" + ordernumber + "&callbackurl=" + callbackurl
				+ "&hrefbackurl=" + hrefbackurl + "&attach=" + attach + "&isshow=" + isshow + "&sign=" + digestHex);

	}

}
