package com.barista.controller;

import com.barista.VO.AccountManageVO;
import com.barista.entity.Account;
import com.barista.result.Result;
import com.barista.result.ResultCode;
import com.barista.service.AccountService;
import com.barista.util.MD5Util;
import com.barista.util.ValueUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * 账务账号管理
 * 暂停或删除账务账号，会暂停或删除其所有业务账号
 * 新增的账务账号是开通状态，删除后只能进行查看详情操作
 * 开通或暂停时要记录对应的操作时间，同时删除暂停时间
 * 删除时记录删除时间
 *
 * @ClassName AccountController
 * @Author zhaoth
 * @Date 2019/8/29 14:37
 * @Version 1.0
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/selectById")
    public Result getAccountInfo(Integer accountId) {
//        if (ValueUtil.haveNullOrBlack(accountId)) {
//            return Result.fail(ResultCode.ILLEGAL_PARAM);
//        }
        Account account = accountService.selectById(accountId);
        if (account != null) {
            account.setAccountLoginPasswd(null);
        }
        return Result.success(account);
    }

    @RequestMapping("/selectByIdcardNo")
    public Result<Account> selectByIdcardNo(String accountIdcardNo) {
        return Result.success(accountService.selectByIdcardNo(accountIdcardNo));
    }

    @RequestMapping("/getPageInfo")
    public Result getPageInfo(Integer currentPage, Integer pageSize) {
        if (ValueUtil.haveNullOrBlack(currentPage, pageSize)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }

        List<Account> accounts = accountService.selectPaging(currentPage, pageSize);
        int count = accountService.selectCount();
        AccountManageVO vo = new AccountManageVO(count, accounts);
        return Result.success(vo);
    }

    /**
     * 通过身份证号，真实姓名，登录，账号状态做筛选
     */
    @RequestMapping("/getPageInfoSelected")
    public Result getPageInfoSelected(Integer currentPage, Integer pageSize, String accountIdcardNo
            , String accountRealName, String accountLoginName, String accountStatus) {
        if (ValueUtil.haveNullOrBlack(currentPage, pageSize)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }
        if (ValueUtil.haveNullOrBlack(accountIdcardNo)) {
            accountIdcardNo = null;
        }
        if (ValueUtil.haveNullOrBlack(accountRealName)) {
            accountRealName = null;
        }
        if (ValueUtil.haveNullOrBlack(accountLoginName)) {
            accountLoginName = null;
        }
        if (Objects.equals(accountStatus, "全部")) {
            accountStatus = null;
        }

        int count = accountService.selectCountFilter(accountIdcardNo, accountRealName, accountLoginName, accountStatus);
        List<Account> list = accountService.selectPagingFilter(currentPage, pageSize
                , accountIdcardNo, accountRealName, accountLoginName, accountStatus);
        AccountManageVO vo = new AccountManageVO(count, list);
        return Result.success(vo);
    }

    @RequestMapping("/add")
    public Result addAccount(Account account, String accountRecommenderIdcardNo, String password) {
        if (ValueUtil.haveNullOrBlack(account)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }

        if (!ValueUtil.haveNullOrBlack(password)) {
            account.setAccountLoginPasswd(MD5Util.encode(password));
        }

        if (!ValueUtil.haveNullOrBlack(accountRecommenderIdcardNo)) {
            Account recommendAccount = accountService.selectByIdcardNo(accountRecommenderIdcardNo);
            account.setAccountRecommenderId(recommendAccount.getAccountId());
        }

        int result = accountService.insertAccount(account);
        if (result > 0) {
            return Result.success("增加账务账号成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }

    @RequestMapping("/startUsing")
    public Result startUsing(Integer accountId) {
        if (ValueUtil.haveNullOrBlack(accountId)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }

        int result = accountService.startUsing(accountId);
        if (result > 0) {
            return Result.success("启用账务账号成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }

    @RequestMapping("/pauseUsing")
    public Result pauseUsing(Integer accountId) {
        if (ValueUtil.haveNullOrBlack(accountId)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }

        int result = accountService.pauseUsing(accountId);
        if (result > 0) {
            return Result.success("暂停账务账号成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }

    @RequestMapping("/delete")
    public Result deleteAccount(Integer accountId) {
        if (ValueUtil.haveNullOrBlack(accountId)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }

        int result = accountService.deleteAccount(accountId);
        if (result > 0) {
            return Result.success("删除账务账号成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }

    @RequestMapping("/update")
    public Result updateAccount(Account account, String accountRecommenderIdcardNo, String oldPassword, String newPassword) {
        if (ValueUtil.haveNullOrBlack(account)) {
            return Result.fail(ResultCode.ILLEGAL_PARAM);
        }

        if (!ValueUtil.haveNullOrBlack(accountRecommenderIdcardNo)) {
            Account recommendAccount = accountService.selectByIdcardNo(accountRecommenderIdcardNo);
            account.setAccountRecommenderId(recommendAccount.getAccountId());
        }

        if (!ValueUtil.haveNullOrBlack(oldPassword, newPassword)) {
            Account dbAccount = accountService.selectById(account.getAccountId());
            if (accountService.checkPassword(oldPassword, dbAccount.getAccountLoginPasswd())) {
                ;
            }
            account.setAccountLoginPasswd(MD5Util.encode(newPassword));
        }

        int result = accountService.updateAccount(account);
        if (result > 0) {
            return Result.success("更改账务账号成功");
        } else {
            return Result.fail(ResultCode.SERVER_ERROR);
        }
    }
}
