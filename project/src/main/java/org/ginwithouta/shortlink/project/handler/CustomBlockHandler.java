package org.ginwithouta.shortlink.project.handler;

import org.ginwithouta.shortlink.project.common.convention.result.Result;
import org.ginwithouta.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.ginwithouta.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

import static org.ginwithouta.shortlink.project.common.enums.ShortLinkErrorCodeEnums.SYSTEM_BUSY;

/**
 * @author Ginwithouta
 * Generate at 2024/2/16
 * 自定义流控策略
 */
public class CustomBlockHandler {
    public static Result<ShortLinkCreateRespDTO> createShortLinkBlockHandlerMethod(ShortLinkCreateReqDTO shortLinkCreateReqDTO) {
        return new Result<ShortLinkCreateRespDTO>().setCode(SYSTEM_BUSY.code()).setMessage(SYSTEM_BUSY.message());
    }
}
