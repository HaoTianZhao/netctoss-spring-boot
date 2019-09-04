package com.barista.controller;

import com.barista.VO.CostManageVO;
import com.barista.entity.Cost;
import com.barista.result.Result;
import com.barista.result.ResultCode;
import com.barista.service.CostService;
import com.barista.util.ValueUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 资费管理
 * 资费状态为1(暂停)时，才可以修改和删除
 * 新增的资费为暂停状态，需手动开启
 *
 * @ClassName CostController
 * @Author zhaoth
 * @Date 2019/8/28 15:12
 * @Version 1.0
 */
@RestController
@RequestMapping("/cost")
public class CostController {

    @Autowired
    private CostService costService;

    @RequestMapping("/getAllCost")
    public Result<List<Cost>> getAllCost() {
        return Result.success(costService.selectByExistField(null));
    }

    @RequestMapping("/getPageInfo")
    public Result getPageInfo(Integer currentPage, Integer pageSize) {
        if (ValueUtil.haveNullOrBlack(currentPage, pageSize)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        int count = costService.selectCount();
        List<Cost> costs = costService.selectPaging(currentPage, pageSize);
        CostManageVO costManageVO = new CostManageVO(count, costs);

        return Result.success(costManageVO);
    }

    @RequestMapping("getPageInfoSelected")
    public Result getPageInfoSelected(Integer currentPage, Integer pageSize, String orderBy) {
        if (ValueUtil.haveNullOrBlack(currentPage, pageSize)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        if (ValueUtil.haveNullOrBlack(orderBy)) {
            getPageInfo(currentPage, pageSize);
        }
        int count = costService.selectCount();
        List<Cost> costs = costService.selectPagingOrder(currentPage, pageSize, orderBy);
        CostManageVO costManageVO = new CostManageVO(count, costs);

        return Result.success(costManageVO);
    }

    @RequestMapping("/add")
    public Result addCost(Cost cost) {
        if (ValueUtil.haveNullOrBlack(cost)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }

        int result = costService.insertCost(cost);
        if (result > 0) {
            return Result.success("增加资费成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }

    @RequestMapping("/startUsing")
    public Result startUsing(Integer costId) {
        if (ValueUtil.haveNullOrBlack(costId)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }

        int result = costService.startUsing(costId);
        if (result > 0) {
            return Result.success("启用资费成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }

    @RequestMapping("/delete")
    public Result deleteCost(Integer costId) {
        if (ValueUtil.haveNullOrBlack(costId)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }

        int result = costService.deleteCost(costId);
        if (result > 0) {
            return Result.success("删除资费成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }

    @RequestMapping("/update")
    public Result updateCost(Cost cost) {
        if (ValueUtil.haveNullOrBlack(cost)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }

        int result = costService.updateCost(cost);
        if (result > 0) {
            return Result.success("更改资费成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }

}
