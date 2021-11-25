package test;

import org.apache.commons.codec.net.URLCodec;

/**
 * @author: RyuZUSUNC
 * @create: 2021-05-14 11:51
 **/

public class test {
    public static void main(String[] args) throws Exception {
        URLCodec urlCodec = new URLCodec();
        System.out.println(urlCodec.encode("https://cloud.baidu.com/campaign/20211111/index.html?track=cp:bokeyuan|pf:pc|pp:H-bokeyuan-21shuangshiyi-shouyebanner-cpa|pu:21shuangshiyi-shouyebanner-cpa|ci:21ssy|kw:10511923"));

    }
}
