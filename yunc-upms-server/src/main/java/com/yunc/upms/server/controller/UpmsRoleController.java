package com.yunc.upms.server.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.liebao.lb7881.common.generic.GenericPageResponse;
import com.liebao.lb7881.common.utils.AssertUtils;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.yunc.upms.common.enums.ErrorCodeEnum;
import com.yunc.upms.common.utils.ResultHandle;
import com.yunc.upms.dao.entity.UpmsRole;
import com.yunc.upms.rpc.service.IUpmsRoleService;
import com.yunc.upms.server.base.BaseController;
import com.yunc.upms.server.request.UpmsRolePageRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
@Controller
@RequestMapping("/upmsRole")
public class UpmsRoleController extends BaseController{
	@Autowired
	private IUpmsRoleService upmsRoleService;

	/**
	 * 系统管理首页
	 * 
	 * @param request
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		return "/role/index";
	}

	/**
	 * 新增用户
	 * 
	 * @param request
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/json/create", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> create(UpmsRole upmsRole, Map<String, Object> resMap) {
		LOGGER.info("enter create upmsRole:{}", upmsRole);
		resMap = ResultHandle.success();
		long time = System.currentTimeMillis();
		upmsRole.setCtime(time);
		upmsRole.setOrders(time);
		upmsRoleService.insert(upmsRole);
		LOGGER.info("exsit create resMap:{}", JSONUtil.toJsonStr(resMap));
		return resMap;
	}

	/**
	 * 更新获取数据
	 * 
	 * @param request
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/json/update/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> update(@PathVariable("id") int id, Map<String, Object> resMap) {
		LOGGER.info("enter create id:{}", id);
		resMap = ResultHandle.success();
		resMap.put("info", upmsRoleService.selectById(id));
		LOGGER.info("exsit update resMap:{}", JSONUtil.toJsonStr(resMap));
		return resMap;
	}

	/**
	 * 更新数据
	 * 
	 * @param request
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/json/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(UpmsRole upmsRole, Map<String, Object> resMap) {
		LOGGER.info("enter update upmsRole:{}", upmsRole);
		resMap = ResultHandle.success();
		AssertUtils.isTrue(upmsRoleService.updateById(upmsRole), ErrorCodeEnum.SYSTEM_ERROR);
		LOGGER.info("exsit update resMap:{}", JSONUtil.toJsonStr(resMap));
		return resMap;
	}
	/**
	 * 删除数据
	 * 
	 * @param request
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/json/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(Integer id, Map<String, Object> resMap) {
		LOGGER.info("enter delete id:{}", id);
		resMap = ResultHandle.success();
		AssertUtils.isTrue(upmsRoleService.deleteById(id), ErrorCodeEnum.SYSTEM_ERROR);
		LOGGER.info("exsit delete resMap:{}", JSONUtil.toJsonStr(resMap));
		return resMap;
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param id
	 * @return [参数说明]
	 * 
	 * @return Map<String,Object> [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/json/list")
	@ResponseBody
	public GenericPageResponse<List<UpmsRole>> list(UpmsRolePageRequest upmsRolePageRequest,
			Map<String, Object> resMap) {
		LOGGER.info("enter list upmsRolePageRequest:{}", upmsRolePageRequest);

		resMap = ResultHandle.success();
		EntityWrapper<UpmsRole> ew = new EntityWrapper<UpmsRole>();
		ew.orderBy("ctime", false);
		UpmsRole uper = new UpmsRole();
		BeanUtils.copyProperties(upmsRolePageRequest, uper);
		ew.setEntity(uper);
		Page<UpmsRole> page = upmsRoleService.selectPage(
				new Page<UpmsRole>(upmsRolePageRequest.getPageNum(), upmsRolePageRequest.getPageSize()), ew);

		GenericPageResponse<List<UpmsRole>> n = new GenericPageResponse<List<UpmsRole>>();
		n.setTotalCount((long) page.getTotal());
		n.setObject(page.getRecords());
		LOGGER.info("exsit list n:{}", n);

		return n;
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param id
	 * @return [参数说明]
	 * 
	 * @return Map<String,Object> [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/json/selectList")
	@ResponseBody
	public Map<String, Object> ztreeList(UpmsRolePageRequest upmsRolePageRequest, Map<String, Object> resMap) {
		LOGGER.info("enter list upmsRolePageRequest:{}", upmsRolePageRequest);

		resMap = ResultHandle.success();
		EntityWrapper<UpmsRole> ew = new EntityWrapper<UpmsRole>();

		resMap.put("info", upmsRoleService.selectList(ew));

		return resMap;
	}
}
