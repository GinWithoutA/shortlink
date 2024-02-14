package org.ginwithouta.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ginwithouta.shortlink.project.dao.entity.*;
import org.ginwithouta.shortlink.project.dao.mapper.*;
import org.ginwithouta.shortlink.project.dto.biz.UvTypeGroupMapperDTO;
import org.ginwithouta.shortlink.project.dto.biz.UvTypeMapperDTO;
import org.ginwithouta.shortlink.project.dto.req.*;
import org.ginwithouta.shortlink.project.dto.resp.*;
import org.ginwithouta.shortlink.project.service.ShortLinkStatsService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ginwithouta
 * Generate at 2023/12/2
 * 短链接监控接口实现层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShortLinkStatsServiceImpl extends ServiceImpl<ShortLinkStatsMapper, ShortLinkStatsDO> implements ShortLinkStatsService {

    private final ShortLinkLocaleStatsMapper statsLocaleMapper;
    private final ShortLinkAccessLogsMapper accessLogsMapper;
    private final ShortLinkBrowserStatsMapper statsBrowserMapper;
    private final ShortLinkOsStatsMapper statsOsMapper;
    private final ShortLinkDeviceStatsMapper statsDeviceMapper;
    private final ShortLinkNetworkStatsMapper statsNetworkMapper;

    @Override
    public ShortLinkStatsRespDTO oneShortLinkStatistics(ShortLinkStatsReqDTO requestParam) {
        List<ShortLinkStatsDO> statsListByShortLink = baseMapper.listStatisticsByShortLink(requestParam);
        if (CollUtil.isEmpty(statsListByShortLink)) {
            // 如果当前短链接没有记录，直接返回 NULL 就行，不用管
            return null;
        }
        /*
         * 获取短链接现有 PV UV UIP
         */
        ShortLinkStatsDO pvUvUipByShortLink = accessLogsMapper.getPvUvUipByShortLink(requestParam);
        /*
         * 短链接监控之基础数据（PV，UV，UIP）
         */
        List<ShortLinkStatsDailyRespDTO> dailyStats = new ArrayList<>();
        // 通过 requestParam 中传入的 startDate 以及 endDate 生成中间每一天的日期，例如 [2023/11/1, 2023/11/2, 2023/11/3, ...]
        List<String> eachDates = DateUtil.rangeToList(DateUtil.parse(requestParam.getStartDate()), DateUtil.parse(requestParam.getEndDate()), DateField.DAY_OF_MONTH)
                .stream()
                .map(DateUtil::formatDate)
                .toList();
        // 遍历每一天，看短链接监控是否有当天的监控信息，如果有则登记，如果没有，则赋值一个 0
        eachDates.forEach(eachDay -> statsListByShortLink.stream()
            .filter(stats -> Objects.equals(eachDay, DateUtil.formatDate(stats.getDate())))
            .findFirst()
            .ifPresentOrElse(stats -> {
                ShortLinkStatsDailyRespDTO dailyRespDTO = ShortLinkStatsDailyRespDTO.builder()
                        .date(eachDay)
                        .pv(stats.getPv())
                        .uip(stats.getUip())
                        .uv(stats.getUv())
                        .build();
                dailyStats.add(dailyRespDTO);
            }, () -> {
                ShortLinkStatsDailyRespDTO dailyRespDTO = ShortLinkStatsDailyRespDTO.builder()
                        .date(eachDay)
                        .pv(0)
                        .uip(0)
                        .uv(0)
                        .build();
                dailyStats.add(dailyRespDTO);
            }));
        /*
         * 短链接监控之地区（仅国内）
         */
        List<ShortLinkStatsLocaleCNRespDTO> localeCNStats = new ArrayList<>();
        List<ShortLinkLocaleStatsDO> listLocaleByShortLink = statsLocaleMapper.listLocaleByShortLink(requestParam);
        int localeCNSum = listLocaleByShortLink.stream()
                .mapToInt(ShortLinkLocaleStatsDO::getCnt)
                .sum();
        listLocaleByShortLink.forEach(each -> {
            double ratio = (double) each.getCnt() / localeCNSum;
            double actualRatio = Math.round(ratio * 100.0) / 100.0;
            ShortLinkStatsLocaleCNRespDTO localeCNRespDTO = ShortLinkStatsLocaleCNRespDTO.builder()
                    .cnt(each.getCnt())
                    .locale(each.getProvince())
                    .ratio(actualRatio)
                    .build();
            localeCNStats.add(localeCNRespDTO);
        });
        /*
         * 短链接监控之 24 小时访问分布情况
         */
        List<Integer> hoursStats = new ArrayList<>(24);
        List<ShortLinkStatsDO> listHoursByShortLink = baseMapper.listHoursStatisticsByShortLink(requestParam);
        for (int i = 0; i < 24; ++i) {
            AtomicInteger hour = new AtomicInteger(i);
            int hourCnt = listHoursByShortLink.stream()
                    .filter(each -> Objects.equals(each.getHour(), hour.get()))
                    .findFirst()
                    .map(ShortLinkStatsDO::getPv)
                    .orElse(0);
            hoursStats.add(hourCnt);
        }
        /*
         * 短链接监控之高频访问 IP
         */
        List<ShortLinkStatsTopIpRespDTO> topIpStats = new ArrayList<>();
        List<HashMap<String, Object>> listTopIpByShortLink = accessLogsMapper.listTopIpByShortLink(requestParam);
        listTopIpByShortLink.forEach(each -> {
            ShortLinkStatsTopIpRespDTO statsTopIpRespDTO = ShortLinkStatsTopIpRespDTO.builder()
                    .ip(each.get("ip").toString())
                    .cnt(Integer.parseInt(each.get("count").toString()))
                    .build();
            topIpStats.add(statsTopIpRespDTO);
        });
        /*
         * 短链接监控之一周内的访问情况
         */
        List<Integer> weekdayStats = new ArrayList<>();
        List<ShortLinkStatsDO> listWeekStatsByShortLink = baseMapper.listWeekStatsByShortLink(requestParam);
        for (int i = 1; i < 8; ++i) {
            AtomicInteger weekday = new AtomicInteger(i);
            int weekdayCnt = listWeekStatsByShortLink.stream()
                    .filter(each -> Objects.equals(each.getWeekday(), weekday.get()))
                    .findFirst()
                    .map(ShortLinkStatsDO::getPv)
                    .orElse(0);
            weekdayStats.add(weekdayCnt);
        }
        /*
         * 短链接监控之浏览器
         */
        List<ShortLinkStatsBrowserRespDTO> browserStats = new ArrayList<>();
        List<HashMap<String, Object>> listBrowserStatsByShortLink = statsBrowserMapper.listBrowserStatsByShortLink(requestParam);
        int browserSum = listBrowserStatsByShortLink.stream()
                .mapToInt(each -> Integer.parseInt(each.get("count").toString()))
                .sum();
        listBrowserStatsByShortLink.forEach(each -> {
            double ratio = (double) Integer.parseInt(each.get("count").toString()) / browserSum;
            double actualRatio = Math.round(ratio * 100.0) / 100.0;
            ShortLinkStatsBrowserRespDTO shortLinkStatsBrowserRespDTO = ShortLinkStatsBrowserRespDTO.builder()
                    .cnt(Integer.parseInt(each.get("count").toString()))
                    .browser(each.get("browser").toString())
                    .ratio(actualRatio)
                    .build();
            browserStats.add(shortLinkStatsBrowserRespDTO);
        });
        /*
         * 短链接监控之操作系统
         */
        List<ShortLinkStatsOsRespDTO> osStats = new ArrayList<>();
        List<HashMap<String, Object>> listOsStatsByShortLink = statsOsMapper.listOsStatsByShortLink(requestParam);
        int osSum = listOsStatsByShortLink.stream()
                .mapToInt(each -> Integer.parseInt(each.get("count").toString()))
                .sum();
        listOsStatsByShortLink.forEach(each -> {
            double ratio = (double) Integer.parseInt(each.get("count").toString()) / osSum;
            double actualRatio = Math.round(ratio * 100.0) / 100.0;
            ShortLinkStatsOsRespDTO osRespDTO = ShortLinkStatsOsRespDTO.builder()
                    .cnt(Integer.parseInt(each.get("count").toString()))
                    .os(each.get("os").toString())
                    .ratio(actualRatio)
                    .build();
            osStats.add(osRespDTO);
        });
        /*
         * 短链接监控之访客类型（指定时间内的访客：新房客，老访客）
         */
        List<ShortLinkStatsUvRespDTO> uvTypeStats = new ArrayList<>();
        HashMap<String, Object> findUvTypeByShortLink = accessLogsMapper.findUvTypeCntByShortLink(requestParam);
        int oldUserCnt = Integer.parseInt(
                Optional.ofNullable(findUvTypeByShortLink)
                        .map(each -> each.get("oldUserCnt"))
                        .map(Object::toString)
                        .orElse("0")
        );
        int newUserCnt = Integer.parseInt(
                Optional.ofNullable(findUvTypeByShortLink)
                        .map(each -> each.get("newUserCnt"))
                        .map(Object::toString)
                        .orElse("0")
        );
        int uvSum = oldUserCnt + newUserCnt;
        double oldRatio = (double) oldUserCnt / uvSum;
        double actualOldRatio = Math.round(oldRatio * 100.0) / 100.0;
        double newRatio = (double) newUserCnt / uvSum;
        double actualNewRatio = Math.round(newRatio * 100.0) / 100.0;
        ShortLinkStatsUvRespDTO newUvRespDTO = ShortLinkStatsUvRespDTO.builder()
                .uvType("newUser")
                .cnt(newUserCnt)
                .ratio(actualNewRatio)
                .build();
        uvTypeStats.add(newUvRespDTO);
        ShortLinkStatsUvRespDTO oldUvRespDTO = ShortLinkStatsUvRespDTO.builder()
                .uvType("oldUser")
                .cnt(oldUserCnt)
                .ratio(actualOldRatio)
                .build();
        uvTypeStats.add(oldUvRespDTO);
        /*
         * 短链接监控之访问设备
         */
        List<ShortLinkStatsDeviceRespDTO> deviceStats = new ArrayList<>();
        List<ShortLinkDeviceStatsDO> listDeviceStatsByShortLink = statsDeviceMapper.listDeviceStatsByShortLink(requestParam);
        int deviceSum = listDeviceStatsByShortLink.stream()
                .mapToInt(ShortLinkDeviceStatsDO::getCnt)
                .sum();
        listDeviceStatsByShortLink.forEach(each -> {
            double ratio = (double) each.getCnt() / deviceSum;
            double actualRatio = Math.round(ratio * 100.0) / 100.0;
            ShortLinkStatsDeviceRespDTO deviceRespDTO = ShortLinkStatsDeviceRespDTO.builder()
                    .cnt(each.getCnt())
                    .device(each.getDevice())
                    .ratio(actualRatio)
                    .build();
            deviceStats.add(deviceRespDTO);
        });
        /*
         * 短链接监控之网络
         */
        List<ShortLinkStatsNetworkRespDTO> networkStats = new ArrayList<>();
        List<ShortLinkNetworkStatsDO> listNetworkStatsByShortLink = statsNetworkMapper.listNetworkStatsByShortLink(requestParam);
        int networkSum = listNetworkStatsByShortLink.stream()
                .mapToInt(ShortLinkNetworkStatsDO::getCnt)
                .sum();
        listNetworkStatsByShortLink.forEach(each -> {
            double ratio = (double) each.getCnt() / networkSum;
            double actualRatio = Math.round(ratio * 100.0) / 100.0;
            ShortLinkStatsNetworkRespDTO networkRespDTO = ShortLinkStatsNetworkRespDTO.builder()
                    .cnt(each.getCnt())
                    .network(each.getNetwork())
                    .ratio(actualRatio)
                    .build();
            networkStats.add(networkRespDTO);
        });
        return ShortLinkStatsRespDTO.builder()
                .pv(pvUvUipByShortLink.getPv())
                .uv(pvUvUipByShortLink.getUv())
                .uip(pvUvUipByShortLink.getUip())
                .daily(dailyStats)
                .localeCnStats(localeCNStats)
                .hourStats(hoursStats)
                .topIpStats(topIpStats)
                .weekdayStats(weekdayStats)
                .browserStats(browserStats)
                .osStats(osStats)
                .uvTypeStats(uvTypeStats)
                .deviceStats(deviceStats)
                .networkStats(networkStats)
                .build();
    }

    @Override
    public ShortLinkGroupStatsRespDTO groupShortLinkStatistics(ShortLinkGroupStatsReqDTO requestParam) {
        List<ShortLinkStatsDO> statsListByGroup = baseMapper.listStatsByGroup(requestParam);
        if (CollUtil.isEmpty(statsListByGroup)) {
            // 如果当前分组没有记录，直接返回 NULL 就行，不用管
            return null;
        }
        /*
         * 获取分组中所有短链接现有 PV UV UIP
         */
        ShortLinkStatsDO pvUvUipByGroup = accessLogsMapper.getPvUvUipByGroup(requestParam);
        /*
         * 短链接分组监控之基础数据（PV，UV，UIP）
         */
        List<ShortLinkStatsDailyRespDTO> dailyStats = new ArrayList<>();
        // 通过 requestParam 中传入的 startDate 以及 endDate 生成中间每一天的日期，例如 [2023/11/1, 2023/11/2, 2023/11/3, ...]
        List<String> eachDates = DateUtil.rangeToList(DateUtil.parse(requestParam.getStartDate()), DateUtil.parse(requestParam.getEndDate()), DateField.DAY_OF_MONTH)
                .stream()
                .map(DateUtil::formatDate)
                .toList();
        // 遍历每一天，看短链接监控是否有当天的监控信息，如果有则登记，如果没有，则赋值一个 0
        eachDates.forEach(eachDay -> statsListByGroup.stream()
                .filter(stats -> Objects.equals(eachDay, DateUtil.formatDate(stats.getDate())))
                .findFirst()
                .ifPresentOrElse(stats -> {
                    ShortLinkStatsDailyRespDTO dailyRespDTO = ShortLinkStatsDailyRespDTO.builder()
                            .date(eachDay)
                            .pv(stats.getPv())
                            .uip(stats.getUip())
                            .uv(stats.getUv())
                            .build();
                    dailyStats.add(dailyRespDTO);
                }, () -> {
                    ShortLinkStatsDailyRespDTO dailyRespDTO = ShortLinkStatsDailyRespDTO.builder()
                            .date(eachDay)
                            .pv(0)
                            .uip(0)
                            .uv(0)
                            .build();
                    dailyStats.add(dailyRespDTO);
                }));
        /*
         * 短链接分组监控之地区（仅国内）
         */
        List<ShortLinkStatsLocaleCNRespDTO> localeCNStats = new ArrayList<>();
        List<ShortLinkLocaleStatsDO> listLocaleByGroup = statsLocaleMapper.listLocaleByGroup(requestParam);
        int localeCNSum = listLocaleByGroup.stream()
                .mapToInt(ShortLinkLocaleStatsDO::getCnt)
                .sum();
        listLocaleByGroup.forEach(each -> {
            double ratio = (double) each.getCnt() / localeCNSum;
            double actualRatio = Math.round(ratio * 100.0) / 100.0;
            ShortLinkStatsLocaleCNRespDTO localeCNRespDTO = ShortLinkStatsLocaleCNRespDTO.builder()
                    .cnt(each.getCnt())
                    .locale(each.getProvince())
                    .ratio(actualRatio)
                    .build();
            localeCNStats.add(localeCNRespDTO);
        });
        /*
         * 短链接分组监控之 24 小时访问分布情况
         */
        List<Integer> hoursStats = new ArrayList<>(24);
        List<ShortLinkStatsDO> listHoursByGroup = baseMapper.listHoursStatisticsByGroup(requestParam);
        for (int i = 0; i < 24; ++i) {
            AtomicInteger hour = new AtomicInteger(i);
            int hourCnt = listHoursByGroup.stream()
                    .filter(each -> Objects.equals(each.getHour(), hour.get()))
                    .findFirst()
                    .map(ShortLinkStatsDO::getPv)
                    .orElse(0);
            hoursStats.add(hourCnt);
        }
        /*
         * 短链接分组监控之高频访问 IP
         */
        List<ShortLinkStatsTopIpRespDTO> topIpStats = new ArrayList<>();
        List<HashMap<String, Object>> listTopIpByGroup = accessLogsMapper.listTopIpByGroup(requestParam);
        listTopIpByGroup.forEach(each -> {
            ShortLinkStatsTopIpRespDTO statsTopIpRespDTO = ShortLinkStatsTopIpRespDTO.builder()
                    .ip(each.get("ip").toString())
                    .cnt(Integer.parseInt(each.get("count").toString()))
                    .build();
            topIpStats.add(statsTopIpRespDTO);
        });
        /*
         * 短链接分组监控之一周内的访问情况
         */
        List<Integer> weekdayStats = new ArrayList<>();
        List<ShortLinkStatsDO> listWeekStatsByGroup = baseMapper.listWeekStatsByGroup(requestParam);
        for (int i = 1; i < 8; ++i) {
            AtomicInteger weekday = new AtomicInteger(i);
            int weekdayCnt = listWeekStatsByGroup.stream()
                    .filter(each -> Objects.equals(each.getWeekday(), weekday.get()))
                    .findFirst()
                    .map(ShortLinkStatsDO::getPv)
                    .orElse(0);
            weekdayStats.add(weekdayCnt);
        }
        /*
         * 短链接监控之浏览器
         */
        List<ShortLinkStatsBrowserRespDTO> browserStats = new ArrayList<>();
        List<HashMap<String, Object>> listBrowserStatsByGroup = statsBrowserMapper.listBrowserStatsByGroup(requestParam);
        int browserSum = listBrowserStatsByGroup.stream()
                .mapToInt(each -> Integer.parseInt(each.get("count").toString()))
                .sum();
        listBrowserStatsByGroup.forEach(each -> {
            double ratio = (double) Integer.parseInt(each.get("count").toString()) / browserSum;
            double actualRatio = Math.round(ratio * 100.0) / 100.0;
            ShortLinkStatsBrowserRespDTO shortLinkStatsBrowserRespDTO = ShortLinkStatsBrowserRespDTO.builder()
                    .cnt(Integer.parseInt(each.get("count").toString()))
                    .browser(each.get("browser").toString())
                    .ratio(actualRatio)
                    .build();
            browserStats.add(shortLinkStatsBrowserRespDTO);
        });
        /*
         * 短链接监控之操作系统
         */
        List<ShortLinkStatsOsRespDTO> osStats = new ArrayList<>();
        List<HashMap<String, Object>> listOsStatsByGroup = statsOsMapper.listOsStatsByGroup(requestParam);
        int osSum = listOsStatsByGroup.stream()
                .mapToInt(each -> Integer.parseInt(each.get("count").toString()))
                .sum();
        listOsStatsByGroup.forEach(each -> {
            double ratio = (double) Integer.parseInt(each.get("count").toString()) / osSum;
            double actualRatio = Math.round(ratio * 100.0) / 100.0;
            ShortLinkStatsOsRespDTO osRespDTO = ShortLinkStatsOsRespDTO.builder()
                    .cnt(Integer.parseInt(each.get("count").toString()))
                    .os(each.get("os").toString())
                    .ratio(actualRatio)
                    .build();
            osStats.add(osRespDTO);
        });
        /*
         * 短链接分组监控之访客类型（指定时间内的访客：新访客，老访客）
         */
        List<ShortLinkStatsUvRespDTO> uvTypeStats = new ArrayList<>();
        HashMap<String, Object> findUvTypeByGroup = accessLogsMapper.findUvTypeCntByGroup(requestParam);
        int oldUserCnt = Integer.parseInt(
                Optional.ofNullable(findUvTypeByGroup)
                        .map(each -> each.get("oldUserCnt"))
                        .map(Object::toString)
                        .orElse("0")
        );
        int newUserCnt = Integer.parseInt(
                Optional.ofNullable(findUvTypeByGroup)
                        .map(each -> each.get("newUserCnt"))
                        .map(Object::toString)
                        .orElse("0")
        );
        int uvSum = oldUserCnt + newUserCnt;
        double oldRatio = (double) oldUserCnt / uvSum;
        double actualOldRatio = Math.round(oldRatio * 100.0) / 100.0;
        double newRatio = (double) newUserCnt / uvSum;
        double actualNewRatio = Math.round(newRatio * 100.0) / 100.0;
        ShortLinkStatsUvRespDTO newUvRespDTO = ShortLinkStatsUvRespDTO.builder()
                .uvType("newUser")
                .cnt(newUserCnt)
                .ratio(actualNewRatio)
                .build();
        uvTypeStats.add(newUvRespDTO);
        ShortLinkStatsUvRespDTO oldUvRespDTO = ShortLinkStatsUvRespDTO.builder()
                .uvType("oldUser")
                .cnt(oldUserCnt)
                .ratio(actualOldRatio)
                .build();
        uvTypeStats.add(oldUvRespDTO);
        /*
         * 短链接分组监控之访问设备
         */
        List<ShortLinkStatsDeviceRespDTO> deviceStats = new ArrayList<>();
        List<ShortLinkDeviceStatsDO> listDeviceStatsByGroup = statsDeviceMapper.listDeviceStatsByGroup(requestParam);
        int deviceSum = listDeviceStatsByGroup.stream()
                .mapToInt(ShortLinkDeviceStatsDO::getCnt)
                .sum();
        listDeviceStatsByGroup.forEach(each -> {
            double ratio = (double) each.getCnt() / deviceSum;
            double actualRatio = Math.round(ratio * 100.0) / 100.0;
            ShortLinkStatsDeviceRespDTO deviceRespDTO = ShortLinkStatsDeviceRespDTO.builder()
                    .cnt(each.getCnt())
                    .device(each.getDevice())
                    .ratio(actualRatio)
                    .build();
            deviceStats.add(deviceRespDTO);
        });
        /*
         * 短链接分组监控之网络
         */
        List<ShortLinkStatsNetworkRespDTO> networkStats = new ArrayList<>();
        List<ShortLinkNetworkStatsDO> listNetworkStatsByGroup = statsNetworkMapper.listNetworkStatsByGroup(requestParam);
        int networkSum = listNetworkStatsByGroup.stream()
                .mapToInt(ShortLinkNetworkStatsDO::getCnt)
                .sum();
        listNetworkStatsByGroup.forEach(each -> {
            double ratio = (double) each.getCnt() / networkSum;
            double actualRatio = Math.round(ratio * 100.0) / 100.0;
            ShortLinkStatsNetworkRespDTO networkRespDTO = ShortLinkStatsNetworkRespDTO.builder()
                    .cnt(each.getCnt())
                    .network(each.getNetwork())
                    .ratio(actualRatio)
                    .build();
            networkStats.add(networkRespDTO);
        });
        return ShortLinkGroupStatsRespDTO.builder()
                .pv(pvUvUipByGroup.getPv())
                .uv(pvUvUipByGroup.getUv())
                .uip(pvUvUipByGroup.getUip())
                .daily(dailyStats)
                .localeCnStats(localeCNStats)
                .hourStats(hoursStats)
                .topIpStats(topIpStats)
                .weekdayStats(weekdayStats)
                .browserStats(browserStats)
                .osStats(osStats)
                .uvTypeStats(uvTypeStats)
                .deviceStats(deviceStats)
                .networkStats(networkStats)
                .build();
    }

    @Override
    public IPage<ShortLinkStatsAccessRecordRespDTO> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        LambdaQueryWrapper<ShortLinkAccessLogsDO> lambdaQueryWrapper = Wrappers.lambdaQuery(ShortLinkAccessLogsDO.class)
                .eq(ShortLinkAccessLogsDO::getGid, requestParam.getGid())
                .eq(ShortLinkAccessLogsDO::getFullShortUrl, requestParam.getFullShortUrl())
                .between(ShortLinkAccessLogsDO::getCreateTime, requestParam.getStartDate(), requestParam.getEndDate())
                .orderByDesc(ShortLinkAccessLogsDO::getCreateTime);
        IPage<ShortLinkAccessLogsDO> accessDoPages = accessLogsMapper.selectPage(requestParam, lambdaQueryWrapper);
        List<String> userAccessLogsList = accessDoPages.getRecords()
                .stream()
                .map(ShortLinkAccessLogsDO::getUser)
                .toList();
        IPage<ShortLinkStatsAccessRecordRespDTO> actualResult = accessDoPages.convert(each -> BeanUtil.toBean(each, ShortLinkStatsAccessRecordRespDTO.class));
        List<Map<String, Object>> uvTypeList = accessLogsMapper.selectUvTypeByUsers(BeanUtil.toBean(requestParam, UvTypeMapperDTO.class), userAccessLogsList);
        actualResult.getRecords().forEach(each -> {
            String uvType = uvTypeList.stream()
                    // 用户在列表中，根据列表中的 uvType 指定用户类型，如果不在，就是旧访客
                    .filter(item -> Objects.equals(item.get("user"), each.getUser()))
                    .findFirst()
                    .map(item -> item.get("uvType").toString())
                    .orElse("旧访客");
            each.setUvType(uvType);
        });
        return actualResult;
    }

    @Override
    public IPage<ShortLinkGroupStatsAccessRecordRespDTO> shortLinkGroupStatsAccessRecord(ShortLinkGroupStatsAccessRecordReqDTO requestParam) {
        LambdaQueryWrapper<ShortLinkAccessLogsDO> lambdaQueryWrapper = Wrappers.lambdaQuery(ShortLinkAccessLogsDO.class)
                .eq(ShortLinkAccessLogsDO::getGid, requestParam.getGid())
                .between(ShortLinkAccessLogsDO::getCreateTime, requestParam.getStartDate(), requestParam.getEndDate())
                .orderByDesc(ShortLinkAccessLogsDO::getCreateTime);
        IPage<ShortLinkAccessLogsDO> accessDoPages = accessLogsMapper.selectPage(requestParam, lambdaQueryWrapper);
        List<String> userAccessLogsList = accessDoPages.getRecords()
                .stream()
                .map(ShortLinkAccessLogsDO::getUser)
                .toList();
        IPage<ShortLinkGroupStatsAccessRecordRespDTO> actualResult = accessDoPages.convert(each -> BeanUtil.toBean(each, ShortLinkGroupStatsAccessRecordRespDTO.class));
        List<Map<String, Object>> uvTypeGroupList = accessLogsMapper.selectGroupUvTypeByUsers(BeanUtil.toBean(requestParam, UvTypeGroupMapperDTO.class), userAccessLogsList);
        actualResult.getRecords().forEach(each -> {
            String uvType = uvTypeGroupList.stream()
                    // 用户在列表中，根据列表中的 uvType 指定用户类型，如果不在，就是旧访客
                    .filter(item -> Objects.equals(item.get("user"), each.getUser()))
                    .findFirst()
                    .map(item -> item.get("uvType").toString())
                    .orElse("旧访客");
            each.setUvType(uvType);
        });
        return actualResult;
    }
}
