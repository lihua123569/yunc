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
import com.yunc.upms.dao.entity.UpmsOrganization;
import com.yunc.upms.rpc.service.IUpmsOrganizationService;
import com.yunc.upms.server.base.BaseController;
import com.yunc.upms.server.request.UpmsOrganizationPageRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lijianhua
 * @since 2017-11-15
 */
@Controller
@RequestMapping("/upmsOrganization")
public class UpmsOrganizationController extends BaseController{
	@Autowired
	private IUpmsOrganizationService upmsOrganizationService;

	/**
	 * 系统管理首页
	 * 
	 * @param request
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
		return "/upmsOrganization/index";
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
	public Map<String, Object> create(UpmsOrganization upmsSystem, Map<String, Object> resMap) {
		LOGGER.info("enter create upmsSystem:{}", upmsSystem);
		resMap = ResultHandle.success();
		long time = System.currentTimeMillis();
		upmsSystem.setCtime(time);
		upmsOrganizationService.insert(upmsSystem);
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
		resMap.put("info", upmsOrganizationService.selectById(id));
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
	public Map<String, Object> update(UpmsOrganization upmsSystem, Map<String, Object> resMap) {
		LOGGER.info("enter update upmsSystem:{}", upmsSystem);
		resMap = ResultHandle.success();
		AssertUtils.isTrue(upmsOrganizationService.updateById(upmsSystem), ErrorCodeEnum.SYSTEM_ERROR);
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
		AssertUtils.isTrue(upmsOrganizationService.deleteById(id), ErrorCodeEnum.SYSTEM_ERROR);
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
	public GenericPageResponse<List<UpmsOrganization>> list(UpmsOrganizationPageRequest upmsSystemPageRequest,
			Map<String, Object> resMap) {
		LOGGER.info("enter list upmsSystemPageRequest:{}", upmsSystemPageRequest);

		resMap = ResultHandle.success();
		EntityWrapper<UpmsOrganization> ew = new EntityWrapper<UpmsOrganization>();
		ew.orderBy("ctime", false);
		UpmsOrganization uper = new UpmsOrganization();
		BeanUtils.copyProperties(upmsSystemPageRequest, uper);
		ew.setEntity(uper);
		Page<UpmsOrganization> page = upmsOrganizationService.selectPage(
				new Page<UpmsOrganization>(upmsSystemPageRequest.getPageNum(), upmsSystemPageRequest.getPageSize()), ew);

		GenericPageResponse<List<UpmsOrganization>> n = new GenericPageResponse<List<UpmsOrganization>>();
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
	@RequestMapping(value = "/json/ztreeList")
	@ResponseBody
	public Map<String, Object> ztreeList(UpmsOrganizationPageRequest upmsSystemPageRequest, Map<String, Object> resMap) {
		LOGGER.info("enter list upmsSystemPageRequest:{}", upmsSystemPageRequest);

		resMap = ResultHandle.success();
		EntityWrapper<UpmsOrganization> ew = new EntityWrapper<UpmsOrganization>();

		resMap.put("info", upmsOrganizationService.selectList(ew));

		return resMap;
	}
}
