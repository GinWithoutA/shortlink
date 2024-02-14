package org.ginwithouta.shortlink.project.common.enums;

import org.ginwithouta.shortlink.project.common.convention.errorcode.IErrorCode;

/**
 * @Package : org.ginwithouta.shortlink.admin.common.enums
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周六
 * @Desc : 短链接错误码枚举类
 */
public enum ShortLinkErrorCodeEnums implements IErrorCode {

    SHORT_LINK_OPERATION_FAIL("B000300", "【短链接】操作失败"),
    SHORT_LINK_CREATE_FAIL("B000301", "【短链接】创建短链接失败"),
    SHORT_LINK_CREATE_SUFFIX_OVER_FLOW("B000302", "【短链接】创建短链接后缀失败：重复尝试次数太多"),
    SHORT_LINK_BLOOM_FAIL("B000303", "【短链接】创建短链接失败：布隆过滤器误判，导致发生重复添加错误"),
    SHORT_LINK_NOT_EXIST("B000304", "【短链接】操作短链接失败，该短链接不存在"),
    SHORT_LINK_STATS_MQ_NOT_ACCOMPLISH("B000401", "【短链接统计消息队列】消息未完成，请稍后重试"),
    SHORT_LINK_GID_UPDATE_NOT_AVALIABLE("B000402", "【短链接分组更新】更新失败，当前短链接正在被访问"),
    SHORT_LINK_BLACK_LIST("B000501", "【短链接异常】非法网址，不得跳转");

    private final String code;

    private final String message;

    ShortLinkErrorCodeEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
