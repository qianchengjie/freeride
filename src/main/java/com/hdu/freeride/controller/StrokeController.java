package com.hdu.freeride.controller;

import com.hdu.freeride.service.StrokeService;
import com.hdu.freeride.util.ResultUtil;
import com.hdu.freeride.views.StrokeView;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Author: XiaoXiaoQian
 * Description:
 * Date: 2017/12/21 9:53
 */
@RestController()
@RequestMapping("stroke")
public class StrokeController {

    @Autowired
    private StrokeService strokeService;

    /**
     * 普通用户发布行程
     * @param strokeView
     * @param bindingResult
     * @return
     */
    @PostMapping("add")
    public Object add(@Valid StrokeView strokeView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult);
        }
        return ResultUtil.success(strokeService.add(strokeView));
    }

    /**
     * 用户取消行程
     * @param userId
     * @param roleId
     * @param strokeId
     * @return
     */
    @PostMapping("cancel")
    public Object cancel(Integer userId, Integer roleId, Integer strokeId) {
        if (userId == null || roleId == null || strokeId == null) {
            return ResultUtil.error("缺少参数");
        }
        strokeService.cancel(userId, roleId, strokeId);
        return ResultUtil.success();
    }

    /**
     * 用户删除行程
     * @param userId
     * @param roleId
     * @param strokeId
     * @return
     */
    @DeleteMapping("delete")
    public Object delete(Integer userId, Integer roleId, Integer strokeId) {
        if (userId == null || roleId == null || strokeId == null) {
            return ResultUtil.error("缺少参数");
        }
        strokeService.delete(userId, roleId, strokeId);
        return ResultUtil.success();
    }

    /**
     * 分页获取用户的某个角色的行程表
     * @param userId
     * @param roleId
     * @param pageNum
     * @return
     */
    @GetMapping("findAll/{pageNum}")
    public Object findAll(Integer userId, Integer roleId, @PathVariable int pageNum) {
        if (userId == null || roleId == null) {
            return ResultUtil.error("缺少参数");
        }
        return ResultUtil.success(strokeService.findAllByUserIdAndRoleId(userId, roleId, pageNum));
    }

    /**
     * 通过行程id查找行程信息
     * @param strokeId
     * @return
     */
    @GetMapping("find")
    public Object find(Integer strokeId) {
        if (strokeId == null) {
            return ResultUtil.error("缺少参数");
        }
        return ResultUtil.success(strokeService.findOne(strokeId));
    }

    /**
     * 司机接受订单
     * @param strokeId
     * @param userId
     * @return
     */
    @PostMapping("strokeTaking")
    public Object find(Integer strokeId, Integer userId) {
        if (userId == null || strokeId == null) {
            return ResultUtil.error("缺少参数");
        }
        return ResultUtil.success(strokeService.strokeTaking(strokeId, userId));
    }


}
